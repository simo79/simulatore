package control;

import java.text.DecimalFormat;

import view.LogForm;
import model.enumerators.CaseClasses;
import model.enumerators.DistributionType;
import control.engine.MG1PRIOsimulator;
import control.engine.MG1simulator;
import control.engine.MM1SJNsimulator;
import control.engine.NumbersGenerator;
import control.engine.TrafficGenerator;
/**
 * 
 * La classe mette a disposizione i metodi per l'avvio delle diverse simulazioni richieste.
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class SimulatorLauncher {
	/**
	 * 
	 * Avvio esercitazione 1 al variare della quantitˆ di numeri da generare
	 * 
	 * @param minN valore minimo di N da cui parte la simulazione
	 * @param maxN valore massimo di N a cui la simulazione deve concludersi
	 * @param passoN valore di incremento di N tra una passo di simulazione e l'altro
	 * @param confidenceLevel livello di confidenza da usare per il calcolo finale dell'indice di confidenza
	 * @param logFrm riferimento alla finestra di log
	 */
	public static void lauchSimulation1_1(int minN, int maxN, int passoN, double confidenceLevel, LogForm logFrm){
		logFrm.reset();
		double[][] fileContent = new NumbersGenerator(minN, maxN, passoN, confidenceLevel,logFrm).run();
		Utility.createCSVFile("GenNCasuali_Nvariabile",null, fileContent);
		logFrm.log("I valori ricavati dalle simulazioni sono riportati sul file GenNCasuali_Nvariabile.csv");
		logFrm.refresh();
	}
	/**
	 * Avvio esercitazione 1 al variare del livello di confidenza
	 * 
	 * @param N quantitˆ di numeri pseudo random da generare ad ogni passo di simulazione
	 * @param minValueConfidenza valore minimo del livello di confidenza da cui parte la simulazione
	 * @param maxValueConfidenza valore massimo del livello di confidenza a cui la simulazione si ferma
	 * @param passoConfidenza valore di incremento del livello di confidenza tra un passo di simulazione e l'altro
	 * @param logFrm riferimento alla finestra di log
	 */
	public static void lauchSimulation1_2(int N, double minValueConfidenza,double maxValueConfidenza,double passoConfidenza , LogForm logFrm){
		logFrm.reset();
		double[][] fileContent = new NumbersGenerator(N,minValueConfidenza,maxValueConfidenza, passoConfidenza,logFrm).run();
		Utility.createCSVFile("GenNCasuali_Confvariabile",null, fileContent);
		logFrm.log("I valori ricavati dalle simulazioni sono riportati sul file GenNCasuali_Confvariabile.csv");
		logFrm.refresh();
	}
	/**
	 * Avvio esercitazione 2 per la generazione di traffici di vario tipo e calcolo del relatico IDC.
	 * 
	 * @param N numero di arrivi da generare
	 * @param nSim numero di passi di simulazione totali	
	 * @param confidenceLevel livello di confidenza da utilizzare
	 * @param T tempo su cui calcolare l'IDC
	 * @param type tipo di distribuzione degli arrivi (esponenziale, deterministico, pareto, SPP)
	 * @param param parametri per il tipo di distribuzione suddetto, si veda la javadoc del RandomGenerator
	 * @param logFrm riferimento alla finestra di log
	 */
	public static void lauchSimulation2(int N, int nSim, double confidenceLevel, double T, DistributionType type, double[] param, LogForm logFrm){
		logFrm.reset();
		new TrafficGenerator(N,nSim,confidenceLevel,T,type, param,logFrm).run();
		logFrm.refresh();
	}
	/**
	 * Avvio esercitazione 3 per la realizzazione di un sistema MG1.
	 * 
	 * @param N numero degli arrivi totali da generare
	 * @param nSim numero di passi di simulazione per ogni valore di ingresso di rho
	 * @param levelOfConfidence livello di confidenza da utilizzare
	 * @param values numero di valori dell'ascissa in cui l'intervallo (0,1) di rho viene suddiviso. Determina l'incremento di rho per ogni simulazione.
	 * @param typeOfService tipo di distribuzione del servizio (esponenziale, deterministico, pareto)
	 * @param par parametri per il tipo di distribuzione suddetto, si veda la javadoc del RandomGenerator
	 * @param logFrm riferimento alla finestra di log
	 */
	 
	public static void lauchSimulation3(int N, int nSim, double levelOfConfidence,int values, DistributionType typeOfService, double[] par,LogForm logFrm){
		double step = 1.0/(values+1);
		double rho=step;
		double lambdaArr;
		double[][] log = new double[values][4];
		int i=0;
		logFrm.getJPBstatus().setMaximum(values);
		DecimalFormat f = new DecimalFormat("#####.########");
		try{
			while(i<values){
				lambdaArr=rho*par[0];
				double[] results = new MG1simulator(lambdaArr,typeOfService, par, nSim, N).run();
				double media = Utility.mediaCamp(results);
				double semiAmp= Utility.semiAmpiezza(results, levelOfConfidence);
				logFrm.log("rho: "+rho+" media: "+media+" semi_amp: "+semiAmp);
				log[i][0]= rho;
				log[i][1]=media;
				log[i][2]=media-semiAmp;
				log[i][3]=media+semiAmp;
				i++;
				rho+=step;
				rho=f.parse(f.format(rho)).doubleValue();
				logFrm.getJPBstatus().setValue(i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		logFrm.log("I valori ricavati dalle simulazioni sono riportati sul file MG1_"+typeOfService.toString()+".csv");
		Utility.createCSVFile("MG1_"+typeOfService.toString(), null, log);
	}
	/**
	 * 
	 * Avvio esercitazione 4 per la realizzazione di un sistema MM1 con prioritˆ non preemptive.
	 *
	 * @param N numero degli arrivi totali da generare
	 * @param nSim numero di passi di simulazione per ogni valore di ingresso di x
	 * @param caseC tipologia di caso da studiare, determina il numero delle classi di prioritˆ e per ciascuna di esse il relativo rho
	 * @param xVal numero di valori dell'ascissa in cui l'intervallo (0,1) di x viene suddiviso. Determina l'incremento di x per ogni simulazione.
	 * @param rho valore di rho totale, determina con x il valore dei singoli rho delle classi di prioritˆ
	 * @param mu frequenza del servizio
	 * @param logFrm riferimento alla finestra di log
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
	}
	
	
	public static void lauchSimulation5(int N, int nSim,int nClasses, double rho, double mu, LogForm logFrm){
		long ora = System.currentTimeMillis();
		double step = 8.0/(mu*nClasses);
		double[][] log = new double[nClasses][2];
		double [][] res = new MM1SJNsimulator(N, rho, mu, nSim, nClasses).run();
		double[] partial = new double[nSim];
		for(int k=0; k<nClasses; k++){
			for(int j=0; j<nSim; j++)
				partial[j]=res[j][k];
			double media = Utility.mediaCamp(partial);
			double k1 = k*step;
			log[k][0]= k1;
			log[k][1]= media;
			
		}
		
		Utility.createCSVFile("test", null, log);
		System.out.println("finito!");
		long tempo = System.currentTimeMillis()-ora;
		System.out.println("Tempo totale "+(tempo*1.0/1000)/60+" minuti");
	}
}
