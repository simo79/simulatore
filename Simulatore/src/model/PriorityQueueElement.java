package model;

public class PriorityQueueElement implements Comparable<PriorityQueueElement> {
	
	private double occurTime;
	private double servTime;
	private int Id;
	
	public PriorityQueueElement(int id, double occurTime, double servTime){
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
	public int compareTo(PriorityQueueElement o) {
		if (this.getOccurrenceTime() < o.getOccurrenceTime())
			return -1;
		if (this.getOccurrenceTime() > o.getOccurrenceTime())
			return 1;
		return 0;
		
	}
	
}