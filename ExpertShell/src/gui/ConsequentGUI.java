package gui;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import datatypes.Consequent;
import datatypes.KBSettings.UncertaintyManagement;
import datatypes.Value;

public class ConsequentGUI {
	
	ConsequentListGUI parent;
	Consequent con;
	Button delButton;
	Combo var;
	Combo assign;
	Combo value;
	Combo logicComb;
	Label filler,filler1;
	Composite uncertaintyContainer;
	Label labelPrior,labelCF;
	Spinner spinPrior,spinCF;
	
	public ConsequentGUI(ConsequentListGUI p, boolean first, Control stopper, Consequent co) {
		
		parent = p;
		con = co;
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		SelectionAdapter s = p.parent.selAdaptor;
		FocusAdapter f = p.parent.focAdaptor;
		KeyAdapter enter = p.parent.enterAdaptor;
		
		//the lines after the first include delete button
		if (!first) {
			delButton = RuleGUIFactory.createButtonDelete(c);
			delButton.addSelectionListener(s);
			filler1 = new Label(c, SWT.NONE);
		}
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		var.addKeyListener(enter);
		var.addSelectionListener(s);
		
		assign = RuleGUIFactory.createComboAssign(c);
		assign.addFocusListener(f);
		assign.addSelectionListener(s);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		value.addKeyListener(enter);
		value.addSelectionListener(s);
		
		uncertaintyContainer = RuleGUIFactory.createCompositeConsequentUncertainty(c);
		labelPrior = RuleGUIFactory.createLabelPrior(uncertaintyContainer);
		spinPrior = RuleGUIFactory.createSpinnerPrior(uncertaintyContainer);
		spinPrior.addFocusListener(f);
		spinPrior.addKeyListener(enter);
		spinPrior.addSelectionListener(s);
		
		labelCF = RuleGUIFactory.createLabelCF(uncertaintyContainer);
		spinCF = RuleGUIFactory.createSpinnerCF(uncertaintyContainer);
		spinCF.addFocusListener(f);
		spinCF.addKeyListener(enter);
		spinCF.addSelectionListener(s);
		
		if (!first) {
			delButton.moveAbove(stopper);
			filler1.moveAbove(stopper);
			var.moveAbove(stopper);
			assign.moveAbove(stopper);
			value.moveAbove(stopper);
			uncertaintyContainer.moveAbove(stopper);
		}
		
		update();
	}
	
	public void update() {
		//update variable box
		var.setItems(parent.parent.getKnowledgeBase().getVariablesArrayAsString());
		var.select(parent.parent.getKnowledgeBase().getVariablesArray().indexOf(con.getVariable()));
		
		//update assignment
		if(con.isNumeric()) {
			assign.select(1);
		} else {
			assign.select(0);
		}
		
	
		//update value
		if(!con.isNumeric()) {
			value.removeAll();
			for(Value v: con.getVariable().getArrayOfPossibleValues()) {
				value.add(v.toString());
			}
			
			int valueIndex = con.getVariable().getValueIndex(con.getValue());
			if(valueIndex != -1) {
				value.select(valueIndex);
			} else {
				value.setText(con.getValue().toString());
			}
		} else {
			value.setText(con.getNumVal().toString());
		}
		
		//update CF
		int factor = (int) Math.pow(10, spinCF.getDigits()); 
		spinCF.setSelection((int) (con.getCertaintyFactor()*factor) );
		
		//update prior
		double select = con.getVariable().getBelief(con.getValue());
		factor = (int) Math.pow(10, spinPrior.getDigits());				
		spinPrior.setSelection( (int) (select*factor) );
		
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
