package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CBanner;
//import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import datatypes.*;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;


public class VariablesGUI extends Composite {
	

	 private String Name;
	 private Text descriptionTxt;
	 private Text txtVariableName;
	 private List variableList;
	 private List possibleValuesList;
	 private Variable currentvariable = new Variable();
	 private KnowledgeBase KBase;
	 private Text QuestionPrompt;
	 private Button btnRadioButtonYes;
	 private Button btnRadioButtonNo;
	 private Button btnSave;
	 private Variable TempVariable = new Variable();		
	 /**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */

	
	public VariablesGUI(Composite parent, int style,  KnowledgeBase kb) {
		
		super(parent, style);

		KBase = kb;
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		setLayout(new GridLayout(2, false));
		variableList = new List(this, SWT.BORDER | SWT.V_SCROLL);
		variableList.setToolTipText("Lists all variables used in the knowledgebase");
		variableList.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		setVariableList();
		Group GroupAddDelete = new Group(this, SWT.NONE);
		possibleValuesList = new List(GroupAddDelete, SWT.BORDER | SWT.V_SCROLL);
		possibleValuesList.setToolTipText("Lists all possible values for this variable which are defined in the knowledgebase");
		possibleValuesList.setBounds(112, 215, 369, 148);
		
		variableList.addSelectionListener(new SelectionAdapter() {	
			@Override
			public void widgetSelected(SelectionEvent e) {
				//selection
				if (e.getSource() == variableList && variableList.getSelection().length !=0)
				{
					setForInput(true);
					possibleValuesList.removeAll();
					currentvariable = KBase.getVariable(variableList.getSelection()[0]);
					if (currentvariable.isUserInput() == false) 
					{
						btnRadioButtonYes.setSelection(false); 
						btnRadioButtonNo.setSelection(true);
					}
					else
					{	
						btnRadioButtonYes.setSelection(true) ;
						btnRadioButtonNo.setSelection(false);
					}
					descriptionTxt.setText(currentvariable.getDescription());
					txtVariableName.setText(currentvariable.getName());
					QuestionPrompt.setText(currentvariable.getQueryPrompt());
					if (currentvariable.getArrayOfPossibleValues()!= null)
					{
						for (Value i : currentvariable.getArrayOfPossibleValues())
						{
							possibleValuesList.add(i.toString());
						}
					}
//TODO adjust so if numeric shows a range of values instead of possible values					
					if(currentvariable instanceof NumericVariable)
					{
						
					}
					variableList.deselectAll();
				}
				
			}
		});

		GridData gd_variableList = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 2);
		gd_variableList.heightHint = 441;
		gd_variableList.widthHint = 141;
		variableList.setLayoutData(gd_variableList);
		
		
		GridData gd_GroupAddDelete = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_GroupAddDelete.heightHint = 423;
		GroupAddDelete.setLayoutData(gd_GroupAddDelete);
		GroupAddDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		Label lblName = new Label(GroupAddDelete, SWT.NONE);
		lblName.setToolTipText("Contains the name of the variable");
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblName.setBounds(26, 25, 80, 15);
		lblName.setText("Variable Name:");
		
		Label lblDescription = new Label(GroupAddDelete, SWT.NONE);
		lblDescription.setToolTipText("Contains a description of the Variable. This description is not used in the knowledgebase");
		lblDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblDescription.setBounds(43, 52, 63, 21);
		lblDescription.setText("Description:");
		
		Label lblAskUser = new Label(GroupAddDelete, SWT.NONE);
		lblAskUser.setToolTipText("Determine if the user is asked to input a value or not");
		lblAskUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblAskUser.setBounds(42, 369, 48, 15);
		lblAskUser.setText("Ask User:");
		
