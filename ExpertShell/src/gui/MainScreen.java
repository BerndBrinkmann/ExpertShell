package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
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
	private TabFolder tabFolder;
	private Combo comboExample;
	private Label labelCurrentKb;
	private Composite composite_3;
	private TabItem tbtmDeveloperInterface;
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
    public QuickStart compositeQS;
 //   private MenuItem newKB;
   // private RuleEditorGUI ruleEditor;
//    private RuleListGUI ruleList;
  //  private Combo targetvariablecombo;
//    private Boolean listChangeFlag = false;
  //  private String variableListLabel = "";
 //   private Variable selectedVariable;
   // private String selectedVariableString;
    private Test_Case test;
    private Test_Numeric testNum;
    private test_CF TestCF;
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
	private Text textNewKb;

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
		testNum = new Test_Numeric();
		TestCF = new test_CF();
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
		
		MenuItem menuItemQuickStart = new MenuItem(menu_3, SWT.NONE);
		menuItemQuickStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileManager.openQuickStart();
			}
		});
		
		menuItemQuickStart.setText("Quick Start Guide");
		menuItemQuickStart.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/speech-balloon-green-q-icon.png"));
		
		MenuItem mntmMaual = new MenuItem(menu_3, SWT.NONE);
		mntmMaual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//open the PDF of the manual
			FileManager.openManual();
			}
		});
		mntmMaual.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/book.jpg"));
		mntmMaual.setText("Manual");
		
		MenuItem mntmAbout = new MenuItem(menu_3, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
			    HelpDialog about = new HelpDialog(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
			    about.open();
				//JOptionPane.showMessageDialog(null, "This Expert System Shell was designed by: \r\n\r\nArie Westland, Bernd Brinkmann, Jessica Taylor, Mandy Bester, and Nathan Cortes.\r\n\r\nFor the partial requirements of KNE441: Computational Intelligence, at the University of Tasmania, 2014.");
			}
			
		});
		mntmAbout.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/info_new.jpg"));
		mntmAbout.setText("About");
		
		tabFolder = new TabFolder(shlExpertSystemShell, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		
		TabItem tbtmMain = new TabItem(tabFolder, SWT.NONE);
		tbtmMain.setText("Main");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		tbtmMain.setControl(composite_1);
		composite_1.setLayout(null);
		
		comboExample = new Combo(composite_1, SWT.NONE);

		comboExample.setBounds(144, 293, 139, 23);
		comboExample.setText("Select Example");
		comboExample.add("Forcast (Linguistic)");
		comboExample.add("Forcast (Numeric)");
		comboExample.add("Boat (Certainty Factor)");

		Button btnLoadExample = new Button(composite_1, SWT.NONE);
		btnLoadExample.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// load an example
				if (comboExample.getSelectionIndex() == 0)
				{
					KBase = test.createBoatKnowlegeBase(window);
					KBase.setRunGui(composite);
					if (Variables!= null)
					{
						Variables.updateKBase(KBase);
					}
					

					if (composite!= null)
					{
						composite.updateKBase(KBase);
					}
					
					labelCurrentKb.setText(KBase.getName());
				}
				if(comboExample.getSelectionIndex() == 1)
				{
					KBase = testNum.createNumericKB(window);
					KBase.setRunGui(composite);
					if (Variables!= null)
					{
						Variables.updateKBase(KBase);
					}
					

					if (composite!= null)
					{
						composite.updateKBase(KBase);
					}
					
					labelCurrentKb.setText(KBase.getName());
				}
				if(comboExample.getSelectionIndex() == 2)
				{
					KBase = TestCF.createCFKB(window);
					KBase.setRunGui(composite);
					if (Variables!= null)
					{
						Variables.updateKBase(KBase);
					}
					

					if (composite!= null)
					{
						composite.updateKBase(KBase);
					}
					
					labelCurrentKb.setText(KBase.getName());
				}
	
			}
		});
		btnLoadExample.setBounds(24, 291, 107, 25);
		btnLoadExample.setText("Load example");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// create new knowledgebase
				if (textNewKb.getText()=="" || textNewKb.getText().equals("Enter Name"))
				{
					JOptionPane.showMessageDialog(null, "Please enter Knowledgebase Name");
				}
					else
				{
				KnowledgeBase NewKb = new KnowledgeBase(textNewKb.getText());
				KBase = NewKb;
				KBase.setRunGui(composite);
				if (Variables!= null)
				{
					Variables.updateKBase(KBase);
				}
				if (composite!= null)
				{
					composite.updateKBase(KBase);
				}
				labelCurrentKb.setText(textNewKb.getText());
				}				 
			}
		});
		btnNewButton.setBounds(24, 331, 107, 25);
		btnNewButton.setText("Create new");
		
		Button btnRun = new Button(composite_1, SWT.NONE);
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// open run tab
				if (labelCurrentKb.getText() !="No Knowledgebase loaded")
				{
				if (composite == null ) // only one run tab can be created
				{
				TabItem tbtmUserInterface = new TabItem(tabFolder, SWT.NONE);
				tbtmUserInterface.setText("Run Knowledgebase");
				
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
				tabFolder.setSelection(tbtmUserInterface);
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please choose a Knowledgebase first");
				}
				
			}
		});
		btnRun.setBounds(24, 393, 107, 25);
		btnRun.setText("Run");
		
		Button btnEdit = new Button(composite_1, SWT.NONE);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// edit the knowledgbase
				if (labelCurrentKb.getText() !="No Knowledgebase loaded")
				{
				if (composite_3 == null)
				{

				tbtmDeveloperInterface = new TabItem(tabFolder, SWT.NONE);
				tbtmDeveloperInterface.addDisposeListener(new DisposeListener() {
					public void widgetDisposed(DisposeEvent e) {
						
						
					}
				});
				tbtmDeveloperInterface.setText("Create/Edit Knowledgebase");
				
				composite_3 = new Composite(tabFolder, SWT.NONE);
				tbtmDeveloperInterface.setControl(composite_3);
				composite_3.setLayout(new GridLayout(2, false));
				
				Composite header = new Composite(composite_3,SWT.BORDER);
				header.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
				header.setLayout(new GridLayout(2, false));
				
				RuleUncertaintyGUI uncertaintyBox = new RuleUncertaintyGUI(header, KBase);
				
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
				
				
				
				
				
				RuleButtonsGUI compEditorControls = new RuleButtonsGUI(compListEditor, SWT.NONE, KBase, ruleList);
				
				
				
				Composite compRuleEditorHolder = new Composite(composite_3, SWT.NONE);
				compRuleEditorHolder.setLayout(new FillLayout(SWT.HORIZONTAL));
				compRuleEditorHolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				
				ruleList.setEditorHolder(compRuleEditorHolder);
				//ruleEditor = new RuleEditorGUI(compRuleEditorHolder, KBase.getRule(2), KBase);
				compRuleEditorHolder.layout();
				composite_3.getParent().getParent().layout(true,true);
				
				
				TabItem tbtmVariables = new TabItem(tabFolder, SWT.NONE);
				tbtmVariables.setText("Variables");
				
				
				
				Variables = new VariablesGUI(tabFolder, SWT.NONE,KBase);
				Variables.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
				tbtmVariables.setControl(Variables);
				
				tabFolder.setSelection(tbtmDeveloperInterface);
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please choose a Knowledgebase first");
				}
		
			}
		});
		btnEdit.setText("Edit");
		btnEdit.setBounds(144, 393, 107, 25);
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem tbtmQS = new TabItem(tabFolder, SWT.NONE);
				tbtmQS.setText("Quick Start Guide");
				
				compositeQS = new QuickStart(tabFolder, SWT.NONE);
				tbtmQS.setControl(compositeQS);

				
			}
		});
		btnNewButton_1.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/speech-balloon-green-q-icon.png"));
		btnNewButton_1.setBounds(10, 10, 26, 25);
		
		textNewKb = new Text(composite_1, SWT.BORDER);
		textNewKb.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textNewKb.getText()==""){
					textNewKb.setText("Enter Name");
				}
			}
		});
		textNewKb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				textNewKb.setText("");
			}
		});
		textNewKb.setBounds(144, 333, 139, 23);
		textNewKb.setText("Enter Name");

		
		labelCurrentKb = new Label(composite_1, SWT.BORDER);
		labelCurrentKb.setAlignment(SWT.CENTER);
		labelCurrentKb.setText("No Knowledgebase loaded");
		labelCurrentKb.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		labelCurrentKb.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD | SWT.ITALIC));
		labelCurrentKb.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		labelCurrentKb.setBounds(24, 245, 259, 25);
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setText("Load");
		lblNewLabel_1.setBounds(0, 0, 720, 518);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel_1.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/ShellImage_final2.jpg"));
		
		MenuItem mntmLoad = new MenuItem(menu_1, SWT.NONE);
		mntmLoad.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/folder.png"));
		mntmLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object Temp;
				Temp = FileManager.loadKnowledgeFile();
				if (Temp !=null)
				{
					KBase = (KnowledgeBase) Temp;
					KBase.setRunGui(composite);
					if (composite!= null)
					{
						composite.updateKBase(KBase);
					}
					if (Variables!= null)
					{
						Variables.updateKBase(KBase);
					}
					labelCurrentKb.setText(KBase.getName());
				}
			}
		});
		mntmLoad.setText("Load");
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				FileManager.saveKnowledgeFile(KBase);

				KBase.setRunGui(composite);
				if (Variables!= null)
				{
					Variables.updateKBase(KBase);
				}
				if (composite!= null)
				{
					composite.updateKBase(KBase);
				}
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
		

		
	}
	public void updateKnowledgeBase()
	{
		composite.updateKBase(KBase);
	}
}
