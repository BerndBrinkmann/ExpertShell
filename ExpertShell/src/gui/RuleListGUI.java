package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import datatypes.KnowledgeBase;
import datatypes.Rule;

public class RuleListGUI extends Composite {
	
	private ArrayList<RuleGUI> ruleGUIs;
	private final KnowledgeBase kb;
	private boolean selectable;
	private int selectedIndex;
	private MouseListener listen;
	
	//create an editable/selectable rulelistGUI linked to a knowledgebase
	public RuleListGUI(Composite parent, int style, KnowledgeBase k) {
		super(parent, style);
		kb = k;
		selectedIndex = -1;
		selectable = true;
		
		setupComposite();
		setupListener();
		
		
		
		ruleGUIs = new ArrayList<RuleGUI>();
		//for each rule in the knowledge base
		for(Rule r : kb.getRuleArray()) {
			//make a new rule display box
			ruleGUIs.add(new RuleGUI(this,style,r,listen));
		}
	}
	
	//create a non-editable list of arbitary rules (for displaying)
	public RuleListGUI(Composite parent, int style, ArrayList<Rule> rulelist) {
		super(parent, style);
		
		kb = null;
		selectable = false;
		setupComposite();
		setupListener();
		
		
		
		for(Rule r : rulelist) {
			ruleGUIs.add(new RuleGUI(this,style,r,null));
		}
	
	}
	
	private void setupComposite() {
		//setup composite
		this.setLayout(new GridLayout(1, false));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
	}
	
	private void setupListener() {
		listen = new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {}

			public void mouseDown(MouseEvent e) {
				Object source;
				int index;
				source = e.getSource();
				index = getIndexFromWidget(source);
				
			}

			public void mouseUp(MouseEvent e) {	}
		};
	}
	
	private int getIndexFromWidget(Object source) {
		int i = -1;
		
		//loop through each ruleGUI element
		for(RuleGUI r: ruleGUIs) {
			i++;
			if (r.getStyledTextWidget() == source)
			{
				return i;
			}
		}
		return i;
	}
	
}
