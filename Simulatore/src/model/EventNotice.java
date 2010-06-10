package model;

import model.enumerators.EventType;
/**
 * Classe che rappresenta un evento di un simulatore MG1
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class EventNotice implements Comparable<EventNotice> {
	public int Id;

	protected double occurrenceTime;
	protected EventType eventType;
	protected double serviceTime;
	/**
	 * 
	 * Crea un nuovo evento con parametri specificati
	 * 
	 * @param id identificatore dell'evento
	 * @param occurrence istante di occorrenza dell'evento
	 * @param type tipo dell'evento: arrivo o partenza
	 * @param service tempo di servizio per un arrivo, 0 per una partenza.
	 */
	public EventNotice(int id,double occurrence, EventType type, double service){
		this.Id=id;
		this.occurrenceTime=occurrence;
		this.eventType = type;
		this.serviceTime=service;
	}
	/**
	 * 
	 * Setta l'istante di occorenza
	 * 
	 * @param time istante di occorrenza
	 */
	public void setOccurrenceTime(float time){
		occurrenceTime = time;
	}
	
	/**
	 * Restituisce l'istante di occorenza
	 * 
	 * @return l'istante di occorrenza dell'evento
	 */
	public double getOccurrenceTime(){
		return occurrenceTime;
	}
	
	/**
	 * 
	 * Setta il tipo di evento
	 * 
	 * @param type tipo dell'evento
	 */
	public void setEventType(EventType type){
		this.eventType = type;
	}
	
	/**
	 * Restituisce il tipo di evento
	 * 
	 * @return tipo dell'evento
	 */
	public EventType getEventType(){
		return eventType;
	}
	
	/**
	 * Setta il tempo di servizio per l'evento
	 * 
	 * @param service tempo di servizio dell'evento
	 */
	public void setServiceTime(float service){
		this.serviceTime = service;
	}
	
	/**
	 * 
	 * Restituisce il tempo di servizio dell'evento
	 * 
	 * @return tempo di servizio dell'evento
	 */
	public double getServiceTime(){
		return serviceTime;
	}
	/**
	 * Rappresentazione in stringa di un evento.
	 */
	public String toString(){
		return "("+Id+") @"+occurrenceTime+" "+eventType+" "+((serviceTime==0)?"":"theta= "+serviceTime);
	}
	
	/**
	 * Metodo per il confronto di due eventi.
	 */
	public int compareTo(EventNotice o) {
		if (this.getOccurrenceTime() <= o.getOccurrenceTime())
			return -1;
		if (this.getOccurrenceTime() > o.getOccurrenceTime())
			return 1;
		return 0;
	}
	
}
