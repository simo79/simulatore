package control.engine;

import view.LogForm;
import control.Utility;
import model.DistributionType;
import model.RandomGenerator;

public class TrafficGenerator {
	private int nSim;
	private int N;
	private double T;
	private DistributionType type = DistributionType.SPP;
	private double[] param;
	private RandomGenerator rnd;
	private LogForm logFrm;
	private double confidenceLevel;
	
	public TrafficGenerator(int N,int nSim, double confidenceLevel,double T, DistributionType type,double[] param){
		this.nSim=nSim;
		this.type=type;
		this.param=param;
		this.confidenceLevel=confidenceLevel;
		this.T=T;
		this.N=N;
		this.nSim=nSim;
		
		logFrm=null;
		

	}
	
	public TrafficGenerator(int N,int nSim,double confidenceLevel,double T, DistributionType type,double[] param,LogForm logFrm){
		this(N,nSim,confidenceLevel,T,type,param);
		this.logFrm=logFrm;
	}
	
	public void run(){	
		double[] result = new double[nSim];
		int i=0;
		int j=0;
		double now=0;
		double arrivi=0;
		
		while(j<nSim){
			rnd = new RandomGenerator(type,param);
			i=0;
			arrivi=0;
			now=0;
			while(i<N){
				now+=rnd.nextRandom();
				if(now<=T)
					arrivi++;
				i++;
			}
			result[j]=arrivi;
			logFrm.log("[step "+j+"] K(T)= "+arrivi);
			j++;
		}
		logFrm.log("E[K(T)]: "+Utility.mediaCamp(result));
		logFrm.log("Confidenza: "+Utility.semiAmpiezza(result, confidenceLevel));
		logFrm.log("IDC(T): "+Utility.indiceDisp(result));	
		//Utility.createCSVFile("log", null, rnd.getLog());
	}
}
