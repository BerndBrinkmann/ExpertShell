package gui;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;

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
}