package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import datatypes.KBSettings;
import datatypes.KnowledgeBase;

public class RuleUncertaintyGUI extends Composite {
	private Group grpUncertainty;
	private Button radioNone,radioCF,radioBay;
	private KBSettings.UncertaintyManagement selected;
	private KnowledgeBase KBase;
	
	public RuleUncertaintyGUI(Composite parent, KnowledgeBase kb) {
		
		super(parent,SWT.NONE);
		super.setLayout(new GridLayout(1,false));
		
		KBase = kb;
		
		SelectionAdapter selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonPress((Button) e.getSource(),true);
			}
		};
		
		grpUncertainty = new Group(this, SWT.NONE);
		grpUncertainty.setText("Uncertainty");
		grpUncertainty.setLayout(new GridLayout(1, false));
		grpUncertainty.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		radioNone = new Button(grpUncertainty, SWT.RADIO);
		radioNone.setText("None/Absolute");
		radioNone.addSelectionListener(selAdaptor);
		
		radioCF = new Button(grpUncertainty, SWT.RADIO);
		radioCF.setText("Certainty Factor");
		radioCF.addSelectionListener(selAdaptor);
		
		radioBay = new Button(grpUncertainty, SWT.RADIO);
		radioBay.setText("Bayesian");
		radioBay.addSelectionListener(selAdaptor);
		
		parent.getParent().layout(true,true);
		
		setFromKB();
		
	}
	
	public void changeKnowledgeBase(KnowledgeBase kb) {
		KBase = kb;
		setFromKB();				
	}
	
	private void buttonPress(Button sourceButton, boolean fromUserClick) {
		
		if (sourceButton == radioNone) {
			radioNone.setSelection(true);
			radioCF.setSelection(false);
			radioBay.setSelection(false);
			selected = KBSettings.UncertaintyManagement.NONE;
		} else if (sourceButton == radioCF) {
			radioNone.setSelection(false);
			radioCF.setSelection(true);
			radioBay.setSelection(false);
			selected = KBSettings.UncertaintyManagement.CF;
		} else if (sourceButton == radioBay) {
			radioNone.setSelection(false);
			radioCF.setSelection(false);
			radioBay.setSelection(true);
			selected = KBSettings.UncertaintyManagement.BAYESIAN;
		}
		
		//ie the user actually clicked the button
		if (fromUserClick){
			KBase.setUncertaintyMethod(selected);
		}
	}
	
	public KBSettings.UncertaintyManagement getUncertainty() {
		return selected;
	}
	
	private void setFromKB() {
		KBSettings.UncertaintyManagement u = KBase.getUncertaintyMethod();
		
		if (u == KBSettings.UncertaintyManagement.NONE) {
			buttonPress(radioNone,false);
		} else if (u == KBSettings.UncertaintyManagement.CF) {
			buttonPress(radioCF,false);
		} else if (u == KBSettings.UncertaintyManagement.BAYESIAN) {
			buttonPress(radioBay,false);
		}
	}
}
