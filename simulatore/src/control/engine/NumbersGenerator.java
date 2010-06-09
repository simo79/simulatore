package control.engine;

import java.util.Random;

import view.LogForm;

import control.Utility;

public class NumbersGenerator {
	private int N=0;
	private int minN;
	private int maxN;
	private int stepN;
	private int nSim=50;
	private double levelOfConfidence=0.0;
	private double minConfidence;
	private double maxConfidence;
	private double stepConfidence;
	private LogForm logFrm;
	
	public NumbersGenerator(int N, double minConfidence, double maxConfidence, double stepConfidence){
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
		logFrm=null;
	}
	
	public NumbersGenerator(int minN, int maxN, int stepN, double levelOfConfidence){
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
		logFrm=null;
	}
	
	public NumbersGenerator(int N, double minConfidence, double maxConfidence, double stepConfidence, LogForm logFrm){
		this(N, minConfidence, maxConfidence, stepConfidence);
		this.logFrm=logFrm;
	}
	
	public NumbersGenerator(int minN, int maxN, int passoN, double levelOfConfidence,LogForm logFrm){
		this(minN, maxN, passoN, levelOfConfidence);
		this.logFrm=logFrm;
	}
	
	public void run(){
		Random rnd = new Random();
		if(levelOfConfidence == 0.0){
			levelOfConfidence = minConfidence;
			double[] result = new double[N];
			double[][] fileContent= new double[nSim][3];
			int k=0;
			while(k<nSim){
				for(int i=0;i<N;i++){
					result[i]=rnd.nextDouble();
				}
				fileContent[k][0]=levelOfConfidence;
				fileContent[k][1]=Utility.mediaCamp(result);
				fileContent[k][2]=Utility.semiAmpiezza(result, N, levelOfConfidence);
				
				levelOfConfidence+=stepConfidence;
				result = new double[N];
				k++;
			}
			Utility.createCSVFile("GenNCasuali",null, fileContent);
		}
		
		if(N == 0){
			N = minN;
			double[] result = new double[N];
			double[][] fileContent= new double[nSim][3];
			int k=0;
			while(k<nSim){
				for(int i=0;i<N;i++){
					result[i]=rnd.nextDouble();
				}
				fileContent[k][0]=N;
				fileContent[k][1]=Utility.mediaCamp(result);
				fileContent[k][2]=Utility.semiAmpiezza(result, N, levelOfConfidence);
				
				N+=stepN;
				result = new double[N];
				k++;
			}
			Utility.createCSVFile("GenNCasuali",null, fileContent);
		}
		
	}
}
