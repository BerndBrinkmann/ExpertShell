package STUART.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.SystemUtils;

import STUART.ADT.*;

public class RuleWizardRulePanel extends JPanel implements ActionListener, ListSelectionListener, ChangeListener, KeyListener
{
	//make everything either private or protected
	
	KnowledgeBase knowledgeBase;
	JScrollPane rulesListScrollPane, ruleDisplayScrollPane;
	JButton newRuleButton, deleteRuleButton, newLineButton, deleteLineButton;
	JComboBox typeCombo, variableCombo, relationCombo, valueCombo;
	JLabel LNLabel, LSLabel, CFLabel, priorLabel;
	JTextField LNField, LSField, CFField, priorField;
	JSlider CFSlider, priorSlider;

	// can parameterise in JDK 1.7
	JList rulesList, ruleDisplayList;
	
	DefaultListModel rulesListModel;
	DefaultListModel ruleDisplayListModel;

	GroupLayout layout;
	
	Rule selectedRule;
	Antecedent selectedAntecedent;
	Consequent selectedConsequent;
	Variable selectedVariable;
	Value selectedValue;
	
	String[] antRelationOptions = new String[] {"IS", "=", "!=", ">", "<", "<=", ">="};
	String[] conRelationOptions = new String[] {"IS", "="};
	Boolean listChangeFlag = false;
	
	//TODO make an update panel method to set the values in the dropdown boxes, or just deselect everything,
	// mainly to be used after returning from running the knowledgebase or changing to the tab
	
	public RuleWizardRulePanel(KnowledgeBase kb)
	{
		super();
		
		knowledgeBase = kb;
		
		createRuleLists();
		createComponents();
		setToolTips();
		setGlobalKeyListeners();
		
		layoutComponents();
		
		if(knowledgeBase.getNumberOfRules() == 0)
			newRuleButton.doClick();
	}
	
	private void createRuleLists()
	{
		rulesListModel = new DefaultListModel();
		ruleDisplayListModel = new DefaultListModel();

		rulesList = new JList(rulesListModel);
		ruleDisplayList = new JList(ruleDisplayListModel);

		rulesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		ruleDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		
		// sets custom renderers, allowing multi-line display and adding appropriate prefixes respectively 
		rulesList.setCellRenderer(new MultiLineCellRenderer());
		ruleDisplayList.setCellRenderer(new RuleDisplayCellRenderer());
		
		rulesList.addListSelectionListener(this);
		rulesList.addKeyListener(this);
		ruleDisplayList.addListSelectionListener(this);
		ruleDisplayList.addKeyListener(this);
		
		rulesListScrollPane = new JScrollPane(rulesList);
		ruleDisplayScrollPane = new JScrollPane(ruleDisplayList);
		
		setRulesList();
	}
	
