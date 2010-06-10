package control.engine;

import java.util.Vector;
import java.util.PriorityQueue;

import view.LogForm;

import model.EventNotice;
import model.SJNPriorityQueueElement;
import model.RandomGenerator;
import model.enumerators.DistributionType;
import model.enumerators.EventType;
/**
 * Classe che realizza un simulatore di un sistema MM1 con scheduling in coda di tipo Shortest Job Next
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class MM1SJNsimulator {
	
	private PriorityQueue<EventNotice> futureEventList;
	
	private PriorityQueue<SJNPriorityQueueElement> priorityQueue;
	
	private int nClasses = 40;
	private int[] classArrival = new int[nClasses];
	private int[] classDeparture = new int[nClasses];
	private double[] wait;
	
	private int N;
	
	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	private int nSim;
	
	private double free;
	private double now;
	private double totWait;
	
	private RandomGenerator rndServizio;
	private RandomGenerator rndArrivi;
	
	private LogForm logFrm;
	private double rho;
	private double mu;
	
	/**
	 * Crea un nuovo simulatore MM1 con i parametri specificati.
	 * 
	 * @param N numero di arrivi da generare
	 * @param rho rho del sistema
	 * @param mu frequenza di servizio (esponenziale)
	 * @param nSim numero di simulazioni consecutive da realizzare
	 */
	public MM1SJNsimulator(int N, double rho, double mu, int nSim){
		this.rho=rho;
		this.mu=mu;
		this.nSim = nSim;
		//this.nClasses=nClasses;
		this.N=N;
		logFrm=null;
		
	}
	/**
	 * 
	 * Crea un nuovo simulatore MM1 con i parametri specificati.
	 * 
	 * @param N numero di arrivi da generare
	 * @param rho rho del sistema
	 * @param mu frequenza di servizio (esponenziale)
	 * @param nSim numero di simulazioni consecutive da realizzare
	 * @param logFrm riferimento alla finestra di log
	 */
	public MM1SJNsimulator(int N, double rho, double mu, int nSim, LogForm logFrm){
		this(N,rho,mu,nSim);
		this.logFrm=logFrm;
	}
	/**
	 * Metodo di inizializzazione delle variabili interne richiamato a ogni step di simulazione
	 */
	private void inizialize() {
		totArrival=0;
		totDeparture=0;
		
		wait = new double[nClasses];
		for(int i=0; i<nClasses; i++){
			wait[i]=0.0;
			classArrival[i]=0;
			classDeparture[i]=0;
		}
		
		totUser=0;
		Id=0;
		rndServizio = new RandomGenerator(DistributionType.Exponential, new double[]{this.mu});
		rndArrivi = new RandomGenerator(DistributionType.Exponential, new double[]{this.rho/this.mu});
		priorityQueue = new PriorityQueue<SJNPriorityQueueElement>();
		futureEventList = new PriorityQueue<EventNotice>();
		futureEventList.add(new EventNotice(Id,rndArrivi.nextRandom(), EventType.Arrival,rndServizio.nextRandom()));
		
		free=0.0;
		now=0.0;
		
	}
	/**
	 * 
	 * Avvia la simulazione del sistema.
	 * 
	 * @return matrice di tempi medi spesi in coda per ogni classe di priorità (il valore è definito da codice pari a 40) per ogni step di simulazione
	 */
	public double[][] run(){
		int step=0;
		double [][] results = new double[nSim][nClasses];
		
		while (step<nSim){	
			inizialize();
			while (totDeparture < N) {
				EventNotice e = futureEventList.poll();
				now = e.getOccurrenceTime();
				if (e.getEventType() == EventType.Arrival) {
					totUser++;
					totArrival++;
					classArrival[calcClassPriority(e.getServiceTime())]++;
					
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
					classDeparture[calcClassPriority(e.getServiceTime())]++;
					if (totUser > 0) {
						int j = 0;
						SJNPriorityQueueElement elem = priorityQueue.poll();
						totWait = totWait + (now - elem.getOccurrenceTime());
						wait[calcClassPriority(elem.getServiceTime())] += (now -elem.getOccurrenceTime());
						futureEventList.add(new EventNotice(elem.getId(), now+elem.getServiceTime(), EventType.Departure, 0));
					}
				}
				
			}
			for(int i=0; i<nClasses; i++){
				
				if (classArrival[i]==0)
					results[step][i] = 0.0;
				else
					results[step][i]=wait[i]/classArrival[i];
			}
			
			step++;
		}
		return results;
	}
	
	/**
	 * Metodo per la determinazione della classe di priorità in funzione del tempo di servizio
	 * 
	 * @param theta tempo di servizio
	 * @return classe di priorità relativa al tempo di servizio indicato
	 */
	private int calcClassPriority(double theta){
	
		double meanTheta = (1.0/this.mu);
		double step = meanTheta/(this.nClasses/2);
		
		int i=(int)Math.floor(theta/step);
		
		return (i>=nClasses)?nClasses-1:i;
	}
}