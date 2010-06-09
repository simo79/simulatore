package control.engine;

import java.util.concurrent.ConcurrentSkipListSet;


import model.DistributionType;
import model.EventNotice;
import model.RandomGenerator;
import model.enumerators.EventType;

public class MG1simulator {
	
	
	private ConcurrentSkipListSet<EventNotice> futureEventList;

	private int totArrival;
	private int totDeparture;
	private int totUser;
	private int Id;
	
	private double lambda;

	private int tries;
	private int max_users;
	
	private RandomGenerator rndServizio;
	private RandomGenerator rndArrivi;
	
	
	public MG1simulator(double rho, DistributionType serv, double[] par,int tries, int users){
		futureEventList = new ConcurrentSkipListSet<EventNotice>();
		this.lambda= rho / par[0];
		rndServizio = new RandomGenerator(serv,par);
		rndArrivi = new RandomGenerator(DistributionType.Exponential, new double[]{lambda});
		this.tries=tries;
		max_users=users;
	}
	/**
	 * 
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
	 * @param tries
	 * @return
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
				EventNotice nextEvent = futureEventList.pollFirst();
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
