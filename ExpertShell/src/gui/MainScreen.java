package gui;


import java.io.Serializable;
import javax.swing.JOptionPane;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import datatypes.*;
import test.*;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;



//note - to reference a widget it must be 'exposed' by right-clicking on it in windowbuilder and selecting 'expose component' --Arie




public class MainScreen  implements Serializable {

	protected Shell shlExpertSystemShell;
	private TabFolder tabFolder;
	private Label labelCurrentKb;
	private Composite composite_3;
	private TabItem tbtmDeveloperInterface;
	private TabItem tbtmUserInterface;
	private RuleListGUI ruleList;
	public KnowledgeBase KBase;
    private ScrolledComposite scrolledComposite;
    public QuickStart compositeQS;
    public TabItem tbtmQS;
    private Test_Case test;
    private Test_Numeric testNum;
    private test_CF TestCF;
    private Test_Thermostat TestTherm;
    static Rule tRule; // a hack to get this into the description function
    public InferenceEngine Inference;
    public runGUI composite; 
    private VariablesGUI Variables;
    static MainScreen window;
    Display display;
    public RuleButtonsGUI compEditorControls;
    
	public KnowledgeBase getKnowledgeBase(){
		return KBase ;
	}
	public SelectionAdapter QSCloseListener;
	public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter AnswerComboListener;
	public SelectionAdapter CFListener;
	public SelectionAdapter CFScaleListener;

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
		TestTherm = new Test_Thermostat();
		
		//resized
		shlExpertSystemShell = new Shell();
		shlExpertSystemShell.setMinimumSize(new Point(132, 10));
		shlExpertSystemShell.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/UTasLogo.png"));

