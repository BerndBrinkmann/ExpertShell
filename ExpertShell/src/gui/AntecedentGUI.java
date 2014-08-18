package gui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
	
	public AntecedentGUI(Composite p, boolean first) {
		
		var = RuleGUIFactory.createComboVar(p);
		logicComparitor = RuleGUIFactory.createComboComparitor(p);
		value = RuleGUIFactory.createComboValue(p);
		filler = new Label(p, SWT.NONE);
	}
	
	public AntecedentGUI(Composite p, Control stopper) {
		
				
		delButton = RuleGUIFactory.createButtonDelete(p);
		logicComb = RuleGUIFactory.createComboComLogic(p);
		
		var = RuleGUIFactory.createComboVar(p);
		logicComparitor = RuleGUIFactory.createComboComparitor(p);
		value = RuleGUIFactory.createComboValue(p);
		filler = new Label(p, SWT.NONE);
		
		delButton.moveAbove(stopper);
		logicComb.moveAbove(stopper);
		var.moveAbove(stopper);
		logicComparitor.moveAbove(stopper);
		value.moveAbove(stopper);
		filler.moveAbove(stopper);
		
	}
	
	public void setListeners(SelectionListener s) {
		delButton.addSelectionListener(s);
		
	}
	
	public void destroy() {
		Composite p = delButton.getParent();
		
		delButton.dispose();
		var.dispose();
		logicComparitor.dispose();
		value.dispose();
		logicComb.dispose();
		filler.dispose();
		
		p.getParent().getParent().layout(true);
	}
	
	
}
