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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import datatypes.*;
import test.*;
import datatypes.KBSettings.InferenceType;
import datatypes.KBSettings.UncertaintyManagement;

import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.StyledText;

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
import org.eclipse.swt.widgets.ScrollBar;

import gui.AnswerGUI;
import datatypes.Variable;
import gui.NoRun;
import gui.NoRunV;
import test.Test_Case;
import datatypes.*;

import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;

public class runGUI extends Composite implements Serializable {
	protected static Shell shlExpertSystemShell;
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
    public static Composite CompQ;
    private Group questionGroup;
    private Label lblNewLabel;
    public static ScrolledComposite scrolledComposite;
    private Menu menu_4;
    private MenuItem newKB;
    private RuleEditorGUI ruleEditor;
    private RuleListGUI ruleList;
    private Combo targetvariablecombo;
    private Boolean listChangeFlag = false;
    private String variableListLabel = "";
    private Variable selectedVariable;
    private Value selectedValue;
    private String selectedVariableString;
    private Test_Case test;
    private Label lblSelectTargetVariable;
    public static Label lblWhyhow;
    private ArrayList<Rule> HowList = new ArrayList<Rule>();
    static Rule tRule; // a hack to get this into the description function
    
    private InferenceEngine Inference;
    public static ScrolledComposite scrolledComposite_1;
	static Rule thisRule;
    
    public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter AnswerComboListener;
	public SelectionAdapter CFListener;
	public SelectionAdapter CFScaleListener;
	private GridData gd_SC_QuickStart;
	private Boolean okayFlag;
	public static Variable resultVar;
	public Variable result;
	
	static Display display;
    
