package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AntecedentListGUI {
	
	
	protected Label ifLabel;
	protected List<Label> fillers;
	protected List<AntecedentGUI> ants;
	protected Button addButton;
	
	public AntecedentListGUI(Composite ruleGrid) {
		
		addFiller(ruleGrid);
		ifLabel = RuleGUIFactory.createLabelIf(ruleGrid);
		ants.add(new AntecedentGUI(ruleGrid));
		addButton = RuleGUIFactory.createButtonAdd(ruleGrid);
		addFillers(4,ruleGrid);
	}
	
	public void add() {
		
	}
	
	private void addFiller(Composite ruleGrid) {
		fillers.add(new Label(ruleGrid, SWT.NONE));
	}
	
	private void addFillers(int n, Composite ruleGrid) {
		
		for(int i=0;i<n;i++){
			addFiller(ruleGrid);
		}
	}
	
	
}
