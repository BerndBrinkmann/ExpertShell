package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.custom.ScrolledComposite;

public class QuestionGUI {
	
	Group questionGroup;
	Composite CompQ;
	ScrolledComposite scrolledComposite;
	
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
	
	public QuestionGUI(Composite CompQ, SelectionAdapter WhyListener, SelectionAdapter HowListener, SelectionAdapter OKListener, SelectionAdapter CFListener, SelectionAdapter CFScaleListener, SelectionAdapter AnswerComboListener){
		WhyL= WhyListener;
		HowL= HowListener;
		OKL= OKListener;
		CFL = CFListener;
		CFScaleL = CFScaleListener;
		AnswerComboL= AnswerComboListener;
		questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
		questionGroup.getParent().getParent().layout(true);
		CompQ.update();
		
	}
	
	public void addQuestion(){
		
		QforUser= UserFactoryGUI.createQuestionLabel(questionGroup);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_label.widthHint=303;
		gd_label.heightHint=65;
		QforUser.setLayoutData(gd_label);

		ans = UserFactoryGUI.createAnswerCombo(questionGroup);
		GridData gd_combo_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
		gd_combo_1.widthHint = 276;
		ans.setLayoutData(gd_combo_1);
		ans.setItems(new String[] {"A", "B"}); //TEST
		ans.setText("Answer");
		
		CFPercentage = UserFactoryGUI.createCFLabel(questionGroup);
		CFPercentage.setVisible(false);
		//lblCF = UserFactoryGUI.createCFLabel(questionGroup);
		//lblCF.setVisible(false);
		
		CFScale = UserFactoryGUI.createCFScale(questionGroup);
		//CFScale.addSelectionListener(CFScaleL);
		//scale = UserFactoryGUI.createCFScale(questionGroup);
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
		CFScale.setVisible(false);
		//scale.setLayoutData(gd_scale);
		//scale.setVisible(false);
		
		WhyButton = UserFactoryGUI.createWhyButton(questionGroup);
		WhyButton.addSelectionListener(WhyL);
		GridData gd_WhyButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_WhyButton.widthHint = 54;
		WhyButton.setLayoutData(gd_WhyButton);
		WhyButton.setText("Why?");
		
		HowButton = UserFactoryGUI.createHowButton(questionGroup);
		HowButton.addSelectionListener(HowL);
		GridData gd_HowButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_HowButton.widthHint = 54;
		HowButton.setLayoutData(gd_HowButton);
		HowButton.setText("How?");
		
		OKButton = UserFactoryGUI.createOKButton(questionGroup);
		OKButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		OKButton.addSelectionListener(OKL);
		
		
	}
	

}
