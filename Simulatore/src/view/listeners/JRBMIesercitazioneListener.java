package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InputForm;

import model.enumerators.GuiPerspective;

public class JRBMIesercitazioneListener implements ActionListener {
	private InputForm form;
	
	public JRBMIesercitazioneListener(InputForm frm){
		super();
		form=frm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().compareTo(GuiPerspective.Esercitazione1.toString())==0)
			form.setPerspective(GuiPerspective.Esercitazione1);
		if(e.getActionCommand().compareTo(GuiPerspective.Esercitazione2.toString())==0)
			form.setPerspective(GuiPerspective.Esercitazione2);
		if(e.getActionCommand().compareTo(GuiPerspective.Esercitazione3.toString())==0)
			form.setPerspective(GuiPerspective.Esercitazione3);
		if(e.getActionCommand().compareTo(GuiPerspective.Esercitazione4.toString())==0)
			form.setPerspective(GuiPerspective.Esercitazione4);
		if(e.getActionCommand().compareTo(GuiPerspective.Esercitazione5.toString())==0)
			form.setPerspective(GuiPerspective.Esercitazione5);
		
	}
	

}
