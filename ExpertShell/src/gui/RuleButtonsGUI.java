package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import datatypes.Antecedent;
import datatypes.Consequent;
import datatypes.KnowledgeBase;
import datatypes.Rule;

public class RuleButtonsGUI extends Composite {

	Button btnUp,btnDown,btnAdd,btnDelete,btnCopy;
	RuleListGUI ruleList;
	KnowledgeBase KBase;
	
	public RuleButtonsGUI(Composite parent, int style, KnowledgeBase kb, RuleListGUI rl) {
		super(parent, style);
		this.setLayout(new GridLayout(5, false));
		ruleList = rl;
		KBase = kb;
		
		SelectionAdapter selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUserAction(e);
			}
		};
		
		btnUp = new Button(this, SWT.NONE);
		//btnUp.setText("\u2191");
		btnUp.setImage(SWTResourceManager.getImage(RuleButtonsGUI.class, "/resources/up_arrow_sign.png"));
		btnUp.addSelectionListener(selAdaptor);
		
		btnDown = new Button(this, SWT.NONE);
		//btnDown.setText("\u2193");
		btnDown.setImage(SWTResourceManager.getImage(RuleButtonsGUI.class, "/resources/down-arrow-inv.jpg"));
		btnDown.addSelectionListener(selAdaptor);
		
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(selAdaptor);
		
		btnDelete = new Button(this, SWT.NONE);
		btnDelete.setText("Del");
		btnDelete.addSelectionListener(selAdaptor);
		
		btnCopy = new Button(this, SWT.NONE);
		btnCopy.setText("Copy");
		btnCopy.addSelectionListener(selAdaptor);
		
	}
	
	private void handleUserAction(SelectionEvent e) {
		Button source = (Button) e.getSource();
		
		if (source == btnUp) {
			
			//move in the kb
			KBase.shiftRuleUp(ruleList.getSelectedRule());
			
			//move in the GUI
			ruleList.shiftSelectedUp();
			ruleList.getParent().layout(true, true);
			
		} else if (source == btnDown) {
			
			//move in the kb
			KBase.shiftRuleDown(ruleList.getSelectedRule());
			
			//move in the GUI
			ruleList.shiftSelectedDown();
			ruleList.getParent().layout(true, true);
		} else if (source == btnAdd) {
			
			//add to kb
			Rule toAdd = new Rule();
			toAdd.addAntecedent(new Antecedent());
			toAdd.addConsequent(new Consequent());
			KBase.AddRule(toAdd);
			
			//update GUI
			ruleList.refresh(true);
			ruleList.setSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			ruleList.getParent().layout(true, true);
			
		} else if (source == btnDelete) {
			
			Rule toDelete = ruleList.getSelectedRule();
						
			//update GUI
			ruleList.deleteSelected();
			
			//delete from kb
			KBase.removeRule(toDelete);
			
			//redraw
			ruleList.setSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			ruleList.getParent().layout(true, true);
			
		} else if (source == btnCopy) {
			
		}
	}
}
