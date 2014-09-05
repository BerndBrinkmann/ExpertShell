package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import datatypes.Antecedent;
import datatypes.KBSettings.UncertaintyManagement;

public class AntecedentListGUI {
	
	protected RuleEditorGUI parent;
	protected Composite container;
	protected Label ifLabel;
	protected List<Label> fillers = new ArrayList<Label>();
	protected ArrayList<AntecedentGUI> antecedents = new ArrayList<AntecedentGUI>();
	protected Composite uncertaintyContainer;
	protected Label lnLabel,lsLabel;
	protected Spinner spinLN, spinLS;
	protected Button addButton;
	
	public AntecedentListGUI(RuleEditorGUI p) {
		
		//make a reference to the ruleEditorGui parent object
		parent = p;
		
		//container is the composite where we're adding controls
		container = p.ruleGrid;
		
		SelectionAdapter s = p.selAdaptor;
		FocusAdapter f = p.focAdaptor;
		KeyAdapter enter = p.enterAdaptor;
		
		addFiller();
		ifLabel = RuleGUIFactory.createLabelIf(container);
		
		//add the first antecedent
		antecedents.add(new AntecedentGUI(this,true,null,parent.rule.getAntecedentArray()[0]));
		
		
		uncertaintyContainer = RuleGUIFactory.createCompositeLNLS(container);
		lnLabel = RuleGUIFactory.createLabelLN(uncertaintyContainer);
		spinLN = RuleGUIFactory.createSpinnerLN(uncertaintyContainer);
		spinLN.addFocusListener(f);
		spinLN.addKeyListener(enter);
		spinLN.addSelectionListener(s);
		
		lsLabel = RuleGUIFactory.createLabelLS(uncertaintyContainer);
		spinLS = RuleGUIFactory.createSpinnerLS(uncertaintyContainer);
		spinLS.addFocusListener(f);
		spinLS.addKeyListener(enter);
		spinLS.addSelectionListener(s);
		
		addButton = RuleGUIFactory.createButtonAdd(container);
		addFillers(5);
		
		addButton.addSelectionListener(s);
		
		//add the rest of the antecedents
		boolean first = true;
		for(Antecedent a : parent.rule.getAntecedentArray()) {
			if (first) {
				first = false;
				continue; //skip the first one - this is already added above!
			}
			antecedents.add(new AntecedentGUI(this,false,addButton,a));
		}
		
		//update LNLS etc
		update();
		
	}
	
	public void add(Antecedent a) {
		antecedents.add(new AntecedentGUI(this, false, addButton, a));
		parent.updateUncertainty();
	}
	
	public void delete(int index) {
		//get the element to delete
		AntecedentGUI toDelete = antecedents.get(index);
		
		//remove the GUI elements it has
		toDelete.destroy();
		
		//remove it from the list of antecedents
		antecedents.remove(index);
		
		parent.updateUncertainty();
	}
	
	private void addFiller() {
		fillers.add(new Label(container, SWT.NONE));
	}
	
	private void addFillers(int n) {
		
		for(int i=0;i<n;i++){
			addFiller();
		}
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
		
		if (uncertaintyContainer.getLayoutData() == null) uncertaintyContainer.setLayoutData(new GridData());  //might have no layout data yet
		
		//hide/unhide the LN/LS container and controls
		((GridData) uncertaintyContainer.getLayoutData()).exclude = !showLNLS;
		uncertaintyContainer.setVisible(showLNLS);
		
		//set the height (span) of the LN/LS container
		
		((GridData) uncertaintyContainer.getLayoutData()).verticalSpan = antecedents.size();
		
		
		//hide/unhide the filler labels in each ant line
		for(AntecedentGUI a: antecedents) {
			if (a.filler.getLayoutData() == null) a.filler.setLayoutData(new GridData());
			((GridData) a.filler.getLayoutData()).exclude = showLNLS;
			a.filler.setVisible(!showLNLS);
		}
		
		
		
	}
	
	public Button getAddButton()
	{
		return addButton;
	}

	public void update() {
		
		//update baeysian shit
		int factor = (int) Math.pow(10, spinLS.getDigits());
		spinLS.setSelection((int) (parent.rule.getLikelihoodOfSufficiency()*factor));
		
		factor = (int) Math.pow(10, spinLN.getDigits());
		spinLN.setSelection((int) (parent.rule.getLikelihoodOfNecessity()*factor));
		
	}
	
	public void updateChildren() {
		//update the GUI values of all antecedents
		for (AntecedentGUI a : antecedents) {
			a.update();
		}
	}
	
	public ArrayList<AntecedentGUI> getAntGUIList() {
		return antecedents;
	}
	

	
	
	
}
