package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import datatypes.KnowledgeBase;
import datatypes.Rule;

public class RuleListGUI extends Composite {
	
	private ArrayList<RuleGUI> ruleGUIs;
	private KnowledgeBase kb;
	private boolean selectable;
	private int selected;
	private MouseListener listen;
	private Composite editorHolder;
	private RuleEditorGUI editor;
	
	//create an editable/selectable rulelistGUI linked to a knowledgebase
	public RuleListGUI(Composite parent, int style, KnowledgeBase k) {
		super(parent, style);
		kb = k;
		selected = -1;
		selectable = true;
		
		setupComposite();
		setupListener();
		
		
		ruleGUIs = new ArrayList<RuleGUI>();
		
		addFromKB();
	}
	
	public void changeKnowledgeBase(KnowledgeBase k) {
		
		kb = k;
		
		
		
		refresh(false);
		
		this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.getParent().layout(true, true);
		
	}
	
	private void addFromKB() {
		//for each rule in the knowledge base
		for(Rule r : kb.getRuleArray()) {
			//make a new rule display box
			ruleGUIs.add(new RuleGUI(this,SWT.NONE,r,listen));
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
				index = getIndexFromControl(source);
			
				if(index == selected) {
					//user has clicked the rule that is already selected
					//ie they want to close the editor
					deselect(index);
				} else if (selected == -1){
					//user has clicked another rule and nothing is selected yet
					select(index);
				} else {
					deselect(selected);
					select(index);
				}
			}

			public void mouseUp(MouseEvent e) {	}
		};
	}
	
	protected void select(int index) {
		
		StyledText st;
		
		st = (StyledText) getControlFromIndex(index);
		
		st.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		st.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		//make a new editor
		editor = new RuleEditorGUI(editorHolder, index, kb, this);
		
		
		//set the new index
		selected = index;
	}

	protected void deselect(int index) {
		
		if (selected == -1) return;
		
		StyledText st = (StyledText) getControlFromIndex(index);
		
		st.setForeground(null);
		st.setBackground(null);
		
		if (!(editor == null)) {
			editor.destroy();  //remove gui elements
			editor = null; //remove reference
		}
		
		
		selected = -1;
		
	}
	
	private Control getControlFromIndex(int index) {
		return (Control) ruleGUIs.get(index).getStyledTextWidget();
	}
	
	public void updateUncertainty(){
		this.refresh(true);
		this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.getParent().getParent().layout(true, true);
	}
	
	public void updateTextOfSelected() {
		ruleGUIs.get(selected).updateText();
	}
	private int getIndexFromControl(Object source) {
		int i = -1;
		
		//loop through each ruleGUI element
		for(RuleGUI r: ruleGUIs) {
			i++;
			if (source == r)
			{
				return i;
			}
		}
		return i;
	}

	public Rule getSelectedRule(){
		return ruleGUIs.get(selected).getRule();
	}
	
	public ArrayList<RuleGUI> getRuleGUIArray(){
		return ruleGUIs;
	}
	
	public int getSelected() {
		return selected;
	}
	
	public void setEditorHolder(Composite compRuleEditorHolder) {
		editorHolder = compRuleEditorHolder;
	}
	
	public void refresh(boolean keepSelection) {
		int rememberSelected = selected;
		
		deselect(selected);
		
		deleteAll();
		
		addFromKB();
		
		if (keepSelection && rememberSelected != -1 && rememberSelected < ruleGUIs.size()) {
			select(rememberSelected);
		}
		
	}
	
	private void deleteAll() {
		for(RuleGUI rg : ruleGUIs) {
			rg.destroy();
			rg.dispose();
		}
	
		ruleGUIs = null;
		ruleGUIs = new ArrayList<RuleGUI>();
		
	}
	public void deleteSelected(){
		RuleGUI toDel = ruleGUIs.get(selected);
		
		deselect(selected);

		ruleGUIs.remove(toDel);
		toDel.destroy();
		toDel.dispose();
		
	}
	
	public void shiftSelectedUp() {
		RuleGUI stopper, toMove;
		
		//cant move if already first (or unselected)
		if (selected < 1){
			System.out.println("cant move up");
			return;
		}
		
		stopper = ruleGUIs.get(selected -1);
		toMove = ruleGUIs.get(selected);
		
		//move in the array
		ruleGUIs.remove(selected);
		ruleGUIs.add(selected - 1, toMove);
		
		//update selected
		selected--;
		
		//move in the composite
		toMove.moveAbove(stopper);
		
	}
	
	public void shiftSelectedDown() {
		RuleGUI stopper, toMove;
		
		//cant move if already last (or unselected)
		if (selected == -1 || selected >= (ruleGUIs.size() -1) ) return;
		
		stopper = ruleGUIs.get(selected + 1);
		toMove = ruleGUIs.get(selected);
		
		//move in the array
		ruleGUIs.remove(selected);
		ruleGUIs.add(selected + 1, toMove);
		
		//update selected
		selected++;
		
		//move in the composite
		toMove.moveBelow(stopper);
		
	}
}
