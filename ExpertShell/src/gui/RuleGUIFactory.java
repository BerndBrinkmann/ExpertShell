package gui;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Spinner;

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
		combo.setItems(new String[] {"is", "is not", "=", "!=", ">", "<", ">=", "<="});
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
}