    /**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public runGUI(Composite parent, int style,KnowledgeBase kb,Shell sh, Display disp) {
		
		super(parent, style);
		shlExpertSystemShell = sh;
		KBase = kb;
		//Inference = MainScreen.createInferenceEngine(KBase);	
		display = disp;
	
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
	grpKnowledgeBaseSelected.setText("Knowledge Base Selected/Opened");
	
	text = new Label(grpKnowledgeBaseSelected, SWT.NONE);
	GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
	gd_text.widthHint = 323;
	text.setText(KBase.getName());
	text.setLayoutData(gd_text);
			
			lblSelectTargetVariable = new Label(grpKnowledgeBaseSelected, SWT.NONE);
			lblSelectTargetVariable.setText("Select Target Variable");
			lblSelectTargetVariable.setVisible(true);
			
			targetvariablecombo = new Combo(grpKnowledgeBaseSelected, SWT.READ_ONLY);
			targetvariablecombo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(targetvariablecombo.getSelectionIndex() != -1)
					{
						if(KBase.getBCConsequents()[targetvariablecombo.getSelectionIndex()] instanceof Consequent)
						{
						selectedVariable = KBase.getBCConsequents()[targetvariablecombo.getSelectionIndex()].getVariable();
						selectedValue = KBase.getBCConsequents()[targetvariablecombo.getSelectionIndex()].getValue();
						}
						else{
							selectedVariable = KBase.getConsequentVariablesArray()[targetvariablecombo.getSelectionIndex()];	
						}	
				}
			}});
			targetvariablecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			targetvariablecombo.setVisible(true);
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
	btnForwardChaining.setSelection(false);
	
	btnBackwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
	btnBackwardChaining.setBounds(10, 62, 145, 16);
	btnBackwardChaining.setText("Backward Chaining");
	btnBackwardChaining.setSelection(false);
	
	//System.out.println(kb.getInferenceMethod());
	
	
	//if inference is unset(null) default to forward
	if (kb.getInferenceMethod() == null) {
		kb.setInferenceMethod(InferenceType.F_CHAINING);
	}
	
	switch(kb.getInferenceMethod())
	{
	case F_CHAINING:
		btnForwardChaining.setSelection(true);
		btnDefault.setSelection(false);
		btnBackwardChaining.setSelection(false);
		break;
	case B_CHAINING:
		btnBackwardChaining.setSelection(true);
		btnDefault.setSelection(false);
		btnForwardChaining.setSelection(false);
		break;
	default:
		btnBackwardChaining.setSelection(false);
		btnDefault.setSelection(true);
		btnForwardChaining.setSelection(false);
		break;	
	}
	
	btnDefault.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			btnDefault.getSelection();
			if (btnDefault.getSelection()==true){
			btnForwardChaining.setSelection(false);
			btnBackwardChaining.setSelection(false);
			targetvariablecombo.setVisible(true);
			lblSelectTargetVariable.setVisible(true);
			lblSelectTargetVariable.setText("Select Target Variable");
			getTargetVariableCombo();
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
			targetvariablecombo.setVisible(true);
			lblSelectTargetVariable.setVisible(true);
			lblSelectTargetVariable.setText("Select Target Variable");
			getTargetVariableCombo();
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
			lblSelectTargetVariable.setText("Select Target");
			getTargetConsequentCombo();
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
	
switch(kb.getUncertaintyMethod())
	{
	case NONE:
		button.setSelection(true);
		btnCertainityFactor.setSelection(false);
		btnBayesianReasoning.setSelection(false);
		break;
	case BAYESIAN:
		button.setSelection(false);
		btnCertainityFactor.setSelection(false);
		btnBayesianReasoning.setSelection(true);
		break;
	
	case CF:
		button.setSelection(false);
		btnCertainityFactor.setSelection(true);
		btnBayesianReasoning.setSelection(false);
		break;
		
	default:		
	}
	
	

	scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
	scrolledComposite.addMouseTrackListener(new MouseTrackAdapter() {
		@Override
		public void mouseEnter(MouseEvent e) {
			scrolledComposite.setFocus();	
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
	CompQ.addMouseTrackListener(new MouseTrackAdapter() {
		@Override
		public void mouseEnter(MouseEvent e) {
			scrolledComposite.setFocus();
		}
	});
	CompQ.addControlListener(new ControlAdapter() {
		@Override
		public void controlResized(ControlEvent e) {
			e.getSource();
			//CompQ.update();
		}
	});
	CompQ.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	CompQ.setLayout(new GridLayout(1, false));
	scrolledComposite.setContent(CompQ);
	scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	
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
			
			
			
			if(btnRun.getText().equals("Run"))
			{
			
	//		KBase = (KnowledgeBase)MainScreen.window.CopyKnowledgeBase(KBase);
			Inference = new InferenceEngine(KBase);
			Inference.stopFlag = false;
			lblWhyhow.setText("");
			resetVariableValues();
			for(Control i: CompQ.getChildren())
			{
				i.dispose();
			}
			btnRun.setText("Stop");
			
			if(text.getText()==""){
				
				JOptionPane.showMessageDialog(null, "Unable to Run. Please select a knowledgebase:\r\n\r\nFile >Open KnowledgeBase > KnowledgeBase\r\n");
				/*NoRun noKB = new NoRun(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
				noKB.open();*/	
			}
			
			if(targetvariablecombo.isVisible()==true && targetvariablecombo.getText()==""){
				
				if(btnBackwardChaining.getSelection()==true){
					JOptionPane.showMessageDialog(null, "Unable to Run.\r\nSelect a Target Variable and Value from the drop down list.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Unable to Run.\r\nSelect a Target Variable from the drop down list.");
				}
			/*	NoRunV noVar = new NoRunV(shlExpertSystemShell, SWT.ICON_INFORMATION|SWT.OK);
				noVar.open();*/
			}
			//There are issues with this... causes screen to crash
		    if(btnForwardChaining.getSelection()==true || btnDefault.getSelection()==true){
				KBase.validate();
				//grpSelectRunMethod.setEnabled(false);
				setRCMethod(false);
				result = Inference.solveForwardChaining(selectedVariable);
				HowList = Inference.getHowList();
				if(result != null && !(Inference.stopFlag))
				{
					AnswerGUI showAnswer = new AnswerGUI(CompQ, result, scrolledComposite , lblWhyhow, Inference.getHowList(), KBase, scrolledComposite_1);	
				}
				//IO.displayResults(result, Inference.getHowList(), KBase);
				setRCMethod(true);
				btnRun.setText("Run");
		    	
			}else if(btnBackwardChaining.getSelection()==true){
				KBase.validate();
				//grpSelectRunMethod.setEnabled(false);
				setRCMethod(false);
				result = Inference.solveBackwardChaining(selectedVariable, selectedValue);
				HowList = Inference.getHowList();
				if(result != null&& !(Inference.stopFlag))
				{
					AnswerGUI showAnswer = new AnswerGUI(CompQ, result, scrolledComposite , lblWhyhow, Inference.getHowList(), KBase, scrolledComposite_1);	
				}
				//IO.displayResults(result, Inference.getHowList(), KBase);
				setRCMethod(true);
				btnRun.setText("Run");
		    }
		}
		else
		{
			resetVariableValues();
			lblWhyhow.setText("");
			for(Control i: CompQ.getChildren())
			{
				i.dispose();
			}
			btnRun.setText("Run");
			setRCMethod(true);
			Inference.stopFlag = true;
			resultVar = null;
			result = null;
		//Inference = null;
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
	
	
	
	
	scrolledComposite_1 = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
	scrolledComposite_1.addMouseTrackListener(new MouseTrackAdapter() {
		@Override
		public void mouseEnter(MouseEvent e) {
			scrolledComposite_1.setFocus();
		}
	});
		
	scrolledComposite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
	GridData gd_scrolledComposite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
	gd_scrolledComposite_1.heightHint = 377;
	gd_scrolledComposite_1.widthHint = 320;
	scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
	scrolledComposite_1.setExpandHorizontal(true);
	scrolledComposite_1.setExpandVertical(true);
	
	
	lblWhyhow = new Label(scrolledComposite_1, SWT.WRAP);
	lblWhyhow.addMouseTrackListener(new MouseTrackAdapter() {
		@Override
		public void mouseEnter(MouseEvent e) {
			scrolledComposite_1.setFocus();
		}
	});
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
	
	//scrolledComposite.setContent(CompQ);
	//scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
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
	
	AnswerComboListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			//needs to find where varaibles are!!!
			System.out.println(combo_1.getText());
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
		else
		{
			targetvariablecombo.removeAll();
		}
//			System.out.println("runs get target var combo");
	}

	public void getTargetConsequentCombo()
	{
	if(KBase.getBCConsequents().length !=0)
		{
			String consequentArrayString[] = new String[KBase.getBCConsequents().length];
			for (int i=0; i<consequentArrayString.length; i++)
			{
				if(KBase.getBCConsequents()[i].getVariable().isNumeric)
				{			
					consequentArrayString[i]= new String (KBase.getBCConsequents()[i].getVariableAsString() + " = " +KBase.getBCConsequents()[i].getValueAsString());
				}
				else
				{
					consequentArrayString[i]= new String (KBase.getBCConsequents()[i].getVariableAsString() + " is " +KBase.getBCConsequents()[i].getValueAsString());
				}
			}
			targetvariablecombo.setItems(consequentArrayString);
		}
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
	
	public void setRCMethod(Boolean enable)
	{
		btnBackwardChaining.setEnabled(enable);
		btnBayesianReasoning.setEnabled(enable);
		btnCertainityFactor.setEnabled(enable);
		btnDefault.setEnabled(enable);
		btnForwardChaining.setEnabled(enable);
		button.setEnabled(enable);	
	}
	
	public static void AskUserForInput(Variable var, Rule rule, KnowledgeBase kb,InferenceEngine Inference)
	{
	
	Group questionGroup;
	questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
	questionGroup.getParent().getParent().layout(true);
	
	
	/*	
	QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference);
	askCFQuestion.addQuestion("Input a value for "+var.getName());
	scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	CompQ.layout();
		*/	
		thisRule = rule;

		/*	
		boolean invalid = true;
		while(invalid)
		{
			try
			{	
				var.userSetCurrentValue(Double.parseDouble(field.getText()));
				invalid = false;
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
				invalid = true;
				JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
			}	
		} */
		
			
	
			//btnCertainityFactor.getSelection();
		   // btnRun.getSelection();
		if(var.getQueryPrompt().trim().equals(""))
		{
			if (kb.getUncertaintyMethod()==KBSettings.UncertaintyManagement.CF)
			{
				QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference,questionGroup,"Input a value for "+var.getName(), var, scrolledComposite, rule,lblWhyhow, scrolledComposite_1);
				//((Display) this).sleep();
			}
			else
			{
				QuestionGUI askQuestion = new QuestionGUI(CompQ, Inference,questionGroup,"Input a value for "+var.getName(), var,scrolledComposite, rule, lblWhyhow,scrolledComposite_1);
			}
		}
		else
		{
			if (kb.getUncertaintyMethod()==KBSettings.UncertaintyManagement.CF)
			{
				QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference,questionGroup,var.getQueryPrompt(), var,scrolledComposite, rule,lblWhyhow,scrolledComposite_1);
			}
			else
			{
				QuestionGUI askQuestion = new QuestionGUI(CompQ, Inference,questionGroup,var.getQueryPrompt(), var,scrolledComposite, rule, lblWhyhow,scrolledComposite_1);
			}
		}
	CompQ.update();
	while (var.currentValue== null && var.numVal ==null && !(Inference.stopFlag)) {
        if (!display.readAndDispatch ())
           display.sleep ();
     }
     display.wake();
     if (Inference.stopFlag)
     {
    	 resultVar = null;
    	 
     }
     else
     {
    	 resultVar = var;
     }
	//return var;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}	
	
	public Boolean okayPress()
	{
		return okayFlag;
	}
	
	public void resetVariableValues()
	{
		for(Variable v : KBase.getVariablesArray())
		{
			//v.setCurrentValue((Value) null);
			v.clearVariable();
			v.setNumVal(null);
		}
		for(Rule r: KBase.getRuleArray())
		{
			r.setFired(false);
		}
	}
	public void updateKBase(KnowledgeBase kb)
	{
		KBase = kb;
		getTargetVariableCombo();
	}
}