	private void createComponents()
	{
//		controlPanel = new JPanel();
		
		newRuleButton = new JButton("New Rule");
		deleteRuleButton = new JButton("Delete Rule");
		newLineButton = new JButton("New Line");
		deleteLineButton = new JButton("Delete Line");
		
		// placeholder values - should have a global keep track of available values and modify appropriately
		typeCombo = new JComboBox(new String[] {"IF", "AND", "OR", "THEN"});
		variableCombo = new JComboBox();
		relationCombo = new JComboBox(new String[] {"IS"});
		valueCombo = new JComboBox();
		
		variableCombo.setEditable(true);
		valueCombo.setEditable(true);
		// set default values in variable combo box - not needed if starting from scratch, but is if you're loading a knowledgebase to start
		setVariableComboList();

//		AutoCompleteDecorator.decorate(variableCombo);
//		AutoCompleteDecorator.decorate(valueCombo);
		
		LNLabel = new JLabel("Likelihood of necessity:");
		LSLabel = new JLabel("Likelihood of sufficiency:");
		LNField = new JTextField(15);
		LSField = new JTextField(15);
		LNLabel.setVisible(false);
		LSLabel.setVisible(false);
		LNField.setVisible(false);
		LSField.setVisible(false);
		
		Hashtable labelTable1 = new Hashtable();
		labelTable1.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable1.put( new Integer( 50 ), new JLabel("0.5") );
		labelTable1.put( new Integer( 100 ), new JLabel("1.0") );
		
		CFLabel = new JLabel("Certainty Factor:");
		CFSlider = new JSlider(0,100);
		CFSlider.setLabelTable( labelTable1 );
		CFSlider.setPaintLabels(true);
		CFSlider.setPaintTicks(true);
		CFLabel.setVisible(false);
		CFSlider.setVisible(false);
		CFField = new JTextField(10);
		CFField.setVisible(false);
		
		Hashtable labelTable2 = new Hashtable();
		labelTable2.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable2.put( new Integer( 50 ), new JLabel("0.5") );
		labelTable2.put( new Integer( 100 ), new JLabel("1.0") );
		
		priorLabel = new JLabel("Prior probability:");
		priorSlider = new JSlider(0,100);
		priorSlider.setLabelTable( labelTable2 );
		priorSlider.setPaintLabels(true);
		priorSlider.setPaintTicks(true);
		priorLabel.setVisible(false);
		priorSlider.setVisible(false);
		priorField = new JTextField(10);
		priorField.setVisible(false);
		
		//add action listener to EVERYTHING
		newRuleButton.addActionListener(this);
		deleteRuleButton.addActionListener(this);
		newLineButton.addActionListener(this);
		deleteLineButton.addActionListener(this);
		
		typeCombo.addActionListener(this);
		variableCombo.addActionListener(this);
//		variableCombo.addItemListener(this);
		
		relationCombo.addActionListener(this);
		valueCombo.addActionListener(this);
		
		LNField.addActionListener(this);
		LSField.addActionListener(this);
		
		CFSlider.addChangeListener(this);
		priorSlider.addChangeListener(this);
		CFField.addActionListener(this);
		priorField.addActionListener(this);
		
	}
	
	private void setToolTips()
	{
		newRuleButton.setToolTipText("Add a new rule to the knowledge base [Ctrl + K]");
		deleteRuleButton.setToolTipText("Remove the selected rule from the knowledge base [Delete]");
		newLineButton.setToolTipText("Add a conditional to the selected rule [Ctrl + L]");
		deleteLineButton.setToolTipText("Remove the selected conditional from the selected rule [Delete]");
		
		typeCombo.setToolTipText("Adjust the type of the selected conditional");
		variableCombo.setToolTipText("Change the variable associated with the selected conditional");
		relationCombo.setToolTipText("Set the relation between the variable and value");
		valueCombo.setToolTipText("Set the comparison value of the selected conditional");
		
		rulesList.setToolTipText("Rearrange with [Shift + UP] or [Shift + DOWN]");
//		ruleDisplayList.setToolTipText("");
	}
	
	private void setGlobalKeyListeners()
	{
		Action newRuleAction = new AbstractAction()
		{
            @Override
            public void actionPerformed(ActionEvent ae)
            {
        			newRuleButton.doClick();
            }
        };
		Action newLineAction = new AbstractAction()
		{
            @Override
            public void actionPerformed(ActionEvent ae)
            {
            	if(selectedRule != null)
        			newLineButton.doClick();
            }
        };
		
        this.getActionMap().put("NewLineButton", newLineAction);
        this.getActionMap().put("NewRuleButton", newRuleAction);
        
        if(SystemUtils.IS_OS_MAC)
        {
        	KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.META_MASK);
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "NewLineButton");
            
            keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.META_MASK);
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "NewRuleButton");
        }
        else
        {
        	KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK);
        	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "NewLineButton");
        	
        	keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK);
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "NewRuleButton");
        }
		
		
       
		
	}
	
	private void layoutComponents()
	{
		layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);	
