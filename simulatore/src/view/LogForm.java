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

	private JFrame jFlogForm = null;  //  @jve:decl-index=0:visual-constraint="157,21"
	private JPanel jCPmain = null;
	private JScrollPane jSPlog = null;  //  @jve:decl-index=0:visual-constraint="766,65"
	private JList jLlog = null;
	
	private DefaultListModel model;
	private JProgressBar jPBstatus = null;
	
	public LogForm(){
		model = new DefaultListModel();
		getJFlogForm().setVisible(true);
		jFlogForm.setLocation(600, 300);
	}
	/**
	 * This method initializes jFlogForm	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFlogForm() {
		if (jFlogForm == null) {
			jFlogForm = new JFrame();
			jFlogForm.setSize(new Dimension(356, 443));
			jFlogForm.setTitle("Log");
			jFlogForm.setContentPane(getJCPmain());
			jFlogForm.addWindowListener(new JFwindowListener());
			
		}
		return jFlogForm;
	}

	/**
	 * This method initializes jCPmain	
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
	 * This method initializes jSPlog	
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
	 * This method initializes jLlog	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJLlog() {
		if (jLlog == null) {
			jLlog = new JList();
			jLlog.setModel(model);
		}
		return jLlog;
	}
	
	public void log(String entry){
		model.addElement(entry);
	}
	
	public void resetLog(){
		model.clear();
	}
	/**
	 * This method initializes jPBstatus	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	public JProgressBar getJPBstatus() {
		if (jPBstatus == null) {
			jPBstatus = new JProgressBar();
			jPBstatus.setMaximum(200);
			jPBstatus.setName("jPBstatus");
		}
		return jPBstatus;
	}
	
	public void refresh(){

		getJLlog().repaint();
		getJLlog().setModel(model);
	}
}

