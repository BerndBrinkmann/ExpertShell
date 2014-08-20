package gui;

//where do I add "group" - child of CompQ that contains all these elements

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


public class AnswerGUI {

	Button WhyButton;
	Button HowButton;
	Button OKButton;
	Combo ans;
	Label QforUser;
	
	public AnswerGUI(Composite CompQ, Control stop){
		
		QforUser= UserFactoryGUI.createQuestionLabel(CompQ);
		ans = UserFactoryGUI.createAnswerCombo(CompQ);
		WhyButton = UserFactoryGUI.createWhyButton(CompQ);
		HowButton = UserFactoryGUI.createHowButton(CompQ);
		OKButton = UserFactoryGUI.createOKButton(CompQ);
		
		QforUser.moveAbove(stop);
		ans.moveAbove(stop);
		WhyButton.moveAbove(stop);
		HowButton.moveAbove(stop);
		OKButton.moveAbove(stop);
		
	}
	
	
	public void setListeners(SelectionListener s) {
		//WhyButton.addSelectionListener(s);
		//HowButton.addSelectionListener(s);
		//OKButton.addSelectionListener(s);
		//ans.addSelectionListener(s);

	}
	
}




