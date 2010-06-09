package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import view.InputForm;

public class JBstartListener implements ActionListener {
	private InputForm form;
	
	public JBstartListener(InputForm form){
		super();
		this.form=form;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			new SimulatorThread(form).start();
	}

}
