package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.FillLayout;

//note - to reference a widget it must be 'exposed' by right-clicking on it in windowbuilder and selecting 'expose component' --Arie


public class MainScreen {

	protected Shell shlExpertSystemShell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button btnBackwardChaining;
	private Button btnBayesianReasoning;
	private Button btnCertainityFactor;
	private Button btnDefault;
	private Button btnForwardChaining;
	private Button button;
	private Combo combo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainScreen window = new MainScreen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlExpertSystemShell.open();
		shlExpertSystemShell.layout();
		while (!shlExpertSystemShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlExpertSystemShell = new Shell();
		shlExpertSystemShell.setMinimumSize(new Point(132, 10));
		shlExpertSystemShell.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/UTasLogo.png"));
		shlExpertSystemShell.setSize(735, 492);
		shlExpertSystemShell.setText("Expert System Shell");
		shlExpertSystemShell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shlExpertSystemShell, SWT.BAR);
		shlExpertSystemShell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		

		
		
		

		
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");
		
		Menu menu_3 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_3);
		
		MenuItem mntmMaual = new MenuItem(menu_3, SWT.NONE);
		mntmMaual.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/book.jpg"));
		mntmMaual.setText("Manual");
		
		
		
		MenuItem mntmAbout = new MenuItem(menu_3, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				
			    HelpDialog about = new HelpDialog(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
			    about.open();
				
			}
			
		});
		mntmAbout.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/info_new.jpg"));
		mntmAbout.setText("About");
		
		TabFolder tabFolder = new TabFolder(shlExpertSystemShell, SWT.NONE);
		
		TabItem tbtmMain = new TabItem(tabFolder, SWT.NONE);
		tbtmMain.setText("Main");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmMain.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		TabItem tbtmUserInterface = new TabItem(tabFolder, SWT.NONE);
		tbtmUserInterface.setText("User Interface");

		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
			}
		});
		tbtmUserInterface.setControl(composite);
		composite.setLayout(new GridLayout(22, false));
		
		Group grpKnowledgeBaseSelected = new Group(composite, SWT.NONE);
		GridData gd_grpKnowledgeBaseSelected = new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1);
		gd_grpKnowledgeBaseSelected.heightHint = 62;
		gd_grpKnowledgeBaseSelected.widthHint = 330;
		grpKnowledgeBaseSelected.setLayoutData(gd_grpKnowledgeBaseSelected);
		grpKnowledgeBaseSelected.setText("Knowledge Base Selected");
		
		text = new Text(grpKnowledgeBaseSelected, SWT.BORDER);
		text.setBounds(10, 32, 316, 21);
		
		
		Group grpSelectRunMethod = new Group(composite, SWT.NONE);
		GridData gd_grpSelectRunMethod = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpSelectRunMethod.widthHint = 169;
		grpSelectRunMethod.setLayoutData(gd_grpSelectRunMethod);
		grpSelectRunMethod.setText("Select Run Method");
		
		btnDefault = new Button(grpSelectRunMethod, SWT.RADIO);
		btnDefault.setBounds(10, 18, 90, 16);
		btnDefault.setText("Default");
		
		
		btnForwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnForwardChaining.setBounds(10, 40, 145, 16);
		btnForwardChaining.setText("Forward Chaining");
		
		btnBackwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnBackwardChaining.setBounds(10, 62, 145, 16);
		btnBackwardChaining.setText("Backward Chaining");
		
		Group grpSelectReasoningMethod = new Group(composite, SWT.NONE);
		GridData gd_grpSelectReasoningMethod = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 6, 1);
		gd_grpSelectReasoningMethod.widthHint = 162;
		grpSelectReasoningMethod.setLayoutData(gd_grpSelectReasoningMethod);
		grpSelectReasoningMethod.setText("Select Reasoning Method");
		
		button = new Button(grpSelectReasoningMethod, SWT.RADIO);
		button.setText("Default");
		button.setBounds(10, 18, 90, 16);
		
		btnCertainityFactor = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnCertainityFactor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCertainityFactor.setText("Certainty Factors");
		btnCertainityFactor.setBounds(10, 62, 145, 16);
		
		btnBayesianReasoning = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnBayesianReasoning.setText("Bayesian Reasoning");
		btnBayesianReasoning.setBounds(10, 40, 145, 16);
		
		
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 15, 1);
		gd_scrolledComposite.heightHint = 291;
		gd_scrolledComposite.widthHint = 326;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_2 = new Composite(scrolledComposite, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Group group = new Group(composite_2, SWT.NONE);
		GridData gd_group = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_group.heightHint = 105;
		gd_group.widthHint = 331;
		group.setLayoutData(gd_group);
		
		String[] ITEMS = {"A", "B"};   /*Test*/
		combo = new Combo(group, SWT.NONE);
		combo.setItems(ITEMS);			/*Test*/
		combo.setText("Answer");
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				System.out.println(combo.getText());
				
			}
			
			public void widgetDefaultSelected(SelectionEvent e){
				e.getSource();
				System.out.println(combo.getText());
			}
		});
		combo.setBounds(10, 90, 320, 23);
		
		text_2 = new Text(group, SWT.WRAP);
		text_2.setBounds(10, 10, 320, 74);
		scrolledComposite.setContent(composite_2);
		scrolledComposite.setMinSize(composite_2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		
		
		
		
		MenuItem mntmOpenKnowledgeBase = new MenuItem(menu_1, SWT.CASCADE);
		mntmOpenKnowledgeBase.setText("Open Knowledge Base");
		
		Menu menu_4 = new Menu(mntmOpenKnowledgeBase);
		mntmOpenKnowledgeBase.setMenu(menu_4);
		
		MenuItem mntmBoat = new MenuItem(menu_4, SWT.NONE);
		mntmBoat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				text.setText("Boat");
				btnDefault.setSelection(true);
				button.setSelection(true);
				btnCertainityFactor.setSelection(false);
				btnBayesianReasoning.setSelection(false);
				btnForwardChaining.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		mntmBoat.setText("Boat");
		
		MenuItem mntmBoatWithCf = new MenuItem(menu_4, SWT.NONE);
		mntmBoatWithCf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				text.setText("Boat with CF");
				btnDefault.setSelection(true);
				button.setSelection(false);
				btnCertainityFactor.setSelection(true);
				btnBayesianReasoning.setSelection(false);
				btnForwardChaining.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		mntmBoatWithCf.setText("Boat with CF");
		
		MenuItem mntmForecast = new MenuItem(menu_4, SWT.NONE);
		mntmForecast.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				text.setText("Forecast");
				btnCertainityFactor.setSelection(true);
				btnDefault.setSelection(true);
				button.setSelection(false);
				btnBayesianReasoning.setSelection(false);
				btnForwardChaining.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		mntmForecast.setText("Forecast");
		
		
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/Save-icon.png"));
		mntmSave.setText("Save");
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				shlExpertSystemShell.close();
				
			}
		});

		mntmExit.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/exit_new.jpg"));
		mntmExit.setText("Exit");
		
		
		
		
	/**	text_1 = new Text(scrolledComposite, SWT.WRAP | SWT.V_SCROLL);
		text_1.setToolTipText("");
		scrolledComposite.setContent(text_1);
		scrolledComposite.setMinSize(text_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		text_1.setText("Test");*/
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		
		
		
		
		
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				//tbtmUserInterface.dispose();
				
			}
		});
		btnNewButton.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/exit_new.jpg"));
		GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 4, 1);
		gd_btnNewButton.widthHint = 113;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Close Interface");
		
		TabItem tbtmDeveloperInterface = new TabItem(tabFolder, SWT.NONE);
		tbtmDeveloperInterface.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				
				
			}
		});
		tbtmDeveloperInterface.setText("Developer Interface");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmDeveloperInterface.setControl(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		
		Button btnNewButton_1 = new Button(composite_4, SWT.NONE);
		btnNewButton_1.setBounds(0, 0, 75, 25);
		btnNewButton_1.setText("New Button");
		
		Button btnNewButton_2 = new Button(composite_4, SWT.NONE);
		btnNewButton_2.setBounds(0, 0, 75, 25);
		btnNewButton_2.setText("New Button");
		
		Composite composite_5 = new Composite(composite_3, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		RuleEditorGUI ruleEditor = new RuleEditorGUI(composite_5);
		composite_5.layout();
		

	}
	public Button getBtnBackwardChaining() {
		return btnBackwardChaining;
	}
	public Button getBtnBayesianReasoning() {
		return btnBayesianReasoning;
	}
	public Button getBtnCertainityFactor() {
		return btnCertainityFactor;
	}
	public Button getBtnDefault() {
		return btnDefault;
	}
	public Button getBtnForwardChaining() {
		return btnForwardChaining;
	}
	public Button getButton() {
		return button;
	}
	public Combo getCombo() {
		return combo;
	}
}
