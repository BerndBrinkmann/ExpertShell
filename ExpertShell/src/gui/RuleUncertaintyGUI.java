package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class RuleUncertaintyGUI extends Composite {
	Group grpUncertainty;
	Button radioNone,radioCF,radioBay;
	
	public RuleUncertaintyGUI(Composite parent) {
		
		super(parent,SWT.NONE);
		super.setLayout(new GridLayout(1,false));
		
		//SelectionAd
		
		grpUncertainty = new Group(this, SWT.NONE);
		grpUncertainty.setText("Uncertainty");
		grpUncertainty.setLayout(new GridLayout(1, false));
		grpUncertainty.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		radioNone = new Button(grpUncertainty, SWT.RADIO);
		radioNone.setText("None/Absolute");
		
	}
	
	
}
