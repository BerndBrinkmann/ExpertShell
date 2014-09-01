package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;












import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;

import datatypes.KBSettings;
import datatypes.KnowledgeBase;
import datatypes.NumericVariable;
import datatypes.Rule;
import datatypes.InferenceEngine;
import datatypes.Variable;
import datatypes.Value;

public class AnswerGUI {
	

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
	Label lblAns;
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
	
	public ArrayList<Rule> HowList;
	public Value[] possibleValues;
	public Boolean okayPress = false;
	
	private Variable var;
	private InferenceEngine infer;
	static Rule tRule;
	static Label lblWhyHow;
	static ScrolledComposite ScrolledComposite_1;
	static KnowledgeBase KBase;
	
	public AnswerGUI(Composite CompQ, Variable result, ScrolledComposite scrolledComposite , Label whyhow, ArrayList<Rule> howList, KnowledgeBase kb, ScrolledComposite scrolledComposite_1){
		
		Group questionGroup;
		questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
		questionGroup.getParent().getParent().layout(true);
		
		
		//HowL= HowListener;
		//OKL= OKListener;
		KBase = kb;
		CFL = CFListener;
		CFScaleL = CFScaleListener;
		AnswerComboL= AnswerComboListener;
		HowList  = howList;
		this.var = result;
		possibleValues = var.getArrayOfPossibleValues();
		lblWhyHow = whyhow;
		String message = getMessage(result,kb,HowList);
		showAnswer(questionGroup, message,kb,CompQ);
		scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.showControl(questionGroup);
		//scrolledComposite_1.showControl(questionGroup);
		
		CompQ.layout();
		questionGroup.setFocus();
		CompQ.update();
		ScrolledComposite_1 = scrolledComposite_1;
		scrolledComposite_1.setMinSize(lblWhyHow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite_1.layout();
		scrolledComposite_1.update();
		
		/*while(var.currentValue == null)
		{
			try
			{
				wait();
			} catch (InterruptedException e){};
			
		}*/
	}
	
	public static void displayHowMessage(ArrayList<Rule> howList)
	{
		
		
		if(howList.isEmpty())
		{
			lblWhyHow.setText("\nA result was not reached\n");
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Rules were fired in the following order: \n\n");
			//System.out.println("\nThe result was reached by firing these rules in this order\n");
			for(Rule r : howList)
			{
				sb.append("Rule " + (r.getRuleNum()+1)+ "\n");
				sb.append(r.toString());
				sb.append("\n");
			}
			lblWhyHow.setText(sb.toString());
		}
		ScrolledComposite_1.setMinSize(lblWhyHow.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		ScrolledComposite_1.layout();
		ScrolledComposite_1.update();
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

public void showAnswer(Group questionGroup, String message, final KnowledgeBase kb, final Composite CompQ){
		
		
	lblAns= UserFactoryGUI.createQuestionLabel(questionGroup);
	lblAns.setText("[Evaluation Complete] \n");
	GridData gd_label3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
	gd_label3.widthHint=303;
	gd_label3.heightHint=15;
	lblAns.setLayoutData(gd_label3);
	
		AforUser = UserFactoryGUI.createQuestionLabel(questionGroup);
		
		AforUser.setText(message);
		
		int lines = 1;
	    int pos = 0;
	    while ((pos = message.indexOf("\n", pos) + 1) != 0) {
	        lines++;
	    }
		
		GridData gd_label2 = new GridData(SWT.LEFT, SWT.CENTER, true, true, 3, (lines+2));
		gd_label2.widthHint=303;
		gd_label2.heightHint = (15*(lines));
		AforUser.setLayoutData(gd_label2);
		
		/*
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
		*/
		HowListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				displayHowMessage(HowList);
				if(HowList.isEmpty())
				{
//TODO					//lblWhyhow.setText("\nA result was not reached\n");
				}
				else
				{
				
//TODO					//lblWhyhow.setText("\nThe result was reached by firing these rules in this order\n");
					for(Rule r : HowList)
					{
//TODO						//lblWhyhow.setText(r.toString());
					}
				}	
			}	
		};
		
		OKListener1 = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				{
					kb.rungui.resetVariableValues();
				for(Control i: CompQ.getChildren())
				{
					i.dispose();
				}
				//var.certaintyFactors.clear();
				var = null;
				kb.rungui.result = null;
				
			}		
		}};
		
		
		
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
		OKButton1.setText("Clear");
	
	
}
	public String getMessage(Variable result, KnowledgeBase kb, ArrayList<Rule> howList)
	{
		String summary;
		if(result != null)
		{
			if(kb.getUncertaintyMethod() == KBSettings.UncertaintyManagement.CF)
			{
				summary = result.getCertaintyValuesString();
			}
			else if(kb.getUncertaintyMethod() == KBSettings.UncertaintyManagement.BAYESIAN)
			{
				summary = result.getBeliefValuesString();

			}
			else
			{
				if(!(result instanceof NumericVariable))
				{
					if(result.getCurrentValue() != null)
					{
						summary =  result + " is " + result.getCurrentValue();
					}
					else
					{
						summary = "A conclusion could not be made from the information provided";
					}
				}
				else
				{
					if(result.getNumVal() != null)
					{
						summary = result + " is " + result.getNumVal();
					}
					else
					{
						summary = "A conclusion could not be made from the information provided";
					}
				}
			}
		}
		else
		{
			summary = "A conclusion could not be made from the information provided";
		}
		
		return summary;
	}

}







	
	
	
	

