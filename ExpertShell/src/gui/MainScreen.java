package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionListener;
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
import test.*;
import datatypes.KBSettings.UncertaintyManagement;

import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.StyledText;







//import STUART.ADT.Rule;
import gui.IO;



//note - to reference a widget it must be 'exposed' by right-clicking on it in windowbuilder and selecting 'expose component' --Arie




public class MainScreen  implements Serializable {

	protected Shell shlExpertSystemShell;
	private Label text;
//	private Text text_1;
//	private Text text_2;
	private Button btnBackwardChaining;
	private Button btnBayesianReasoning;
	private Button btnCertainityFactor;
	private Button btnDefault;
	private Button btnForwardChaining;
	private Button button;
//	private Button btnRun;
//	private Combo combo;
//	private Combo combo_1;
	private Scale scale;
	private Label lblCf;
	public KnowledgeBase KBase;
//	private Button OKButton;
//	private Button HowButton;
//	private Button WhyButton;
//    private Composite CompQ;
//    private Group questionGroup;
//    private Label lblNewLabel;
    private ScrolledComposite scrolledComposite;
    private Menu menu_4;
 //   private MenuItem newKB;
   // private RuleEditorGUI ruleEditor;
//    private RuleListGUI ruleList;
  //  private Combo targetvariablecombo;
//    private Boolean listChangeFlag = false;
  //  private String variableListLabel = "";
 //   private Variable selectedVariable;
   // private String selectedVariableString;
    private Test_Case test;
   // private Label lblSelectTargetVariable;
   // private Label lblWhyhow;
 //   private ArrayList<Rule> HowList = new ArrayList<Rule>();
    static Rule tRule; // a hack to get this into the description function
    public InferenceEngine Inference;
    public runGUI composite; 
    static MainScreen window;
    Display display;
    
	public KnowledgeBase getKnowledgeBase(){
		return KBase ;
	}

