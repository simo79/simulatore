package control.engine;

import org.apache.commons.math.random.MersenneTwister;

import view.LogForm;

import control.Utility;

public class NumbersGenerator {
	private int N;
	private double minConfidence;
	private double maxConfidence;
	
	private int minN;
	private int maxN;
	private double levelOfConfidence;
	
	private int stepN;
	private int nSim=50;
	

	private double stepConfidence;
	private LogForm logFrm;
	
	public NumbersGenerator(int N, double minConfidence, double maxConfidence, double stepConfidence, LogForm frm){
		this.levelOfConfidence=0.0;
		this.N=N;
		if (minConfidence >= 1)
			this.minConfidence=0.75;
		else
			this.minConfidence=minConfidence;
		if (maxConfidence <= this.minConfidence)
			this.maxConfidence = 0.99;
		else
			this.maxConfidence=maxConfidence;
		if (stepConfidence < (this.maxConfidence-this.minConfidence))
			this.stepConfidence=stepConfidence;
		else
			this.stepConfidence=0.25;
		this.nSim = (int)(Math.floor((this.maxConfidence-this.minConfidence)/this.stepConfidence))+1;
		logFrm=frm;
	}
	
	public NumbersGenerator(int minN, int maxN, int stepN, double levelOfConfidence, LogForm frm){
		this.minN=minN;
		if (maxN > minN)
			this.maxN=maxN;
		else
			this.maxN=10*minN;
		if (stepN < (this.maxN-this.minN))
			this.stepN=stepN;
		else
			this.stepN=2;
		this.nSim=((this.maxN-this.minN)/this.stepN)+1;
		this.levelOfConfidence=levelOfConfidence;
		logFrm=frm;
	}
	
	public double[][] run(){
		MersenneTwister rnd = new MersenneTwister(System.currentTimeMillis());
		double[][] fileContent=null;
		logFrm.getJPBstatus().setMaximum(nSim);
		if(levelOfConfidence == 0.0){
			//Tengo costante N vario il livello di confidenza
			levelOfConfidence = minConfidence;
			double[] result = new double[N];
			fileContent= new double[nSim][3];
			int k=0;
			while(k<nSim){
				for(int i=0;i<N;i++){
					result[i]=rnd.nextDouble();
				}
				fileContent[k][0]=levelOfConfidence;
				fileContent[k][1]=Utility.mediaCamp(result);
				fileContent[k][2]=Utility.semiAmpiezza(result, levelOfConfidence);
				levelOfConfidence+=stepConfidence;
				result = new double[N];
				k++;
				logFrm.getJPBstatus().setValue(k);
			}
		}
		
		if(N == 0){
			//Tengo costante N vario il vivello di confidenza
			N = minN;
			double[] result = new double[N];
			fileContent= new double[nSim][3];
			int k=0;
			while(k<nSim){
				for(int i=0;i<N;i++){
					result[i]=rnd.nextDouble();
				}
				fileContent[k][0]=N;
				fileContent[k][1]=Utility.mediaCamp(result);
				fileContent[k][2]=Utility.semiAmpiezza(result, levelOfConfidence);
				
				N+=stepN;
				result = new double[N];
				k++;
				logFrm.getJPBstatus().setValue(k);
			}
		}
		return fileContent;
	}
}
