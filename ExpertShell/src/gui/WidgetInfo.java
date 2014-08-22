package gui;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class WidgetInfo {
	
	Group group;
	Source source;
	int index;
	
	
	public WidgetInfo(Widget w, RuleEditorGUI r) {
		
		index = -1; //default for index
		
		if (w == r.antList.getAddButton()) {
			group = Group.ANTECEDENT;
			source = Source.ADD;
		}
	}
	
	public enum Group {
		RULE,ANTECEDENT,CONCEQUENT
	}
	
	public enum Source {
		ADD,DELETE,
	}
}
