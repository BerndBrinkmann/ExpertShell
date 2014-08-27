package gui;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;

public final class RuleGUIFactory {
	/**
	 * @wbp.factory
	 */
	public static Label createLabelIf(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("IF");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Combo createComboVar(Composite parent) {
		Combo combo = new Combo(parent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return combo;
	}
	/**
	 * @wbp.factory
	 */
	public static Combo createComboComparitor(Composite parent) {
		Combo combo = new Combo(parent, SWT.READ_ONLY);
		combo.setItems(new String[] {"=", "!=", ">", "<", "<=", ">=", "is", "is not", });
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.select(0);
		return combo;
	}
	/**
	 * @wbp.factory
	 */
	public static Combo createComboValue(Composite parent) {
		Combo combo = new Combo(parent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return combo;
	}
	/**
	 * @wbp.factory
	 */
	public static Combo createComboComLogic(Composite parent) {
		Combo combo = new Combo(parent, SWT.READ_ONLY);
		combo.setItems(new String[] {"AND", "OR"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.select(0);
		return combo;
	}
	/**
	 * @wbp.factory
	 */
	public static Combo createComboAssign(Composite parent) {
		Combo combo = new Combo(parent, SWT.READ_ONLY);
		combo.setItems(new String[] {"is", "="});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.select(0);
		return combo;
	}
	/**
	 * @wbp.factory
	 */
	public static Button createButtonDelete(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setText("X");
		button.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		return button;
	}
	/**
	 * @wbp.factory
	 */
	public static Button createButtonAdd(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setText("+");
		button.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		return button;
	}
	/**
	 * @wbp.factory
	 */
	public static Composite createCompositeRuleHolder(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(6, false));
		return composite;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabelThen(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("THEN");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Composite createCompositeLNLS(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		return composite;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabelLN(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("LN:");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Spinner createSpinnerLN(Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		return spinner;
	}
	/**
	 * @wbp.factory
	 */
	public static Spinner createSpinnerLS(Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		return spinner;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabelLS(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("LS:");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabelPrior(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Prior:");
		label.setBounds(0, 0, 55, 15);
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Spinner createSpinnerPrior(Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		spinner.setDigits(2);
		spinner.setMaximum(100);
		spinner.setMinimum(1);
		return spinner;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabelCF(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("CF:");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static Spinner createSpinnerCF(Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		return spinner;
	}
	/**
	 * @wbp.factory
	 */
	public static Composite createCompositeConsequentUncertainty(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		return composite;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source text "IF\t\t\t\tthingy is blue\r\nTHEN\t\tother_thing is yes\r\n\t\t\t\tblah is no"
	 */
	public static StyledText createStyledTextRule(Composite parent, String text) {
		StyledText styledText = new StyledText(parent, SWT.BORDER);
		styledText.setText(text);
		styledText.setEditable(false);
		styledText.setEnabled(false);
		return styledText;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source text "1."
	 */
	public static Group createGroupRuleContainer(Composite parent, String text) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setText(text);
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		return group;
	}
	
}