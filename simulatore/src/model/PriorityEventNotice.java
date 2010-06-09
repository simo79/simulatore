package model;

import model.enumerators.EventType;

public class PriorityEventNotice extends EventNotice {
	private int priority;
	
	public PriorityEventNotice(int id,double occurrence, EventType type, double service,int priority){
		super(id,occurrence,type,service);
		this.priority=priority;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public String toString(){
		return "("+Id+") @"+occurrenceTime+" "+eventType+" "+((serviceTime==0)?"":"theta= "+serviceTime);
	} 
}
