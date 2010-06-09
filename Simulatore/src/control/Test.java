package control;

import java.text.DecimalFormat;

import control.engine.MG1PRIOsimulator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int xVal=100;
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
		}

		
	}

}
