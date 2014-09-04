package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;

public class QuickStart extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QuickStart(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 500;
		gd_scrolledComposite.widthHint = 684;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel.widthHint = 569;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Welcome to The Expert System Shell. The following is a quick introduction to the program to get you started!");
		
		Label lblNewLabel_1 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_1.setText("New Label");
		
		Label lblNewLabel_2 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_2.setText("New Label");
		
		Label lblNewLabel_3 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_3.setText("New Label");
		
		Label lblNewLabel_4 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_4.setText("New Label");
		
		Label lblNewLabel_5 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_5.setText("New Label");
		
		Label lblNewLabel_6 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_6 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_6.setText("New Label");
		
		Label lblNewLabel_7 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_7 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_7.setText("New Label");
		
		Label lblNewLabel_8 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_8 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_8.setText("New Label");
		
		Label lblNewLabel_9 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_9 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_9.setText("New Label");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
