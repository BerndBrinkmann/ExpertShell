package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import datatypes.Antecedent;
import datatypes.Consequent;
import datatypes.KBSettings.UncertaintyManagement;

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
		
		//get the click listener from parent class
		SelectionAdapter s = p.selAdaptor;
		
		addFiller();
		thenLabel = RuleGUIFactory.createLabelThen(container);
		
		//add the first conseq
		conseqents.add(new ConsequentGUI(this,true,null,parent.rule.getConsequentArray()[0]));
		
		addButton = RuleGUIFactory.createButtonAdd(container);
				
		addButton.addSelectionListener(s);
		
		//add the rest of the antecedents
		boolean first = true;
		for(Consequent c : parent.rule.getConsequentArray()) {
			if (first) {
				first = false;
				continue; //skip the first one - this is already added above!
			}
			conseqents.add(new ConsequentGUI(this,false,addButton,c));
		}
		
		updateUncertainty();
	}
	
	public void add(Consequent c) {
		ConsequentGUI cg = new ConsequentGUI(this, false, addButton, c);
		conseqents.add(cg);
		cg.update();
		updateUncertainty();
	}
	
	public void delete(int index) {
		//get the element to delete
		ConsequentGUI toDelete = conseqents.get(index);
		
		//remove the GUI elements it has
		toDelete.destroy();
		
		//remove it from the list of antecedents
		conseqents.remove(index);
		
	}
	
	public void updateUncertainty() {
		
		UncertaintyManagement u = parent.rule.getUncertaintyMethod();
		
		boolean showLNLS;
		boolean showPrior;
		boolean showCF;
		
		switch (u) {
		case BAYESIAN:
			showLNLS = true;
			showPrior = true;
			showCF = false;
			break;
		case CF:
			showLNLS = false;
			showPrior = false;
			showCF = true;
			break;
		default:
		case NONE:
			showLNLS = false;
			showPrior = false;
			showCF = false;
			break;
		}
				
		//hide/unhide the prior/cf in each ant line
		for(ConsequentGUI c : conseqents) {
			
			//set default griddata if not set
			if (c.labelPrior.getLayoutData() == null) c.labelPrior.setLayoutData(new GridData());
			if (c.spinPrior.getLayoutData() == null) c.spinPrior.setLayoutData(new GridData());
			if (c.labelCF.getLayoutData() == null) c.labelCF.setLayoutData(new GridData());
			if (c.spinCF.getLayoutData() == null) c.spinCF.setLayoutData(new GridData());
			
			((GridData) c.labelPrior.getLayoutData()).exclude = !showPrior;
			((GridData) c.spinPrior.getLayoutData()).exclude = !showPrior;
			
			((GridData) c.labelCF.getLayoutData()).exclude = !showCF;
			((GridData) c.spinCF.getLayoutData()).exclude = !showCF;
			
			c.labelPrior.setVisible(showPrior);
			c.spinPrior.setVisible(showPrior);
			
			c.labelCF.setVisible(showCF);
			c.spinCF.setVisible(showCF);
		}
		
		
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
