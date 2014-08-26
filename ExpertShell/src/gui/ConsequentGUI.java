package gui;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import datatypes.KBSettings.UncertaintyManagement;

public class ConsequentGUI {
	
	ConsequentListGUI parent;
	Button delButton;
	Combo var;
	Combo assign;
	Combo value;
	Combo logicComb;
	Label filler,filler1;
	Composite uncertaintyContainer;
	Label labelPrior,labelCF;
	Spinner spinPrior,spinCF;	
	
	public ConsequentGUI(ConsequentListGUI p, boolean first, Control stopper) {
		
		parent = p;
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		SelectionAdapter s = p.parent.selAdaptor;
		FocusAdapter f = p.parent.focAdaptor;
		
		//the lines after the first include delete button
		if (!first) {
			delButton = RuleGUIFactory.createButtonDelete(c);
			delButton.addSelectionListener(s);
			filler1 = new Label(c, SWT.NONE);
		}
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		assign = RuleGUIFactory.createComboAssign(c);
		assign.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		uncertaintyContainer = RuleGUIFactory.createCompositeConsequentUncertainty(c);
		labelPrior = RuleGUIFactory.createLabelPrior(uncertaintyContainer);		
		spinPrior = RuleGUIFactory.createSpinnerPrior(uncertaintyContainer);
		labelCF = RuleGUIFactory.createLabelCF(uncertaintyContainer);
		spinCF = RuleGUIFactory.createSpinnerCF(uncertaintyContainer);	
		//filler = new Label(c, SWT.NONE);
		
		if (!first) {
			delButton.moveAbove(stopper);
			filler1.moveAbove(stopper);
			var.moveAbove(stopper);
			assign.moveAbove(stopper);
			value.moveAbove(stopper);
			uncertaintyContainer.moveAbove(stopper);
		}
	}
	
	
	public void destroy() {
		Composite c = parent.container;
		
		delButton.dispose();
		var.dispose();
		assign.dispose();
		value.dispose();
		filler1.dispose();
		labelPrior.dispose();
		spinPrior.dispose();
		labelCF.dispose();
		spinCF.dispose();
		uncertaintyContainer.dispose();
		
	}
	
}
