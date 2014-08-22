package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ConsequentListGUI {
	
	protected RuleEditorGUI parent;
	protected Composite container;
	protected Label thenLabel;
	protected List<Label> fillers = new ArrayList<Label>();
	protected ArrayList<ConsequentGUI> conseqents = new ArrayList<ConsequentGUI>();
	protected Button addButton;
	
	public ConsequentListGUI(RuleEditorGUI p) {
		
		//make a reference to the ruleEditorGui parent object
		parent = p;
		
		//container is the composite where we're adding controls
		container = p.ruleGrid;
		
		SelectionAdapter s = p.selAdaptor;
		
		addFiller();
		thenLabel = RuleGUIFactory.createLabelThen(container);
		conseqents.add(new ConsequentGUI(this,true));
		addButton = RuleGUIFactory.createButtonAdd(container);
				
		addButton.addSelectionListener(s);
	}
	
	public void add() {
		conseqents.add(new ConsequentGUI(this, addButton));
		
		container.getParent().getParent().layout(true);
	}
	
	public void delete(int index) {
		//get the element to delete
		ConsequentGUI toDelete = conseqents.get(index);
		
		//remove the GUI elements it has
		toDelete.destroy();
		
		//remove it from the list of antecedents
		conseqents.remove(index);
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
