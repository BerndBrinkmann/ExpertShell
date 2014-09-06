package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

public class RuleListGUI_temp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RuleListGUI_temp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinHeight(50);
		GridData gd_scrolledComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 115;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		
		final Composite compList = new Composite(scrolledComposite, SWT.NONE);
		compList.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				compList.setFocus();
			}
		});
		compList.setLayout(new GridLayout(1, false));
		
		Group group = RuleGUIFactory.createGroupRuleContainer(compList, "1.");
		
		StyledText styledText = RuleGUIFactory.createStyledTextRule(group, "IF\t\t\t\tthingy is blue\r\nTHEN\t\tother_thing is yes\r\n\t\t\t\tblah is no");
		styledText.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		Group group_1 = new Group(compList, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group_1.setText("2.");
		group_1.setBounds(0, 0, 157, 67);
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		StyledText styledText_1 = new StyledText(group_1, SWT.BORDER);
		styledText_1.setText("IF\t\t\t\tthingy is blue\r\nTHEN\t\tother_thing is yes\r\n\t\t\t\tblah is no");
		styledText_1.setEnabled(false);
		styledText_1.setEditable(false);
		scrolledComposite.setContent(compList);
		scrolledComposite.setMinSize(compList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(5, false));
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setBounds(0, 0, 75, 25);
		btnNewButton_1.setText("\u2191");
		
		Button button = new Button(composite_2, SWT.NONE);
		button.setBounds(0, 0, 75, 25);
		button.setText("\u2193");
		
		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.setText("Add");
		
		Button btnNewButton_3 = new Button(composite_2, SWT.NONE);
		btnNewButton_3.setText("Del");
		
		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		btnNewButton_2.setBounds(0, 0, 75, 25);
		btnNewButton_2.setText("Copy");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
