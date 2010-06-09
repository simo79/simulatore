package control;

import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistributionImpl;

public class Utility {
	
	public static double mediaCamp(double[] values){
		double tmp=0;
		for(int i=0; i<values.length;i++)
			tmp+=values[i];
		return tmp/values.length;
	}
	
	public static double varCamp(double[] values){
		double media = mediaCamp(values);
		double tmp=0;
		for(int i=0; i<values.length;i++)
			tmp+=Math.pow((values[i]-media),2);
		return tmp/(values.length - 1);
	}
	
	public static double semiAmpiezza(double[] values, int n, double levelOfConfidence){
		TDistributionImpl td = new TDistributionImpl(n-1);
		double ts = 0;
		try{
			ts = td.inverseCumulativeProbability(levelOfConfidence);
		}catch (MathException e) {
			System.out.println("Errore calcolo semiampiezza");
		}
		double var = varCamp(values);
		return ts*Math.sqrt(var/values.length);
	}
	
	public static double indiceDisp(double[] values){
		return varCamp(values)/mediaCamp(values);
	}
	
	public static void createCSVFile(String fileName,String[] intestazione, double[][] table){
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
		nf.setMinimumFractionDigits(10);
		File file = new File(fileName+".csv");
		try{
			FileWriter fileWriter = new FileWriter(file);
			String lineBuffer = new String();
			String separatore = "; ";
			if(intestazione!=null){
				for(int i=0;i<intestazione.length;i++){
					lineBuffer+=intestazione[i];
					if(i<intestazione.length-1)
						lineBuffer+=separatore;
				}
				fileWriter.write(lineBuffer);
				fileWriter.write('\n');
			}
			lineBuffer = new String();
			//Scorre le righe
			for(int i=0;i<table.length;i++){
				//Scorro le colonne
				for(int k=0;k<table[i].length;k++){
					lineBuffer += nf.format(table[i][k]); 
					if(k<table[i].length-1)
						lineBuffer+=separatore;
				}
				fileWriter.write(lineBuffer);
				if(i<table.length-1)
					fileWriter.write('\n');
				lineBuffer = "";
			}
			fileWriter.close();
		}catch(Exception e){
			System.out.println("Errore scrittura file di log");
		}
		
	}
}
