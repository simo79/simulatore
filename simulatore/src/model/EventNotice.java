package model;

import model.enumerators.EventType;

public class EventNotice implements Comparable<EventNotice> {
	public int Id;

	protected double occurrenceTime;
	protected EventType eventType;
	protected double serviceTime;
	
	public EventNotice(int id,double occurrence, EventType type, double service){
		this.Id=id;
		this.occurrenceTime=occurrence;
		this.eventType = type;
		this.serviceTime=service;
	}
	
	public void setOccurrenceTime(float time){
		occurrenceTime = time;
	}
	
	public double getOccurrenceTime(){
		return occurrenceTime;
	}
	
	public void setEventType(EventType type){
		this.eventType = type;
	}
	
	public EventType getEventType(){
		return eventType;
	}
	
	public void setServiceTime(float service){
		this.serviceTime = service;
	}
	
	public double getServiceTime(){
		return serviceTime;
	}
	
	public String toString(){
		return "("+Id+") @"+occurrenceTime+" "+eventType+" "+((serviceTime==0)?"":"theta= "+serviceTime);
	}

	public int compareTo(EventNotice o) {
		if (this.getOccurrenceTime() <= o.getOccurrenceTime())
			return -1;
		if (this.getOccurrenceTime() > o.getOccurrenceTime())
			return 1;
		return 0;
	}
	
}
