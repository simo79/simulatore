package control.engine;

import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;

import view.LogForm;

import model.DistributionType;
import model.EventNotice;
import model.SJNPriorityQueueElement;
import model.RandomGenerator;
import model.enumerators.EventType;

public class MM1SJNsimulator {
	
	private ConcurrentSkipListSet<EventNotice> futureEventList;
	
	private ConcurrentSkipListSet<SJNPriorityQueueElement> priorityQueue;
	
	//private int nClasses;

	private int N;
	
	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	//private int[] classArrival;
	//private int[] classDeparture;
	
	private int nSim;
	//private int max_users;
	
	private double free;
	private double now;
	//private double[] wait;
	private double totWait;
	
	private RandomGenerator rndServizio;
	private RandomGenerator rndArrivi;
	
	private LogForm logFrm;
	private double rho;
	private double mu;
	/**
	 * 
	 * @param N
	 * @param nClasses
	 * @param rho
	 * @param mu
	 * @param nSim
	 */
	public MM1SJNsimulator(int N, double rho, double mu, int nSim){
		this.rho=rho;
		this.mu=mu;
		this.nSim = nSim;
		//this.nClasses=nClasses;
		this.N=N;
		logFrm=null;
		
		//priorityQueue = new ConcurrentSkipListSet<SJNPriorityQueueElement>();
		//futureEventList = new ConcurrentSkipListSet<EventNotice>();
		
		//classArrival = new int[nClasses];
		//classDeparture = new int[nClasses];
		//wait = new double[nClasses];
		
		/*
		for(int i=0; i<nClasses; i++){
			rndArrivi[i]=new RandomGenerator(DistributionType.Exponential, new double[]{rho[i]/mu});
			priorityQueues.add(i, new ConcurrentSkipListSet<PriorityQueueElement>());
		}*/
	}
	/**
	 * 
	 * @param N
	 * @param nClasses
	 * @param rho
	 * @param mu
	 * @param nSim
	 * @param logFrm
	 */
	public MM1SJNsimulator(int N, double rho, double mu, int nSim, LogForm logFrm){
		this(N,rho,mu,nSim);
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
		rndServizio = new RandomGenerator(DistributionType.Exponential, new double[]{this.mu});
		rndArrivi = new RandomGenerator(DistributionType.Exponential, new double[]{this.rho/this.mu});
		priorityQueue = new ConcurrentSkipListSet<SJNPriorityQueueElement>();
		futureEventList = new ConcurrentSkipListSet<EventNotice>();
		
		futureEventList.add(new EventNotice(Id,rndArrivi.nextRandom(), EventType.Arrival,rndServizio.nextRandom()));
		/*
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
			System.out.println((PriorityEventNotice)tmp[j]);*/
		free=0.0;
		now=0.0;
		
	}
	/**
	 * 
	 * @return
	 */
	public double[] run(){
		int step=0;
		double [] results = new double[nSim];
		//double [][] results = new double[nSim][nClasses];
		while (step<nSim){
			//System.out.println("simulazione: "+step);
			inizializza();
			while (totDeparture < N) {
				EventNotice e = futureEventList.pollFirst();
				now = e.getOccurrenceTime();
				if (e.getEventType() == EventType.Arrival) {
					totUser++;
					totArrival++;
					//classArrival[e.getPriority()]++;
					
					if (totUser == 1) {
						free = now + e.getServiceTime();
						futureEventList.add(new EventNotice(e.Id, free, EventType.Departure, 0));
						
					}else{
						free = free + e.getServiceTime();
						SJNPriorityQueueElement elm = new SJNPriorityQueueElement(e.Id, now, e.getServiceTime());
						priorityQueue.add(elm);
					}
					
					if (totArrival < N) {
						Id++;
						futureEventList.add(new EventNotice(Id, now+rndArrivi.nextRandom(), EventType.Arrival, rndServizio.nextRandom()));
						
						
					}
				}else if (e.getEventType() == EventType.Departure) {
					totUser--;
					totDeparture++;
					//classDeparture[e.getPriority()]++;
					if (totUser > 0) {
						int j = 0;
						SJNPriorityQueueElement elem = priorityQueue.pollFirst();
						totWait = totWait + (now - elem.getOccurrenceTime());
						//wait[j] = wait[j] + (now -elem.getOccurrenceTime());
						futureEventList.add(new EventNotice(elem.getId(), now+elem.getServiceTime(), EventType.Departure, 0));
					}
				}
				
			}
			/*
			for (int j = 0; j < nClasses; j++){
				
				System.out.print(""+wait[j]);
				System.out.print(" "+classDeparture[j]);
				System.out.println(" ");
				
				results[step][j] = wait[j]/classDeparture[j];
			}
			*/
			results[step] = totWait/totArrival;
			step++;
		}
		return results;
	}
}