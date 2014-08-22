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
	
	protected RuleEditorGUI parent;
	protected Composite container;
	protected Label ifLabel;
	protected List<Label> fillers = new ArrayList<Label>();
	protected ArrayList<AntecedentGUI> antecedents = new ArrayList<AntecedentGUI>();
	protected Button addButton;
	
	public AntecedentListGUI(RuleEditorGUI p) {
		
		//make a reference to the ruleEditorGui parent object
		parent = p;
		
		//container is the composite where we're adding controls
		container = p.ruleGrid;
		
		SelectionAdapter s = p.selAdaptor;
		
		addFiller();
		ifLabel = RuleGUIFactory.createLabelIf(container);
		antecedents.add(new AntecedentGUI(this,true));
		addButton = RuleGUIFactory.createButtonAdd(container);
		addFillers(4);
		
		addButton.addSelectionListener(s);
	}
	
	public void add() {
		antecedents.add(new AntecedentGUI(this, addButton));
		
		container.getParent().getParent().layout(true);
	}
	
	public void delete(int index) {
		//get the element to delete
		AntecedentGUI toDelete = antecedents.get(index);
		
		//remove the GUI elements it has
		toDelete.destroy();
		
		//remove it from the list of antecedents
		antecedents.remove(index);
	}
	
	private void addFiller() {
		fillers.add(new Label(container, SWT.NONE));
	}
	
	private void addFillers(int n) {
		
		for(int i=0;i<n;i++){
			addFiller();
		}
	}
	
	
	public Button getAddButton()
	{
		return addButton;
	}
	
	
}
