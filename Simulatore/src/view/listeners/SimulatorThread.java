package view.listeners;

import control.SimulatorLauncher;
import view.InputForm;
import model.DistributionType;
import model.enumerators.CaseClasses;
import model.enumerators.GuiPerspective;

public class SimulatorThread extends Thread {
	private InputForm form;
	public SimulatorThread(InputForm form){
		this.form=form;
	}
	@Override
	public void run() {
		form.getLog().reset();
		if(form.getSelectedPerspective()==GuiPerspective.Esercitazione1){
			switch(form.getCostraintValue()){
				case VincolaConfidenza:
					//Devo variare N
					int minValueN = Integer.parseInt(form.getJTFminRange().getText());
					int maxValueN = Integer.parseInt(form.getJTFmaxRange().getText());
					int passoN = Integer.parseInt(form.getJTFpasso().getText());
					double confidenceLevel = Double.parseDouble(form.getJTFvaloreVicolato().getText());
					//Lancio la simulazione
					SimulatorLauncher.lauchSimulation1_1(minValueN, maxValueN, passoN, confidenceLevel, form.getLog());
					break;
				case VincolaN:
					//Devo variare la confidenza
					int N = Integer.parseInt(form.getJTFvaloreVicolato().getText());
					double minValueConfidenza = Double.parseDouble(form.getJTFminRange().getText());
					double maxValueConfidenza = Double.parseDouble(form.getJTFmaxRange().getText());
					double passoConfidenza = Double.parseDouble(form.getJTFpasso().getText());
					//Lancio la simulazione
					SimulatorLauncher.lauchSimulation1_2(N, minValueConfidenza, maxValueConfidenza, passoConfidenza, form.getLog());					
					break;
			}		
		}
		if(form.getSelectedPerspective()==GuiPerspective.Esercitazione2){
			//Recupero i parametri dalla form
			int N = Integer.parseInt(form.getJTFN().getText());
			int numSim = Integer.parseInt(form.getJTFnumSim().getText());
			double confidenceLevel = Double.parseDouble(form.getJTFconfidenceLevel().getText());
			double T = Double.parseDouble(form.getJTFT().getText());
			DistributionType type=(DistributionType)form.getJCBdistrSelector().getSelectedItem();
			double[] param = form.getDistributionParameters();
			//Lancio la simulazione
			SimulatorLauncher.lauchSimulation2(N,numSim,confidenceLevel,T,type,param,form.getLog());
		}
		if(form.getSelectedPerspective()==GuiPerspective.Esercitazione3){
			//Recupero i parametri dalla form
			int N = Integer.parseInt(form.getJTFN().getText());
			int nSim = Integer.parseInt(form.getJTFnumSim().getText());
			int values = Integer.parseInt(form.getJTFvalues().getText()); 
			double confidenceLevel = Double.parseDouble(form.getJTFconfidenceLevel().getText());
			double mu= Double.parseDouble(form.getJTFmu().getText());
			DistributionType type=(DistributionType)form.getJCBdistrSelector().getSelectedItem();
			double[] param = form.getDistributionParameters();
			//Lancio la simulazione
			SimulatorLauncher.lauchSimulation3(N,nSim,confidenceLevel,values,type,param,mu,form.getLog());
		}
		if(form.getSelectedPerspective()==GuiPerspective.Esercitazione4){
			form.logEse4Legenda();
			CaseClasses caseC=(CaseClasses)form.getJCBcase().getSelectedItem();
			//Recupero i parametri dalla form
			int N = Integer.parseInt(form.getJTFN().getText());
			int nSim = Integer.parseInt(form.getJTFnumSim().getText());
			int xValues = Integer.parseInt(form.getJTFvalues().getText());  
			double[] param = form.getDistributionParameters();
			double mu= Double.parseDouble(form.getJTFmu().getText());
			//Lancio la simulazione
			SimulatorLauncher.lauchSimulation4(N,nSim,caseC,xValues,param[0],mu,form.getLog());
		}
		form.getLog().refresh();
	}

}
