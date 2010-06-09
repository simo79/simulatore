package control.engine;

import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;

import view.LogForm;

import model.DistributionType;
import model.PriorityEventNotice;
import model.PriorityQueueElement;
import model.RandomGenerator;
import model.enumerators.EventType;

public class MG1PRIOsimulator {
	
	private ConcurrentSkipListSet<PriorityEventNotice> futureEventList;
	
	private Vector<ConcurrentSkipListSet<PriorityQueueElement>> priorityQueues;
	
	private int nClasses;

	private int N;
	
	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	private int[] classArrival;
	private int[] classDeparture;
	
	private int nSim;
	//private int max_users;
	
	private double free;
	private double now;
	private double[] wait;
	
	private RandomGenerator rndServizio;
	private RandomGenerator[] rndArrivi;
	
	private LogForm logFrm;
	private double[] rhos;
	/**
	 * 
	 * @param N
	 * @param nClasses
	 * @param rho
	 * @param mu
	 * @param nSim
	 */
	public MG1PRIOsimulator(int N, int nClasses, double[] rho, double mu, int nSim){
		this.rhos=rho;
		this.nSim = nSim;
		this.nClasses=nClasses;
		this.N=N;
		logFrm=null;
		
		priorityQueues = new Vector<ConcurrentSkipListSet<PriorityQueueElement>>();
		futureEventList = new ConcurrentSkipListSet<PriorityEventNotice>();
		
		classArrival = new int[nClasses];
		classDeparture = new int[nClasses];
		wait = new double[nClasses];
		
		rndServizio = new RandomGenerator(DistributionType.Exponential, new double[]{mu});
		rndArrivi = new RandomGenerator[nClasses];
		
		for(int i=0; i<nClasses; i++){
			rndArrivi[i]=new RandomGenerator(DistributionType.Exponential, new double[]{rho[i]/mu});
			priorityQueues.add(i, new ConcurrentSkipListSet<PriorityQueueElement>());
		}
	}
	/**
	 * 
	 * @param N
	 * @param nClasses
	 * @param rho
	 * @param mu
	 * @param tries
	 * @param logFrm
	 */
	public MG1PRIOsimulator(int N, int nClasses, double[] rho, double mu, int tries, LogForm logFrm){
		this(N,nClasses,rho,mu,tries);
		this.logFrm=logFrm;
	}
	/**
	 * 
	 */
	private void inizializza() {
		totArrival=0;
		totDeparture=0;
		totUser=0;
		Id=0;
		futureEventList = new ConcurrentSkipListSet<PriorityEventNotice>();
		priorityQueues = new Vector<ConcurrentSkipListSet<PriorityQueueElement>>();
		for(int i=0;i<nClasses;i++){
			classArrival[i]=0;
			classDeparture[i]=0;
			wait[i]=0.0;
			double rndArr= rndArrivi[i].nextRandom();
			double rndSer= rndServizio.nextRandom();
			futureEventList.add(new PriorityEventNotice(Id,rndArr, EventType.Arrival,rndSer , i));
			priorityQueues.add(i, new ConcurrentSkipListSet<PriorityQueueElement>());
			Id++;
		}
		Object[] tmp = futureEventList.toArray();
		for(int j=0; j<futureEventList.size();j++)
			System.out.println((PriorityEventNotice)tmp[j]);
		free=0.0;
		now=0.0;
		
	}
	/**
	 * 
	 * @return
	 */
	public double[][] run(){
		int step=0;
		double [][] results = new double[nSim][nClasses];
		while (step<nSim){
			System.out.println("simulazione: "+step);
			inizializza();
			while (totDeparture < N) {
				PriorityEventNotice e = futureEventList.pollFirst();
				now = e.getOccurrenceTime();
				if (e.getEventType() == EventType.Arrival) {
					totUser++;
					totArrival++;
					classArrival[e.getPriority()]++;
					
					if (totUser == 1) {
						free = now + e.getServiceTime();
						futureEventList.add(new PriorityEventNotice(e.Id, free, EventType.Departure, 0, e.getPriority()));
						
					}else{
						free = free + e.getServiceTime();
						PriorityQueueElement elm = new PriorityQueueElement(e.Id, now, e.getServiceTime());
						priorityQueues.get(e.getPriority()).add(elm);
					}
					
					if (totArrival < N) {
						Id++;
						futureEventList.add(new PriorityEventNotice(Id, now+rndArrivi[e.getPriority()].nextRandom(), EventType.Arrival, rndServizio.nextRandom(), e.getPriority()));
						
						
					}
				}else if (e.getEventType() == EventType.Departure) {
					totUser--;
					totDeparture++;
					classDeparture[e.getPriority()]++;
					if (totUser > 0) {
						int j = 0;
						while (priorityQueues.get(j).isEmpty()) 
							j++;
						PriorityQueueElement elem = priorityQueues.get(j).pollFirst();
						wait[j] = wait[j] + (now -elem.getOccurrenceTime());
						futureEventList.add(new PriorityEventNotice(elem.getId(), now+elem.getServiceTime(), EventType.Departure, 0, j));
					}
				}
				
			}
			for (int j = 0; j < nClasses; j++){
				
					System.out.print(""+wait[j]);
					System.out.print(" "+classDeparture[j]);
					System.out.println(" ");
				
				results[step][j] = wait[j]/classDeparture[j];
			}
			step++;
		}
		return results;
	}
}