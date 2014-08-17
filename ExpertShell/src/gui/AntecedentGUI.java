package gui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class AntecedentGUI {
	
	Button delButton;
	Combo var;
	Combo logicComparitor;
	Combo value;
	Combo logicComb;
	Label filler;
	
	public AntecedentGUI(Composite ruleGrid) {
				
		var = RuleGUIFactory.createComboVar(ruleGrid);
		logicComparitor = RuleGUIFactory.createComboComparitor(ruleGrid);
		value = RuleGUIFactory.createComboValue(ruleGrid);
		filler = new Label(ruleGrid, SWT.NONE);
	}
	
	public AntecedentGUI(Control stopper, Composite ruleGrid) {
		
		delButton = RuleGUIFactory.createButtonDelete(ruleGrid);
		logicComb = RuleGUIFactory.createComboComLogic(ruleGrid);
		
		var = RuleGUIFactory.createComboVar(ruleGrid);
		logicComparitor = RuleGUIFactory.createComboComparitor(ruleGrid);
		value = RuleGUIFactory.createComboValue(ruleGrid);
		filler = new Label(ruleGrid, SWT.NONE);
		
		delButton.moveAbove(stopper);
		logicComb.moveAbove(stopper);
		var.moveAbove(stopper);
		logicComparitor.moveAbove(stopper);
		value.moveAbove(stopper);
		filler.moveAbove(stopper);
	}
	
}
