package control;

import java.text.DecimalFormat;

import view.LogForm;
import model.DistributionType;
import model.enumerators.CaseClasses;
import control.engine.MG1PRIOsimulator;
import control.engine.MG1simulator;
import control.engine.NumbersGenerator;
import control.engine.TrafficGenerator;

public class SimulatorLauncher {
	/**
	 * Simulazione esercitazione1 al variare di N
	 * @param form
	 */
	public static void lauchSimulation1_1(int minN, int maxN, int passoN, double confidenceLevel, LogForm logFrm){
		if(logFrm!=null){
			logFrm.reset();
			new NumbersGenerator(minN, maxN, passoN, confidenceLevel,logFrm).run();
		}else
			new NumbersGenerator(minN, maxN, passoN ,confidenceLevel).run();
	}
	/**
	 * Simulazione esercitazione1 al variare del livello di confidenza
	 */
	public static void lauchSimulation1_2(int N, double minValueConfidenza,double maxValueConfidenza,double passoConfidenza , LogForm logFrm){
		if(logFrm!=null){
			logFrm.reset();
			new NumbersGenerator(N,minValueConfidenza,maxValueConfidenza, passoConfidenza,logFrm).run();
		}else
			new NumbersGenerator(N,minValueConfidenza,maxValueConfidenza, passoConfidenza).run();
	}
	/**
	 * 
	 * @param form
	 */
	public static void lauchSimulation2(int N, int nSim, double confidenceLevel, double T, DistributionType type, double[] param, LogForm logFrm){
		if(logFrm!=null){
			logFrm.reset();
			new TrafficGenerator(N,nSim,confidenceLevel,T,type, param,logFrm).run();
		}else
			new TrafficGenerator(N,nSim,confidenceLevel,T,type, param).run();
	}
	/**
	 * 
	 * @param form
	 */
	public static void lauchSimulation3(int N, int nSim, double levelOfConfidence,int values, DistributionType typeOfService, double[] par,double mu,LogForm logFrm){
		double step = 1.0/values;
		double rho=step;
		double[][] log = new double[values][3];
		int i=0;
		logFrm.getJPBstatus().setMaximum(values);
		while(rho<1){
			double[] results = new MG1simulator(rho, typeOfService, par, nSim, N).run();
			double media = Utility.mediaCamp(results);
			double semiAmp= Utility.semiAmpiezza(results, levelOfConfidence);
			logFrm.log("rho: "+rho+"media: "+media+" semi_amp: "+semiAmp);
			log[i][0]= rho;
			log[i][1]=media;
			log[i][2]=semiAmp;
			i++;
			rho+=step;
			logFrm.getJPBstatus().setValue(i);
		}
		logFrm.log("I valori ricavati dalle simulazioni sono riportati sul file MG1.csv");
		Utility.createCSVFile("MG1", null, log);
	}
	/**
	 * 
	 * @param form
	 */
	public static void lauchSimulation4(int N, int nSim, CaseClasses caseC,int xVal, double rho, double mu, LogForm logFrm){
		double[][] log = null;
		double[] rhos;
		Integer i;
		double step;
		double x;
		DecimalFormat f = new DecimalFormat("#####.########");
		switch(caseC){
		case Caso1:
			rhos = new double[2];
			log = new double[xVal][5];
			i=0;
			logFrm.getJPBstatus().setMaximum(xVal);
			step=1.0/(xVal+1);
			x=step;
			try{
				//while (x<1.0){
				while(i<xVal){
					rhos[0]= x*rho;
					rhos[1]= (1-x)*rho;
					double[][] results = new MG1PRIOsimulator(N, 2, rhos, mu, nSim, logFrm).run();
					double[] partial = new double[nSim];
					log[i][0]=x;
					for(int k=0; k<2; k++){
						for(int j=0; j<nSim; j++)
							partial[j]=results[j][k];
						double media = Utility.mediaCamp(partial);
						double semi = Utility.semiAmpiezza(partial, 0.95);
						log[i][k+1]= media;
						log[i][k+3]= semi;
						
					}
					logFrm.log("x: "+x+" n0: "+log[i][1]+" n1: "+log[i][2]);
					i++;
					logFrm.getJPBstatus().setValue(i);
					x+=step;
					x=f.parse(f.format(x)).doubleValue();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Caso2:
			rhos = new double[3];
			log = new double[xVal][7];
			i=0;
			logFrm.getJPBstatus().setMaximum(xVal);
			step=1.0/(xVal+1);
			x=step;
			try{
				while(i<xVal){
				//while (x<1){
					rhos[0]= x/2*rho;
					rhos[1]= x/2*rho;
					rhos[2]= (1-x)*rho;
					double[][] results = new MG1PRIOsimulator(N, 3, rhos, mu, nSim, logFrm).run();
					double[] partial = new double[nSim];
					log[i][0]=x;
					for(int k=0; k<3; k++){
						for(int j=0; j<nSim; j++)
							partial[j]=results[j][k];
						double media = Utility.mediaCamp(partial); 
						double semi = Utility.semiAmpiezza(partial, 0.95);
						//System.out.print(" "+media);
						log[i][k+1]= media;
						log[i][k+4]= semi;
						
					}
					logFrm.log("x: "+x+" n0: "+log[i][1]+" n1: "+log[i][2]+" n2: "+log[i][3]);
					i++;
					logFrm.getJPBstatus().setValue(i);
					x+=step;
					x=f.parse(f.format(x)).doubleValue();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Caso3:
			rhos = new double[3];
			log = new double[xVal][7];
			i=0;
			logFrm.getJPBstatus().setMaximum(xVal);
			step=1.0/(xVal+1);
			x=step;
			try{
				while(i<xVal){
				//while (x<1){
					rhos[0]= x/10*rho;
					rhos[1]= x*9/10*rho;
					rhos[2]= (1-x)*rho;
					double[][] results = new MG1PRIOsimulator(N, 3, rhos, mu, nSim, logFrm).run();
					double[] partial = new double[nSim];
					log[i][0]=x;
					for(int k=0; k<3; k++){
						for(int j=0; j<nSim; j++)
							partial[j]=results[j][k];
						double media = Utility.mediaCamp(partial); 
						double semi = Utility.semiAmpiezza(partial, 0.95);
						//System.out.print(" "+media);
						log[i][k+1]= media;
						log[i][k+4]= semi;
						
					}
					logFrm.log("x: "+x+" n0: "+log[i][1]+" n1: "+log[i][2]+" n2: "+log[i][3]);
					i++;
					logFrm.getJPBstatus().setValue(i);
					x+=step;
					x=f.parse(f.format(x)).doubleValue();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Caso4:
			rhos = new double[3];
			log = new double[xVal][7];
			i=0;
			logFrm.getJPBstatus().setMaximum(xVal);
			step=1.0/(xVal+1);
			x=step;
			try{
				while(i<xVal){
				//while (x<1){
					rhos[0]= x*rho;
					rhos[1]= (1-x)/2*rho;
					rhos[2]= (1-x)/2*rho;
					double[][] results = new MG1PRIOsimulator(N, 3, rhos, mu, nSim, logFrm).run();
					double[] partial = new double[nSim];
					log[i][0]=x;
					for(int k=0; k<3; k++){
						for(int j=0; j<nSim; j++)
							partial[j]=results[j][k];
						double media = Utility.mediaCamp(partial); 
						double semi = Utility.semiAmpiezza(partial, 0.95);
						//System.out.print(" "+media);
						log[i][k+1]= media;
						log[i][k+4]= semi;
						
					}
					logFrm.log("x: "+x+" n0: "+log[i][1]+" n1: "+log[i][2]+" n2: "+log[i][3]);
					i++;
					logFrm.getJPBstatus().setValue(i);
					x+=step;
					x=f.parse(f.format(x)).doubleValue();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		logFrm.log("I valori ricavati dalle simulazioni sono riportati sul file MM1PRIO.csv");
		Utility.createCSVFile("MM1PRIO", null, log);
		logFrm.refresh();
	}
}
