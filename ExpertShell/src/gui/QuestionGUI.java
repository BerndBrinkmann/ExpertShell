package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.custom.ScrolledComposite;

import datatypes.InferenceEngine;
import datatypes.Rule;
import datatypes.Value;
import datatypes.Variable;

public class QuestionGUI {
	
	Button WhyButton;
	Button HowButton;
	Button OKButton;
	Combo ans;
	Label QforUser;
	//Label lblCF;
	Label CFPercentage;
	//Scale scale;
	Scale CFScale;
	SelectionAdapter WhyL;
	SelectionAdapter HowL;
	SelectionAdapter OKL;
	SelectionAdapter CFL;
	SelectionAdapter AnswerComboL;
	SelectionAdapter CFScaleL;
	
	public SelectionAdapter WhyListener;
	public SelectionAdapter HowListener;
	public SelectionAdapter OKListener;
	public SelectionAdapter AnswerComboListener;
	public ArrayList<Rule> howlist;
	public Value[] possibleValues;
	public Boolean okayPress = false;
	
	static Rule tRule;
	private Variable var;
	private InferenceEngine infer;
	static Label lblWhyHow;
	private Boolean OkayFlag1 =false;
	private Boolean OkayFlag2 = false;
	static ScrolledComposite scrolledComposite_1;
	
	public QuestionGUI(Composite CompQ, InferenceEngine Inference, Group questionGroup, String message, Variable var, ScrolledComposite scrolledComposite, Rule currentRule, Label whyhow,ScrolledComposite ScrolledComposite_1){
		scrolledComposite_1 = ScrolledComposite_1;
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
	}
	
	public void addQuestion(String message, Group questionGroup){
		
		QforUser= UserFactoryGUI.createQuestionLabel(questionGroup);
		if(var.isNumeric && var.getMin() != null && var.getMax() != null)
		{
			StringBuilder extender = new StringBuilder();
			extender.append(message);
			extender.append(". [" + var.getMin()+ ":"+ var.getMax() + "]");
			QforUser.setText(extender.toString());
		}
		else
		{
			QforUser.setText(message);
		}

		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint=370;
		gd_label.heightHint=20;
		QforUser.setLayoutData(gd_label);

		ans = UserFactoryGUI.createAnswerCombo(questionGroup);
		GridData gd_combo_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_1.widthHint = 276;
		ans.setLayoutData(gd_combo_1);
		
		if(var.isNumeric)
		{
			ans.setText("Enter Number");
		}
		else
		{
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
		}
		

		WhyListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();	
				displayWhyMessage();
			}
		};

		OKListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				if(var.isNumeric)
				{
					Boolean doub =true;
					try
					{
						double d = Double.parseDouble(ans.getText());
					}
					catch(NumberFormatException nfe)
					{
						doub = false;
					}
					if(doub)
					{
						if(var.getMin() != null && var.getMax() != null)
						{
							if(Double.parseDouble(ans.getText())>=var.getMin() && Double.parseDouble(ans.getText())<=var.getMax())
								{
								var.setNumVal(Double.parseDouble(ans.getText()));
								System.out.println(Double.parseDouble(ans.getText()));
								System.out.println(var.getNumVal());
								setQueryBoxEnabled(false);
								}
							else
								{
								JOptionPane.showMessageDialog(null, "Please enter within ["+ var.getMin() + ":" + var.getMax() + "]");
								}
						}
						else
						{
							var.setNumVal(Double.parseDouble(ans.getText()));
							System.out.println(Double.parseDouble(ans.getText()));
							System.out.println(var.getNumVal());
							setQueryBoxEnabled(false);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid value");
					}
				}
				else{
				if(ans.getSelectionIndex() != -1)
				{	
					var.setCurrentValue(possibleValues[ans.getSelectionIndex()]);
					setQueryBoxEnabled(false);	
				}
				}
				
			}
		};
		AnswerComboListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				//needs to find where variables are!!!
				System.out.println(ans.getText());
			}	
		};
		
		ans.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(!(var.isNumeric))
				{
				if(ans.getSelectionIndex() != -1)
				{
					OKButton.setEnabled(true);
				}
				}
				else
				{
					OKButton.setEnabled(true);
				}
			}
		});
		

		WhyButton = UserFactoryGUI.createWhyButton(questionGroup);
		WhyButton.addSelectionListener(WhyListener);
		GridData gd_WhyButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_WhyButton.widthHint = 54;
		WhyButton.setLayoutData(gd_WhyButton);
		WhyButton.setText("Why?");
		
		HowButton = UserFactoryGUI.createHowButton(questionGroup);
		//HowButton.addSelectionListener(HowListener);
		GridData gd_HowButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_HowButton.widthHint = 54;
		HowButton.setLayoutData(gd_HowButton);
		HowButton.setText("How?");
		HowButton.setVisible(false);
		
		OKButton = UserFactoryGUI.createOKButton(questionGroup);
		OKButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton.addSelectionListener(OKListener);
		OKButton.setEnabled(false);	
		if(var.isNumeric)
		{
		OKButton.setEnabled(true);	
		}
		
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
	
	public Boolean getOkayPress()
	{
		return okayPress;
	}
	
	public  void displayWhyMessage()
	{
		StringBuilder s = new StringBuilder();
		s.append("\nInference Engine is currently trying to fire Rule " + (tRule.getRuleNum()+1) + ":\n\n");
		s.append(tRule != null ? tRule.toString() : "null");
		s.append("\nIt needs the value of Variable: \n\n" + var.getName() + " from user.");
		lblWhyHow.setText(s.toString());
		scrolledComposite_1.setMinSize(lblWhyHow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite_1.layout();
		scrolledComposite_1.update();
	}
	
	public void setQueryBoxEnabled(Boolean enable)
	{
		WhyButton.setEnabled(enable);
		HowButton.setEnabled(enable);
		OKButton.setEnabled(enable);
		ans.setEnabled(enable);
		QforUser.setEnabled(enable);
		//CFPercentage.setEnabled(enable);
		//CFScale.setEnabled(enable);
	}

}
