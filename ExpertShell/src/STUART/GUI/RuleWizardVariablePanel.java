package STUART.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import STUART.ADT.*;

public class RuleWizardVariablePanel extends JPanel implements ActionListener, ListSelectionListener, DocumentListener
{
	KnowledgeBase knowledgeBase;
	
	JLabel nameLabel, descriptionLabel, queryLabel, askLabel, valuesLabel;
	JTextField nameField;
	JTextArea queryField, descriptionField;
	JRadioButton askYes, askNo;
	ButtonGroup askGroup;
	
	JList variableList, valueList;
	DefaultListModel variableListModel, valueListModel;
	JScrollPane variableListScrollPane, valueListScrollPane;
	
	GroupLayout layout;
	
	Variable selectedVariable;
	boolean blockInput;
	
	public RuleWizardVariablePanel(KnowledgeBase kb)
	{
		super();
		
		knowledgeBase = kb;
		
		createComponents();
		setToolTips();
		layoutComponents();
		
	}
	
	public void setKnowledgeBase(KnowledgeBase kb)
	{
		knowledgeBase = kb;
		selectedVariable = null;
		
		setVariableList();
	}
	
	public void createComponents()
	{
		nameLabel = new JLabel("Name:");
		descriptionLabel = new JLabel("Description:");
		queryLabel = new JLabel("Query Prompt:");
		askLabel = new JLabel("Ask User?");
		valuesLabel = new JLabel("Possible values");
		
		nameField = new JTextField(30);
		nameField.addActionListener(this);
		
		descriptionField = new JTextArea(3, 30);
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setBorder(UIManager.getBorder("TextField.border"));
		descriptionField.getDocument().addDocumentListener(this);
		
		queryField = new JTextArea(3, 30);
		queryField.setLineWrap(true);
		queryField.setWrapStyleWord(true);
		queryField.setBorder(UIManager.getBorder("TextField.border"));
		queryField.getDocument().addDocumentListener(this);
		

		askYes = new JRadioButton("Yes");
		askNo = new JRadioButton("No");
		
		askGroup = new ButtonGroup();
		askGroup.add(askYes);
		askGroup.add(askNo);
		askYes.addActionListener(this);
		askNo.addActionListener(this);
		
		variableListModel = new DefaultListModel();
		variableList = new JList(variableListModel);
		variableListScrollPane = new JScrollPane(variableList);
		variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		variableList.addListSelectionListener(this);
		
		valueListModel = new DefaultListModel();
		valueList = new JList(valueListModel);
		valueList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		valueList.setLayoutOrientation(JList.VERTICAL_WRAP);
		valueList.setVisibleRowCount(-1);
		valueListScrollPane = new JScrollPane(valueList);
		
		setVariableList();
		setValueList();
	}
	
	private void setToolTips()
	{
		// .setToolTipText("");
		
		nameLabel.setToolTipText("Change the name of the selected variable");
		descriptionLabel.setToolTipText("Give a description to a variable");
		queryLabel.setToolTipText("This will be displayed when the user is required to input a value for the selected variable");
		askLabel.setToolTipText("Sets whether the inference engine will ask for the value of this variable");
		valuesLabel.setToolTipText("A list of all possible values that the selected variable may take");
		
		variableList.setToolTipText("List of all variables in the knowledge base");
	}
	//clears the gui variable list, goes through kb and checks for non-'default' vars and
	//adds to variable list model which is in a JList called varList
	public void setVariableList()
	{
		blockInput = true;
		variableListModel.clear();
		//declaration:expression. interates through an array and the declaration takes the value for each iteration
		for (Variable v : knowledgeBase.getVariablesArray())
		{
			if(!v.getName().equals("default"))	
				variableListModel.addElement(v);
		}
		
		blockInput = false;
	}
	
	public void setValueList()
	{
		valueListModel.clear();
		
		if(selectedVariable != null)
		{	
			for (Value v : selectedVariable.getArrayOfPossibleValues())
			{
				if(!v.getName().equals("Other"))
					valueListModel.addElement(v);
			}
		}
	}
	
	public void updatePanel()
	{
		selectedVariable = null;
		setVariableList();
		setValueList();
	}

 	public void layoutComponents()
	{
		layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHonorsVisibility(false);
		
		layout.linkSize(SwingConstants.HORIZONTAL, nameLabel, descriptionLabel, queryLabel, askLabel);
		layout.linkSize(SwingConstants.HORIZONTAL, nameField, descriptionField, queryField);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addComponent(variableListScrollPane, 200, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(nameLabel)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,200))
						.addGroup(layout.createSequentialGroup()
								.addComponent(descriptionLabel)
								.addComponent(descriptionField))
						.addGroup(layout.createSequentialGroup()
								.addComponent(queryLabel)
								.addComponent(queryField))
						.addGroup(layout.createSequentialGroup()
								.addComponent(askLabel)
								.addComponent(askYes)
								.addComponent(askNo))
						.addComponent(valuesLabel)
						.addComponent(valueListScrollPane))
		);
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addComponent(variableListScrollPane)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup()
								.addComponent(descriptionLabel)
								.addComponent(descriptionField))
						.addGroup(layout.createParallelGroup()
								.addComponent(queryLabel)
								.addComponent(queryField))
						.addGroup(layout.createParallelGroup()
								.addComponent(askLabel)
								.addComponent(askYes)
								.addComponent(askNo))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(valuesLabel)
						.addComponent(valueListScrollPane, 90, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
		);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == nameField && selectedVariable != null)
		{
			if(!nameField.getText().trim().equals(""))
				selectedVariable.setName(nameField.getText().trim());
			repaint();
		}
		
		if(e.getSource() == askYes && selectedVariable != null)
		{
			selectedVariable.setUserInput(true);
		}
		
		if(e.getSource() == askNo && selectedVariable != null)
		{
			selectedVariable.setUserInput(false);
		}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getSource() == variableList && !e.getValueIsAdjusting() && !blockInput)
		{
			selectedVariable = (Variable) variableList.getSelectedValue();
			
			nameField.setText(selectedVariable.getName());
			descriptionField.setText(selectedVariable.getDescription());
			queryField.setText(selectedVariable.getQueryPrompt());
			
			if(selectedVariable.isUserInput())
				askYes.setSelected(true);
			else
				askNo.setSelected(true);
			
			setValueList();
		}
	}
	
	public void insertUpdate(DocumentEvent e) 
	{
		Document doc = e.getDocument();
		
		if(doc == descriptionField.getDocument() && selectedVariable != null)
		{
			try
			{
				selectedVariable.setDescription(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		} else if(doc == queryField.getDocument() && selectedVariable != null)
		{
			try
			{
				selectedVariable.setQueryPrompt(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		}
	}
 
	public void removeUpdate(DocumentEvent e)
	{
		Document doc = e.getDocument();
		if(doc == descriptionField.getDocument() && selectedVariable != null)
		{
			try
			{
				selectedVariable.setDescription(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		} else if(doc == queryField.getDocument() && selectedVariable != null)
		{
			try
			{
				selectedVariable.setQueryPrompt(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		}
	}
 
	public void changedUpdate(DocumentEvent e)
	{ 
		//Plain text components do not fire these events
	}

}