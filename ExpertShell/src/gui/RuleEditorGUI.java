package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class RuleEditorGUI {
	

	Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	
	public SelectionAdapter selAdaptor;
	
	public RuleEditorGUI(Composite p) {
		
	
		selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				antList.add();
				System.out.println("Add");
			}
		};
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(ruleGrid);
		antList.setListeners(selAdaptor);
		ruleGrid.getParent().layout();
	}
}
