package gui;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StyledText;


public class RuleGUI_temp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RuleGUI_temp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Composite compHolder = RuleGUIFactory.createCompositeRuleHolder(this);
		GridLayout gridLayout = (GridLayout) compHolder.getLayout();
		new Label(compHolder, SWT.NONE);
		
		Label label = new Label(compHolder, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("IF");
		
		Combo combo = new Combo(compHolder, SWT.NONE);
		combo.setItems(new String[] {"traffic_light", "asdf", "qwer"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);
		
		Combo combo_1 = RuleGUIFactory.createComboComparitor(compHolder);
		
		Combo combo_2 = RuleGUIFactory.createComboValue(compHolder);
		combo_2.setItems(new String[] {"green", "red"});
		combo_2.select(0);
		
		Composite composite = new Composite(compHolder, SWT.NONE);
		
		Button button = RuleGUIFactory.createButtonDelete(compHolder);
		
		Combo combo_3 = RuleGUIFactory.createComboComLogic(compHolder);
		
		Combo combo_4 = new Combo(compHolder, SWT.NONE);
		combo_4.setItems(new String[] {"traffic_light", "asdf", "qwer"});
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_4.select(0);
		
		Combo combo_5 = new Combo(compHolder, SWT.READ_ONLY);
		combo_5.setItems(new String[] {"is", "=", "<="});
		combo_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo_5.select(0);
		
		Combo combo_6 = new Combo(compHolder, SWT.NONE);
		combo_6.setItems(new String[] {"green", "red"});
		combo_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_6.select(0);
		new Label(compHolder, SWT.NONE);
		
		Button button_1 = RuleGUIFactory.createButtonAdd(compHolder);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		
		Label label_2 = new Label(compHolder, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("THEN");
		
		Combo combo_7 = new Combo(compHolder, SWT.NONE);
		combo_7.setItems(new String[] {"action"});
		combo_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_7.select(0);
		
		Combo combo_8 = RuleGUIFactory.createComboAssign(compHolder);
		
		Combo combo_9 = new Combo(compHolder, SWT.NONE);
		combo_9.setItems(new String[] {"green", "red"});
		combo_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_9.select(0);
		new Label(compHolder, SWT.NONE);
		
		Button button_2 = new Button(compHolder, SWT.NONE);
		button_2.setText("X");
		button_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		new Label(compHolder, SWT.NONE);
		
		Combo combo_10 = new Combo(compHolder, SWT.NONE);
		combo_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo combo_11 = new Combo(compHolder, SWT.READ_ONLY);
		combo_11.setItems(new String[] {"is", "=", "<="});
		combo_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo_11.select(0);
		
		Combo combo_12 = new Combo(compHolder, SWT.NONE);
		combo_12.setItems(new String[] {"green", "red"});
		combo_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_12.select(0);
		new Label(compHolder, SWT.NONE);
		
		Button button_6 = new Button(compHolder, SWT.NONE);
		button_6.setText("+");
		button_6.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);
		new Label(compHolder, SWT.NONE);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
