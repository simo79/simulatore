package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.InputForm;

import model.DistributionType;
 
public class JCBdistrSelectorListener implements ActionListener {
	private InputForm form;
	public JCBdistrSelectorListener(InputForm form){
		super();
		this.form=form;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DistributionType distr = (DistributionType)((JComboBox)e.getSource()).getSelectedItem();
		form.setDistributionPerspective(distr);
		
	}
}