		shlExpertSystemShell.setSize(870, 703);
		shlExpertSystemShell.setText("Expert System Shell");
		shlExpertSystemShell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shlExpertSystemShell, SWT.BAR);
		shlExpertSystemShell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmExample = new MenuItem(menu, SWT.CASCADE);
		mntmExample.setText("Load Example");
		Menu menu_2 = new Menu(mntmExample);
		mntmExample.setMenu(menu_2);
		
		MenuItem mnItemCFBoat = new MenuItem(menu_2, SWT.NONE);
		mnItemCFBoat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase = TestCF.createCFKB(window);
				updateKnowledgeBase();
				labelCurrentKb.setText(KBase.getName());
			}
		});
		mnItemCFBoat.setText("Boat Example (CF)");
		
		MenuItem mnItemForecast = new MenuItem(menu_2, SWT.NONE);
		mnItemForecast.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase = test.createBoatKnowlegeBase(window);
				updateKnowledgeBase();					
				labelCurrentKb.setText(KBase.getName()); 	
			}
		});
		mnItemForecast.setText("Forecast Example (Bay.)");
		
		MenuItem mnItemNumeric = new MenuItem(menu_2, SWT.NONE);
		 mnItemNumeric.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KBase = testNum.createNumericKB(window);
				updateKnowledgeBase();
				labelCurrentKb.setText(KBase.getName());
			}
		});
		 mnItemNumeric.setText("Numeric Example");
	
		 MenuItem mnItemThermo = new MenuItem(menu_2, SWT.NONE);
		 mnItemThermo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					KBase = TestTherm.createThermostat(window);
					updateKnowledgeBase();
					labelCurrentKb.setText(KBase.getName());
				}
			});
		 mnItemThermo.setText("Thermostat Test");
		
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
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateKnowledgeBase();
			}
		});
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		
		TabItem tbtmMain = new TabItem(tabFolder, SWT.NONE);
		tbtmMain.setText("Main");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		tbtmMain.setControl(composite_1);
		composite_1.setLayout(null);
		
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
		textNewKb.setBounds(115, 446, 172, 23);
		textNewKb.setText("Enter Name");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// create new knowledgebase
				if (textNewKb.getText()=="" || textNewKb.getText().equals("Enter Name"))
				{
					JOptionPane.showMessageDialog(null, "Please enter Knowledge-Base name");
				}
					else
				{
				KnowledgeBase NewKb = new KnowledgeBase(textNewKb.getText());
				KBase = NewKb;
				updateKnowledgeBase();
				labelCurrentKb.setText(textNewKb.getText());
				}				 
			}
		});
		btnNewButton.setBounds(293, 444, 87, 25);
		btnNewButton.setText("Create New");
		
		QSCloseListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				tbtmQS.dispose();
				compositeQS =null;
			}	
		};

		
		labelCurrentKb = new Label(composite_1, SWT.NONE);
		labelCurrentKb.setAlignment(SWT.CENTER);
		labelCurrentKb.setText("No Knowledge-Base Loaded");
		labelCurrentKb.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		labelCurrentKb.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD | SWT.ITALIC));
		labelCurrentKb.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		labelCurrentKb.setBounds(116, 341, 265, 25);
		
		Button btnRun = new Button(composite_1, SWT.NONE);
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// open run tab
			Run();
				
			}
		});
		btnRun.setBounds(300, 400, 93, 25);
		btnRun.setText("Run");
		
		
		
		Button btnEdit = new Button(composite_1, SWT.NONE);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// edit the knowledgbase
				if (labelCurrentKb.getText() !="No Knowledge-Base Loaded")
				{
				if (composite_3 == null)
				{

				tbtmDeveloperInterface = new TabItem(tabFolder, SWT.NONE);
				tbtmDeveloperInterface.addDisposeListener(new DisposeListener() {
					public void widgetDisposed(DisposeEvent e) {
						
						
					}
				});
				tbtmDeveloperInterface.setText("Create/Edit Knowledge-Base");
				
				composite_3 = new Composite(tabFolder, SWT.NONE);
				tbtmDeveloperInterface.setControl(composite_3);
				composite_3.setLayout(new GridLayout(2, false));
				
				Composite header = new Composite(composite_3,SWT.BORDER);
				header.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						updateKnowledgeBase();
					}
				});
				header.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
				header.setLayout(new GridLayout(3, false));
				
				RuleUncertaintyGUI uncertaintyBox = new RuleUncertaintyGUI(header, KBase);
				Button runFromEditor = new Button(header, SWT.NONE);
				runFromEditor.setText(" Run ");
				runFromEditor.addSelectionListener(new SelectionAdapter() {
					
					public void widgetSelected(SelectionEvent e) {
						//run the thing
				
						Run();
					}
				});
				
				Label submitchanges = new Label(header, SWT.LEFT);
				submitchanges.setText("          -Press <Tab> to cycle through editor.\n          -Press <Enter> to submit changes.");
				
				Composite compListEditor = new Composite(composite_3, SWT.NONE);
				compListEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
				compListEditor.setLayout(new GridLayout(1, false));
				
				scrolledComposite = new ScrolledComposite(compListEditor, SWT.BORDER | SWT.V_SCROLL);
			//	gd_scrolledComposite.heightHint = 428;
				scrolledComposite.setAlwaysShowScrollBars(true);
				GridData gd_scrolledComposite_2 = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
				gd_scrolledComposite_2.heightHint = 400;
				gd_scrolledComposite_2.widthHint = 320;
				scrolledComposite.setLayoutData(gd_scrolledComposite_2);
				scrolledComposite.setExpandHorizontal(true);
				scrolledComposite.setExpandVertical(true);
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
				
				
				ruleList = new RuleListGUI(scrolledComposite, SWT.NONE, KBase);
				ruleList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
				ruleList.setLayout(new GridLayout(1, false));
				scrolledComposite.setContent(ruleList);
				scrolledComposite.setMinSize(ruleList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				uncertaintyBox.setRuleList(ruleList);
				
				compEditorControls = new RuleButtonsGUI(compListEditor, SWT.NONE, KBase, ruleList);				
				
				Composite compRuleEditorHolder = new Composite(composite_3, SWT.NONE);
				compRuleEditorHolder.setLayout(new FillLayout(SWT.HORIZONTAL));
				compRuleEditorHolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				
				ruleList.setEditorHolder(compRuleEditorHolder);
				compRuleEditorHolder.layout();
				composite_3.getParent().getParent().layout(true,true);
				
				
				TabItem tbtmVariables = new TabItem(tabFolder, SWT.NONE);
				tbtmVariables.setText("Variables");

				Variables = new VariablesGUI(tabFolder, SWT.NONE,KBase);
				
				//Variables.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
				tbtmVariables.setControl(Variables);
				//Variables.setLayout(new GridLayout(2, true));
				Variables.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						updateKnowledgeBase();
					}
				});
				
				}
				tabFolder.setSelection(tbtmDeveloperInterface);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please choose a Knowledge-Base first");
				}
		
			}
		});
		btnEdit.setText("Edit");
		btnEdit.setBounds(201, 400, 93, 25);
		
		Button btnLoad = new Button(composite_1, SWT.NONE);
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Object Temp;
				Temp = FileManager.loadKnowledgeFile();
				if (Temp !=null)
				{
					KBase = (KnowledgeBase) Temp;
					
					updateKnowledgeBase();
					labelCurrentKb.setText(KBase.getName());
				
			}
			}});
		btnLoad.setBounds(108, 400, 87, 25);
		btnLoad.setText("Load...");
		Label lblNewLabel_1 = new Label(composite_1, SWT.HORIZONTAL | SWT.CENTER);
		lblNewLabel_1.setText("Load");
		lblNewLabel_1.setBounds(0, 0, 836, 607);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel_1.setImage(SWTResourceManager.getImage(MainScreen.class, "/resources/ShellImage_done.jpg"));
		
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
					
					updateKnowledgeBase();
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
				updateKnowledgeBase();
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
		
		if (Variables!= null)
		{
			Variables.updateKBase(KBase);
		}
		if (composite!= null)
		{
			composite.updateKBase(KBase);
			KBase.setRunGui(composite);
		}
		 if (ruleList != null)
		 {
			 ruleList.changeKnowledgeBase(KBase);
			 compEditorControls.update(KBase);
		 }
		 
	}
	public void Run()
	{
		if (labelCurrentKb.getText() !="No Knowledge-Base Loaded")
		{
		if (composite == null ) // only one run tab can be created
		{
		tbtmUserInterface = new TabItem(tabFolder, SWT.NONE);
		tbtmUserInterface.setText("Run Knowledge-Base");
		
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
		}
		tabFolder.setSelection(tbtmUserInterface);
		updateKnowledgeBase();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please choose a Knowledge-Base first");
		}
	}
}
