package gui;

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

public class QuestionGUI {
	
	Group questionGroup;
	Composite CompQ;
	
	Button WhyButton;
	Button HowButton;
	Button OKButton;
	Combo ans;
	Label QforUser;
	Label CFPercentage;
	Scale CFScale;
	
	
	public SelectionAdapter selAdaptor;
	
	public QuestionGUI(Composite CompQ){
		
		questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
		questionGroup.getParent().layout();
		
	}
	
	public void addQuestion(){
		
		QforUser= UserFactoryGUI.createQuestionLabel(questionGroup);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_label.widthHint=302;
		gd_label.heightHint=65;
		QforUser.setLayoutData(gd_label);
		
		
		ans = UserFactoryGUI.createAnswerCombo(questionGroup);
		GridData gd_combo_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_1.widthHint = 280;
		ans.setLayoutData(gd_combo_1);
		ans.setText("Answer");
		ans.setItems(new String[] {"A", "B"}); //TEST
		
		CFPercentage = UserFactoryGUI.createCFLabel(questionGroup);
		
		CFScale = UserFactoryGUI.createCFScale(questionGroup);
		
		
		WhyButton = UserFactoryGUI.createWhyButton(questionGroup);
		WhyButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		HowButton = UserFactoryGUI.createHowButton(questionGroup);
		HowButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		OKButton = UserFactoryGUI.createOKButton(questionGroup);
		OKButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				// Attempt at creating new Question box...
					    QforUser.setText("OK Pressed"); //test to see if button working
						//QuestionGUI askQuestion = new QuestionGUI(CompQ);
						//askQuestion.addQuestion();
						//AnswerGUI userAnswer = new AnswerGUI(questionGroup);
						//CompQ.layout();
						//questionGroup.layout();

				//notes: scrolled composite doesnt scroll with additional frames
			    // creates tiny box
			}
		});
	}
	

}
