package control.engine;

import java.util.Vector;
import java.util.PriorityQueue;

import view.LogForm;

import model.PriorityEventNotice;
import model.PriorityQueueElement;
import model.RandomGenerator;
import model.enumerators.DistributionType;
import model.enumerators.EventType;
/**
 * Classe che realizza un simulatore di un sistema MM1 con prioritˆ
 *  
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 * 
 */
public class MG1PRIOsimulator {
	
	private PriorityQueue<PriorityEventNotice> futureEventList;
	
	private Vector<PriorityQueue<PriorityQueueElement>> priorityQueues;
	
	private int nClasses;

	private int N;
	
	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	private int[] classArrival;
	private int[] classDeparture;
	
	private int nSim;
	
	private double free;
	private double now;
	private double[] wait;
	
	private RandomGenerator rndServizio;
	private RandomGenerator[] rndArrivi;
	
	private LogForm logFrm;

	/**
	 * Crea un nuovo simulatore con i parametri specificati.
	 * 
	 * @param N numero di arrivi da simulare
	 * @param nClasses numero di classi di prioritˆ degli eventi di arrivo
	 * @param rho array con rho rispettivi ad ogni classe di prioritˆ
	 * @param mu frequenza di servizio (esponenziale)
	 * @param nSim numero di simulazioni successive
	 */
	public MG1PRIOsimulator(int N, int nClasses, double[] rho, double mu, int nSim){
		this.nSim = nSim;
		this.nClasses=nClasses;
		this.N=N;
		logFrm=null;
		
		priorityQueues = new Vector<PriorityQueue<PriorityQueueElement>>();
		futureEventList = new PriorityQueue<PriorityEventNotice>();
		
		classArrival = new int[nClasses];
		classDeparture = new int[nClasses];
		wait = new double[nClasses];
		
		rndServizio = new RandomGenerator(DistributionType.Exponential, new double[]{mu});
		rndArrivi = new RandomGenerator[nClasses];
		
		for(int i=0; i<nClasses; i++){
			rndArrivi[i]=new RandomGenerator(DistributionType.Exponential, new double[]{rho[i]*mu});
			priorityQueues.add(i, new PriorityQueue<PriorityQueueElement>());
		}
	}
	/**
	 * Crea un nuovo simulatore con i parametri specificati.
	 * 
	 * @param N numero di arrivi da simulare
	 * @param nClasses numero di classi di prioritˆ degli eventi di arrivo
	 * @param rho array con rho rispettivi ad ogni classe di prioritˆ
	 * @param mu frequenza di servizio (esponenziale)
	 * @param nSim numero di simulazioni successive
	 * @param logFrm riferimento alla finestra di log
	 */
	public MG1PRIOsimulator(int N, int nClasses, double[] rho, double mu, int nSim, LogForm logFrm){
		this(N,nClasses,rho,mu,nSim);
		this.logFrm=logFrm;
	}
	/**
	 * Metodo di inizializzazione delle variabili interne richiamato a ogni step di simulazione
	 */
	private void inizialize() {
		totArrival=0;
		totDeparture=0;
		totUser=0;
		Id=0;
		futureEventList = new PriorityQueue<PriorityEventNotice>();
		priorityQueues = new Vector<PriorityQueue<PriorityQueueElement>>();
		for(int i=0;i<nClasses;i++){
			classArrival[i]=0;
			classDeparture[i]=0;
			wait[i]=0.0;
			double rndArr= rndArrivi[i].nextRandom();
			double rndSer= rndServizio.nextRandom();
			futureEventList.add(new PriorityEventNotice(Id,rndArr, EventType.Arrival,rndSer , i));
			totArrival++;
			priorityQueues.add(i, new PriorityQueue<PriorityQueueElement>());
			Id++;
		}
		free=0.0;
		now=0.0;
		
	}
	/**
	 * Avvia la simulazione del sistema 
	 * 
	 * @return matrice di double dove per ogni step di simulazioni si ha il tempo media in coda per ogni classe di prioritˆ
	 */
	public double[][] run(){
		int step=0;
		double [][] results = new double[nSim][nClasses];
		while (step<nSim){
			inizialize();
			while (totDeparture < N) {
				PriorityEventNotice e = futureEventList.poll();
				now = e.getOccurrenceTime();
				if (e.getEventType() == EventType.Arrival) {
					totUser++;
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
						totArrival++;
						
					}
				}else if (e.getEventType() == EventType.Departure) {
					totUser--;
					totDeparture++;
					classDeparture[e.getPriority()]++;
					if (totUser > 0) {
						int j = 0;
						while (priorityQueues.get(j).isEmpty()) 
							j++;
						PriorityQueueElement elem = priorityQueues.get(j).poll();
						wait[j] = wait[j] + (now -elem.getOccurrenceTime());
						futureEventList.add(new PriorityEventNotice(elem.getId(), now+elem.getServiceTime(), EventType.Departure, 0, j));
					}
				}
				
			}
			for (int j = 0; j < nClasses; j++){
				results[step][j] = wait[j]/classDeparture[j];
			}
			step++;
		}
		return results;
	}
}