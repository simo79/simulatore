package model;

import model.enumerators.EventType;
/**
 * Classe che rappresenta un evento di un simulatore MG1 con prioritˆ.
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 */
public class PriorityEventNotice extends EventNotice {
	private int priority;
	/**
	 * Crea un nuovo evento con parametri specificati
	 * 
	 * @param id identificatore dell'evento
	 * @param occurrence istante di occorrenza dell'evento
	 * @param type tipo dell'evento: arrivo o partenza
	 * @param service tempo di servizio per un arrivo, 0 per una partenza.
	 * @param priority classe di prioritˆ, valori bassi equivalgono a classi piu prioritarie.
	 */
	public PriorityEventNotice(int id,double occurrence, EventType type, double service,int priority){
		super(id,occurrence,type,service);
		this.priority=priority;
	}
	
	/**
	 * Restituisce la classe di prioritˆ dell'evento.
	 * 
	 * @return la classe di prioritˆ dell'evento
	 */
	public int getPriority(){
		return this.priority;
	}
	
	/**
	 * Rappresentazione in stringa di un evento.
	 */
	public String toString(){
		return "("+Id+") @"+occurrenceTime+" "+eventType+" "+((serviceTime==0)?"":"theta= "+serviceTime);
	} 
}
