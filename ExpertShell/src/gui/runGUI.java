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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import test.Test_Case;
import datatypes.*;

public class runGUI extends Composite implements Serializable {
	protected Shell shlExpertSystemShell;
	private Label text;
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
    private RuleListGUI ruleList;
    private Combo targetvariablecombo;
    private Boolean listChangeFlag = false;
    private String variableListLabel = "";
    private Variable selectedVariable;
    private String selectedVariableString;
    private Test_Case test;
    private Label lblSelectTargetVariable;
    private Label lblWhyhow;
    private ArrayList<Rule> HowList = new ArrayList<Rule>();
    static Rule tRule; // a hack to get this into the description function
    private InferenceEngine Inference;
	
    public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter AnswerComboListener;
	public SelectionAdapter CFListener;
	public SelectionAdapter CFScaleListener;
	private GridData gd_SC_QuickStart;
    
    /**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public runGUI(Composite parent, int style,KnowledgeBase kb,Shell sh) {
		
		super(parent, style);
		shlExpertSystemShell = sh;
		KBase = kb;
		//Inference = MainScreen.createInferenceEngine(KBase);	
		Inference = new InferenceEngine(KBase);
	
	
	this.addControlListener(new ControlAdapter() {
		@Override
		public void controlResized(ControlEvent e) {
		}
	});
	

		
	GridLayout gl_composite = new GridLayout(4, false);
	gl_composite.marginWidth = 3;
	this.setLayout(gl_composite);
	
	
	
	Group grpKnowledgeBaseSelected = new Group(this, SWT.NONE);
	grpKnowledgeBaseSelected.setLayout(new GridLayout(3, false));
	GridData gd_grpKnowledgeBaseSelected = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
	gd_grpKnowledgeBaseSelected.heightHint = 62;
	gd_grpKnowledgeBaseSelected.widthHint = 345;
	grpKnowledgeBaseSelected.setLayoutData(gd_grpKnowledgeBaseSelected);
	grpKnowledgeBaseSelected.setText("Knowledge Base Selected");
	
	text = new Label(grpKnowledgeBaseSelected, SWT.NONE);
	GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
	gd_text.widthHint = 323;
	text.setLayoutData(gd_text);
			
			lblSelectTargetVariable = new Label(grpKnowledgeBaseSelected, SWT.NONE);
			lblSelectTargetVariable.setText("Select Target Variable");
			lblSelectTargetVariable.setVisible(false);
			
			targetvariablecombo = new Combo(grpKnowledgeBaseSelected, SWT.READ_ONLY);
			targetvariablecombo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(targetvariablecombo.getSelectionIndex() != -1)
					{
						selectedVariable = KBase.getConsequentVariablesArray()[targetvariablecombo.getSelectionIndex()];
					}
				}
			});
			targetvariablecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			targetvariablecombo.setVisible(false);
			this.getTargetVariableCombo();
			
			
			btnRun = new Button(grpKnowledgeBaseSelected, SWT.NONE);
			btnRun.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			btnRun.setText("Run");
						
	
	Group grpSelectRunMethod = new Group(this, SWT.NONE);
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
			btnDefault.getSelection();
			if (btnDefault.getSelection()==true){
			btnForwardChaining.setSelection(false);
			btnBackwardChaining.setSelection(false);
			targetvariablecombo.setVisible(false);
			lblSelectTargetVariable.setVisible(false);
			}
		}
	});
	btnForwardChaining.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			btnForwardChaining.getSelection();
			if(btnForwardChaining.getSelection()==true){
			btnDefault.setSelection(false);
			btnBackwardChaining.setSelection(false);
			targetvariablecombo.setVisible(false);
			lblSelectTargetVariable.setVisible(false);
			}
		}
	});
	
	btnBackwardChaining.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			btnBackwardChaining.getSelection();
			if(btnBackwardChaining.getSelection()==true){
			btnDefault.setSelection(false);
			btnForwardChaining.setSelection(false);
			targetvariablecombo.setVisible(true);
			lblSelectTargetVariable.setVisible(true);
			}
		}
	});
	
	Group grpSelectReasoningMethod = new Group(this, SWT.NONE);
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
	btnCertainityFactor.setSelection(false);
	
	btnBayesianReasoning = new Button(grpSelectReasoningMethod, SWT.RADIO);
	btnBayesianReasoning.setText("Bayesian Reasoning");
	btnBayesianReasoning.setBounds(10, 40, 145, 16);
	btnBayesianReasoning.setSelection(false);
	
	
	scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
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

	/**Original question box for reference*/
	/*questionGroup = new Group(CompQ, SWT.NONE);
	questionGroup.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
	questionGroup.setLayout(new GridLayout(3, false));
	lblNewLabel = new Label(questionGroup, SWT.NONE);
	GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
	gd_lblNewLabel.widthHint = 303;
	gd_lblNewLabel.heightHint = 65;
	lblNewLabel.setLayoutData(gd_lblNewLabel);
	String[] ITEMS = {"A", "B"};   /*Test
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
	scale.setVisible(false);*/
	
