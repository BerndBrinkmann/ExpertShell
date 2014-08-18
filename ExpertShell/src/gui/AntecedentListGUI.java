package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AntecedentListGUI {
	
	protected Composite parent;
	protected Label ifLabel;
	protected List<Label> fillers = new ArrayList<Label>();
	protected List<AntecedentGUI> antecedents = new ArrayList<AntecedentGUI>();
	protected Button addButton;
	
	public AntecedentListGUI(Composite p) {
		
		parent = p;
		
		addFiller();
		ifLabel = RuleGUIFactory.createLabelIf(parent);
		antecedents.add(new AntecedentGUI(parent,true));
		addButton = RuleGUIFactory.createButtonAdd(parent);
		
		addFillers(4);
	}
	
	public void add() {
		System.out.println("Add");
		antecedents.add(new AntecedentGUI(parent, addButton));
		
		parent.getParent().getParent().layout(true);
	}
	
	private void addFiller() {
		fillers.add(new Label(parent, SWT.NONE));
	}
	
	private void addFillers(int n) {
		
		for(int i=0;i<n;i++){
			addFiller();
		}
	}
	
	public void setListeners(SelectionListener s) {
		// ie 'for every antecedent'
		for(AntecedentGUI a : antecedents) {
			a.setListeners(s);
		}
	}
	
	
}
