package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import datatypes.Antecedent;
import datatypes.Rule;

public class RuleGUI extends Group {
	
	private Rule rule;
	private StyledText styledRule;
	
	public RuleGUI(Composite parent, int style, Rule r, MouseListener listen) {
		super(parent, style);
		rule = r;
		
		styledRule = RuleGUIFactory.createStyledTextRule(this, "");
		if (!(listen == null)) {
			styledRule.addMouseListener(listen);
		}
		
		setupGroup();
		updateText();
		
		
	}
	
	public void updateText() {
		
		StyleRange styleIFTHEN = new StyleRange();
		StyleRange styleVar = new StyleRange();
		StyleRange styleCompareAssign = new StyleRange();
		StyleRange styleValue = new StyleRange();
		
		styleIFTHEN.fontStyle = SWT.ITALIC + SWT.BOLD;
		styleVar.fontStyle = SWT.ITALIC;
		styleCompareAssign.fontStyle = SWT.NONE;
		styleValue.fontStyle = SWT.ITALIC;
		
		//set the label of the group (1 is the first rule)
		this.setText((rule.getRuleNum()+1) + ".");
		
		//reset text
		styledRule.setText("");
		
		//set the text of the styled rulebox thingy
		appendWithStyle("IF",styleIFTHEN);
		append("\t\t\t"); //three tabs
		for(Antecedent a : rule.getAntecedentArray()) {
			appendWithStyle(a.getVariableAsString(),styleVar);  //the variable name
			append(" "); //space
			appendWithStyle("=is",styleCompareAssign);  //comparison
			//appendWithStyle(a.getComparisonAsString(),styleCompareAssign);  //comparison
			append(" "); //space
			appendWithStyle(a.getValueAsString(),styleValue);
			append("\r\n");
		}
		
	}
	
	private void appendWithStyle(String s,StyleRange styleToCopy) {
		StyleRange style = (StyleRange) styleToCopy.clone();
		int length;
		
		style.start = styledRule.getCharCount();
		style.length = s.length();
		
		styledRule.append(s);
		styledRule.setStyleRange(style);
	}
	
	private void append(String s) {
		styledRule.append(s);
	}
	
	public Object getStyledTextWidget() {
		return styledRule;
	}
	
	private void setupGroup() {
		//setup group
		this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.setLayout(new FillLayout(SWT.HORIZONTAL));
	}
	
	//I subclass whatever I want mwahahaha
	@Override
	protected void checkSubclass () {
		//
	}
}
