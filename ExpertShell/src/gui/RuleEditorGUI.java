package gui;

import gui.WidgetTypes.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import datatypes.Antecedent;
import datatypes.Connectives;
import datatypes.Consequent;
import datatypes.Comparison;
import datatypes.KnowledgeBase;
import datatypes.Rule;
import datatypes.Value;
import datatypes.Variable;

public class RuleEditorGUI {
	
	boolean consoleDebugOutput = true; //----debug----
	
	public Composite ruleGrid;
	AntecedentListGUI antList;
	ConsequentListGUI consList;
	final KnowledgeBase kb;
	final Rule rule;
	private RuleListGUI ruleListGUI;
	
	public SelectionAdapter selAdaptor;
	public FocusAdapter focAdaptor;
	public KeyAdapter enterAdaptor;
	
	public RuleEditorGUI(Composite p, int index, KnowledgeBase k, RuleListGUI rl) {
		
		kb = k;
		rule = kb.getRule(index);
		ruleListGUI = rl;
		
		//for button 'clicks' and combo list 'clicks'
		selAdaptor = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUserAction(e);
			}
		};
		
		//for combo boxes losing focus
		focAdaptor = new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				handleUserAction(e);
			}
		};
		
		//for pressing 'enter' key
		enterAdaptor = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.character == SWT.CR) {
					handleUserAction(e);
				}
			}
		};
		
		ruleGrid = RuleGUIFactory.createCompositeRuleHolder(p);
		
		antList = new AntecedentListGUI(this);
		consList = new ConsequentListGUI(this);
		updateUncertainty();
		ruleGrid.getParent().getParent().layout(true,true);
	}
	
	public void destroy() {
		//remove/delete all GUI objects in the editor
		destroy(ruleGrid);
		ruleGrid.dispose();
		
	}
	
	public void destroy(Control c) {
		//if its a composite then delete its children
		if (c instanceof Composite) {
			for (Control con: ((Composite) c).getChildren()) {
				//recursive.  such recursion.  much programming skill. wow.
				destroy(con);
			}
		} else { //an actual control rather than a container
			c.dispose();
		}
	}
	
	public RuleListGUI getParentRuleList() {
		return ruleListGUI;
	}
	public void updateUncertainty() {
		//this just re-calculates the vertical span of the 'lnls' container composite
		antList.updateUncertainty();
		consList.updateUncertainty();
	}
	
	public KnowledgeBase getKnowledgeBase() {
		return kb;
	}
	
	public void debug(String s) {
		if (consoleDebugOutput) { 
			System.out.println(s);
		}
	}
	
	public void handleUserAction(TypedEvent e) {
		
		Widget w = (Widget) e.getSource();  //get the control that the event happened on
		
		//make a new 'info' object to get stuff about the source button/combo
		WidgetInfo info = new WidgetInfo(w,RuleEditorGUI.this);
		
		//get the index of the control (for example in a list of consequents)
		int index = info.getIndex();
		Source source = info.getSource();
		Group group = info.getGroup();
		
		
		if (group == Group.ANTECEDENT) {
			Antecedent antecedent = rule.getAntecedent(index);
			if (source == Source.ADD) {
				
				debug("Add antecedent");
				
				Antecedent toAdd;
				
				Variable variableToAdd = kb.getVariable("default");
				if (variableToAdd == null) {
					//default isn't a var yet
					toAdd = new Antecedent();
				} else {
					toAdd = new Antecedent( variableToAdd, (Value)null );
				}
					
				//update KB
				rule.addAntecedent(toAdd);
				
				//update GUI
				antList.add(toAdd);
				this.getParentRuleList().updateTextOfSelected();
				ruleGrid.getParent().getParent().layout(true,true);
				
				
			} else if (source == Source.DELETE) {
				
				debug("Delete antecedent: " + index);
				Antecedent toDelete = antecedent;
				
				//update KB
				rule.removeConditional(toDelete);
				
				//update GUI
				antList.delete(index);
				this.getParentRuleList().updateTextOfSelected();
				ruleGrid.getParent().getParent().layout(true,true);
				
			} else if (source == Source.VARIABLE) {
				debug("Change antecedent variable: " + index);
				
				Combo combo = (Combo)w;
				
				String comboText;
				
				comboText = combo.getText().trim();
				
				if (combo.getSelectionIndex() == -1 && !(comboText.equals(""))) {
					//ie. combo was changed (typed into) but they didn't select an existing var from the list
					//ignore blank text
					
					//see if the variable already exists in kb
					Variable var = kb.getVariable(comboText);
					
					//the variable name doesn't exist in kb (yet!)
					if (var == null) {
						//make a new variable with this name
						var = new Variable(comboText);
						
						//add it to the kb
						kb.addVariable(var);
					}
					
					antecedent.setVariable(var);
					
				} else { //user has selected an existing variable
					
					//set the var in the kb
					antecedent.setVariable(kb.getVariable(comboText));
				}
				
				//update values of GUI elements for this antecedent
				antList.getAntGUIList().get(index).update();
				
			} else if (source == Source.COMPARE) {
				debug("Change antecedent comparitor logic: " + index);
				
				//get a pointer to the combo involved
				Combo combo = (Combo)w;
				
				//get index of item selected
				Comparison selected = Comparison.values()[combo.getSelectionIndex()];
				
				//set in the KB
				antecedent.setComparison(selected);
				
				//update the GUI
				antList.getAntGUIList().get(index).update();
			
				
				
			} else if (source == Source.VALUE) {
				debug("Change antecedent value: " + index);
				
				Combo combo = (Combo)w;
				
				boolean numeric = antecedent.getIsNumeric();
				
				String comboText = combo.getText().trim();
			
				if(!numeric) {
					
					//check if it already exists
					int exists = antecedent.getVariable().getValueIndex(comboText);
												
					if (exists != -1) {
						//already exists - set the value to be the one found
						antecedent.setValue(antecedent.getVariable().getPossibleValue(exists));
					} else {
						//add a new value with this name
						antecedent.setValue(new Value(comboText,antecedent));
					}
					
				} else if(numeric) {
					if(!comboText.equals("")) {
						try {
							//try and parse the combobox text as a double
							antecedent.setValue(Double.parseDouble(comboText));
						} catch (NumberFormatException ex) {}
					}
				}
				
				
			} else if (source == Source.COMBINE) {
				debug("Change antecedent combinational logic: " + index);
				
				Combo combo = (Combo)w;
				
				if(combo.getSelectionIndex() == 0) {
					//selected "AND"
					rule.setConnective(Connectives.AND);
				} else if (combo.getSelectionIndex() == 1) {
					//selected "OR"
					rule.setConnective(Connectives.OR);
				}
				
				//update all connectives
				antList.updateChildren();
				
			}
		//---------------------CONSEQENT START-------------
		} else if (group == Group.CONSEQUENT) {
			Consequent consequent = rule.getConsequent(index);
			
			if (source == Source.ADD) {
				debug("Add consequent");
				
				Consequent toAdd = new Consequent();
				
				//update KB
				rule.addConsequent(toAdd);
				
				//update GUI
				consList.add(toAdd);
				ruleGrid.getParent().getParent().layout(true,true);
				
			} else if (source == Source.DELETE) {
				debug("Delete consequent: " + index);
				Consequent toDelete = consequent;
				
				//update KB
				rule.removeConditional(toDelete);
				
				//update GUI
				consList.delete(index);
				ruleGrid.getParent().getParent().layout(true,true);
				
			} else if (source == Source.VARIABLE) {
				debug("Change consequent variable: " + index);
				
				Combo combo = (Combo)w;
				
				String comboText;
				
				comboText = combo.getText().trim();
				
				if (combo.getSelectionIndex() == -1 && !(comboText.equals(""))) {
					//ie. combo was changed (typed into) but they didn't select an existing var from the list
					//ignore blank text
					
					//see if the variable already exists in kb
					Variable var = kb.getVariable(comboText);
					
					//the variable name doesn't exist in kb (yet!)
					if (var == null) {
						//make a new variable with this name
						var = new Variable(comboText);
						
						//add it to the kb
						kb.addVariable(var);
					}
					
					consequent.setVariable(var);
					
				} else { //user has selected an existing variable
					
					//set the var in the kb
					consequent.setVariable(kb.getVariable(comboText));
				}
				
				//update values of GUI elements for this antecedent
				consList.getConsGUIList().get(index).update();
				
			} else if (source == Source.ASSIGN) {
				debug("Change assignment: " + index);
				
				//get a pointer to the combo involved
				Combo combo = (Combo)w;
				
				if (combo.getSelectionIndex() == 0) {
					//selected "is"
					consequent.setIsNumeric(false);
				} else if (combo.getSelectionIndex() == 1) {
					//selected "="
					consequent.setIsNumeric(true);
				}
				
				//update the GUI
				consList.getConsGUIList().get(index).update();
				
				
			} else if (source == Source.VALUE) {
				debug("Change consequent value: " + index);
				
				Combo combo = (Combo)w;
				
				boolean numeric = consequent.getIsNumeric();
				
				String comboText = combo.getText().trim();
			
				if(!numeric) {
					
					//check if it already exists
					int exists = consequent.getVariable().getValueIndex(comboText);
												
					if (exists != -1) {
						//already exists - set the value to be the one found
						consequent.setValue(consequent.getVariable().getPossibleValue(exists));
					} else {
						//add a new value with this name
						consequent.setValue(new Value(comboText,consequent));
					}
					
				} else if(numeric) {
					if(!comboText.equals("")) {
						try {
							//try and parse the combobox text as a double
							consequent.setValue(Double.parseDouble(comboText));
						} catch (NumberFormatException ex) {}
					}
				}
				
			} else if (source == Source.PRIOR) {
				debug("Change prior value: " + index);
				
				Spinner spin = (Spinner)w;
				
				Double prior;
				
				double factor = Math.pow(10,spin.getDigits());
				prior = ((double) spin.getSelection()) / factor;
				
				consequent.setBeliefOfSelected(prior);
				
			} else if (source == Source.CF) {
				debug("Change certainty factor: " + index);
				
				Spinner spin = (Spinner)w;
				
				Double cf;
				
				double factor = Math.pow(10,spin.getDigits());
				cf = ((double) spin.getSelection()) / factor;
				
				consequent.setCertaintyFactor(cf);
			}
		}
		
		//update the list text
		this.getParentRuleList().updateTextOfSelected();
	}

	
}
