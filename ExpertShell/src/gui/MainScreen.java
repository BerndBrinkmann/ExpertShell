package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Scale;
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
import org.eclipse.wb.swt.SWTResourceManager;

import datatypes.*;
import datatypes.KBSettings.UncertaintyManagement;

import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;


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
	private Button btnRun;
	private Combo combo;
	private Combo combo_1;
	private Scale scale;
	private Label lblCf;
	private KnowledgeBase KBase;
	private Button OKButton;
	private Button HowButton;
	private Button WhyButton;
    private Composite CompQ;
    private Group questionGroup;
    private Label lblNewLabel;
    private ScrolledComposite scrolledComposite;
    private Menu menu_4;
    private MenuItem newKB;
    private RuleEditorGUI ruleEditor;

	public KnowledgeBase getKnowledgeBase(){
		return KBase ;
	}


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
	//
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
		//create default KnowledgeBase
		KBase = new KnowledgeBase("default");
		
		
		shlExpertSystemShell = new Shell();
		shlExpertSystemShell.setMinimumSize(new Point(132, 10));
		shlExpertSystemShell.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/UTasLogo.png"));

		shlExpertSystemShell.setSize(722, 563);
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
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
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
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.marginWidth = 3;
		composite.setLayout(gl_composite);
		
		Group grpKnowledgeBaseSelected = new Group(composite, SWT.NONE);
		grpKnowledgeBaseSelected.setLayout(new GridLayout(3, false));
		GridData gd_grpKnowledgeBaseSelected = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_grpKnowledgeBaseSelected.heightHint = 62;
		gd_grpKnowledgeBaseSelected.widthHint = 345;
		grpKnowledgeBaseSelected.setLayoutData(gd_grpKnowledgeBaseSelected);
		grpKnowledgeBaseSelected.setText("Knowledge Base Selected");
		
		text = new Text(grpKnowledgeBaseSelected, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_text.widthHint = 307;
		text.setLayoutData(gd_text);
				
				Label lblSelectTargetVariable = new Label(grpKnowledgeBaseSelected, SWT.NONE);
				lblSelectTargetVariable.setText("Select Target Variable");
				
				Combo combo_2 = new Combo(grpKnowledgeBaseSelected, SWT.NONE);
				combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				btnRun = new Button(grpKnowledgeBaseSelected, SWT.NONE);
				
						
								
							
						btnRun.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						btnRun.setText("Run");
						
						btnRun.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								e.getSource();
								//KnowledgeBase.validate();
							    btnCertainityFactor.getSelection();
							    btnRun.getSelection();
								if (btnCertainityFactor.getSelection()==true){
									QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ);
									askCFQuestion.addQuestion();
									//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
									scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
									CompQ.layout();
									//scrolledComposite.layout();
									button.setSelection(false);
									btnBayesianReasoning.setSelection(false);
								}else
								{
									QuestionGUI askQuestion = new QuestionGUI(CompQ);
									askQuestion.addQuestion();
									//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
									scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
									CompQ.layout();
									btnCertainityFactor.setSelection(false);
									;
									//questionGroup.layout();
								}
							}
						});
		
		Group grpSelectRunMethod = new Group(composite, SWT.NONE);
		GridData gd_grpSelectRunMethod = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpSelectRunMethod.widthHint = 159;
		grpSelectRunMethod.setLayoutData(gd_grpSelectRunMethod);
		grpSelectRunMethod.setText("Select Run Method");
		
		btnDefault = new Button(grpSelectRunMethod, SWT.RADIO);
		btnDefault.setBounds(10, 18, 90, 16);
		btnDefault.setText("Default");
		btnDefault.setSelection(true);
		
		btnForwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnForwardChaining.setBounds(10, 40, 145, 16);
		btnForwardChaining.setText("Forward Chaining");
		
		btnBackwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnBackwardChaining.setBounds(10, 62, 145, 16);
		btnBackwardChaining.setText("Backward Chaining");
		
		btnDefault.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				btnForwardChaining.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		btnForwardChaining.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				btnDefault.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		
		btnBackwardChaining.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				btnDefault.setSelection(false);
				btnForwardChaining.setSelection(false);
			}
		});
		
		Group grpSelectReasoningMethod = new Group(composite, SWT.NONE);
		GridData gd_grpSelectReasoningMethod = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_grpSelectReasoningMethod.widthHint = 157;
		grpSelectReasoningMethod.setLayoutData(gd_grpSelectReasoningMethod);
		grpSelectReasoningMethod.setText("Select Reasoning Method");
		
		button = new Button(grpSelectReasoningMethod, SWT.RADIO);
		button.setText("Default");
		button.setBounds(10, 18, 90, 16);
		button.setSelection(true);
		
		btnCertainityFactor = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnCertainityFactor.setText("Certainty Factors");
		btnCertainityFactor.setBounds(10, 62, 145, 16);
		
		btnBayesianReasoning = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnBayesianReasoning.setText("Bayesian Reasoning");
		btnBayesianReasoning.setBounds(10, 40, 145, 16);
		
		
		scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
			}
		});
		scrolledComposite.setAlwaysShowScrollBars(true);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_scrolledComposite.heightHint = 381;
		gd_scrolledComposite.widthHint = 329;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		CompQ = new Composite(scrolledComposite, SWT.NONE);
		CompQ.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				e.getSource();
				//CompQ.update();
			}
		});
		CompQ.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		CompQ.setLayout(new GridLayout(1, false));
		

		//composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));

		
		questionGroup = new Group(CompQ, SWT.NONE);
		questionGroup.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		questionGroup.setLayout(new GridLayout(3, false));
		lblNewLabel = new Label(questionGroup, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_lblNewLabel.widthHint = 303;
		gd_lblNewLabel.heightHint = 65;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		String[] ITEMS = {"A", "B"};   /*Test*/
		combo_1 = new Combo(questionGroup, SWT.NONE);
		//different from QuestionGUI
		GridData gd_combo_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_1.widthHint = 276;
		combo_1.setLayoutData(gd_combo_1);
		combo_1.setItems(ITEMS);			
		combo_1.setText("Answer");
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				System.out.println(combo_1.getText());
				
			}
			
			public void widgetDefaultSelected(SelectionEvent e){
				e.getSource();
				System.out.println(combo_1.getText());
			}
		});

			
		lblCf = new Label(questionGroup, SWT.NONE);
		lblCf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		//GridData gd_lblCf = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		//gd_lblCf.widthHint = 101;
		//lblCf.setLayoutData(gd_lblCf);
		lblCf.setText("CF%");
		lblCf.setVisible(false);
		
		scale = new Scale(questionGroup, SWT.NONE);
		scale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				int perspectivevalue=scale.getSelection();
				GridData gd_lblCf = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
				gd_lblCf.widthHint = 101;
				lblCf.setLayoutData(gd_lblCf);
				lblCf.setText(""+(perspectivevalue));
			}
		});
		GridData gd_scale = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_scale.widthHint = 216;
		scale.setLayoutData(gd_scale);
		scale.setVisible(false);
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				button.getSelection();
				if (button.getSelection()==true){
					scale.setVisible(false);
					lblCf.setVisible(false);
					btnCertainityFactor.setSelection(false);
					btnBayesianReasoning.setSelection(false);
		}
			}
		});
		
		btnBayesianReasoning.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				btnBayesianReasoning.getSelection();
				if (btnBayesianReasoning.getSelection()==true){
					scale.setVisible(false);
					lblCf.setVisible(false);
					btnCertainityFactor.setSelection(false);
					button.setSelection(false);
		}
			}
		});
		
		
		btnCertainityFactor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				btnCertainityFactor.getSelection();
				if (btnCertainityFactor.getSelection()==true){
					scale.setVisible(true);
					lblCf.setVisible(true);
					button.setSelection(false);
					btnBayesianReasoning.setSelection(false);
		}	
			}
		});
		
		
		
		WhyButton = new Button(questionGroup, SWT.NONE);
		WhyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
			}
		});
		GridData gd_WhyButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_WhyButton.widthHint = 54;
		WhyButton.setLayoutData(gd_WhyButton);
		WhyButton.setText("Why?");
		
		HowButton = new Button(questionGroup, SWT.NONE);
		HowButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
			}
		});
		GridData gd_HowButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_HowButton.widthHint = 54;
		HowButton.setLayoutData(gd_HowButton);
		HowButton.setText("How?");
		
		OKButton = new Button(questionGroup, SWT.NONE);
		OKButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				// Attempt at creating new Question box...
	
					}
				});
				
					  
		OKButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton.setText("OK");
		
		scrolledComposite.setContent(CompQ);
		scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		

		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(composite, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		GridData gd_scrolledComposite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_scrolledComposite_1.heightHint = 377;
		gd_scrolledComposite_1.widthHint = 320;
		scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		Label lblWhyhow = new Label(scrolledComposite_1, SWT.WRAP);
		lblWhyhow.setText("Why/How");
		scrolledComposite_1.setContent(lblWhyhow);
		scrolledComposite_1.setMinSize(lblWhyhow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		MenuItem mntmOpenKnowledgeBase = new MenuItem(menu_1, SWT.CASCADE);
		mntmOpenKnowledgeBase.setText("Open Knowledge Base");
		
		menu_4 = new Menu(mntmOpenKnowledgeBase);
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
				if (btnCertainityFactor.getSelection()==true){
					scale.setVisible(true);
					lblCf.setVisible(true);
				}
				if (btnCertainityFactor.getSelection()==false){
					scale.setVisible(false);
					lblCf.setVisible(false);
				}
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
				if (btnCertainityFactor.getSelection()==true){
					scale.setVisible(true);
					lblCf.setVisible(true);
				}
				if (btnCertainityFactor.getSelection()==false){
					scale.setVisible(false);
					lblCf.setVisible(false);
				}
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
				btnCertainityFactor.getSelection();
				if (btnCertainityFactor.getSelection()==true){
					scale.setVisible(true);
					lblCf.setVisible(true);
				}
				if (btnCertainityFactor.getSelection()==false){
					scale.setVisible(false);
					lblCf.setVisible(false);
				}
				btnBayesianReasoning.setSelection(false);
				btnForwardChaining.setSelection(false);
				btnBackwardChaining.setSelection(false);
			}
		});
		mntmForecast.setText("Forecast");
		
		
		
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
			//Creating menu items dynamically for new knowledge base
			// needs save all rules and settings etc. - look for FileManager
				newKB = new MenuItem(menu_4, SWT.NONE);
				//newKB.addSelectionListener(this);
				newKB.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						e.getSource();
			}
		});
				newKB.setText("newKB"+ KBase.getName());
				
			}
		});
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
		
		
		
		
	/*	text_1 = new Text(scrolledComposite, SWT.WRAP | SWT.V_SCROLL);
		text_1.setToolTipText("");
		scrolledComposite.setContent(text_1);
		scrolledComposite.setMinSize(text_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		
	/*	Button btnNewButton = new Button(composite, SWT.NONE);
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
		btnNewButton.setText("Close Interface");*/

		
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
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase.setUncertaintyMethod(UncertaintyManagement.NONE);
				ruleEditor.updateUncertainty();
				ruleEditor.ruleGrid.getParent().getParent().layout(true,true);
			}
		});
		btnNewButton_1.setBounds(0, 0, 75, 25);
		btnNewButton_1.setText("none");
		
		Button btnNewButton_2 = new Button(composite_4, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase.setUncertaintyMethod(UncertaintyManagement.CF);
				ruleEditor.updateUncertainty();
				ruleEditor.ruleGrid.getParent().getParent().layout(true,true);
			}
		});
		btnNewButton_2.setBounds(0, 0, 75, 25);
		btnNewButton_2.setText("cf");
		
		Button btnNewButton = new Button(composite_4, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase.setUncertaintyMethod(UncertaintyManagement.BAYESIAN);
				ruleEditor.updateUncertainty();
				ruleEditor.ruleGrid.getParent().getParent().layout(true,true);
			}
		});
		btnNewButton.setText("bays");
		
		Composite composite_5 = new Composite(composite_3, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ruleEditor = new RuleEditorGUI(composite_5, KBase);
		composite_5.layout();
		
		TabItem tbtmVariables = new TabItem(tabFolder, SWT.NONE);
		tbtmVariables.setText("Variables");
		
		VariablesGUI Variables = new VariablesGUI(tabFolder, SWT.NONE,KBase);
		tbtmVariables.setControl(Variables);
		
		

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