	button.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			button.getSelection();
			if (button.getSelection()==true){
				//scale.setVisible(false);
				//lblCf.setVisible(false);
				btnCertainityFactor.setSelection(false);
				btnBayesianReasoning.setSelection(false);
				KBase.setUncertaintyMethod(KBSettings.UncertaintyManagement.NONE);
	}
		}
	});
	
	btnBayesianReasoning.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			btnBayesianReasoning.getSelection();
			if (btnBayesianReasoning.getSelection()==true){
				//scale.setVisible(false);
				//lblCf.setVisible(false);
				btnCertainityFactor.setSelection(false);
				button.setSelection(false);
				KBase.setUncertaintyMethod(KBSettings.UncertaintyManagement.BAYESIAN);
	}
		}
	});
	
	
	btnCertainityFactor.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			btnCertainityFactor.getSelection();
			if (btnCertainityFactor.getSelection()==true){
				//scale.setVisible(true);
				//lblCf.setVisible(true);
				button.setSelection(false);
				btnBayesianReasoning.setSelection(false);
				KBase.setUncertaintyMethod(KBSettings.UncertaintyManagement.CF);
	}	
		}
	});
	
	
	btnRun.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			
			if(text.getText()==""){
				
				NoRun noKB = new NoRun(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
				noKB.open();	
			}
			
			if(targetvariablecombo.isVisible()==true && targetvariablecombo.getText()==""){
				
				NoRunV noVar = new NoRunV(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
				noVar.open();
			}
			
			//There are issues with this... causes screen to crash
		    if(btnForwardChaining.getSelection()==true || btnDefault.getSelection()==true){
				KBase.validate();
				Variable result = Inference.solveForwardChaining();
				HowList = Inference.getHowList();
				IO.displayResults(result, Inference.getHowList(), KBase);	
		    	
			}else if(btnBackwardChaining.getSelection()==true){
				KBase.validate();
				Variable result = Inference.solveBackwardChaining();
				HowList = Inference.getHowList();
				IO.displayResults(result, Inference.getHowList(), KBase);		
		    }
			
			
			// this should not be handled by run button, KBase needs to tell it when to ask a new question
		    /*
		    btnCertainityFactor.getSelection();
		    btnRun.getSelection();
			if (btnCertainityFactor.getSelection()==true){
				QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
				askCFQuestion.addQuestion();
				//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
				//scrolledComposite.layout();
				button.setSelection(false);
				btnBayesianReasoning.setSelection(false);
			}else
			{
				QuestionGUI askQuestion = new QuestionGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
				askQuestion.addQuestion();
				//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
				btnCertainityFactor.setSelection(false);
				//End of section that needs to be moved
				
			}
			*/
			//IO.setMainFrame(OKButton);

			/**This code cause GUI to close when called - issue somewhere*/
		//	FileManager.saveKnowledgeFile(KBase);

			//KBase.validate();
			//Variable result = Inference.solveForwardChaining();
			//HowList = Inference.getHowList();
			//IO.displayResults(result, Inference.getHowList(), KBase);	

		}
	});
	
	
	
	
	ScrolledComposite scrolledComposite_1 = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
	scrolledComposite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
	GridData gd_scrolledComposite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
	gd_scrolledComposite_1.heightHint = 377;
	gd_scrolledComposite_1.widthHint = 320;
	scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
	scrolledComposite_1.setExpandHorizontal(true);
	scrolledComposite_1.setExpandVertical(true);
	
	
	lblWhyhow = new Label(scrolledComposite_1, SWT.WRAP);
	lblWhyhow.setText("Why/How");
	scrolledComposite_1.setContent(lblWhyhow);
	scrolledComposite_1.setMinSize(lblWhyhow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	new Label(this, SWT.NONE);
	new Label(this, SWT.NONE);
	new Label(this, SWT.NONE);
	new Label(this, SWT.NONE);
	/**Original Question Box for reference*/
	/*
	WhyButton = new Button(questionGroup, SWT.NONE);
	WhyButton.addSelectionListener(new SelectionAdapter() {
		@Override
		//I Don't understand what "thisRule" is doing - returns Null, need to replace with actual rule?
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			//lblWhyhow.setText(IO.displayWhyMessage());
			StringBuilder s = new StringBuilder();
			s.append("\nI am trying to evaluate the rule\n");
			s.append(tRule != null ? tRule.toString() : "null");
			s.toString();
			lblWhyhow.setText(""+s);
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
			//lblWhyhow.setText(IO.displayHowMessage(howList);
			
			if(HowList.isEmpty())
			{
				lblWhyhow.setText("\nA result was not reached\n");
			}
			else
			{
			
				lblWhyhow.setText("\nThe result was reached by firing these rules in this order\n");
				for(Rule r : HowList)
				{
					lblWhyhow.setText(r.toString());
				}
			}	
			
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
	OKButton.setText("OK");*/
	
	scrolledComposite.setContent(CompQ);
	scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
/*
	ScrolledComposite scrolledComposite_1 = new ScrolledComposite(composite, SWT.BORDER | SWT.V_SCROLL);
	scrolledComposite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
	GridData gd_scrolledComposite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
	gd_scrolledComposite_1.heightHint = 377;
	gd_scrolledComposite_1.widthHint = 320;
	scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
	scrolledComposite_1.setExpandHorizontal(true);
	scrolledComposite_1.setExpandVertical(true);
	
	lblWhyhow = new Label(scrolledComposite_1, SWT.WRAP);
	lblWhyhow.setText("Why/How");
	scrolledComposite_1.setContent(lblWhyhow);
	scrolledComposite_1.setMinSize(lblWhyhow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	new Label(composite, SWT.NONE);
	new Label(composite, SWT.NONE);
	new Label(composite, SWT.NONE);
	new Label(composite, SWT.NONE);
*/	
	WhyListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();	
			StringBuilder s = new StringBuilder();
			s.append("\nI am trying to evaluate the rule\n");
			s.append(tRule != null ? tRule.toString() : "null");
			s.toString();
			lblWhyhow.setText(""+s);
		}
	};
	HowListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			if(HowList.isEmpty())
			{
				lblWhyhow.setText("\nA result was not reached\n");
			}
			else
			{
			
				lblWhyhow.setText("\nThe result was reached by firing these rules in this order\n");
				for(Rule r : HowList)
				{
					lblWhyhow.setText(r.toString());
				}
			}	
		}	
	};
	OKListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
		}
	};
	AnswerComboListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			//needs to find where varaibles are!!!
			System.out.println(combo_1.getText());
		}	
	};
	CFListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
		}
	};
	CFScaleListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			/*int perspectivevalue=scale.getSelection();
			GridData gd_lblCf = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
			gd_lblCf.widthHint = 101;
			lblCf.setLayoutData(gd_lblCf);
			lblCf.setText(""+(perspectivevalue));*/
		}
	};
	
}

	public void getTargetVariableCombo()
	{
		if(KBase.getConsequentVariablesArray().length !=0)
		{
//			System.out.println("enters if statement");
			String consequentArrayString[] = new String[KBase.getConsequentVariablesArray().length];
					for (int i=0; i<consequentArrayString.length; i++)
					{
						consequentArrayString[i]= KBase.getConsequentVariablesArray()[i].toString();
					}
			targetvariablecombo.setItems(consequentArrayString);
		}
//			System.out.println("runs get target var combo");
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
	
	public Variable AskUserForInput(Variable var, Rule rule)
	{
	
			if(var instanceof NumericVariable)
			{
//				JPanel panel = new JPanel();
//				
//				if(var.getQueryPrompt().trim().equals(""))
//				{
//					panel.add(new JLabel("Input a value for "+var.getName()));
//				}
//				else
//				{
//					panel.add(new JLabel(var.getQueryPrompt()));
//				}
//				
//				JTextField field = new JTextField("0.0", 10);
//				
//				JButton why = new JButton("Why?");
//				why.addActionListener(new ActionListener() { 
//					  public void actionPerformed(ActionEvent e) { 
//						  displayWhyMessage();
//					  } 
//					} );
//				
//				panel.add(field);
//				panel.add(why);
//
//				JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
//				
//				boolean invalid = true;
//				while(invalid)
//				{
//					try
//					{	
//						var.userSetCurrentValue(Double.parseDouble(field.getText()));
//						invalid = false;
//					}
//					catch(NumberFormatException ex)
//					{
//						System.out.println("Please enter a valid number.");
//						invalid = true;
//						JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
//					}	
//				}
			}
			else
			{
				//btnCertainityFactor.getSelection();
			   // btnRun.getSelection();
				if(var.getQueryPrompt().trim().equals(""))
				{
	
				if (btnCertainityFactor.getSelection()==true){
					QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
					askCFQuestion.addQuestion("Input a value for "+var.getName());
					//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
					scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					CompQ.layout();
					//scrolledComposite.layout();
					button.setSelection(false);
					btnBayesianReasoning.setSelection(false);
				}else
				{
					QuestionGUI askQuestion = new QuestionGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
					askQuestion.addQuestion("Input a value for "+var.getName());
					//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
					scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					CompQ.layout();
					btnCertainityFactor.setSelection(false);
					//End of section that needs to be moved
					
				}
				}
				else
				{
					if (btnCertainityFactor.getSelection()==true){
						QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
						askCFQuestion.addQuestion(var.getQueryPrompt());
						//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
						scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						CompQ.layout();
						//scrolledComposite.layout();
						button.setSelection(false);
						btnBayesianReasoning.setSelection(false);
					}else
					{
						QuestionGUI askQuestion = new QuestionGUI(CompQ, WhyListener, HowListener, OKListener, CFListener, CFScaleListener, AnswerComboListener);
						askQuestion.addQuestion(var.getQueryPrompt());
						//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
						scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						CompQ.layout();
						btnCertainityFactor.setSelection(false);
						//End of section that needs to be moved
						
					}	
				}
				
				JPanel panel = new JPanel();
				
				if(var.getQueryPrompt().trim().equals(""))
				{
					panel.add(new JLabel("Input a value for "+var.getName()));
				}
				else
				{
					panel.add(new JLabel(var.getQueryPrompt()));
				}
				
				JComboBox combox = new JComboBox(var.getArrayOfPossibleValues());
				
				JButton why = new JButton("Why?");
				why.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  //displayWhyMessage(rule);
					  } 
					} );
				
				panel.add(combox);
				panel.add(why);

				JOptionPane.showMessageDialog(null, panel,"",JOptionPane.PLAIN_MESSAGE);
				Value val =  (Value) combox.getSelectedItem();
				var.userSetCurrentValue(val);
			}
		
		
		return var;
	}
	public static void displayWhyMessage(Rule rule)
	{
		StringBuilder s = new StringBuilder();
		s.append("\nI am trying to evaluate the rule\n");
		s.append(rule != null ? rule.toString() : "null");
		System.out.println(s.toString());
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}	
}
