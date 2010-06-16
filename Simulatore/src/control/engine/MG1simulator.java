package control.engine;

import java.util.PriorityQueue;
import model.EventNotice;
import model.RandomGenerator;
import model.enumerators.DistributionType;
import model.enumerators.EventType;
/**
 * Classe che realizza un simulatore di un sistema MG1
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 * 
 */
public class MG1simulator {
	
	
	private PriorityQueue<EventNotice> futureEventList;

	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	private int tries;
	private int max_users;
	
	private RandomGenerator rndServizio;
	private RandomGenerator rndArrivi;
	
	/**
	 * Crea un nuovo simulatore MG1 con i parametri specificati.
	 * 
	 * @param lambdaArr frequenza degli arrivi
	 * @param serv tipo di distribuzione del servizio
	 * @param par array di parametri per la distribuzione del servizio (vedi Random Generator)
	 * @param tries numero di simulazioni successive eseguite
	 * @param users numero di utenti in arrivo al sistema
	 */
	public MG1simulator(double lambdaArr,DistributionType serv, double[] par, int tries, int users){
		futureEventList = new PriorityQueue<EventNotice>();
		rndServizio = new RandomGenerator(serv,par);
		rndArrivi = new RandomGenerator(DistributionType.Exponential, new double[]{lambdaArr});
		this.tries=tries;
		max_users=users;
	}
	/**
	 * Metodo di inizializzazione delle variabili interne richiamato a ogni step di simulazione
	 */
	private void initialize(){
		Id=0;
		totArrival=0;
		totDeparture=0;
		totUser=0;
		futureEventList.add(new EventNotice(Id,rndArrivi.nextRandom(),EventType.Arrival,rndServizio.nextRandom()));
	}
	/**
	 * 
	 * Avvia la simulazione del sistema.
	 * 
	 * @return array di tempi medi spesi in coda per ogni step di simulazione
	 */
	public double[] run(){
		double[] result = new double[tries];
		int step=0;
		while(step<tries){
			//System.out.println("Simulazione "+(step+1));
			double now=0;
			double free=0;
			double w=0;
			initialize();
			while (totDeparture < max_users) {
				EventNotice nextEvent = futureEventList.poll();
				//System.out.println(nextEvent);
				now = nextEvent.getOccurrenceTime();
				if(nextEvent.getEventType() == EventType.Arrival){
					//Evento di Arrivo
					totArrival++;
					totUser++;
					if(totUser==1){
						free=now + nextEvent.getServiceTime();
					}else{
						w = w + (free-now);
						free= free + nextEvent.getServiceTime();
					}
					
					futureEventList.add(new EventNotice(nextEvent.Id,free,EventType.Departure,0d));
					
					if(totArrival<max_users){
						futureEventList.add(new EventNotice(++Id,now + rndArrivi.nextRandom(),EventType.Arrival,rndServizio.nextRandom()));
					}
				}else if(nextEvent.getEventType() == EventType.Departure){
					//Evento di Fine Servizio
					totUser--;
					totDeparture++;
					
				}
			}
			w=w/max_users;
			result[step]=w;
			//System.out.println("Tempo medio in coda: "+w);
			step++;
		}
		return result;
	}

}
