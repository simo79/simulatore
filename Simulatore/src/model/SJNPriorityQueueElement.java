package model;
/**
 * Classe che rappresenta un arrivo inserito all'interno di una coda di attesa con sheduling Shortest Job First.
 *
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class SJNPriorityQueueElement implements Comparable<SJNPriorityQueueElement>{
		
	private double occurTime;
	private double servTime;
	private int Id;
	
	/**
	 * Crea l'elemento con i parametri specificati.
	 * 
	 * @param id identificatore
	 * @param occurTime istante di occorenza
	 * @param servTime tempo di servizio
	 */
	public SJNPriorityQueueElement(int id, double occurTime, double servTime){
		this.Id = id;
		this.occurTime = occurTime;
		this.servTime = servTime;
		
	}
	/**
	 * Restituisce l'istante di occorenza
	 * 
	 * @return l'istante di occorrenza dell'evento
	 */
	public double getOccurrenceTime(){
		return this.occurTime;
	}
	
	/**
	 * Restituisce il tempo di servizio
	 * 
	 * @return tempo di servizio dell'evento
	 */
	public double getServiceTime(){
		return this.servTime;
	}

	/**
	 * Restituisce l'id del elemento
	 * 
	 * @return l'id
	 */
	public int getId(){
		return this.Id;
	}
	
	/**
	 * Rappresentazione in stringa di un evento.
	 */
	@Override
	public String toString() {
		
		// TODO Auto-generated method stub
		return new String("Id: "+Id+" occurence: "+occurTime+" service: "+servTime);
	}
	/**
	 * Metodo per il confronto (SJF)
	 */
	@Override
	public int compareTo(SJNPriorityQueueElement o) {
		if (this.getServiceTime() < o.getServiceTime())
			return -1;
		if (this.getServiceTime() > o.getServiceTime())
			return 1;
		return 0;
		
	}
		
}
