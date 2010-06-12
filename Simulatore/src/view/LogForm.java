package view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JList;

import view.listeners.JFwindowListener;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;

public class LogForm {

	private JFrame jFlogForm = null;  //  @jve:decl-index=0:visual-constraint="10,98"
	private JPanel jCPmain = null;
	private JScrollPane jSPlog = null;  
	private JList jLlog = null;
	
	private DefaultListModel model;
	private JProgressBar jPBstatus = null;
	
	public LogForm(){
		model = new DefaultListModel();
		getJFlogForm().setVisible(true);
		jFlogForm.setLocation(600, 300);
	}
	
	//[start] Elementi grafici
	/**
	 * Metodo che inizializza e restituisce la form principale 	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFlogForm() {
		if (jFlogForm == null) {
			jFlogForm = new JFrame();
			jFlogForm.setSize(new Dimension(595, 443));
			jFlogForm.setTitle("Log");
			jFlogForm.setContentPane(getJCPmain());
			jFlogForm.addWindowListener(new JFwindowListener());
			
		}
		return jFlogForm;
	}

	/**
	 * Metodo che inizializza e restituisce il ContenPane 	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJCPmain() {
		if (jCPmain == null) {
			jCPmain = new JPanel();
			jCPmain.setLayout(new BoxLayout(getJCPmain(), BoxLayout.Y_AXIS));
			jCPmain.add(getJPBstatus());
			jCPmain.add(getJSPlog());
		}
		return jCPmain;
	}

	/**
	 * Metodo che inizializza e restituisce lo scrollpane 	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJSPlog() {
		if (jSPlog == null) {
			jSPlog = new JScrollPane();
			jSPlog.setLayout(new ScrollPaneLayout());
			jSPlog.setViewportView(getJLlog());
		}
		return jSPlog;
	}

	/**
	 * Metodo che inizializza e restituisce la Jlist 	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJLlog() {
		if (jLlog == null) {
			jLlog = new JList();
		}
		return jLlog;
	}
	/**
	 * This method initializes jPBstatus	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJPBstatus() {
		if (jPBstatus == null) {
			jPBstatus = new JProgressBar();
		}
		return jPBstatus;
	}
	//[end]
	
	public void log(String entry){
		model.addElement(entry);
	}
	
	public void reset(){
		model = new DefaultListModel();
		jPBstatus.setValue(0);
	}
	
	public void refresh(){
		getJLlog().setModel(model);
	}
	
	
	
	
}

