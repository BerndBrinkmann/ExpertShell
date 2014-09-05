package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;



public class QuickStart extends Composite {
	
	public SelectionAdapter QSCloseL;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QuickStart(Composite parent, int style, SelectionAdapter QSCloseListener) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		QSCloseL = QSCloseListener;
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 480;
		gd_scrolledComposite.widthHint = 684;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.WRAP);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel.widthHint = 569;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Welcome to The Expert System Shell. The following is a quick introduction to the program to get you started!");
		
		Label lblMainScreen = new Label(composite, SWT.NONE);
		lblMainScreen.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMainScreen.setText("Main Screen:");
		
		Label lblNewLabel_1 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_1.setText("main screen pic inserted here");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_main = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_main = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_main.setText("main screen info inserted here");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_2 = new Label(composite, SWT.WRAP);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		GridData gd_lblNewLabel_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_2.setText("Run Knowledgebase - starting the evaluation process");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_3 = new Label(composite, SWT.WRAP);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/runpic.PNG"));
		GridData gd_lblNewLabel_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_4 = new Label(composite, SWT.WRAP);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/runInfo.PNG"));
		GridData gd_lblNewLabel_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_5 = new Label(composite, SWT.WRAP);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		GridData gd_lblNewLabel_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_5.setText("Run Knowledgebase - finishing the evaluation process");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_6 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_6 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_6.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/howpic.PNG"));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_7 = new Label(composite, SWT.WRAP);
		lblNewLabel_7.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/howinfo.PNG"));
		GridData gd_lblNewLabel_7 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_8 = new Label(composite, SWT.WRAP);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		GridData gd_lblNewLabel_8 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_8.setText("Create/Edit Knowledgebase:");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_9 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_9 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_9.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/editor_pic.PNG"));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_10 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_10 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_10.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/editor_info.PNG"));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_var = new Label(composite, SWT.WRAP);
		lblNewLabel_var.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		GridData gd_lblNewLabel_var = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_var.setText("Variables:");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Label lblNewLabel_11 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_11 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_11.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/variables image.PNG"));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_12 = new Label(composite, SWT.WRAP);
		GridData gd_lblNewLabel_12 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		lblNewLabel_12.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/variables_info.PNG"));
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(QSCloseL);
		

		btnNewButton.setImage(SWTResourceManager.getImage(QuickStart.class, "/resources/delete2.jpg"));
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
		btnNewButton.setText("Close");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
