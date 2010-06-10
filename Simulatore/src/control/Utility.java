package control;

import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistributionImpl;
/**
 * 
 * Classe contenente utilities richiamate frequentemente nelle simulazioni.
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 *
 */
public class Utility {
	/**
	 * Calcola la media campionaria di un array di double.
	 * 
	 * @param values array di double di cui si vuole calcolare la media campionaria
	 * @return media campionaria dei valori forniti
	 */
	public static double mediaCamp(double[] values){
		double tmp=0;
		for(int i=0; i<values.length;i++)
			tmp+=values[i];
		return tmp/values.length;
	}
	
	/**
	 * Calcola la varianza campionaria di un array di double.
	 * 
	 * @param values array di double di cui si vuole calcolare la varianza campionaria
	 * @return varianza campionaria dei valori forniti
	 */
	public static double varCamp(double[] values){
		double media = mediaCamp(values);
		double tmp=0;
		for(int i=0; i<values.length;i++)
			tmp+=Math.pow((values[i]-media),2);
		return tmp/(values.length - 1);
	}
	
	/**
	 * Calcola la semi ampiezza dell'intervallo di confidenza di una serie di valori, definito il livello di confidenza da adottare.
	 * 
	 * @param values array di double di cui si vuole calcolare la semi ampiezza dell'intervallo di confidenza
	 * @param levelOfConfidence livello di confidenza da adottare
	 * @return la semi ampiezza dell'intervallo di confidenza dei valori passati, sulla base del livello di confidenza scelto
	 */
	public static double semiAmpiezza(double[] values, double levelOfConfidence){
		TDistributionImpl td = new TDistributionImpl(values.length-1);
		double ts = 0;
		try{
			ts = td.inverseCumulativeProbability(levelOfConfidence);
		}catch (MathException e) {
			System.out.println("Errore calcolo semiampiezza");
		}
		double var = varCamp(values);
		return ts*Math.sqrt(var/values.length);
	}
	
	/**
	 * 
	 * Calcola l'indice di dispersione di una serie di valori.
	 * 
	 * @param values valori di cui si vuole avere l'indice di dispersione
	 * @return indice di dispersione calcolato
	 */
	public static double indiceDisp(double[] values){
		return varCamp(values)/mediaCamp(values);
	}
	
	/**
	 * 
	 * Crea un file CVS (comma separated values) con nome indicato. Il contenuto viene fornito tramite una matrice di double.
	 * 
	 * @param fileName nome del file da generare
	 * @param intestazione array di stringhe con l'intestazione di ogni colonna del file di uscita
	 * @param table matrice di double contenente i valori da inserire nel file
	 */
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