	public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter AnswerComboListener;
	public SelectionAdapter CFListener;
	public SelectionAdapter CFScaleListener;
	private GridData gd_SC_QuickStart;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			window = new MainScreen();
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
		display = new Display ();
		createContents();
		shlExpertSystemShell.open();
		shlExpertSystemShell.layout();
		while (!shlExpertSystemShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		//System.exit(0);
		display.dispose();
		shlExpertSystemShell.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e)
			{
				System.out.println("trying to exit now");
				System.exit(0);
			}
		});
		shlExpertSystemShell.dispose();
		System.exit(0);
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		//create default KnowledgeBase
		KBase = new KnowledgeBase("default");
		test = new Test_Case();
		//KBase = FileManager.loadKnowledgeFile();
		//KBase.SetName("boat_kb");
		//Inference = new InferenceEngine(KBase);
		
		//resized
		shlExpertSystemShell = new Shell();
		shlExpertSystemShell.setMinimumSize(new Point(132, 10));
		shlExpertSystemShell.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/UTasLogo.png"));

		shlExpertSystemShell.setSize(757, 611);
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
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		tbtmMain.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		GridData gd_lblNewLabel_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblNewLabel_1.heightHint = 515;
		gd_lblNewLabel_1.widthHint = 719;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/ShellImage_final.jpg"));
		
		TabItem tbtmQuickStart = new TabItem(tabFolder, SWT.NONE);
		tbtmQuickStart.setText("Quick Start Guide");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmQuickStart.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		ScrolledComposite SC_QuickStart = new ScrolledComposite(composite_2, SWT.BORDER | SWT.V_SCROLL);
		SC_QuickStart.setExpandHorizontal(true);
		SC_QuickStart.setAlwaysShowScrollBars(true);
		GridData gd_SC_QuickStart;
		gd_SC_QuickStart = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_SC_QuickStart.heightHint = 514;
		gd_SC_QuickStart.widthHint = 699;
		SC_QuickStart.setLayoutData(gd_SC_QuickStart);
		SC_QuickStart.setExpandVertical(true);
		
		Label lblNewLabel_2 = new Label(SC_QuickStart, SWT.WRAP | SWT.HORIZONTAL);
		lblNewLabel_2.setText("Welcome to Expert System Shell. \r\nThe following is a quick description of the purope of each page in this program and how to get started.\r\n\r\nRun Knowledgebase:\r\n* Open a knowledgebase that you would like to run:  File > Open Knowledgebase > Knowledgebase.  \r\nThe name of the knowlegebase should be displayed in the top left hand side of the screen. \r\n* Select a run method: Default (Runs Forward Chaining), Forward Chaining, or Backward Chaining.\r\n* Select a reasoning method: Default (no uncertainty), Bayesian Reasoning, or Certainty Factors.\r\n* If backward chaining is selected for the run method, the user will also be required to enter a target value from the drop down list\r\nthat will appear at the top left of the screen.\r\n* Press Run to start execution process. The Expert System Shell will ask the user to input a value for a given variable if needed. If \r\ncertainty factor is set as the reasoning method, the user will also be required to enter a certainity with the slider that will appear. \r\nThe user can ask the system \"Why?\" if they would like to ask the system why it needs that value - this will be displayed on the right \r\nside of the screen, otherwise they can press \"OK\" to continue the evaluation process. This process will continue, asking the user \r\nmore questions if required, until an answer is reached.\r\n* The Expert System will produce an answer in the same space as the question boxes. The user can ask the system \"How?\" it \r\nreached that conclusion - this will be displayed on the right side of the screen, or press \"OK\" to end the evaluation process. \r\nThe user can then re-run the knowledgebase, or open a different knowledgebase to run.\r\n\r\nCreate/Edit Knowledgebase:\r\n\r\n\r\nVariables:\r\n*Displays a list of variables on the left hand side of the screen. A user can click on these variables to select one, and it will be \r\ndisplayed in the main control box under \"Variable Name\". A user is not permitted to create a new variable in this tab.\r\n* The user can change the name of these variables, add a description, and provide it possible values. The user is also permitted to \r\nadd a Question Prompt. If no question prompt is selected, whilst running the expert system shell in the Run Knowledgebase tab, \r\nany questions related to this varaible will state: \"Enter a value for *variable*\". The user can customise this prompt if desired, \r\nfor example: \"How is the weather today?\"\r\n* The Yes/No buttons at the bottom of the screen enable the user to select whether or not to ask the user for an input value for \r\nthis variable when running the system.\r\n* The save button saves any changes made to the variables.");
		SC_QuickStart.setContent(lblNewLabel_2);
		SC_QuickStart.setMinSize(lblNewLabel_2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TabItem tbtmUserInterface = new TabItem(tabFolder, SWT.NONE);
		tbtmUserInterface.setText("Run Knowledgebase");

		KBase = test.createBoatKnowlegeBase(this);
		
		composite = new runGUI(tabFolder, SWT.NONE,KBase,shlExpertSystemShell, display);
		tbtmUserInterface.setControl(composite);
		KBase.setRunGui(composite);
		
		composite.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e)
			{
				composite.dispose();
				System.out.println("trying to exit now");
				System.exit(0);
			}
		});
		
		
		MenuItem mntmOpenKnowledgeBase = new MenuItem(menu_1, SWT.CASCADE);
		mntmOpenKnowledgeBase.setText("Open Knowledge Base");
		
		menu_4 = new Menu(mntmOpenKnowledgeBase);
		mntmOpenKnowledgeBase.setMenu(menu_4);
		
		
		MenuItem mntmBoat = new MenuItem(menu_4, SWT.NONE);
		mntmBoat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				//want to actually open boat KB when this is pressed
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
		
		KBase = test.createBoatKnowlegeBase(this);
		
		MenuItem mntmLoad = new MenuItem(menu_1, SWT.NONE);
		mntmLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			KBase = FileManager.loadKnowledgeFile();
			}
		});
		mntmLoad.setText("Load");
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				FileManager.saveKnowledgeFile(KBase);
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
		tbtmDeveloperInterface.setText("Create/Edit Knowledgebase");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmDeveloperInterface.setControl(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Composite compListEditor = new Composite(composite_3, SWT.NONE);
		compListEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		compListEditor.setLayout(new GridLayout(1, false));
		
		scrolledComposite = new ScrolledComposite(compListEditor, SWT.BORDER | SWT.V_SCROLL);
	//	gd_scrolledComposite.heightHint = 428;
		GridData gd_scrolledComposite_2 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_scrolledComposite_2.heightHint = 400;
		scrolledComposite.setLayoutData(gd_scrolledComposite_2);
		scrolledComposite.addListener(SWT.MouseWheel, new Listener() {
            public void handleEvent(Event event) {
                int wheelCount = event.count;
                wheelCount = (int) Math.ceil(wheelCount / 3.0f);
                while (wheelCount < 0) {
                    scrolledComposite.getVerticalBar().setIncrement(4);
                    wheelCount++;
                }

                while (wheelCount > 0) {
                    scrolledComposite.getVerticalBar().setIncrement(-4);
                    wheelCount--;
                }
            }
        });
		
		
		RuleListGUI ruleList = new RuleListGUI(scrolledComposite, SWT.NONE, KBase);
		scrolledComposite.setContent(ruleList);
		ruleList.setSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		//scrolledComposite.setMinSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		composite_3.getParent().getParent().layout(true,true);
		
		
		
		Composite compEditorControls = new Composite(compListEditor, SWT.NONE);
		compEditorControls.setLayout(new GridLayout(5, false));
		
		Button button_1 = new Button(compEditorControls, SWT.NONE);
		button_1.setText("\u2191");
		
		Button button_2 = new Button(compEditorControls, SWT.NONE);
		button_2.setText("\u2193");
		
		Button button_3 = new Button(compEditorControls, SWT.NONE);
		button_3.setText("Add");
		
		Button button_4 = new Button(compEditorControls, SWT.NONE);
		button_4.setText("Del");
		
		Button button_5 = new Button(compEditorControls, SWT.NONE);
		button_5.setText("Copy");
		
		Composite compRuleEditorHolder = new Composite(composite_3, SWT.NONE);
		compRuleEditorHolder.setLayout(new FillLayout(SWT.HORIZONTAL));
		compRuleEditorHolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		ruleList.setEditorHolder(compRuleEditorHolder);
		//ruleEditor = new RuleEditorGUI(compRuleEditorHolder, KBase.getRule(2), KBase);
		compRuleEditorHolder.layout();
		
		TabItem tbtmVariables = new TabItem(tabFolder, SWT.NONE);
		tbtmVariables.setText("Variables");
		
		
		
		VariablesGUI Variables = new VariablesGUI(tabFolder, SWT.NONE,KBase);
		Variables.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		tbtmVariables.setControl(Variables);
		
		
	}
	
	/*public void setVariableCombo()
	{
	listChangeFlag = true;
	targetvariablecombo.removeAll();
	
	if(KBase.getConsequentVariablesArray().length ==0)
	{
	for (Variable v : KBase.getConsequentVariablesArray())
		{
		if(v != null)
		{	
		variableListLabel = v.toString();
			targetvariablecombo.add(variableListLabel);
		}
		}
	if(targetvariablecombo.getSelectionIndex() != -1){
	selectedVariable = KBase.getConsequentVariablesArray()[targetvariablecombo.getSelectionIndex()];
	}
	
	}
	
	
	listChangeFlag = false;	
	}*/

	


