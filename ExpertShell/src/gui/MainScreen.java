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

import org.apache.commons.lang3.SerializationUtils;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;



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
	private Combo comboExample;
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
    private VariablesGUI Variables;
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
		composite_1.setLayout(null);
		
		Button btnLoadKbFromFile = new Button(composite_1, SWT.NONE);
		btnLoadKbFromFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Object Temp;
				Temp = FileManager.loadKnowledgeFile();
				if (Temp !=null)
				{
					KBase = (KnowledgeBase) Temp;
					KBase.setRunGui(composite);
					Variables.updateKBase(KBase);
					composite.updateKBase(KBase);
				}

				
			}
		});
		btnLoadKbFromFile.setBounds(58, 274, 86, 25);
		btnLoadKbFromFile.setText("Load from file");
		
		comboExample = new Combo(composite_1, SWT.NONE);
		comboExample.setBounds(160, 245, 139, 23);
		comboExample.add("Weather");
		comboExample.add("Weather Numeric");

		Button btnLoadExample = new Button(composite_1, SWT.NONE);
		btnLoadExample.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// load an example
				if (comboExample.getSelectionIndex() == 0)
				{
					KBase = test.createBoatKnowlegeBase(window);
					KBase.setRunGui(composite);
					Variables.updateKBase(KBase);
					composite.updateKBase(KBase);
				}
			}
		});
		btnLoadExample.setBounds(58, 243, 85, 25);
		btnLoadExample.setText("Load example");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(58, 377, 63, 25);
		btnNewButton.setText("Edit");
		
		Button btnRun = new Button(composite_1, SWT.NONE);
		btnRun.setBounds(127, 377, 75, 25);
		btnRun.setText("Run");
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.setBounds(492, 35, 102, 25);
		btnNewButton_1.setText("Quick Start Guide");
		
		Button btnNewButton_2 = new Button(composite_1, SWT.NONE);
		btnNewButton_2.setBounds(622, 35, 75, 25);
		btnNewButton_2.setText("Manuel");
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setText("Load");
		lblNewLabel_1.setBounds(0, 0, 730, 528);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
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
		
		Composite composite_4 = new Composite(SC_QuickStart, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite_4, SWT.WRAP);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 682;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Welcome to The Expert System Shell. The following is a quick description of each page in this program and how to get started.\r\n");
		
		Label lblNewLabelOpen = new Label(composite_4, SWT.NONE);
		lblNewLabelOpen.setText("\r\nOpen/Save a Knowledgebase:");
		
		Label lblNewLabelOpenPic = new Label(composite_4, SWT.NONE);
		lblNewLabelOpenPic.setText("\r\n open/save image needs inserting here:");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblRunKnowledgebase = new Label(composite_4, SWT.NONE);
		lblRunKnowledgebase.setText("\r\nRun Knowledgebase:");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblStartingTheEvaluation = new Label(composite_4, SWT.NONE);
		lblStartingTheEvaluation.setText("Starting the Evaluation Process");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/runpic.PNG"));
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_3.heightHint = 287;
		gd_lblNewLabel_3.widthHint = 643;
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/runInfo.PNG"));
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_4 = new Label(composite_4, SWT.NONE);
		lblNewLabel_4.setText("Finalising the Evaluation Process\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_4Pic = new Label(composite_4, SWT.NONE);
		lblNewLabel_4Pic.setText("How image needs to be inserted here\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_5 = new Label(composite_4, SWT.NONE);
		lblNewLabel_5.setText("\r\nCreate/Edit Knowledgebase:");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_5Pic = new Label(composite_4, SWT.NONE);
		lblNewLabel_5Pic.setText("rule editor page image needs to be inserted here\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_5Info = new Label(composite_4, SWT.NONE);
		lblNewLabel_5Info.setText("rule editor page info needs to be inserted here\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_6 = new Label(composite_4, SWT.NONE);
		lblNewLabel_6.setText("\r\nVariables:");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_6Pic = new Label(composite_4, SWT.NONE);
		lblNewLabel_6Pic.setText("variables page image needs to be inserted here\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel_6Info = new Label(composite_4, SWT.NONE);
		lblNewLabel_6Info.setText("varaibles page info needs to be inserted here\r\n");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label ReferToManual = new Label(composite_4, SWT.NONE);
		ReferToManual.setText("For further information, the complete manual can be found in\r\nHelp>Manual");
		SC_QuickStart.setContent(composite_4);
		SC_QuickStart.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TabItem tbtmUserInterface = new TabItem(tabFolder, SWT.NONE);
		tbtmUserInterface.setText("Run Knowledgebase");
		
		if (KBase.getName() == "default")
		{
			KBase = test.createBoatKnowlegeBase(this);
		}
		
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
		
		MenuItem mntmOpenKnowlledgebase = new MenuItem(menu, SWT.CASCADE);
		mntmOpenKnowlledgebase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object Temp;
				Temp = FileManager.loadKnowledgeFile();
				if (Temp !=null)
				{
					KBase = (KnowledgeBase) Temp;
					KBase.setRunGui(composite);
					Variables.updateKBase(KBase);
					composite.updateKBase(KBase);
				}

				
			}
		});
		mntmOpenKnowlledgebase.setText("Open Knowledgebase");
		
		MenuItem mntmSaveKnowledgebase = new MenuItem(menu, SWT.CASCADE);
		mntmSaveKnowledgebase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//is executed if save knowledgebase is selected
				e.getSource();
				FileManager.saveKnowledgeFile(KBase);
				KBase.setRunGui(composite);
				composite.updateKBase(KBase);
		//		composite;
			}

		});
		mntmSaveKnowledgebase.setText("Save Knowledgebase");
		
		



		
		
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
		
		
		
		Variables = new VariablesGUI(tabFolder, SWT.NONE,KBase);
		Variables.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		tbtmVariables.setControl(Variables);
		
		
	}
	public void updateKnowledgeBase()
	{
		composite.updateKBase(KBase);
	}
}