		descriptionTxt = new Text(GroupAddDelete, SWT.BORDER);
		descriptionTxt.setToolTipText("Contains a description of the Variable. This description is not used in the knowledgebase");
		descriptionTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				
				//currentvariable.setDescription(descriptionTxt.getText());
			}
		});
		descriptionTxt.setBounds(112, 49, 369, 104);
		

		
		txtVariableName = new Text(GroupAddDelete, SWT.BORDER);
		txtVariableName.setToolTipText("Contains the name of the variable");
		txtVariableName.setBounds(113, 22, 192, 21);
		
		
		
		Group group = new Group(GroupAddDelete, SWT.NONE);
		group.setToolTipText("Determine if the user is asked to input a value or not");
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		group.setBounds(103, 369, 105, 31);
		
		btnRadioButtonYes = new Button(group, SWT.RADIO);
		btnRadioButtonYes.setToolTipText("Determine if the user is asked to input a value or not");
		btnRadioButtonYes.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		btnRadioButtonYes.setSelection(true);
		btnRadioButtonYes.setBounds(10, 10, 39, 16);
		btnRadioButtonYes.setText("Yes");
		
		btnRadioButtonNo = new Button(group, SWT.RADIO);
		btnRadioButtonNo.setToolTipText("Determine if the user is asked to input a value or not");
		btnRadioButtonNo.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		btnRadioButtonNo.setBounds(55, 10, 39, 16);
		btnRadioButtonNo.setText("No");
		
		Label lblPossibleValues = new Label(GroupAddDelete, SWT.NONE);
		lblPossibleValues.setToolTipText("Lists all possible values for this variable which are defined in the knowledgebase");
		lblPossibleValues.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblPossibleValues.setBounds(19, 219, 87, 15);
		lblPossibleValues.setText("Possible Values:");
		
		btnSave = new Button(GroupAddDelete, SWT.NONE);
		btnSave.setToolTipText("Save changes to Knowledgebase");
		btnSave.setBounds(103, 406, 75, 25);
		btnSave.setText("Save");
		
		QuestionPrompt = new Text(GroupAddDelete, SWT.BORDER);
		QuestionPrompt.setToolTipText("Sets the question associated with this variable");
		QuestionPrompt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			
				//currentvariable.setQueryPrompt(QuestionPrompt.getText());
			
			}
		});
		QuestionPrompt.setBounds(113, 159, 368, 50);
		
		Label lblQuestionPrompt = new Label(GroupAddDelete, SWT.NONE);
		lblQuestionPrompt.setToolTipText("Sets the question associated with this variable");
		lblQuestionPrompt.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblQuestionPrompt.setBounds(10, 159, 97, 15);
		lblQuestionPrompt.setText("Question Prompt:");
		
		new Label(this, SWT.NONE);
		
		setForInput(false);
		
		btnSave.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseDown(MouseEvent e) {
				// Save button was pressed
				if(e.getSource() == btnSave && currentvariable != null)
				{
							
					Boolean flag = false;
					TempVariable = currentvariable;
					for (int i = 0;i< KBase.getVariablesArray().size();i++)
					{
						if (KBase.getVariablesArray().get(i).getName().equals(txtVariableName.getText()) && !(currentvariable.getName().equals(txtVariableName.getText())))
						{
							flag = true;
							JOptionPane.showMessageDialog(null, "Variable with that name already exists");
						}
					 
					}
					if(!flag)
					{
						if ( KBase.getTarget() == TempVariable)
						{
							KBase.setTarget(TempVariable);
						}
					TempVariable.setUserInput(btnRadioButtonYes.getSelection());
					TempVariable.setName(txtVariableName.getText().trim());
					TempVariable.setDescription(descriptionTxt.getText());
					TempVariable.setQueryPrompt(QuestionPrompt.getText());
					MainScreen.window.updateKnowledgeBase();
					}
					//KBase.saveVariable(TempVariable,currentvariable );
					variableList.removeAll();
					for (Variable v: KBase.getVariablesArray())
					{
						variableList.add(v.getName());
						//setVariableList();
					}
				}
					
				
				
				if(e.getSource() == btnRadioButtonYes && TempVariable!=null)
				{
					TempVariable.setUserInput(true);
					
				}
				
				if(e.getSource() == btnRadioButtonNo && TempVariable!=null)
				{
					TempVariable.setUserInput(false);
				}
			}
		});
		
		
	}

	public void setForInput(Boolean enable)
	{
		txtVariableName.setEnabled(enable);
		btnRadioButtonYes.setEnabled(enable);
		btnRadioButtonNo.setEnabled(enable);
		descriptionTxt.setEnabled(enable);
		QuestionPrompt.setEnabled(enable);
		possibleValuesList.setEnabled(enable);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setVariableList()
	{
		variableList.removeAll();
		if(KBase.getVariablesArray().isEmpty() == false)
		{
		for (Variable v : KBase.getVariablesArray())
		{
			if(v != null)
			{
				variableList.add(v.toString());
			}
				
		}
		}
	}
	public void updateKBase(KnowledgeBase kb)
	{
		KBase = kb;
		setVariableList();
		 descriptionTxt.setText("");
		 txtVariableName.setText("");
		 QuestionPrompt.setText("");
		 possibleValuesList.removeAll();
		setForInput(false);
		
	}
	
}
