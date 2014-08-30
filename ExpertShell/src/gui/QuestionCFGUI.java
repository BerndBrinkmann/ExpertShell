package gui;

import java.util.ArrayList;






import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;

import datatypes.Rule;
import datatypes.InferenceEngine;
import datatypes.Variable;
import datatypes.Value;

public class QuestionCFGUI {
	

//	Composite CompQ;
//	ScrolledComposite scrolledComposite;
	
	Button WhyButton;
	Button HowButton;
	Button OKButton;
	Button WhyButton1;
	Button HowButton1;
	Button OKButton1;
	Combo ans;
	Combo ans1;
	Label QforUser;
	Label AforUser;
	Label CFPercentage;
	Label CFPercentage1;
	Scale CFScale;
	Scale CFScale1;
	//Label lblCF;
	//Scale scale;
	//SelectionAdapter HowL;
	//SelectionAdapter OKL;
	SelectionAdapter CFL;
	SelectionAdapter AnswerComboL;
	SelectionAdapter CFScaleL;
	
	public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter OKListener1;
	public SelectionAdapter AnswerComboListener;
	public SelectionAdapter CFListener;
	public SelectionAdapter CFScaleListener;
	
	public ArrayList<Rule> howlist;
	public Value[] possibleValues;
	public Boolean okayPress = false;
	
	private Variable var;
	private InferenceEngine infer;
	static Rule tRule;
	static Label lblWhyHow;
	
	public QuestionCFGUI(Composite CompQ, InferenceEngine Inference, Group questionGroup, String message, Variable var, ScrolledComposite scrolledComposite, Rule currentRule, Label whyhow){
		//HowL= HowListener;
		//OKL= OKListener;
		CFL = CFListener;
		CFScaleL = CFScaleListener;
		AnswerComboL= AnswerComboListener;
		howlist  = Inference.getHowList();
		this.var = var;
		possibleValues = var.getArrayOfPossibleValues();
		tRule = currentRule;
		lblWhyHow = whyhow;
		addQuestion(message,questionGroup);
		scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.showControl(questionGroup);
		CompQ.layout();
		questionGroup.setFocus();
		CompQ.update();
		
		
		/*while(var.currentValue == null)
		{
			try
			{
				wait();
			} catch (InterruptedException e){};
			
		}*/
	}
	
	public void addQuestion(String message, Group questionGroup){
		
		QforUser= UserFactoryGUI.createQuestionLabel(questionGroup);
		QforUser.setText(message);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint=303;
		gd_label.heightHint=65;
		QforUser.setLayoutData(gd_label);
		
		
		
		ans = UserFactoryGUI.createAnswerCombo(questionGroup);
		GridData gd_combo_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_1.widthHint = 276;
		ans.setLayoutData(gd_combo_1);
		
		if(possibleValues.length !=0)
		{
			String possiblevString[] = new String[possibleValues.length];
					for (int i=0; i<possibleValues.length; i++)
					{
						possiblevString[i]= possibleValues[i].toString();
					}
			ans.setItems(possiblevString);
		}
		ans.setText("Choose Value");
		
		CFPercentage = UserFactoryGUI.createCFLabel(questionGroup);
		CFPercentage.setVisible(true);
		//lblCF = UserFactoryGUI.createCFLabel(questionGroup);
		//lblCF.setVisible(true);
		
		CFScale = UserFactoryGUI.createCFScale(questionGroup);
		//scale= UserFactoryGUI.createCFScale(questionGroup);
		//scale.addSelectionListener(CFScaleL);
		
		CFScale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				int perspectivevalue=CFScale.getSelection();
				GridData gd_lblCf = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
				gd_lblCf.widthHint = 101;
				CFPercentage.setLayoutData(gd_lblCf);
				CFPercentage.setText(""+(perspectivevalue));
				//lblCF.setLayoutData(gd_lblCf);
				//lblCF.setText(""+(perspectivevalue));
			}
		});
		GridData gd_scale = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_scale.widthHint = 216;
		CFScale.setLayoutData(gd_scale);
		CFScale.setVisible(true);
		//scale.setLayoutData(gd_scale);
		//scale.setVisible(true);
		
		WhyListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();	
				displayWhyMessage();
				//lblWhyhow.setText(""+s);
			}
		};
	
		OKListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				if(ans.getSelectionIndex() != -1)
				{
					Double CFSelection = (double) CFScale.getSelection();
					CFSelection = CFSelection/100;
					var.setCurrentValue(possibleValues[ans.getSelectionIndex()]);
					var.setCertaintyFactor(var.getCurrentValue(),CFSelection);
					setQueryBoxEnabled(false);
					
				}
			}	
		};
		AnswerComboListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				//needs to find where varaibles are!!!
				System.out.println(ans.getText());
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
		
		WhyButton = UserFactoryGUI.createWhyButton(questionGroup);
		WhyButton.addSelectionListener(WhyListener);
		GridData gd_WhyButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_WhyButton.widthHint = 54;
		WhyButton.setLayoutData(gd_WhyButton);
		WhyButton.setText("Why?");
		
		HowButton = UserFactoryGUI.createHowButton(questionGroup);
		GridData gd_HowButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_HowButton.widthHint = 54;
		HowButton.setLayoutData(gd_HowButton);
		HowButton.setText("How?");
		HowButton.setVisible(false);
		
		OKButton = UserFactoryGUI.createOKButton(questionGroup);
		OKButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton.addSelectionListener(OKListener);
		OKButton.setText("OK");
		
		
		
		
	
		
	}
	
	public static void displayHowMessage(ArrayList<Rule> howList)
	{
		if(howList.isEmpty())
		{
			lblWhyHow.setText("\nA result was not reached\n");
		}
		else
		{
		
			System.out.println("\nThe result was reached by firing these rules in this order\n");
			for(Rule r : howList)
			{
				lblWhyHow.setText(r.toString());
			}
		}
	}
	public void displayWhyMessage()
	{
		
		StringBuilder s = new StringBuilder();
		s.append("\nInference Engine is currently trying to fire Rule " + (tRule.getRuleNum()+1) + ":\n\n");
		s.append(tRule != null ? tRule.toString() : "null");
		s.append("\nIt needs the value of Variable: \n\n" + var.getName() + " from user.");
		lblWhyHow.setText(s.toString());
	}
	
	public Boolean getOkayPress()
	{
		return okayPress;
	}
	
	public void setQueryBoxEnabled(Boolean enable)
	{
		WhyButton.setEnabled(enable);
		HowButton.setEnabled(enable);
		OKButton.setEnabled(enable);
		ans.setEnabled(enable);
		QforUser.setEnabled(enable);
		CFPercentage.setEnabled(enable);
		CFScale.setEnabled(enable);
	}

