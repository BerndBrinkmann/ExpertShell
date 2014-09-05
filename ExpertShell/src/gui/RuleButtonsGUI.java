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
import datatypes.Value;
import datatypes.Variable;

public class RuleButtonsGUI extends Composite {

	Button btnUp,btnDown,btnAdd,btnDelete,btnCopy;
	RuleListGUI ruleList;
	KnowledgeBase KBase;
	
	public RuleButtonsGUI(Composite parent, int style, KnowledgeBase kb, RuleListGUI rl) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI Semibold", 9, SWT.BOLD));
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
		//btnAdd.setImage(SWTResourceManager.getImage(RuleButtonsGUI.class, "/resources/Add file.png"));
		btnAdd.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 9, SWT.NORMAL));
	    btnAdd.setText("Add Rule");
		btnAdd.addSelectionListener(selAdaptor);
		
		btnDelete = new Button(this, SWT.NONE);
		//btnDelete.setImage(SWTResourceManager.getImage(RuleButtonsGUI.class, "/resources/delete_file.jpg"));
		btnDelete.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 9, SWT.BOLD));
		btnDelete.setText("Delete Rule");
		btnDelete.addSelectionListener(selAdaptor);
		
		btnCopy = new Button(this, SWT.NONE);
		btnCopy.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 9, SWT.BOLD));
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
			Antecedent antToAdd;
			Consequent conToAdd;
			
			Variable variableToAdd = KBase.getVariable("default");
			if (variableToAdd == null) {
				//default isn't a var yet
				variableToAdd = new Variable("default");
			}
			
			antToAdd = new Antecedent( variableToAdd, (Value)null );
			conToAdd = new Consequent( variableToAdd, (Value)null );
			
			toAdd.addAntecedent(antToAdd);
			toAdd.addConsequent(conToAdd);
			KBase.AddRule(toAdd);
			
			//update GUI
			ruleList.refresh(true);
			ruleList.setSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			ruleList.getParent().layout(true, true);
			
		} else if (source == btnDelete) {
			//make sure there's something selected
			if(ruleList.getSelected() != -1) {
				
				Rule toDelete = ruleList.getSelectedRule();
							
				//update GUI
				ruleList.deleteSelected();
				
				//delete from kb
				KBase.removeRule(toDelete);
				
				//redraw
				ruleList.setSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				ruleList.getParent().layout(true, true);
			}
		} else if (source == btnCopy) {
			
		}
	}
}
