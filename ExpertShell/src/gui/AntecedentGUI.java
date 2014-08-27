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
import org.eclipse.swt.widgets.Widget;

import datatypes.Antecedent;
import datatypes.Connectives;
import datatypes.Value;
import datatypes.Variable;

public class AntecedentGUI {
	
	AntecedentListGUI parent;
	Antecedent ant;
	Button delButton;
	Combo var;
	Combo logicComparitor;
	Combo value;
	Combo logicComb;
	Label filler;
	
	public AntecedentGUI(AntecedentListGUI p, boolean first, Control stopper, Antecedent a) {
		
		parent = p;
		ant = a;
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		SelectionAdapter s = p.parent.selAdaptor;
		FocusAdapter f = p.parent.focAdaptor;
		
		if (!first) {
			delButton = RuleGUIFactory.createButtonDelete(c);
			delButton.addSelectionListener(s);
			
			logicComb = RuleGUIFactory.createComboComLogic(c);
			logicComb.addFocusListener(f);
		}
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		logicComparitor = RuleGUIFactory.createComboComparitor(c);
		logicComparitor.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		filler = new Label(c, SWT.NONE);
		
		if (!first) {
			//move these new controls above the 'stopper'. usually the add button
			delButton.moveAbove(stopper);
			logicComb.moveAbove(stopper);
			var.moveAbove(stopper);
			logicComparitor.moveAbove(stopper);
			value.moveAbove(stopper);
			filler.moveAbove(stopper);
		}
		
		update();
	}
	
	public void update() {
		
		// update AND/OR combo if we have one for this ant.
		if (!(logicComb == null)) {
			if (parent.parent.rule.getConnective() == Connectives.AND) {
				logicComb.select(0);
			} else {
				//must be OR
				logicComb.select(1);
			}
		}
		
		//update variable
		var.removeAll();
		for (Variable v : parent.parent.getKnowledgeBase().getVariablesArray()) {
			if(!v.getName().equals("default"));
				var.add(v.toString());
		}
		var.select(parent.parent.getKnowledgeBase().getVariablesArray().indexOf(ant.getVariable()));
		
		//update comparison
		logicComparitor.select(ant.getComparison().getIndex());
		
		//(add numeric!!!!!!!!!!!!!!)
		//update value
		value.removeAll();
		for (Value v : ant.getVariable().getArrayOfPossibleValues()) {
			value.add(v.toString());
		}
		value.select(ant.getVariable().getValueIndex(ant.getValue()));
		
	}
	
	public void destroy() {
		
		
		delButton.dispose();
		var.dispose();
		logicComparitor.dispose();
		value.dispose();
		logicComb.dispose();
		filler.dispose();
	}
	
}
