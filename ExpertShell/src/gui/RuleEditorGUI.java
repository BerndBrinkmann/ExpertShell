package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class RuleEditorGUI {
	
	Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	
	
	public RuleEditorGUI(Composite p) {
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(ruleGrid);
		
		ruleGrid.getParent().layout();
	}
}