public void showAnswer(String message, Group questionGroup){
		
		AforUser = UserFactoryGUI.createQuestionLabel(questionGroup);
		AforUser.setText(message);
		GridData gd_label2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_label2.widthHint=303;
		gd_label2.heightHint=65;
		AforUser.setLayoutData(gd_label2);
		
		
		ans1 = UserFactoryGUI.createAnswerCombo(questionGroup);
		GridData gd_combo_11 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_11.widthHint = 276;
		ans1.setLayoutData(gd_combo_11);
		ans1.setVisible(false);
		
		ans1.setText("Choose Value");
		
		CFPercentage1 = UserFactoryGUI.createCFLabel(questionGroup);
		CFPercentage1.setVisible(false);
		//lblCF = UserFactoryGUI.createCFLabel(questionGroup);
		//lblCF.setVisible(true);
		
		CFScale1 = UserFactoryGUI.createCFScale(questionGroup);
		//scale= UserFactoryGUI.createCFScale(questionGroup);
		//scale.addSelectionListener(CFScaleL);
		GridData gd_scale1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_scale1.widthHint = 216;
		CFScale1.setLayoutData(gd_scale1);
		CFScale1.setVisible(false);
		//scale.setLayoutData(gd_scale);
		//scale.setVisible(true);
		
		HowListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				displayHowMessage(howlist);
				if(howlist.isEmpty())
				{
//TODO					//lblWhyhow.setText("\nA result was not reached\n");
				}
				else
				{
				
//TODO					//lblWhyhow.setText("\nThe result was reached by firing these rules in this order\n");
					for(Rule r : howlist)
					{
//TODO						//lblWhyhow.setText(r.toString());
					}
				}	
			}	
		};
		
		OKListener1 = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				
			}		
		};
		
		
		
		WhyButton1 = UserFactoryGUI.createWhyButton(questionGroup);
		GridData gd_WhyButton1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_WhyButton1.widthHint = 54;
		WhyButton1.setLayoutData(gd_WhyButton1);
		WhyButton1.setText("Why?");
		WhyButton1.setVisible(false);
		
		HowButton1 = UserFactoryGUI.createHowButton(questionGroup);
		HowButton1.addSelectionListener(HowListener);
		GridData gd_HowButton1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_HowButton1.widthHint = 54;
		HowButton1.setLayoutData(gd_HowButton1);
		HowButton1.setText("How?");
		
		OKButton1 = UserFactoryGUI.createOKButton(questionGroup);
		OKButton1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton1.addSelectionListener(OKListener1);
		OKButton1.setText("OK");
	
	
}
}







	
	
	
	

