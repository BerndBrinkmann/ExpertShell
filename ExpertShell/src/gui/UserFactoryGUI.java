package gui;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Scale;

public final class UserFactoryGUI {
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_group
	 */
	public static Group createQuestionGroup(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		//group.setLayoutData(layoutData);
		return group;
		

	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_lblNewLabel
	 */
	public static Label createQuestionLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1)); //maybe??
		//label.setLayoutData(layoutData);
		return label;
	}
	
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_combo
	 */
	public static Combo createAnswerCombo(Composite parent) {
		org.eclipse.swt.widgets.Combo combo = new org.eclipse.swt.widgets.Combo(parent, org.eclipse.swt.SWT.NONE);
		//combo.setLayoutData(layoutData);
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1)); //maybe??
		combo.setText("Answer");
		return combo;
	}

	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_scale
	 */
	public static Scale createCFScale(Composite parent) {
		Scale scale = new Scale(parent, SWT.NONE);
		scale.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1)); //maybe??
		//scale.setLayoutData(layoutData);
		return scale;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_lblCf
	 */
	public static Label createCFLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("CF%");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1)); //maybe??
		//label.setLayoutData(layoutData);
		return label;
	}
	
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_WhyButton
	 */
	public static Button createWhyButton(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1)); //maybe??
		//button.setLayoutData(layoutData);
		button.setText("Why?");
		return button;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layoutData gd_HowButton
	 */
	public static Button createHowButton(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1)); //maybe??
		//button.setLayoutData(layoutData);
		button.setText("How?");
		return button;
	}
	/**
	 * @wbp.factory
	 */
	public static Button createOKButton(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button.setText("OK");
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
			}
		});
				return button;		
						
	}
	
	
	
}