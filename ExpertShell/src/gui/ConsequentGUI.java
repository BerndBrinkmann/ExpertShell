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

public class ConsequentGUI {
	
	AntecedentListGUI parent;
	Button delButton;
	Combo var;
	Combo assign;
	Combo value;
	Combo logicComb;
	Label filler,filler1;
	
	ArrayList<Widget> widgets = new ArrayList<Widget>();
	
	public ConsequentGUI(ConsequentListGUI p, boolean first) {
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		FocusAdapter f = p.parent.focAdaptor;
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		assign = RuleGUIFactory.createComboAssign(c);
		assign.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		filler = new Label(c, SWT.NONE);
		
	}
	
	public ConsequentGUI(ConsequentListGUI p, Control stopper) {
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		SelectionAdapter s = p.parent.selAdaptor;
		FocusAdapter f = p.parent.focAdaptor;
		
		delButton = RuleGUIFactory.createButtonDelete(c);
		delButton.addSelectionListener(s);
		
		filler1 = new Label(c, SWT.NONE);
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		assign = RuleGUIFactory.createComboAssign(c);
		assign.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		filler = new Label(c, SWT.NONE);
		
		delButton.moveAbove(stopper);
		filler1.moveAbove(stopper);
		var.moveAbove(stopper);
		assign.moveAbove(stopper);
		value.moveAbove(stopper);
		filler.moveAbove(stopper);
		
				
	}
	
	
	public void destroy() {
		Composite p = delButton.getParent();
		
		delButton.dispose();
		var.dispose();
		assign.dispose();
		value.dispose();
		filler1.dispose();
		filler.dispose();
		
		p.getParent().getParent().layout(true);
	}
	
}
