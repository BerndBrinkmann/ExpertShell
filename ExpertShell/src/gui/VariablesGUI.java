package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CBanner;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;

public class VariablesGUI extends Composite {
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VariablesGUI(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBounds(258, 372, 501, 78);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBounds(258, 10, 501, 356);
		
		Label lblName = new Label(composite_1, SWT.NONE);
		lblName.setBounds(10, 10, 87, 15);
		lblName.setText("Variable Name:");
		
		Label lblDescription = new Label(composite_1, SWT.NONE);
		lblDescription.setBounds(30, 51, 66, 21);
		lblDescription.setText("Description:");
		
		Label lblAskUser = new Label(composite_1, SWT.NONE);
		lblAskUser.setBounds(42, 314, 55, 15);
		lblAskUser.setText("Ask User:");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(101, 48, 378, 226);
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(103, 7, 203, 21);
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setBounds(103, 303, 97, 31);
		
		Button btnYes = new Button(group, SWT.RADIO);
		btnYes.setSelection(true);
		btnYes.setBounds(10, 10, 39, 16);
		btnYes.setText("Yes");
		
		Button btnRadioButton = new Button(group, SWT.RADIO);
		btnRadioButton.setBounds(55, 10, 39, 16);
		btnRadioButton.setText("No");
		
		List list = new List(this, SWT.BORDER);
		list.setBounds(10, 10, 242, 440);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
