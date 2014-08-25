package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import datatypes.KnowledgeBase;
import datatypes.Rule;

public class RuleListGUI extends Composite {
	
	private ArrayList<Group> ruleGUIs;
	private final KnowledgeBase kb;
	private boolean selectable;
	
	//create an editable/selectable rulelistGUI
	public RuleListGUI(Composite parent, int style, KnowledgeBase k) {
		super(parent, style);
		setupComposite();
		
		kb = k;
	}
	
	//create a non-editable list of arbitary rules (for displaying)
	public RuleListGUI(Composite parent, int style, ArrayList<Rule> rulelist) {
		super(parent, style);
		setupComposite();
		
		makeRuleListGUI(k,null,false);
	}
	
	
	private void setupComposite() {
		//setup composite
		this.setLayout(new GridLayout(1, false));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
	}

}
