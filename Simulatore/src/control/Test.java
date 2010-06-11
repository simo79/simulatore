package control;

import java.text.DecimalFormat;

import control.engine.MG1PRIOsimulator;
import control.engine.MM1SJNsimulator;
/**
 * classe utilizzata per dei test sulle prime simulazioni.
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class Test {

	/**
	 * avvia il test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long ora = System.currentTimeMillis();
		int N=100000;
		int nSim = 1000;
		int nClasses = 30;
		double mu = 1.0;
		double rho =0.8;
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
		
	
		// TODO Auto-generated method stub
		/*int xVal=100;
		int nSim=100;
		double mu=1;
		int N=1000;
		
		double[][] log;
		double rho =0.8;
		
		double[] rhos = new double[2];
		log = new double[xVal-1][3];
		Integer i=0;
		
		double x=0.5;
		rhos[0]= x*rho;
		rhos[1]= (1-x)*rho;
		double[][] results = new MG1PRIOsimulator(N, 2, rhos, mu, nSim).run();
		double[] partial = new double[nSim];
		log[i][0]=x;
	
		for(int k=0; k<2; k++){
			for(int j=0; j<nSim; j++){
				partial[j]=results[j][k];
				if(k==1)
				System.out.print(" "+partial[j]);
			}
			System.out.println(" ");
			double media = Utility.mediaCamp(partial); 
			log[i][k+1]= media;
			if(k==1)System.out.println(""+media);
		}*/

		
	}

}
