package gui;

import gui.WidgetTypes.*;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public class RuleEditorGUI {
	
	boolean consoleDebugOutput = true; //debug
	
	public Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	
	public SelectionAdapter selAdaptor;
	public FocusAdapter focAdaptor;
	
	public RuleEditorGUI(Composite p) {
		

		selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUserAction(e);
			}
		};
		focAdaptor = new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				handleUserAction(e);
			}
		};
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(this);
		
		ruleGrid.getParent().layout();
	}
	
	public void debug(String s) {
		if (consoleDebugOutput) { 
			System.out.println(s);
		}
	}
	
	public void handleUserAction(TypedEvent e) {
		
		Widget w = (Widget) e.getSource();  //get the control that the event happened on
		
		//make a new 'info' object to get stuff about the source button/combo
		WidgetInfo info = new WidgetInfo(w,RuleEditorGUI.this);
		
		//get the index of the control (for example in a list of concequents)
		int index = info.getIndex();
		Source source = info.getSource();
		Group group = info.getGroup();
		
		if (group == Group.ANTECEDENT) {
			if (source == Source.ADD) {
				debug("Add antecedent");
				antList.add();
				//add code to modify KB here!
			} else if (source == Source.DELETE) {
				debug("Delete antecedent: " + index);
				antList.delete(index);
				//add code to modify KB here!
			} else if (source == Source.VARIABLE) {
				debug("Change antecedent variable: " + index);
				//add code to modify KB here!
			} else if (source == Source.COMPARE) {
				debug("Change antecedent comparitor logic: " + index);
				//add code to modify KB here!
			} else if (source == Source.VALUE) {
				debug("Change antecedent value: " + index);
				//add code to modify KB here!
			} else if (source == Source.COMBINE) {
				debug("Change antecedent combinational logic: " + index);
				//add code to modify KB here!
			}
		}
	}
}
