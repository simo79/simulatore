package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InputForm;

public class JRBvincolaListener implements ActionListener{
	private InputForm frm;
	public JRBvincolaListener(InputForm frm){
		super();
		this.frm=frm;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frm.switchPerspectiveSimulazioneVariabile();
		
	}

}
