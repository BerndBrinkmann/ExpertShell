package gui;

import gui.WidgetTypes.*;

import org.eclipse.swt.widgets.Widget;

public class WidgetInfo {
	
	Group group;
	Source source;
	int index,i;
	
	
	public WidgetInfo(Widget w, RuleEditorGUI r) {
		
		index = -1; //default for index
		
		if (w == r.antList.getAddButton()) {
			group = Group.ANTECEDENT;
			source = Source.ADD;
			return;
		}
		if (w == r.antList.spinLN) {
			group = Group.ANTECEDENT;
			source = Source.LN;
			return;
		}
		if (w == r.antList.spinLS) {
			group = Group.ANTECEDENT;
			source = Source.LS;
			return;
		}
		
		if (w == r.consList.getAddButton()) {
			group = Group.CONSEQUENT;
			source = Source.ADD;
			return;
		}
		
		i = 0;
		group = Group.ANTECEDENT; //assume in ant list
		for(AntecedentGUI a : r.antList.antecedents) {
			if (w == a.delButton) {
				source = Source.DELETE;
				index = i;
				return;
			}
			if (w == a.logicComb) {
				source = Source.COMBINE;
				index = i;
				return;
			}
			if (w == a.var) {
				source = Source.VARIABLE;
				index = i;
				return;
			}
			if (w == a.logicComparitor) {
				source = Source.COMPARE;
				index = i;
				return;
			}
			if (w == a.value) {
				source = Source.VALUE;
				index = i;
				return;
			}

			
			i++;
		}
		
		i = 0;
		group = Group.CONSEQUENT;
		for(ConsequentGUI a : r.consList.conseqents) {
			if (w == a.delButton) {
				source = Source.DELETE;
				index = i;
				return;
			}
			if (w == a.logicComb) {
				source = Source.COMBINE;
				index = i;
				return;
			}
			if (w == a.var) {
				source = Source.VARIABLE;
				index = i;
				return;
			}
			if (w == a.assign) {
				source = Source.ASSIGN;
				index = i;
				return;
			}
			if (w == a.value) {
				source = Source.VALUE;
				index = i;
				return;
			}
			if (w == a.spinCF) {
				source = Source.CF;
				index = i;
				return;
			}
			if (w == a.spinPrior) {
				source = Source.PRIOR;
				index = i;
				return;
			}

			i++;
		}
		
		group = null;
		source = null;
		
	}
	
	public Source getSource() {
		return source;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public int getIndex() {
		return index;
	}
	
}
