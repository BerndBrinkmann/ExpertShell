package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import datatypes.Antecedent;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.KBSettings;
import datatypes.Rule;

public class RuleGUI extends Group {
	
	private Rule rule;
	private StyledText styledRule;
	
	public RuleGUI(Composite parent, int style, Rule r, MouseListener listen) {
		super(parent, style);
		rule = r;
		
		styledRule = RuleGUIFactory.createStyledTextRule(this, "");
		if (!(listen == null)) {
			this.addMouseListener(listen);
			styledRule.addMouseListener(listen);
		}
		
		setupGroup();
		updateText();
		
	}
	
	public void updateText() {
		boolean first;
		
		StyleRange styleIFTHEN = new StyleRange();
		StyleRange styleVar = new StyleRange();
		StyleRange styleCompareAssign = new StyleRange();
		StyleRange styleValue = new StyleRange();
		
		styleIFTHEN.fontStyle = SWT.ITALIC + SWT.BOLD;
		styleVar.fontStyle = SWT.ITALIC;
		styleCompareAssign.fontStyle = SWT.NONE;
		styleValue.fontStyle = SWT.ITALIC;
		
		//set the label of the group (1 is the first rule)
		int number = ((RuleListGUI) this.getParent()).getRuleGUIArray().indexOf(this);
		this.setText((number+1) + ".");
		
		//reset text
		styledRule.setText("");
		
		//set the text of the styled rulebox thingy
		appendWithStyle("IF",styleIFTHEN);
		append("\t\t\t\t");
		first = true;
		for(Antecedent a : rule.getAntecedentArray()) {
			if (!first) {
				if (rule.getConnective() == Connectives.AND) {
					append("AND\t\t");
				} else {
					append("OR\t\t");
				}
			}
			appendWithStyle(a.getVariableAsString(),styleVar);  //the variable name
			append(" "); //space
			appendWithStyle(a.getComparisonAsString(),styleCompareAssign);  //comparison
			append(" "); //space
			appendWithStyle(a.getValueAsString(),styleValue);
			
			if(first && rule.getUncertaintyMethod() == KBSettings.UncertaintyManagement.BAYESIAN){
				appendWithStyle("  (LN: "+rule.getLikelihoodOfNecessity() +
						", LS: " + rule.getLikelihoodOfSufficiency() + ")", styleValue);
			}
				
			append("\r\n");
			
			
			
			first = false;
		}
		appendWithStyle("THEN",styleIFTHEN);
		append("\t\t"); //three tabs
		
		first = true;
		for(Consequent c : rule.getConsequentArray()) {
			if (first) {
				first = false;
			} else {
				append("\r\n\t\t\t\t");
			}
			appendWithStyle(c.getVariableAsString(),styleVar);  //the variable name
			append(" "); //space
			
			//comparison
			if(c.isNumeric())
			{
				appendWithStyle("=",styleCompareAssign);  
			} else {
				appendWithStyle("is",styleCompareAssign); 
			}
			
			append(" "); //space
			appendWithStyle(c.getValueAsString(),styleValue);
			
			if (rule.getUncertaintyMethod() == KBSettings.UncertaintyManagement.CF) {
				appendWithStyle("  (CF: " + c.getCertaintyFactor() + ")", styleValue);
			} else if (rule.getUncertaintyMethod() == KBSettings.UncertaintyManagement.BAYESIAN) {
				appendWithStyle("  (Prior: " + c.getBeliefOfSelected()  + ")", styleValue);
			}
			
		}
		
	}
	
	private void appendWithStyle(String s,StyleRange styleToCopy) {
		StyleRange style = (StyleRange) styleToCopy.clone();
		
		style.start = styledRule.getCharCount();
		style.length = s.length();
		
		styledRule.append(s);
		styledRule.setStyleRange(style);
	}
	
	private void append(String s) {
		styledRule.append(s);
	}
	
	public Rule getRule(){
		return rule;
	}
	
	public Object getStyledTextWidget() {
		return styledRule;
	}
	
	private void setupGroup() {
		//setup group
		this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.setLayout(new FillLayout(SWT.HORIZONTAL));
	}
	
	public void destroy() {
		rule = null;
		styledRule.dispose();
	}
	//I subclass whatever I want mwahahaha
	@Override
	protected void checkSubclass () {
		//
	}
}
