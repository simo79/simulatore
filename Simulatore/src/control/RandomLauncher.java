package control;

import org.apache.commons.math.random.MersenneTwister;
/**
 * Classe per la realizzazione dello Scatter Plot. Impiegando il generatore di numeri pseudo casuali realizza punti con coordinate (x,y) comprese nel range (0,1).
 * 
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class RandomLauncher {

	//numeri da generare
	private static final int TRIES=1000;
		
	/**
	 * Avvia la generazione dei numeri e la redazione del file che contiene le coordinate per lo scatter plot.
	 * 
	 * @param args args[0] è il numero di punti da generare, se null il default è 1000.
	 */
	public static void main(String[] args) {
		
		int tries;
		MersenneTwister rnd=new MersenneTwister();
		double[][] table;			
		
		if (args.length!=0)
			tries=Integer.parseInt(args[0]);
		else
			tries=TRIES;
		
		table = new double[tries][2];
		
		for (int i=0;i<tries;i++){
			table[i][0]=rnd.nextDouble();
			table[i][1]=rnd.nextDouble();
		}
		Utility.createCSVFile("scatterplot", null, table);
		System.out.println(tries+" points generated.");
		System.out.println("Data successfully saved on file 'scatterplot.cvs'.");
	}

}
