package control;

import org.apache.commons.math.random.MersenneTwister;

public class RandomLauncher {

	private static final int TRIES=1000;
		
	/**
	 * @param args
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
