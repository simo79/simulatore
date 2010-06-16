package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import model.enumerators.CaseClasses;
import model.enumerators.CostraintValue;
import model.enumerators.DistributionType;
import model.enumerators.GuiPerspective;

import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;

import view.listeners.JBstartListener;
import view.listeners.JCBdistrSelectorListener;
import view.listeners.JFwindowListener;
import view.listeners.JMIesciListener;
import view.listeners.JRBMIesercitazioneListener;
import view.listeners.JRBvincolaListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class InputForm {
	private GuiPerspective perspective;  
	private CostraintValue costraintValue; 
	private LogForm logForm;
	
	private JFrame jFmainFrame = null;  
	private JPanel jCPmainPanel = null;
	
	private JComboBox jCBdistrSelector = null;
	private JComboBox jCBcase = null;
	
	private JLabel jLfrequenza = null;  
	private JLabel jLalpha = null; 
	private JLabel jLrapporto = null;  
	private JLabel jLq01 = null;
	private JLabel jLq10 = null;
	private JLabel jLN = null;
	private JLabel jLnumSim = null;
	private JLabel jLT = null;
	private JLabel jLvalues = null;
	private JLabel jLconfidenceLevel = null;
	private JLabel jLrange = null;
	private JLabel jLtrattino = null;
	private JLabel jLpasso = null;
	private JLabel jLvalVicolato = null;
	private JLabel jLrho = null;
	
	private JTextField jTFfrequenza = null;
	private JTextField jTFalpha = null;
	private JTextField jTFrapp = null;
	private JTextField jTFg10 = null;
	private JTextField jTFg01 = null;
	private JTextField jTFN = null;
	private JTextField jTFnumSim = null;
	private JTextField jTFT = null;
	private JTextField jTFconfidenceLevel = null;
	private JTextField jTFminRange = null;
	private JTextField jTFmaxRange = null;
	private JTextField jTFpasso = null;
	private JTextField jTFvaloreVicolato = null;
	private JTextField jTFrho = null;
	private JTextField jTFvalues = null;
	
	private JButton jBstart = null;
	
	private JPanel jPdistribution = null;
	private JPanel jPsimulation = null;
	private JPanel jPparSimVariabili = null;
	private JPanel jPservizio = null;
	private JPanel jPhide = null;
	
	//Menu
	private JMenuBar jMBmainMenu = null;
	private JMenu jMfile = null;
	private ButtonGroup groupRBmenu;  
	private JRadioButtonMenuItem jRBMIesercitazione1 = null;
	private JRadioButtonMenuItem jRBMIesercitazione2 = null;
	private JRadioButtonMenuItem jRBMIesercitazione3 = null;
	private JRadioButtonMenuItem jRBMIesercitazione4 = null;
	private JRadioButtonMenuItem jRBMIesercitazione5 = null;
	private JMenuItem jMIesci = null;
	
	
	private ButtonGroup groupRBvincola;
	private JRadioButton jRBvincolaN = null;
	private JRadioButton jRBvincolaConfidenza = null;
	

	
	public InputForm(){
		setPerspective(GuiPerspective.Inizializza);
		getJFmainFrame().setVisible(true);
		getJFmainFrame().setLocation(300, 300);
		logForm = new LogForm();
	}
	/**
	 * This method initializes jFmainFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFmainFrame() {
		if (jFmainFrame == null) {
			jFmainFrame = new JFrame();
			jFmainFrame.setSize(new Dimension(270, 690));
			jFmainFrame.setTitle("Simulatore");
			jFmainFrame.setResizable(false);
			jFmainFrame.setJMenuBar(getJJMBmainMenu());
			jFmainFrame.setContentPane(getJCPmainPanel());
			jFmainFrame.addWindowListener(new JFwindowListener());;
		}
		return jFmainFrame;
	}
	
	//[start] ComboBox
	/**
	 * This method initializes jCBdistrSelector	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getJCBdistrSelector() {
		if (jCBdistrSelector == null) {
			jCBdistrSelector = new JComboBox(DistributionType.values());
			jCBdistrSelector.setBounds(new Rectangle(10, 20, 220, 23));
			jCBdistrSelector.addActionListener(new JCBdistrSelectorListener(this));
			jCBdistrSelector.removeItem(DistributionType.SPP);
		}
		return jCBdistrSelector;
	}
	/**
	 * This method initializes jCBcase	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getJCBcase() {
		if (jCBcase == null) {
			jCBcase = new JComboBox(CaseClasses.values());
			jCBcase.setBounds(new Rectangle(15, 410, 230, 23));
		}
		return jCBcase;
	}
	/**
	 * This method initializes jBstart	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBstart() {
		if (jBstart == null) {
			jBstart = new JButton();
			jBstart.setText("Start");
			jBstart.setBounds(new Rectangle(15, 6, 240, 26));
			jBstart.addActionListener(new JBstartListener(this));
		}
		return jBstart;
	}
	//[end]
	
	//[start] Label
	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLfrequenza() {
		if (jLfrequenza == null) {
			jLfrequenza = new JLabel();
			jLfrequenza.setText("lambda:");
			jLfrequenza.setBounds(new Rectangle(12, 50, 130, 20));
		}
		return jLfrequenza;
	}
	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLalpha() {
		if (jLalpha == null) {
			jLalpha = new JLabel();
			jLalpha.setText("alpha:");
			jLalpha.setBounds(new Rectangle(12, 75, 130, 20));
			jLalpha.setEnabled(false);
		}
		return jLalpha;
	}
	/**
	 * This method initializes jLabel2	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLrapporto() {
		if (jLrapporto == null) {
			jLrapporto = new JLabel();
			jLrapporto.setText("rapporto:");
			jLrapporto.setBounds(new Rectangle(12, 100, 130, 20));
			jLrapporto.setEnabled(false);
		}
		return jLrapporto;
	}
	/**
	 * This method initializes jLabel3	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLq01() {
		if (jLq01 == null) {
			jLq01 = new JLabel();
			jLq01.setText("q01:");
			jLq01.setBounds(new Rectangle(12, 125, 130, 20));
			jLq01.setEnabled(false);
		}
		return jLq01;
	}
	/**
	 * This method initializes jLabel4	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLq10() {
		if (jLq10 == null) {
			jLq10 = new JLabel();
			jLq10.setText("q10:");
			jLq10.setBounds(new Rectangle(12, 150, 130, 20));
			jLq10.setEnabled(false);
		}
		return jLq10;
	}
	
	private JLabel getJLT(){
		if (jLT == null) {
			jLT = new JLabel();
			jLT.setBounds(new Rectangle(10, 95, 130, 20));
			jLT.setText("T:");
			jLT.setEnabled(false);
		}
		return jLT;
	}
	
	private JLabel getjLnumSim(){
		if(jLnumSim==null){
			jLnumSim = new JLabel();
			jLnumSim.setText("numero simulazioni:");
			jLnumSim.setBounds(new Rectangle(10, 20, 130, 20));
		}
		return jLnumSim;
	}
	/**
	 * This method initializes jLconfidenceLevel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLconfidenceLevel() {
		if (jLconfidenceLevel == null) {
			jLconfidenceLevel = new JLabel();
			jLconfidenceLevel.setText("livello confidenza:");
			jLconfidenceLevel.setBounds(new Rectangle(10, 70, 130, 20));
		}
		return jLconfidenceLevel;
	}
	/**
	 * This method initializes jLN	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLN(){
		if(jLN == null){
			jLN = new JLabel();
			jLN.setText("N:");
			jLN.setBounds(new Rectangle(10, 45, 130, 20));
		}
		return jLN;
	}
	/**
	 * This method initializes jLvalues	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLvalues() {
		if (jLvalues == null) {
			jLvalues = new JLabel();
			jLvalues.setText("valori in (0,1):");
			jLvalues.setBounds(new Rectangle(10, 120, 130, 20));
			jLvalues.setEnabled(false);
		}
		return jLvalues;
	}
	private JLabel getJLtrattino(){
		if(jLtrattino==null){
			jLtrattino = new JLabel();
			jLtrattino.setBounds(new Rectangle(190, 68, 9, 10));
			jLtrattino.setText("-");
		}
		return jLtrattino;
	}
	private JLabel getJLrange(){
		if(jLrange==null){
			jLrange = new JLabel();
			jLrange.setBounds(new Rectangle(10, 65, 120, 20));
			jLrange.setText("range confidenza:");
		}
		return jLrange;
	}
	private JLabel getJLpasso(){
		if(jLpasso==null){
			jLpasso = new JLabel();
			jLpasso.setBounds(new Rectangle(10, 90, 120, 20));
			jLpasso.setText("passo:");
		}
		return jLpasso;
	}
	private JLabel getJLvalVicolato(){
		if(jLvalVicolato==null){
			jLvalVicolato = new JLabel();
			jLvalVicolato.setBounds(new Rectangle(10, 115, 120, 20));
			jLvalVicolato.setText("N:");
		}
		return jLvalVicolato;
	}
	/**
	 * This method initializes jLmu	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLrho() {
		if (jLrho == null) {
			jLrho = new JLabel();
			jLrho.setText("rho:");
			jLrho.setBounds(new Rectangle(12, 20, 130, 20));
		}
		return jLrho;
	}
	//[end]
	
	//[start] TextField
	/**
	 * This method initializes jTFlambdaMed	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTFfrequenza() {
		if (jTFfrequenza == null) {
			jTFfrequenza = new JTextField();
			jTFfrequenza.setBounds(new Rectangle(150, 50, 100, 20));
			jTFfrequenza.setEnabled(true);
		}
		return jTFfrequenza;
	}
	/**
	 * This method initializes jTFalpha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTFalpha() {
		if (jTFalpha == null) {
			jTFalpha = new JTextField();
			jTFalpha.setBounds(new Rectangle(150, 75, 100, 20));
			jTFalpha.setEnabled(false);
		}
		return jTFalpha;
	}
	/**
	 * This method initializes jTFrapp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTFrapp() {
		if (jTFrapp == null) {
			jTFrapp = new JTextField();
			jTFrapp.setBounds(new Rectangle(150, 100, 100, 20));
			jTFrapp.setEnabled(false);
		}
		return jTFrapp;
	}
	/**
	 * This method initializes jTFg10	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTFq10() {
		if (jTFg10 == null) {
			jTFg10 = new JTextField();
			jTFg10.setBounds(new Rectangle(150, 150, 100, 20));
			jTFg10.setEnabled(false);		
		}
		return jTFg10;
	}
	/**
	 * This method initializes jTFg01	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTFq01() {
		if (jTFg01 == null) {
			jTFg01 = new JTextField();
			jTFg01.setBounds(new Rectangle(150, 125, 100, 20));
			jTFg01.setEnabled(false);
		}
		return jTFg01;
	}
	/**
	 * This method initializes jTFN	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFN() {
		if (jTFN == null) {
			jTFN = new JTextField();
			jTFN.setBounds(new Rectangle(150, 45, 100, 20));
		}
		return jTFN;
	}
	/**
	 * This method initializes jTFnumSim	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFnumSim() {
		if (jTFnumSim == null) {
			jTFnumSim = new JTextField();
			jTFnumSim.setBounds(new Rectangle(150, 20, 100, 20));
		}
		return jTFnumSim;
	}
	/**
	 * This method initializes jTFT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFT() {
		if (jTFT == null) {
			jTFT = new JTextField();
			jTFT.setBounds(new Rectangle(150, 95, 100, 20));
			jTFT.setEnabled(false);
		}
		return jTFT;
	}
	/**
	 * This method initializes jTFconfidenceLevel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFconfidenceLevel() {
		if (jTFconfidenceLevel == null) {
			jTFconfidenceLevel = new JTextField();
			jTFconfidenceLevel.setBounds(new Rectangle(150, 70, 100, 20));
		}
		return jTFconfidenceLevel;
	}
	/**
	 * This method initializes jTFminRange	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFminRange() {
		if (jTFminRange == null) {
			jTFminRange = new JTextField();
			jTFminRange.setBounds(new Rectangle(140, 65, 50, 20));
		}
		return jTFminRange;
	}
	/**
	 * This method initializes jTFmaxRange	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFmaxRange() {
		if (jTFmaxRange == null) {
			jTFmaxRange = new JTextField();
			jTFmaxRange.setBounds(new Rectangle(200, 65, 50, 20));
		}
		return jTFmaxRange;
	}
	/**
	 * This method initializes jTFpasso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFpasso() {
		if (jTFpasso == null) {
			jTFpasso = new JTextField();
			jTFpasso.setBounds(new Rectangle(140, 90, 50, 20));
		}
		return jTFpasso;
	}
	/**
	 * This method initializes jTFvaloreVicolato	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFvaloreVicolato() {
		if (jTFvaloreVicolato == null) {
			jTFvaloreVicolato = new JTextField();
			jTFvaloreVicolato.setBounds(new Rectangle(140, 115, 50, 20));
		}
		return jTFvaloreVicolato;
	}
	/**
	 * This method initializes jTFmu	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFrho() {
		if (jTFrho == null) {
			jTFrho = new JTextField();
			jTFrho.setBounds(new Rectangle(150, 20, 100, 20));
		}
		return jTFrho;
	}
	/**
	 * This method initializes jTFvalues	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTFvalues() {
		if (jTFvalues == null) {
			jTFvalues = new JTextField();
			jTFvalues.setBounds(new Rectangle(150, 120, 100, 20));
			jTFvalues.setEnabled(false);
		}
		return jTFvalues;
	}
	//[end]
	
	//[start] Pannelli
	/**
	 * This method initializes jCPmainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJCPmainPanel() {
		if (jCPmainPanel == null) {
			jCPmainPanel = new JPanel();
			jCPmainPanel.setLayout(null);
			jCPmainPanel.add(getJPparSimVariabili(), null);
			jCPmainPanel.add(getJPhide(), null);
			jCPmainPanel.add(getJBstart(), null);
		}
		return jCPmainPanel;
	}
	/**
	 * This method initializes jPdistribution	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPdistribution() {
		if (jPdistribution == null) {
			jPdistribution = new JPanel();
			jPdistribution.setLayout(null);
			jPdistribution.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Parametri Distribuzione", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			jPdistribution.setBounds(new Rectangle(0, 155, 260, 190));
			jPdistribution.add(getJCBdistrSelector(), null);
			jPdistribution.add(getJLfrequenza(), null);
			jPdistribution.add(getJLalpha(), null);
			jPdistribution.add(getJTFfrequenza(), null);
			jPdistribution.add(getJTFalpha(), null);
			jPdistribution.add(getJTFrapp(), null);
			jPdistribution.add(getJTFq01(), null);
			jPdistribution.add(getJLrapporto(), null);
			jPdistribution.add(getJLq01(), null);
			jPdistribution.add(getJLq10(), null);
			jPdistribution.add(getJTFq10(), null);
		}
		return jPdistribution;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPsimulation() {
		if (jPsimulation == null) {
			jPsimulation = new JPanel();
			jPsimulation.setLayout(null);
			jPsimulation.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Parametri Simulazione Costanti", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			//Aggiunta Elementi
			jPsimulation.setBounds(new Rectangle(0, 0, 260, 150));
			jPsimulation.add(getJLT(), null);
			jPsimulation.add(getJTFN(), null);
			jPsimulation.add(getJTFT(), null);
			jPsimulation.add(getJLconfidenceLevel(), null);
			jPsimulation.add(getJTFconfidenceLevel(), null);
			jPsimulation.add(getJLvalues(), null);
			jPsimulation.add(getJTFvalues(), null);
			jPsimulation.add(getJLN(), null);
			jPsimulation.add(getJTFnumSim(), null);
			jPsimulation.add(getjLnumSim(), null);
			jPsimulation.add(jLN, null);
		}
		return jPsimulation;
	}
	/**
	 * This method initializes jPparSimVariabili	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPparSimVariabili() {
		if (jPparSimVariabili == null) {
			groupRBvincola=new ButtonGroup();
			jPparSimVariabili = new JPanel();
			jPparSimVariabili.setLayout(null);
			jPparSimVariabili.setBounds(new Rectangle(5, 44, 260, 147));
			jPparSimVariabili.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Parametri Simulazione Variabili", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			//Aggiunta Elementi
			jPparSimVariabili.add(getJRBvincolaN(), null);
			jPparSimVariabili.add(getJRBvincolaConfidenza(), null);
			jPparSimVariabili.add(getJLrange(), null);
			jPparSimVariabili.add(getJTFminRange(), null);
			jPparSimVariabili.add(getJTFmaxRange(), null);
			jPparSimVariabili.add(getJLtrattino(), null);
			jPparSimVariabili.add(getJLpasso(), null);
			jPparSimVariabili.add(getJTFpasso(), null);
			jPparSimVariabili.add(getJLvalVicolato(), null);
			jPparSimVariabili.add(getJTFvaloreVicolato(), null);
		}
		return jPparSimVariabili;
	}
	/**
	 * This method initializes jPhide	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPhide() {
		if (jPhide == null) {
			jPhide = new JPanel();
			jPhide.setLayout(null);
			jPhide.setBounds(new Rectangle(5, 194, 260, 441));
			jPhide.add(getJPservizio(), null);
			jPhide.add(getJPdistribution(), null);
			jPhide.add(getJPsimulation(), null);
			jPhide.add(getJCBcase(), null);
		}
		return jPhide;
	}
	/**
	 * This method initializes jPservizio	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPservizio() {
		if (jPservizio == null) {
			jPservizio = new JPanel();
			jPservizio.setLayout(null);
			jPservizio.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Utilizzazione", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			jPservizio.setBounds(new Rectangle(0, 350, 260, 50));
			jPservizio.add(getJLrho(), null);
			jPservizio.add(getJTFrho(), null);
		}
		return jPservizio;
	}
	//[end]
	
	//[start] Elementi MenuBar
	/**
	 * This method initializes jJMBmainMenu	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMBmainMenu() {
		if (jMBmainMenu == null) {
			groupRBmenu = new ButtonGroup();
			jMBmainMenu = new JMenuBar();
			jMBmainMenu.add(getJMenu());
		}
		return jMBmainMenu;
	}
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMfile == null) {
			jMfile = new JMenu("File");
			jMfile.add(getJRBMIesercitazione1());
			jMfile.add(getJRBMIesercitazione2());
			jMfile.add(getJRBMIesercitazione3());
			jMfile.add(getJRBMIesercitazione4());
			jMfile.add(getJRBMIesercitazione5());
			jMfile.addSeparator();
			jMfile.add(getJMIesci());

		}
		return jMfile;
	}
	/**
	 * This method initializes jRadioButtonMenuItem	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRBMIesercitazione1() {
		if (jRBMIesercitazione1 == null) {
			jRBMIesercitazione1 = new JRadioButtonMenuItem(GuiPerspective.Esercitazione1.toString());
			jRBMIesercitazione1.setSelected(true);
			groupRBmenu.add(jRBMIesercitazione1);
			jRBMIesercitazione1.addActionListener(new JRBMIesercitazioneListener(this));
		}
		return jRBMIesercitazione1;
	}
	
	/**
	 * This method initializes jRBMIesercitazione2	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRBMIesercitazione2() {
		if (jRBMIesercitazione2 == null) {
			jRBMIesercitazione2 = new JRadioButtonMenuItem(GuiPerspective.Esercitazione2.toString());
			groupRBmenu.add(jRBMIesercitazione2);
			jRBMIesercitazione2.addActionListener(new JRBMIesercitazioneListener(this));
		}
		return jRBMIesercitazione2;
	}
	/**
	 * This method initializes jRBMIesercitazione3	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRBMIesercitazione3() {
		if (jRBMIesercitazione3 == null) {
			jRBMIesercitazione3 = new JRadioButtonMenuItem(GuiPerspective.Esercitazione3.toString());
			groupRBmenu.add(jRBMIesercitazione3);
			jRBMIesercitazione3.addActionListener(new JRBMIesercitazioneListener(this));
		}
		return jRBMIesercitazione3;
	}
	/**
	 * This method initializes JRBMIesercitazione4	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRBMIesercitazione4() {
		if (jRBMIesercitazione4 == null) {
			jRBMIesercitazione4 = new JRadioButtonMenuItem(GuiPerspective.Esercitazione4.toString());
			groupRBmenu.add(jRBMIesercitazione4);
			jRBMIesercitazione4.addActionListener(new JRBMIesercitazioneListener(this));
		}
		return jRBMIesercitazione4;
	}
	/**
	 * This method initializes jRBMIesercitazione5	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRBMIesercitazione5() {
		if (jRBMIesercitazione5 == null) {
			jRBMIesercitazione5 = new JRadioButtonMenuItem(GuiPerspective.Esercitazione5.toString());
			groupRBmenu.add(jRBMIesercitazione5);
			jRBMIesercitazione5.addActionListener(new JRBMIesercitazioneListener(this));
		}
		return jRBMIesercitazione5;
	}
	/**
	 * This method initializes jMIesci	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMIesci() {
		if (jMIesci == null) {
			jMIesci = new JMenuItem("Esci");
			jMIesci.addActionListener(new JMIesciListener());
		}
		return jMIesci;
	}
	//[end]
	
	//[start] Gestione Perspective
	public GuiPerspective getSelectedPerspective(){
		return perspective;
	}
	public void setPerspective(GuiPerspective persp){
		switch(persp){
			case Esercitazione1:
				if(perspective!=GuiPerspective.Esercitazione1){
					logForm.reset();
					perspective=GuiPerspective.Esercitazione1;
					jCBdistrSelector.removeItem(DistributionType.SPP);			
					jLT.setEnabled(false);
					jTFT.setEnabled(false);
					//Impostazioni Visualizzazione Form/Pannelli
					jFmainFrame.setSize(new Dimension(getJFmainFrame().getSize().width,245));
					jPparSimVariabili.setVisible(true);
					jPhide.setVisible(false);
				}
				break;
			case Esercitazione2:
				if(perspective!= GuiPerspective.Esercitazione2){
					logForm.reset();
					perspective = GuiPerspective.Esercitazione2;
					//Impostazione distribuzione
					jCBdistrSelector.setEditable(true);
					jCBdistrSelector.addItem(DistributionType.SPP);
					//Varie
					jLT.setEnabled(true);
					jTFT.setEnabled(true);
					jLvalues.setEnabled(false);
					jTFvalues.setEnabled(false);
					jCBdistrSelector.setSelectedItem(DistributionType.Exponential);
					jLfrequenza.setText("lambda:");
					jCBdistrSelector.setEnabled(true);
					jCBdistrSelector.setEditable(false);
					jPdistribution.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Distribuzione Traffico", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
					//Impostazioni Visualizzazione Form/Pannelli
					jFmainFrame.setSize(new Dimension(getJFmainFrame().getSize().width, 440));
					jPparSimVariabili.setVisible(false);
					jPhide.setVisible(true);
					
				}
				break;
			case Esercitazione3:
				if(perspective != GuiPerspective.Esercitazione3){
					logForm.reset();
					perspective = GuiPerspective.Esercitazione3;
					//Impostazione distribuzione
					jCBdistrSelector.setEditable(true);
					jCBdistrSelector.removeItem(DistributionType.SPP);
					//Disabilito il campo intervallo T
					jLT.setEnabled(false);
					jTFT.setEnabled(false);
					//Abilito campo values ed imposto etichetta
					jLvalues.setEnabled(true);
					jLvalues.setText("valori di rho in (0,1):");
					jTFvalues.setEnabled(true);
					
					jLfrequenza.setText("mu:");
					jCBdistrSelector.setEnabled(true);
					jCBdistrSelector.setEditable(false);
					jCBdistrSelector.setSelectedItem(DistributionType.Exponential);
					jPdistribution.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Distribuzione del Servizio", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
					//Impostazioni Visualizzazione Form/Pannelli
					jFmainFrame.setSize(new Dimension(getJFmainFrame().getSize().width,440));
					jPparSimVariabili.setVisible(false);
					jPhide.setVisible(true);
				}
				break;
			case Esercitazione4:
				if(perspective != GuiPerspective.Esercitazione4){
					logForm.reset();
					logEse4Legenda();
					logForm.refresh();
					perspective = GuiPerspective.Esercitazione4;
					//Impostazioni distribuzione
					jCBdistrSelector.setSelectedItem(DistributionType.Exponential);
					jCBdistrSelector.setEnabled(false);
					//Impostazione Caso
					jCBcase.setSelectedItem(CaseClasses.Caso1);
					//Varie
					jLfrequenza.setText("mu:");
					jLvalues.setEnabled(true);
					jLvalues.setText("valori di x in (0,1):");
					jTFvalues.setEnabled(true);
					jPdistribution.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Distribuzione del Servizio", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
					//Impostazioni Visualizzazione Form/Pannelli
					jFmainFrame.setSize(new Dimension(getJFmainFrame().getSize().width,535));
					jPparSimVariabili.setVisible(false);
					jPhide.setVisible(true);
				}
				break;
			case Esercitazione5:
				if(perspective != GuiPerspective.Esercitazione5){
					logForm.reset();
					perspective = GuiPerspective.Esercitazione5;
					//Impostazioni distribuzione
					jCBdistrSelector.setSelectedItem(DistributionType.Exponential);
					jCBdistrSelector.setEnabled(false);
					jLfrequenza.setText("mu:");
					//Disabilito il campo intervallo T
					jLT.setEnabled(false);
					jTFT.setEnabled(false);
					//Disabilito il campo values
					jLvalues.setEnabled(true);
					jTFvalues.setEnabled(true);
					jLvalues.setText("Classi");
					//Impostazione titolo pannello distribuzione
					jPdistribution.setBorder(new TitledBorder(new LineBorder(Color.black, 1, false),"Distribuzione Degli Arrivi", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
					//Impostazioni Visualizzazione Form/Pannelli
					jFmainFrame.setSize(new Dimension(getJFmainFrame().getSize().width,495));
					jPparSimVariabili.setVisible(false);
					jPhide.setVisible(true);
				}
				break;
			case Inizializza:
				perspective=GuiPerspective.Esercitazione1;
				costraintValue = CostraintValue.VincolaN;
				//Impostazioni Visualizzazione Form/Pannelli
				getJFmainFrame().setSize(new Dimension(getJFmainFrame().getSize().width,250));
				getJPparSimVariabili().setVisible(true);
				getJPhide().setVisible(false);
				jPhide.setLocation(jPparSimVariabili.getLocation().x,jPparSimVariabili.getLocation().y);
				break;
		}	
	}
	/**
	 * 
	 * @param distr
	 */
	public void setDistributionPerspective(DistributionType distr){
		switch(distr){
		case Deterministic:
			getJLfrequenza().setEnabled(true);
			getJTFfrequenza().setEnabled(true);
			getJLalpha().setEnabled(false);
			getJTFalpha().setEnabled(false);
			getJLrapporto().setEnabled(false);
			getJTFrapp().setEnabled(false);
			getJLq01().setEnabled(false);
			getJTFq01().setEnabled(false);
			getJLq10().setEnabled(false);
			getJTFq10().setEnabled(false);
			break;
		case Exponential:
			getJLrho().setEnabled(true);
			getJTFfrequenza().setEnabled(true);
			getJLalpha().setEnabled(false);
			getJTFalpha().setEnabled(false);
			getJLrapporto().setEnabled(false);
			getJTFrapp().setEnabled(false);
			getJLq01().setEnabled(false);
			getJTFq01().setEnabled(false);
			getJLq10().setEnabled(false);
			getJTFq10().setEnabled(false);
			break;
		case Pareto:
			getJLfrequenza().setEnabled(true);
			getJTFfrequenza().setEnabled(true);
			getJLalpha().setEnabled(true);
			getJTFalpha().setEnabled(true);
			getJLrapporto().setEnabled(false);
			getJTFrapp().setEnabled(false);
			getJLq01().setEnabled(false);
			getJTFq01().setEnabled(false);
			getJLq10().setEnabled(false);
			getJTFq10().setEnabled(false);
			break;
		case SPP:
			getJLfrequenza().setEnabled(true);
			getJTFfrequenza().setEnabled(true);
			getJLalpha().setEnabled(false);
			getJTFalpha().setEnabled(false);
			getJLrapporto().setEnabled(true);
			getJTFrapp().setEnabled(true);
			getJLq01().setEnabled(true);
			getJTFq01().setEnabled(true);
			getJLq10().setEnabled(true);
			getJTFq10().setEnabled(true);
			break;
		}
	}
	public CostraintValue getCostraintValue(){
		return costraintValue;
	}
	public void switchPerspectiveSimulazioneVariabile(){
		if(jRBvincolaN.isSelected()){
			//Imposto come parametro variabile la confidenza
			jLrange.setText("range confidenza:");
			jLvalVicolato.setText("N:");
			costraintValue = CostraintValue.VincolaN;
		}else{
			//Imposto come parametro variabile N
			jLrange.setText("range N:");
			jLvalVicolato.setText("confidenza:");
			costraintValue=CostraintValue.VincolaConfidenza;
		}
		
	}
	//[end]
		
	//[start] Radio Button
	/**
	 * This method initializes jRBvincolaN	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRBvincolaN() {
		if (jRBvincolaN == null) {
			jRBvincolaN = new JRadioButton();
			jRBvincolaN.setBounds(new Rectangle(5, 20, 150, 20));
			jRBvincolaN.setText(CostraintValue.VincolaN.toString());
			groupRBvincola.add(jRBvincolaN);
			jRBvincolaN.setSelected(true);
			jRBvincolaN.addActionListener(new JRBvincolaListener(this));
		}
		return jRBvincolaN;
	}
	/**
	 * This method initializes jRBvincolaConfidenza	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRBvincolaConfidenza() {
		if (jRBvincolaConfidenza == null) {
			jRBvincolaConfidenza = new JRadioButton();
			jRBvincolaConfidenza.setBounds(new Rectangle(5, 40, 150, 20));
			jRBvincolaConfidenza.setText(CostraintValue.VincolaConfidenza.toString());
			groupRBvincola.add(jRBvincolaConfidenza);
			jRBvincolaConfidenza.addActionListener(new JRBvincolaListener(this));
		}
		return jRBvincolaConfidenza;
	}
	//[end]
	
	//[start] Funzioni
	public LogForm getLog(){
		return logForm;
	}
	/**
	 * Resituisce un vettore con i parametri immessi da form della distribuzione selezionata
	 * Deterministic = {frequenza di servizio}
	 * Exponential = {frequenza di servizio}
	 * Pareto = {frequenza di servizio, alpha}
	 * SPP = {frequenza di servizio, rapporto, q01, q10}
	 * 
	 * @return vettore di parametri
	 */
	public double[] getDistributionParameters(){
		double[] param=null;
		switch ((DistributionType)getJCBdistrSelector().getSelectedItem()){
			case Deterministic:
				param = new double[1];
				param[0]=Double.parseDouble(getJTFfrequenza().getText());
				break;
			case Exponential:
				param = new double[1];
				param[0]=Double.parseDouble(getJTFfrequenza().getText());
				break;
			case Pareto:
				param = new double[2];
				param[0]=Double.parseDouble(getJTFfrequenza().getText());
				param[1]=Double.parseDouble(getJTFalpha().getText());
				break;
			case SPP:
				param = new double[4];
				param[0]= Double.parseDouble(getJTFfrequenza().getText());
				param[1]= Double.parseDouble(getJTFrapp().getText());
				param[2]= Double.parseDouble(getJTFq01().getText());
				param[3]= Double.parseDouble(getJTFq10().getText());
				break;
		}
		return param;
	}
	
	public void logEse4Legenda(){
		logForm.log("[caso1] rho1 = x rho, rho2 = (1-x) rho");
		logForm.log("[caso2] rho1 = rho2 = x/2 rho, rho3 = (1-x) rho");
		logForm.log("[caso3] rho1 = x/10 rho, rho2 = 9/10 x rho, rho3 = (1-x) rho");
		logForm.log("[caso4] rho1 = x rho, rho2 = rho3 = (1-x)/2 rho");
		logForm.log(" ");
	}
	//[end]
	
	
	
	
	
	
	
	
} 
