package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class QuestionGUI {
	
	Group questionGroup;
	
	
	public SelectionAdapter selAdaptor;
	
	public QuestionGUI(Composite CompQ){
		
		questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
		questionGroup.getParent().layout();
		
	}

}
