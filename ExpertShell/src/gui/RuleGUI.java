package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import datatypes.Rule;

public class RuleGUI extends Group {
	
	Rule rule;
	StyledText styledRule;
	
	public RuleGUI(Composite parent, int style, Rule r, MouseListener listen) {
		super(parent, style);
		setupGroup();
	}
	
	public void updateText() {
		//set the label of the group
		this.setText(rule.getRuleNum() + ".");
		
		
	}
	
	public Object getStyledTextWidget() {
		return styledRule;
	}
	
	private void setupGroup() {
		//setup group
		this.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.setText("");
		this.setLayout(new FillLayout(SWT.HORIZONTAL));
	}
}
