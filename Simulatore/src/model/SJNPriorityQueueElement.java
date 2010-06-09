package model;

public class SJNPriorityQueueElement implements Comparable<SJNPriorityQueueElement>{
		
	private double occurTime;
	private double servTime;
	private int Id;
	
	public SJNPriorityQueueElement(int id, double occurTime, double servTime){
		this.Id = id;
		this.occurTime = occurTime;
		this.servTime = servTime;
		
	}
	
	public double getOccurrenceTime(){
		return this.occurTime;
	}
	
	public double getServiceTime(){
		return this.servTime;
	}

	public int getId(){
		return this.Id;
	}
	
	@Override
	public String toString() {
		
		// TODO Auto-generated method stub
		return new String("Id: "+Id+" occurence: "+occurTime+" service: "+servTime);
	}
	@Override
	public int compareTo(SJNPriorityQueueElement o) {
		if (this.getServiceTime() < o.getServiceTime())
			return -1;
		if (this.getServiceTime() > o.getServiceTime())
			return 1;
		return 0;
		
	}
		
}
