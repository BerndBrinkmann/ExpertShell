package gui;

import gui.WidgetTypes.*;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import datatypes.Antecedent;
import datatypes.Consequent;
import datatypes.KBSettings.UncertaintyManagement;
import datatypes.KnowledgeBase;
import datatypes.Rule;

public class RuleEditorGUI {
	
	boolean consoleDebugOutput = true; //----debug----
	
	public Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	final KnowledgeBase kb;
	final Rule rule;
	
	public SelectionAdapter selAdaptor;
	public FocusAdapter focAdaptor;
	
	public RuleEditorGUI(Composite p, Rule r, KnowledgeBase k) {
		
		kb = k;
		rule = r;
		
		//for button 'clicks'
		selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUserAction(e);
			}
		};
		
		//for combo boxes losing focus
		focAdaptor = new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				handleUserAction(e);
			}
		};
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(this);
		consList = new ConsequentListGUI(this);
		updateUncertainty();
		ruleGrid.getParent().getParent().layout(true,true);
	}
	
	public void destroy() {
		//remove/delete all GUI objects in the editor
		destroy(ruleGrid);
		ruleGrid.dispose();
		
	}
	
	public void destroy(Control c) {
		//if its a composite then delete its children
		if (c instanceof Composite) {
			for (Control con: ((Composite) c).getChildren()) {
				//recursive.  such recursion.  much programming skill. wow.
				destroy(con);
			}
		} else { //an actual control rather than a container
			c.dispose();
		}
	}
	
	public void updateUncertainty() {
		antList.updateUncertainty();
		consList.updateUncertainty();
	}
	
	public KnowledgeBase getKnowledgeBase() {
		return kb;
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
		
		//get the index of the control (for example in a list of consequents)
		int index = info.getIndex();
		Source source = info.getSource();
		Group group = info.getGroup();
		
				
		
		if (group == Group.ANTECEDENT) {
			if (source == Source.ADD) {
				debug("Add antecedent");
				antList.add(new Antecedent());
				ruleGrid.getParent().getParent().layout(true,true);
				//add code to modify KB here!
			} else if (source == Source.DELETE) {
				debug("Delete antecedent: " + index);
				antList.delete(index);
				ruleGrid.getParent().getParent().layout(true,true);
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
		} else if (group == Group.CONSEQUENT) {
			if (source == Source.ADD) {
				debug("Add consequent");
				consList.add(new Consequent());
				ruleGrid.getParent().getParent().layout(true,true);
				//add code to modify KB here!
			} else if (source == Source.DELETE) {
				debug("Delete consequent: " + index);
				consList.delete(index);
				ruleGrid.getParent().getParent().layout(true,true);
				//add code to modify KB here!
			} else if (source == Source.VARIABLE) {
				debug("Change consequent variable: " + index);
				//add code to modify KB here!
			} else if (source == Source.COMPARE) {
				debug("Change consequent assignment: " + index);
				//add code to modify KB here!
			} else if (source == Source.VALUE) {
				debug("Change consequent value: " + index);
				//add code to modify KB here!
			} else if (source == Source.ASSIGN) {
				debug("Change assignment: " + index);
				//add code to modify KB here!
			}
		}
	}

	
}
