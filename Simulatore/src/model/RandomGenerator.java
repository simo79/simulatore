package model;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math.random.MersenneTwister;

public class RandomGenerator {
	private ArrayList<double[]> log;
	private Random rnd;
	private DistributionType type;
	//Esponenziale
	private double lambda0;
	//SPP
	private double lambda1;
	private double q01;
	private double q10;
	private int stato;
	private double currentLambda;
	private double currentProb;
	private double nextTransition;
	//Deterministica
	private double deterministicValue;
	//Pareto
	private double alpha;
	private double k;
	/**
	 * Desterministic: 
	 * 	param[0] = mu
	 * Esponential:
	 * 	param[0] = mu
	 * Pareto:
	 * 	param[0] = mu
	 * 	param[1] = alpha
	 * SPP:
	 * 	param[0] = mu
	 * 	param[1] = rapporto tra lambda
	 * 	param[2] = q01
	 *  param[3] = q10
	 * 
	 */
	public RandomGenerator(DistributionType type,double[] param){
		log = new ArrayList<double[]>();
		rnd = new Random();
		double lambdaMed,r;
		this.type=type;
		switch(type){
		case Deterministic:
			deterministicValue=1/param[0];
			break;
		case Exponential:
			lambda0=param[0];
			break;
		case Pareto:
			this.alpha = param[1];
			this.k =((1/param[0])*(alpha-1))/alpha;
			break;
		case SPP:
			lambdaMed=param[0];
			r=param[1];
			this.q01=param[2];
			this.q10=param[3];
			this.lambda0=(lambdaMed)/(q10+r*q01);
			this.lambda1=r*lambda0;
			currentLambda = lambda0;
			currentProb = q01;
			nextTransition=nextRandomExponential(q01);
			stato=0;
			break;
		}
	}
	
	public double nextRandom(){
		double rndValue=0;
		switch(type){
		case Deterministic:
			rndValue=deterministicValue;
			break;
		case Exponential:
			rndValue = -Math.log(1-rnd.nextDouble()) / lambda0;
			break;
		case Pareto:
			double y = rnd.nextDouble();
			rndValue = k / Math.pow(1-y,1/alpha);
			break;
		case SPP:
			//Nuovo arrivo
			double nextArrival = nextRandomExponential(currentLambda); 
			if(nextTransition>nextArrival){
				nextTransition-=nextArrival;
				rndValue = nextArrival;
			}else{
				switchState();
				double time = nextTransition;
				nextTransition=nextRandomExponential(currentProb);
				rndValue = time+nextRandom();
			}
			break;
		}
		return rndValue;
	}
	/**
	 * Restituisce un numero casuale con distribuzione Esponenziale per un lambda 
	 * passato come parametro.
	 * @param lambda
	 * @return
	 */
	private double nextRandomExponential(double lambda){
		return -Math.log(rnd.nextDouble()) / lambda;
	}
	/**
	 * 
	 */
	private void switchState(){
		if(stato==0){
			stato=1;
			currentLambda=lambda1;
			currentProb=q10;
		}else{
			stato=0;
			currentLambda=lambda0;
			currentProb=q01;
		}
	}
	
	public double[][] getLog(){
		double[][] logConv= new double[log.size()][2];
		int row=0;
		while(!log.isEmpty()){
			for(int i=0;i<log.get(row).length;i++)
				logConv[row][i]=log.get(row)[i];
		}
		return logConv;
	}

}