//		layout.setHonorsVisibility(false);
//		layout.setHonorsVisibility(CFSlider, true);
//		layout.setHonorsVisibility(CFLabel, true);
		
		layout.linkSize(SwingConstants.HORIZONTAL, LNLabel, LSLabel, CFLabel);
				
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(newRuleButton)
								.addComponent(deleteRuleButton))
						.addComponent(rulesListScrollPane, 250, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(newLineButton)
								.addComponent(deleteLineButton))
						.addGroup(layout.createSequentialGroup()
								.addComponent(typeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(variableCombo)
								.addComponent(relationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(valueCombo))
						.addComponent(ruleDisplayScrollPane)
						.addGroup(layout.createSequentialGroup()
								.addComponent(LSLabel)
								.addComponent(LSField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100))
						.addGroup(layout.createSequentialGroup()
								.addComponent(LNLabel)
								.addComponent(LNField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100))
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(CFLabel)
								.addComponent(CFSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100)
								.addComponent(CFField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100))
						.addGroup(layout.createSequentialGroup()
								.addComponent(priorLabel)
								.addComponent(priorSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100)
								.addComponent(priorField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,100)))	
		);
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(newRuleButton)
								.addComponent(deleteRuleButton))
						.addComponent(rulesListScrollPane))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(newLineButton)
								.addComponent(deleteLineButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(typeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(variableCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(relationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(valueCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(ruleDisplayScrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, 200)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup()
								.addComponent(LSLabel)
								.addComponent(LSField,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup()
								.addComponent(LNLabel)
								.addComponent(LNField,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						
						.addGroup(layout.createParallelGroup()
								.addComponent(CFLabel)
								.addComponent(CFSlider,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(CFField,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup()
								.addComponent(priorLabel)
								.addComponent(priorSlider,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(priorField,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
	}
	
	public void updatePanel()
	{
		//TODO start replacing separate calls to these methods with this - watch out for errors!
		setRulesList();
		setRuleDisplayList();
		setTypeBox();
		setVariableComboList();
		setRelationCombo();
		setValueBoxList();
		setUncertaintyFields();
		
		repaint();
	}

	private void setRulesList()
	{
		listChangeFlag = true;
		rulesListModel.clear();
		
		for(Rule r:knowledgeBase.getRuleArray())
		{
			rulesListModel.addElement(r);
		}
		rulesList.setSelectedValue(selectedRule, true);
		
		listChangeFlag = false;
	}
	
	private void setRuleDisplayList()
	{
		listChangeFlag = true;
		
		ruleDisplayListModel.clear();
		
		if(selectedRule != null)
		{	
			for(Antecedent A : selectedRule.getAntecedentArray())
			{
				ruleDisplayListModel.addElement(A);
			}
			
			for(Consequent C : selectedRule.getConsequentArray())
			{
				ruleDisplayListModel.addElement(C);
			}
			
			if(selectedAntecedent != null)
				ruleDisplayList.setSelectedValue(selectedAntecedent, true);
			else if(selectedConsequent != null)
				ruleDisplayList.setSelectedValue(selectedConsequent, true);
		}
		listChangeFlag = false;
	}
	
	private void setVariableComboList()
	{
		listChangeFlag = true;
		variableCombo.removeAllItems();
		
		for (Variable v : knowledgeBase.getVariablesArray())
		{
			if(!v.getName().equals("default"))
				variableCombo.addItem(v);
		}
		
		variableCombo.setSelectedItem(selectedVariable);
		listChangeFlag = false;
	}
	
	private void setValueBoxList()
	{
		listChangeFlag = true;
		
		valueCombo.removeAllItems();
		
		if(selectedVariable != null)
		{
			if(selectedVariable.getIsNumeric())
			{
				if(selectedAntecedent != null)
					valueCombo.addItem(selectedAntecedent.getNumVal());
				else if(selectedConsequent != null)
					valueCombo.addItem(selectedConsequent.getNumVal());
				
				valueCombo.setSelectedIndex(0);
			}
			else	
			{
				for(Value v : selectedVariable.getArrayOfPossibleValues())
				{
					if(!v.getName().equals("Other"))	
						valueCombo.addItem(v);
				}
				valueCombo.setSelectedItem(selectedValue);
			}
		}
		
		listChangeFlag = false;
	}
	
	private void setTypeBox()
	{	
		listChangeFlag = true;
		
		if(selectedAntecedent != null && ruleDisplayList.getSelectedIndex() == 0)
    		typeCombo.setSelectedItem("IF");
    	else if(selectedAntecedent != null && selectedRule.getConnective() == Connectives.AND)
    		typeCombo.setSelectedItem("AND");
    	else if(selectedAntecedent != null && selectedRule.getConnective() == Connectives.OR)
    		typeCombo.setSelectedItem("OR");
    	else if(selectedConsequent != null)
    		typeCombo.setSelectedItem("THEN");
    	else
    		typeCombo.setSelectedItem("IF");
		
		listChangeFlag = false;
	}
	
	public void setRelationCombo()
	{
		listChangeFlag = true;
		relationCombo.removeAllItems();
		
		if(selectedAntecedent != null)
		{
			for(String s : antRelationOptions)
			{
				relationCombo.addItem(s);
			}
			
			if(selectedAntecedent.getIsNumeric())
			{
				switch(selectedAntecedent.getComparison())
				{
					case EQ:
						relationCombo.setSelectedItem("=");
						break;
					case GT:
						relationCombo.setSelectedItem(">");
						break;
					case GTEQ:
						relationCombo.setSelectedItem(">=");
						break;
					case LT:
						relationCombo.setSelectedItem("<");
						break;
					case LTEQ:
						relationCombo.setSelectedItem("<=");
						break;
					case NEQ:
						relationCombo.setSelectedItem("!=");
						break;
				}
			}
		}
		else if(selectedConsequent != null)
		{
			for(String s : conRelationOptions)
			{
				relationCombo.addItem(s);
			}
			
			if(selectedConsequent.getIsNumeric())
				relationCombo.setSelectedItem("=");
			else
				relationCombo.setSelectedItem("IS");
		}
		else
		{
			relationCombo.addItem("IS");
			relationCombo.setSelectedItem("IS");
		}
		listChangeFlag = false;
	}
	
	public void setUncertaintyFields()
	{
		listChangeFlag = true;
		
		CFLabel.setVisible(false);
		CFSlider.setVisible(false);
		CFField.setVisible(false);
		priorLabel.setVisible(false);
		priorSlider.setVisible(false);
		priorField.setVisible(false);
		LNLabel.setVisible(false);
		LSLabel.setVisible(false);
		LNField.setVisible(false);
		LSField.setVisible(false);
		
		if(selectedAntecedent != null && !selectedAntecedent.getIsNumeric() && knowledgeBase.getUncertaintyMethod() == UncertaintyMethod.BAYESIAN)
		{
			LNLabel.setVisible(true);
			LSLabel.setVisible(true);
			LNField.setVisible(true);
			LSField.setVisible(true);
			
			LNField.setText(Double.toString(selectedAntecedent.getLikelihoodOfNecessity()));
			LSField.setText(Double.toString(selectedAntecedent.getLikelihoodOfSufficiency()));
		}
		
		if(selectedConsequent != null && knowledgeBase.getUncertaintyMethod() == UncertaintyMethod.CERTAINTY_FACTOR)
		{
			CFLabel.setVisible(true);
			CFSlider.setVisible(true);
			CFField.setVisible(true);
			
			CFSlider.setValue((int) (selectedConsequent.getCertaintyFactor() * 100));
			CFField.setText("" + selectedConsequent.getCertaintyFactor());
		}
		
		if(selectedConsequent != null && selectedVariable != null && selectedValue != null
				&& !selectedConsequent.getIsNumeric() 
				&& !selectedValue.getName().equals("default")
				&& !selectedValue.getName().equals("") 
				&&  knowledgeBase.getUncertaintyMethod() == UncertaintyMethod.BAYESIAN)
		{
			priorLabel.setVisible(true);
			priorSlider.setVisible(true);
			priorField.setVisible(true);
			
			priorSlider.setValue((int) (selectedConsequent.getVariable().getBelief(selectedValue) * 100));
			priorField.setText("" + selectedConsequent.getVariable().getBelief(selectedValue));
		}
		
		listChangeFlag = false;
	}
	
	public void setKnowledgeBase(KnowledgeBase kb)
	{
		knowledgeBase = kb;
		
		selectedRule = null;
		selectedAntecedent = null;
		selectedConsequent = null;
		selectedVariable = null;
		selectedValue = null;
		
		if(knowledgeBase.getNumberOfRules() == 0)
			newRuleButton.doClick();
		
		setRulesList();
		setRuleDisplayList();
		setVariableComboList();
		setValueBoxList();
		setTypeBox();
		
		repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newRuleButton)
		{
//			System.out.println("New Rule button fired");
			
			selectedRule = new Rule();
			knowledgeBase.addRule(selectedRule);
			
			selectedAntecedent = null;
			selectedConsequent = null;
			selectedVariable = null;
			selectedValue = null;

			newLineButton.doClick();
			
			repaint();
		}
		
		if(e.getSource() == deleteRuleButton && selectedRule != null)
		{
//			System.out.println("Delete Rule button fired");
			int i = rulesList.getSelectedIndex();
			
			if(i == rulesListModel.size() - 1)
				i--;
			else if(!(rulesListModel.size() > 0))
				i = -1;
			
			knowledgeBase.removeRule(selectedRule);
			setRulesList();
			
			if(i != -1)
				selectedRule = (Rule) rulesListModel.get(i);
			else
				selectedRule = null;
			
			rulesList.setSelectedValue(selectedRule, true);
			
			selectedAntecedent = null;
			selectedConsequent = null;
			selectedVariable = null;
			selectedValue = null;
			
			setRuleDisplayList();
			setVariableComboList();
			setValueBoxList();
			setTypeBox();
			setUncertaintyFields();
			
			repaint();
		}
		
		if(e.getSource() == newLineButton && selectedRule != null)
		{
//			System.out.println("New Line button fired");
			
			selectedAntecedent = new Antecedent();
			selectedConsequent = null;
			selectedVariable = null;
			selectedValue = selectedAntecedent.getValue();
			
			selectedRule.addAntecedent(selectedAntecedent);
			
			setRulesList();
			setRuleDisplayList();
			setVariableComboList();
			setValueBoxList();
			setTypeBox();
			setRelationCombo();
			setUncertaintyFields();
			
			repaint();
			
			variableCombo.requestFocus();
		}
		
		if(e.getSource() == deleteLineButton && selectedRule != null)
		{
//			System.out.println("delete Line button fired");
			
			selectedRule.removeConditional(ruleDisplayList.getSelectedValue());
			
			selectedAntecedent = null;
			selectedConsequent = null;
			selectedVariable = null;
			selectedValue = null;
			
			setRulesList();
			setRuleDisplayList();
			setTypeBox();
			setVariableComboList();
			setRelationCombo();
			setValueBoxList();
			setUncertaintyFields();
			
			repaint();
		}
		
		if(e.getSource() == typeCombo && !listChangeFlag)
		{
//			System.out.println("Type Combo changed");
			
			if(selectedAntecedent != null && typeCombo.getSelectedItem().equals("THEN"))
			{	

				//here we go, convert an antecedent to a consequent
				selectedConsequent = selectedAntecedent.convertToConsequent();
				selectedRule.removeConditional(selectedAntecedent);
				selectedRule.addConsequent(selectedConsequent);
				selectedAntecedent = null;
			}
			else if(selectedConsequent != null && !typeCombo.getSelectedItem().equals("THEN"))
			{
					selectedAntecedent = selectedConsequent.convertToAntecedent();
					selectedRule.removeConditional(selectedConsequent);
					selectedRule.addAntecedent(selectedAntecedent);
					selectedConsequent = null;
			}
			
			if(selectedAntecedent != null || selectedConsequent != null)
			{
				if(typeCombo.getSelectedItem().equals("AND"))
					selectedRule.setConnective(Connectives.AND);
				
				if(typeCombo.getSelectedItem().equals("OR"))
					selectedRule.setConnective(Connectives.OR);
				
				
			}
			setRuleDisplayList();
			setValueBoxList();
			setRelationCombo();
			setTypeBox();
			setUncertaintyFields();
			repaint();
		}
		
		if(e.getSource() == variableCombo && e.getActionCommand().equals("comboBoxChanged"))
		{			
//			System.out.println(variableCombo.getSelectedItem() + " at index: " + variableCombo.getSelectedIndex());
			
			if(variableCombo.getSelectedItem() != null && variableCombo.getSelectedItem() != selectedVariable && !listChangeFlag)
			{
				// we have liftoff. checked to see whether it's the current item or blank
				// otherwise, see if it's a new string or another variable, then update the rule accordingly
				
//				System.out.println("Variable Combo changed");
				
				if(variableCombo.getSelectedIndex() == -1)
				{
					// we have a new variable in the system!
					// do we bother to check whether it was in the system already? if the name matches another variable
					// but the user typed it in, it still registers as a new variable - we don't want duplicates in the kb
					// so compare against the variable list in the combo box already, in name only.
					
					// create a new variable object, clear the current value in the program and ant/con and update value list 
					// (update values method can handle no possible values, i think.)
					// then add it to the antecedent or consequent (have to check manually, didn't think that one through!)
					
					String newVar = (String) variableCombo.getSelectedItem();
					int alreadyExists = -1;
					
					for(int i = 0; i < variableCombo.getItemCount(); i++)
					{
						if(newVar.equalsIgnoreCase(((Variable) variableCombo.getItemAt(i)).getName()))
							alreadyExists = i;
					}
					
					// create the new variable, depending on whether it already exists
					if(alreadyExists != -1)
						selectedVariable = (Variable) variableCombo.getItemAt(alreadyExists);
					else
						selectedVariable = new Variable(newVar);

				}
				else
				{
					selectedVariable = (Variable) variableCombo.getSelectedItem();
				}
				
				if(selectedAntecedent != null)
					selectedAntecedent.setVariable(selectedVariable);
				else if(selectedConsequent != null)
					selectedConsequent.setVariable(selectedVariable);
				
				// making setVariable method in antecedent/consequent null it's own value, or set it to a blank value. so it know's it's consistent
				
				selectedValue = null;
				setValueBoxList();									
//				setVariableComboList();   //commented to prevent reordering on arrow selection in box
				setRelationCombo();
				setUncertaintyFields();
				repaint();
			}
		}
		
		if(e.getSource() == relationCombo && e.getActionCommand().equals("comboBoxChanged") && !listChangeFlag)
		{
//			System.out.println("Relation Combo changed");
			
			if(selectedAntecedent != null)
			{
				if(relationCombo.getSelectedItem().equals("IS"))
				{
					selectedAntecedent.setIsNumeric(false);
					selectedValue = null;
				}
				else if(relationCombo.getSelectedItem().equals("="))
				{
					selectedAntecedent.setComparison(Comparison.EQ);
				}
				else if(relationCombo.getSelectedItem().equals(">"))
				{
					selectedAntecedent.setComparison(Comparison.GT);
				}
				else if(relationCombo.getSelectedItem().equals(">="))
				{
					selectedAntecedent.setComparison(Comparison.GTEQ);
				}
				else if(relationCombo.getSelectedItem().equals("<"))
				{
					selectedAntecedent.setComparison(Comparison.LT);
				}
				else if(relationCombo.getSelectedItem().equals("<="))
				{
					selectedAntecedent.setComparison(Comparison.LTEQ);
				}
				else if(relationCombo.getSelectedItem().equals("!="))
				{
					selectedAntecedent.setComparison(Comparison.NEQ);
				}
			}
			else if(selectedConsequent != null)
			{
				if(relationCombo.getSelectedItem().equals("IS"))
				{
					selectedConsequent.setIsNumeric(false);
					selectedValue = null;
				}
				else if(relationCombo.getSelectedItem().equals("="))
				{
					selectedConsequent.setIsNumeric(true);
				}
			}
			
			setValueBoxList();									
			setVariableComboList();
			setRelationCombo();
			setUncertaintyFields();
			repaint();
			
		}
		
		
		if(e.getSource() == valueCombo && e.getActionCommand().equals("comboBoxChanged") && (selectedAntecedent != null || selectedConsequent != null))
		{
			if(valueCombo.getSelectedItem() != null && valueCombo.getSelectedItem() != selectedValue && !listChangeFlag)
			{
//				System.out.println("Value Combo changed\nSelected index = " + valueCombo.getSelectedItem());
				
				boolean numeric = false;
				
				if(selectedAntecedent != null)
				{
					if(selectedAntecedent.getIsNumeric() && valueCombo.getSelectedIndex() == -1)
					{
						numeric = true;
						if(!valueCombo.getSelectedItem().equals(""))
						{
							try
							{
								selectedAntecedent.setValue(Double.parseDouble("" + valueCombo.getSelectedItem()));
							}
							catch (NumberFormatException ex){}
						}
					}
				}
				
				else if(selectedConsequent != null)
				{
					if(selectedConsequent.getIsNumeric() && valueCombo.getSelectedIndex() == -1)
					{
						numeric = true;
						if(!valueCombo.getSelectedItem().equals(""))
						{
							try
							{
								selectedConsequent.setValue(Double.parseDouble("" + valueCombo.getSelectedItem()));
							}
							catch (NumberFormatException ex){}
						}	
					}
				}
				
				
				if(!numeric)
				{
					if(valueCombo.getSelectedIndex() == -1)
					{
						String newVar = valueCombo.getSelectedItem().toString();
						int alreadyExists = -1;
						
						for(int i = 0; i < valueCombo.getItemCount(); i++)
						{
							if(newVar.equalsIgnoreCase(((Value) valueCombo.getItemAt(i)).getName()))
								alreadyExists = i;
						}
						
						if(alreadyExists != -1)
							selectedValue = (Value) valueCombo.getItemAt(alreadyExists);
						else// if(!newVar.equals(""))
							selectedValue = new Value(newVar);
					}
					else// if(!valueCombo.getSelectedItem().equals(""))
					{
						selectedValue = (Value) valueCombo.getSelectedItem();
					}
					
					if(selectedAntecedent != null)
					{
	//TODO				System.out.println("set ant value and new line");
						selectedAntecedent.setValue(selectedValue);
	//					newLineButton.doClick();
					}
						
					else if(selectedConsequent != null)
						selectedConsequent.setValue(selectedValue);
				}
				
				
				// making setValue method in ant/con add the value automatically to the variable's list of possibles
				
				setVariableComboList();
				setValueBoxList();
				setUncertaintyFields();
				repaint();
			}
		}
		
		if(e.getSource() == LNField && !listChangeFlag)
		{
			try
			{
				selectedAntecedent.setLikelihoodOfNecessity(Double.parseDouble(LNField.getText()));
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
			}
			catch(NullPointerException ex)
			{
				System.out.println("Please enter a number.");
			}
			setUncertaintyFields();
			setRulesList();
			setRuleDisplayList();
		}
		
		if(e.getSource() == LSField && !listChangeFlag)
		{
			try
			{
				selectedAntecedent.setLikelihoodOfSufficiency(Double.parseDouble(LSField.getText()));
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
			}
			catch(NullPointerException ex)
			{
				System.out.println("Please enter a number.");
			}
			setUncertaintyFields();
			setRulesList();
			setRuleDisplayList();
		}
		
		if(e.getSource() == CFField && !listChangeFlag)
		{
			try
			{
				Double cf = Double.parseDouble(CFField.getText());
				if(cf >= 0.0 && cf <= 1.0)
					selectedConsequent.setCertaintyFactor(cf);
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
			}
			catch(NullPointerException ex)
			{
				System.out.println("Please enter a number.");
			}
			setUncertaintyFields();
			setRulesList();
			setRuleDisplayList();
		}
		
		if(e.getSource() == priorField && !listChangeFlag && !selectedValue.getName().equals("Default"))
		{
			try
			{
				Double p = Double.parseDouble(priorField.getText());
				if(p >= 0.0 && p <= 1.0)
					selectedConsequent.getVariable().setBelief(selectedValue, p);
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
			}
			catch(NullPointerException ex)
			{
				System.out.println("Please enter a number.");
			}
			setUncertaintyFields();
			setRulesList();
			setRuleDisplayList();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{		
		if (e.getSource() == rulesList && !e.getValueIsAdjusting() && !listChangeFlag)
		{
			selectedRule = 	(Rule) rulesList.getSelectedValue();
			
			// may not be required if calls to update combo boxes occur in the if statements setting them
			selectedAntecedent = null;
			selectedConsequent = null;
			selectedVariable = null;
			selectedValue = null;
			
			variableCombo.setSelectedItem(null);
			valueCombo.setSelectedItem(null);
			setRuleDisplayList();
			setTypeBox();
			setRelationCombo();
			setUncertaintyFields();
			
//			System.out.println("Selected rule index: " + rulesList.getSelectedIndex());
		}
		
		if (e.getSource() == ruleDisplayList && !e.getValueIsAdjusting() && !listChangeFlag)
		{
			if(ruleDisplayList.getSelectedValue() instanceof Antecedent)
			{	
				selectedAntecedent = (Antecedent) ruleDisplayList.getSelectedValue();
				selectedConsequent = null;
				
				selectedVariable = selectedAntecedent.getVariable();
				selectedValue = selectedAntecedent.getValue();
			}
			else if(ruleDisplayList.getSelectedValue() instanceof Consequent)
			{	
				selectedConsequent = (Consequent) ruleDisplayList.getSelectedValue();
				selectedAntecedent = null;
				
				selectedVariable = selectedConsequent.getVariable();
				selectedValue = selectedConsequent.getValue();
			}
			variableCombo.setSelectedItem(selectedVariable);
			setValueBoxList();
			setTypeBox();
			setRelationCombo();
			setUncertaintyFields();
		}
	}

	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == CFSlider && !listChangeFlag)
		{
			selectedConsequent.setCertaintyFactor((((double) CFSlider.getValue())/100));
		}
		
		if(e.getSource() == priorSlider && !listChangeFlag && !selectedValue.getName().equals("Default"))
		{
			selectedConsequent.getVariable().setBelief(selectedValue, (((double) priorSlider.getValue())/100));
		}

		setUncertaintyFields();
		setRulesList();
		setRuleDisplayList();
		
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == rulesList && selectedRule != null 
				&& e.getKeyCode() == KeyEvent.VK_UP && e.isShiftDown())
		{
			knowledgeBase.shiftRuleUp(selectedRule);
			setRulesList();
			repaint();
			e.consume();
		}
		if(e.getSource() == rulesList && selectedRule != null 
				&& e.getKeyCode() == KeyEvent.VK_DOWN && e.isShiftDown())
		{
			knowledgeBase.shiftRuleDown(selectedRule);
			setRulesList();
			repaint();
			e.consume();
		}
		
		if(e.getSource() == rulesList && e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			deleteRuleButton.doClick();
		}
		if(e.getSource() == ruleDisplayList && e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			deleteLineButton.doClick();
		}
	}
	
	
 	class RuleDisplayCellRenderer extends JLabel implements ListCellRenderer 
	{
	    public RuleDisplayCellRenderer() 
	    {
	        super();
	    	setOpaque(true);
	    }

	    public Component getListCellRendererComponent(JList list,
	                                                  Object value,
	                                                  int index,
	                                                  boolean isSelected,
	                                                  boolean cellHasFocus) 
	    {
	    	String prefix = "THEN ";
	        Border border = null;

	    	if(index == 0 && value instanceof Antecedent)
	    		prefix = "IF ";
	    	else if(value instanceof Antecedent)
	    		prefix = selectedRule.getConnective().toString() + " ";
//	    	else if(index == selectedRule.getNumberOfAntecedents())
//	    		prefix = "THEN ";
	        
	    	setText(prefix + value.toString());

	        Color background;
	        Color foreground;

	        // check if this cell represents the current DnD drop location
	        JList.DropLocation dropLocation = list.getDropLocation();
	        if (dropLocation != null
	                && !dropLocation.isInsert()
	                && dropLocation.getIndex() == index)
	        {
	            background = UIManager.getColor("List.dropCellBackground");
	            foreground = UIManager.getColor("List.dropCellForeground");
	            
	            isSelected = true;

	        // check if this cell is selected
	        } else if (isSelected) 
	        {
	            background = list.getSelectionBackground();
	            foreground = list.getSelectionForeground();

	        // unselected, and not the DnD drop location
	        } else 
	        {
	            background = list.getBackground();
	            foreground = list.getForeground();
	        };

	        setBackground(background);
	        setForeground(foreground);
	        
	        if (cellHasFocus) 
	        {
	        	if (isSelected) 
	        	{
	        		border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
	        	}
	        	if (border == null) 
	        	{
	        		border = UIManager.getBorder("List.focusCellHighlightBorder");
	        	}
	        } else
	        {
	        	border = UIManager.getBorder("List.noFocusBorder");
	        }
	        setBorder(border);

	        return this;
	    }
	}
}