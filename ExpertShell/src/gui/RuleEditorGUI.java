package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public class RuleEditorGUI {
	

	Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	
	public SelectionAdapter selAdaptor;
	
	public RuleEditorGUI(Composite p) {
		
	
		selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Widget w = e.item;  //get the control that the event happened on
				
				//sometimes e.item returns null - so use getSource
				//as per http://tinyurl.com/mpygruh
				if (w == null) {
					w = (Widget) e.getSource();   
				}
				
				//make a new 
				WidgetInfo info = new WidgetInfo(w,RuleEditorGUI.this);
				
				if (info.group == WidgetInfo.Group.ANTECEDENT) {
					antList.add();
				}
				
			}
		};
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(ruleGrid);
		antList.setListeners(selAdaptor);
		ruleGrid.getParent().layout();
	}
}
