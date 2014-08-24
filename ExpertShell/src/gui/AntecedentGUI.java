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

public class AntecedentGUI {
	
	AntecedentListGUI parent;
	Button delButton;
	Combo var;
	Combo logicComparitor;
	Combo value;
	Combo logicComb;
	Label filler;
	
	ArrayList<Widget> widgets = new ArrayList<Widget>();
	
	public AntecedentGUI(AntecedentListGUI p, boolean first) {
		
		//assign parent of this antGUI
		parent = p;
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		FocusAdapter f = p.parent.focAdaptor;
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		logicComparitor = RuleGUIFactory.createComboComparitor(c);
		logicComparitor.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		filler = new Label(c, SWT.NONE);
		
	}
	
	public AntecedentGUI(AntecedentListGUI p, Control stopper) {
		
		parent = p;
		
		//get where to draw controls
		Composite c = p.container;
		
		//get listeners
		SelectionAdapter s = p.parent.selAdaptor;
		FocusAdapter f = p.parent.focAdaptor;
		
		delButton = RuleGUIFactory.createButtonDelete(c);
		delButton.addSelectionListener(s);
		
		logicComb = RuleGUIFactory.createComboComLogic(c);
		logicComb.addFocusListener(f);
		
		var = RuleGUIFactory.createComboVar(c);
		var.addFocusListener(f);
		
		logicComparitor = RuleGUIFactory.createComboComparitor(c);
		logicComparitor.addFocusListener(f);
		
		value = RuleGUIFactory.createComboValue(c);
		value.addFocusListener(f);
		
		filler = new Label(c, SWT.NONE);
		
		//move these new controls above the 'stopper'. usually the add button
		delButton.moveAbove(stopper);
		logicComb.moveAbove(stopper);
		var.moveAbove(stopper);
		logicComparitor.moveAbove(stopper);
		value.moveAbove(stopper);
		filler.moveAbove(stopper);
		
	}
	
	
	public void destroy() {
		
		Composite c = parent.container;
		
		delButton.dispose();
		var.dispose();
		logicComparitor.dispose();
		value.dispose();
		logicComb.dispose();
		filler.dispose();
		
		c.getParent().getParent().layout();
	}
	
}
