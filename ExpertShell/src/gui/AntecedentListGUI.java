package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AntecedentListGUI {
	
	
	protected Label ifLabel;
	protected List<Label> fillers = new ArrayList<Label>();
	protected List<AntecedentGUI> antList = new ArrayList<AntecedentGUI>();
	protected Button addButton;
	
	public AntecedentListGUI(Composite ruleGrid) {
		
		addFiller(ruleGrid);
		ifLabel = RuleGUIFactory.createLabelIf(ruleGrid);
		antList.add(new AntecedentGUI(ruleGrid,true));
		addButton = RuleGUIFactory.createButtonAdd(ruleGrid);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				add();
			}
		});
		
		
		addFillers(4,ruleGrid);
	}
	
	public void add() {
		antList.add(new AntecedentGUI(addButton));
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
