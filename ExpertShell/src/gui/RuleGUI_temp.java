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
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class RuleGUI_temp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RuleGUI_temp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Composite compRuleGrid = RuleGUIFactory.createCompositeRuleHolder(this);
		GridLayout gl_compRuleGrid = (GridLayout) compRuleGrid.getLayout();
		new Label(compRuleGrid, SWT.NONE);
		
		Label label = new Label(compRuleGrid, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("IF");
		
		Combo combo = new Combo(compRuleGrid, SWT.NONE);
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println(e.toString());
			}
		});
		combo.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				System.out.println(e.toString());
			}
		});
		
		
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				//
				System.out.println(e.toString());
			}
		});
		combo.setItems(new String[] {"traffic_light", "asdf", "qwer"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);
		
		Combo combo_1 = RuleGUIFactory.createComboComparitor(compRuleGrid);
		
		Combo combo_2 = RuleGUIFactory.createComboValue(compRuleGrid);
		combo_2.setItems(new String[] {"green", "red"});
		combo_2.select(0);
		
		Composite composite = RuleGUIFactory.createCompositeLNLS(compRuleGrid);
		
		Label lblNewLabel = RuleGUIFactory.createLabelLN(composite);
		
		Spinner spinner = RuleGUIFactory.createSpinnerLN(composite);
		
		Label lblNewLabel_1 = RuleGUIFactory.createLabelLS(composite);
		
		Spinner spinner_1 = RuleGUIFactory.createSpinnerLS(composite);
		
		Button button = RuleGUIFactory.createButtonDelete(compRuleGrid);
		
		Combo combo_3 = RuleGUIFactory.createComboComLogic(compRuleGrid);
		
		Combo combo_4 = new Combo(compRuleGrid, SWT.NONE);
		combo_4.setItems(new String[] {"traffic_light", "asdf", "qwer"});
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_4.select(0);
		
		Combo combo_5 = new Combo(compRuleGrid, SWT.READ_ONLY);
		combo_5.setItems(new String[] {"is", "=", "<="});
		combo_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo_5.select(0);
		
		Combo combo_6 = new Combo(compRuleGrid, SWT.NONE);
		combo_6.setItems(new String[] {"green", "red"});
		combo_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_6.select(0);
		new Label(compRuleGrid, SWT.NONE);
		
		Button button_1 = RuleGUIFactory.createButtonAdd(compRuleGrid);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//stuff
			}
		});
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		
		Label label_2 = RuleGUIFactory.createLabelThen(compRuleGrid);
		
		Combo combo_7 = new Combo(compRuleGrid, SWT.NONE);
		combo_7.setItems(new String[] {"action"});
		combo_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_7.select(0);
		
		Combo combo_8 = RuleGUIFactory.createComboAssign(compRuleGrid);
		
		Combo combo_9 = new Combo(compRuleGrid, SWT.NONE);
		combo_9.setItems(new String[] {"green", "red"});
		combo_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_9.select(0);
		
		Composite composite_1 = RuleGUIFactory.createCompositeConsequentUncertainty(compRuleGrid);
		
		Label lblPrior = RuleGUIFactory.createLabelPrior(composite_1);
		
		Spinner spinner_2 = RuleGUIFactory.createSpinnerPrior(composite_1);
		
		Label lblCf = RuleGUIFactory.createLabelCF(composite_1);
		
		Spinner spinner_3 = RuleGUIFactory.createSpinnerCF(composite_1);
		
		Button button_2 = new Button(compRuleGrid, SWT.NONE);
		button_2.setText("X");
		button_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		new Label(compRuleGrid, SWT.NONE);
		
		Combo combo_10 = new Combo(compRuleGrid, SWT.NONE);
		combo_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo combo_11 = new Combo(compRuleGrid, SWT.READ_ONLY);
		combo_11.setItems(new String[] {"is", "=", "<="});
		combo_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo_11.select(0);
		
		Combo combo_12 = new Combo(compRuleGrid, SWT.NONE);
		combo_12.setItems(new String[] {"green", "red"});
		combo_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_12.select(0);
		new Label(compRuleGrid, SWT.NONE);
		
		Button button_6 = new Button(compRuleGrid, SWT.NONE);
		button_6.setText("+");
		button_6.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		new Label(compRuleGrid, SWT.NONE);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Group grpUncertainty = new Group(composite_2, SWT.NONE);
		grpUncertainty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("-----------");
			}
		});
		grpUncertainty.setText("Uncertainty");
		grpUncertainty.setLayout(new GridLayout(1, false));
		grpUncertainty.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Combo combo_13 = new Combo(grpUncertainty, SWT.NONE);
		combo_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("selection");
			}
		});
		combo_13.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				System.out.println("verify");
			}
		});
		combo_13.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				System.out.println("modify");
			}
		});
		combo_13.setItems(new String[] {"one", "two", "three"});
		combo_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_13.setText("choose");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
