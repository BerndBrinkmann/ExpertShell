package gui;


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
	Composite parent;
	
	public AntecedentGUI(Composite ruleGrid, boolean first) {
		parent = ruleGrid;
		
		var = RuleGUIFactory.createComboVar(parent);
		logicComparitor = RuleGUIFactory.createComboComparitor(parent);
		value = RuleGUIFactory.createComboValue(parent);
		filler = new Label(parent, SWT.NONE);
	}
	
	public AntecedentGUI(Control stopper) {
		
				
		delButton = RuleGUIFactory.createButtonDelete(parent);
		logicComb = RuleGUIFactory.createComboComLogic(parent);
		
		var = RuleGUIFactory.createComboVar(parent);
		logicComparitor = RuleGUIFactory.createComboComparitor(parent);
		value = RuleGUIFactory.createComboValue(parent);
		filler = new Label(parent, SWT.NONE);
		
		delButton.moveAbove(stopper);
		logicComb.moveAbove(stopper);
		var.moveAbove(stopper);
		logicComparitor.moveAbove(stopper);
		value.moveAbove(stopper);
		filler.moveAbove(stopper);
	}
	
}
