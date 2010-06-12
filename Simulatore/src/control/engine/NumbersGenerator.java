package control.engine;

import java.util.Random;

import org.apache.commons.math.random.MersenneTwister;

import view.LogForm;

import control.Utility;

/**
 * Classe utilizzata per la realizzazione della prima esercitazione per la
 * generazione di numeri pseudo random tra (0,1) e calcolo della loro media e
 * intervallo di confidenza
 * 
 * @author Matteo Desanti, Elia Maldini, Alessandro Montalti
 * 
 */
public class NumbersGenerator {
	private int N;
	private double minConfidence;
	private double maxConfidence;

	private int minN;
	private int maxN;
	private double levelOfConfidence;

	private int stepN;
	private int nSim = 50;

	private double stepConfidence;
	private LogForm logFrm;

	/**
	 * Crea un nuovo generatore per la simulazione al variare del livello di
	 * confidenza.
	 * 
	 * @param N
	 *            numero di valori da generare
	 * @param minConfidence
	 *            valore minimo del livello di confidenza da cui partire
	 * @param maxConfidence
	 *            valore massimo del livello di confidenza a cui arrivare
	 * @param stepConfidence
	 *            valore di incremento del livello di confidenza tra uno step di
	 *            simulazione e l'altro
	 * @param frm
	 *            riferimento alla finestra di log
	 */
	public NumbersGenerator(int N, double minConfidence, double maxConfidence,
			double stepConfidence, LogForm frm) {
		this.levelOfConfidence = 0.0;
		this.N = N;
		if (minConfidence >= 1)
			this.minConfidence = 0.75;
		else
			this.minConfidence = minConfidence;
		if (maxConfidence <= this.minConfidence)
			this.maxConfidence = 0.99;
		else
			this.maxConfidence = maxConfidence;
		if (stepConfidence < (this.maxConfidence - this.minConfidence))
			this.stepConfidence = stepConfidence;
		else
			this.stepConfidence = 0.25;
		this.nSim = (int) (Math.floor((this.maxConfidence - this.minConfidence)
				/ this.stepConfidence)) + 1;
		logFrm = frm;
	}

	/**
	 * Crea un nuovo generatore per la simulazione al variare del numero di
	 * valori generati
	 * 
	 * @param minN
	 *            valore minimo di N da cui parte la simulazione
	 * @param maxN
	 *            valore massimo di N a cui concludere la simulazione
	 * @param stepN
	 *            valore di incremento di N tra passi successivi di simulazione
	 * @param levelOfConfidence
	 *            livello di confidenza da utilizzare per il calcolo
	 *            dell'intervallo
	 * @param frm
	 *            riferimento alla finestra di log
	 */

	public NumbersGenerator(int minN, int maxN, int stepN,
			double levelOfConfidence, LogForm frm) {
		this.minN = minN;
		if (maxN > minN)
			this.maxN = maxN;
		else
			this.maxN = 10 * minN;
		if (stepN < (this.maxN - this.minN))
			this.stepN = stepN;
		else
			this.stepN = 2;
		this.nSim = ((this.maxN - this.minN) / this.stepN) + 1;
		this.levelOfConfidence = levelOfConfidence;
		logFrm = frm;
	}

	/**
	 * Lancia la simulazione con i parametri specificati
	 * 
	 * @return una matrici di nSim (=50) righe e 3 colonne formattate come
	 *         segue:
	 * 
	 *         caso 1 (variare del livello di confidenza) livello di confidenza
	 *         - media dei valori - semi ampiezza dell'intervallo
	 * 
	 *         caso 2 (variare del numero di valori generati) numero di valori
	 *         generati - media dei valori - semi ampiezza dell'intervallo
	 */
	public double[][] run() {
		Random rnd = new Random();
		double[][] fileContent = null;
		logFrm.getJPBstatus().setMaximum(nSim);
		if (levelOfConfidence == 0.0) {
			// Tengo costante N vario il livello di confidenza
			levelOfConfidence = minConfidence;
			double[] result = new double[N];
			fileContent = new double[nSim][4];
			int k = 0;
			while (k < nSim) {
				for (int i = 0; i < N; i++) {
					result[i] = rnd.nextDouble();
				}
				fileContent[k][0] = levelOfConfidence;
				double media = Utility.mediaCamp(result);
				double semiAmp = Utility.semiAmpiezza(result,levelOfConfidence);
				fileContent[k][1] = media;
				fileContent[k][2] = media-semiAmp;
				fileContent[k][3] = media+semiAmp;
				levelOfConfidence += stepConfidence;
				result = new double[N];
				k++;
				logFrm.getJPBstatus().setValue(k);
			}
		}

		if (N == 0) {
			// Tengo costante N vario il vivello di confidenza
			N = minN;
			double[] result = new double[N];
			fileContent = new double[nSim][4];
			int k = 0;
			while (k < nSim) {
				for (int i = 0; i < N; i++) {
					result[i] = rnd.nextDouble();
				}
				fileContent[k][0] = N;
				double media = Utility.mediaCamp(result);
				double semiAmp = Utility.semiAmpiezza(result,levelOfConfidence);
				fileContent[k][1] = media;
				fileContent[k][2] = media-semiAmp;
				fileContent[k][3] = media+semiAmp;
				N += stepN;
				result = new double[N];
				k++;
				logFrm.getJPBstatus().setValue(k);
			}
		}
		return fileContent;
	}
}