/*
public static Double getCertainty(String message)
{
	JPanel panel = new JPanel();
	panel.add(new JLabel(message));
	JTextField cfField = new JTextField("0.5", 10);
	
	Hashtable labelTable = new Hashtable();
	labelTable.put( new Integer( 0 ), new JLabel("0.0") );
	labelTable.put( new Integer( 50 ), new JLabel("0.5") );
	labelTable.put( new Integer( 100 ), new JLabel("1.0") );
	
	JSlider slider = new JSlider(0,100,50);
	slider.setLabelTable( labelTable );
	slider.setPaintLabels(true);
	slider.setPaintTicks(true);
	
	class SliderListener implements ChangeListener
	{
		JSlider s; JTextField f;
		public SliderListener(JSlider s, JTextField f)
		{
			this.s = s; this.f = f;
		}
		public void stateChanged(ChangeEvent e)
		{
			f.setText("" + ((double)s.getValue())/100);
		}
	}
	class FieldListener implements ActionListener
	{
		JSlider s; JTextField f;
		public FieldListener(JSlider s, JTextField f)
		{
			this.s = s; this.f = f;
		}
		public void actionPerformed(ActionEvent e)
		{
			s.setValue((int) (Double.parseDouble(f.getText()) * 100));
		}
	}
	
	slider.addChangeListener(new SliderListener(slider, cfField));
	cfField.addActionListener(new FieldListener(slider, cfField));

	panel.add(slider);
	panel.add(cfField);

	JOptionPane.showMessageDialog(null, panel,"",JOptionPane.PLAIN_MESSAGE);
	return  (Double)(((double)slider.getValue())/100);
}
*/

//public static InferenceEngine createInferenceEngine(KnowledgeBase kb)
//{
//    InferenceEngine Inference;
//    Inference = new InferenceEngine(kb);
//    return Inference;
///}
}
