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
		//setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		setLayout(new GridLayout(2, false));
		
		variableList = new List(this, SWT.BORDER | SWT.V_SCROLL);
		variableList.setToolTipText("Lists all variables used in the knowledgebase");
		variableList.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		variableList.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
		
		setVariableList();
		Group GroupAddDelete = new Group(this, SWT.NONE);
		
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
					if(currentvariable.isNumeric)
					{
						
					}
					variableList.deselectAll();
				}
				
			}
		});

		GridData gd_variableList = new GridData(SWT.FILL, SWT.TOP, false, true, 1, 2);
		gd_variableList.heightHint = 714;
		gd_variableList.widthHint = 141;
		variableList.setLayoutData(gd_variableList);
		GroupAddDelete.setLayout(new GridLayout(2, false));
		
		
		GridData gd_GroupAddDelete = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_GroupAddDelete.heightHint = 713;
		GroupAddDelete.setLayoutData(gd_GroupAddDelete);
		GroupAddDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		Label lblName = new Label(GroupAddDelete, SWT.NONE);
		lblName.setToolTipText("Contains the name of the variable");
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblName.setText("Variable Name:");
		
		

		
		txtVariableName = new Text(GroupAddDelete, SWT.BORDER);
		GridData gd_txtVariableName = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtVariableName.widthHint = 424;
		txtVariableName.setLayoutData(gd_txtVariableName);
		txtVariableName.setToolTipText("Contains the name of the variable");
		
		Label lblDescription = new Label(GroupAddDelete, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblDescription.setToolTipText("Contains a description of the Variable. This description is not used in the knowledgebase");
		lblDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblDescription.setText("Description:");
		
		descriptionTxt = new Text(GroupAddDelete, SWT.BORDER);
		GridData gd_descriptionTxt = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_descriptionTxt.heightHint = 170;
		gd_descriptionTxt.widthHint = 424;
		descriptionTxt.setLayoutData(gd_descriptionTxt);
		descriptionTxt.setToolTipText("Contains a description of the Variable. This description is not used in the knowledgebase");
		descriptionTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				
				//currentvariable.setDescription(descriptionTxt.getText());
			}
		});
		
		Label lblQuestionPrompt = new Label(GroupAddDelete, SWT.NONE);
		lblQuestionPrompt.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblQuestionPrompt.setToolTipText("Sets the question associated with this variable");
		lblQuestionPrompt.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblQuestionPrompt.setText("Question Prompt:");
		
		QuestionPrompt = new Text(GroupAddDelete, SWT.BORDER);
		GridData gd_QuestionPrompt = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_QuestionPrompt.heightHint = 54;
		gd_QuestionPrompt.widthHint = 424;
		QuestionPrompt.setLayoutData(gd_QuestionPrompt);
		QuestionPrompt.setToolTipText("Sets the question associated with this variable");
		QuestionPrompt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			
				//currentvariable.setQueryPrompt(QuestionPrompt.getText());
			
			}
		});
		
		Label lblPossibleValues = new Label(GroupAddDelete, SWT.NONE);
		lblPossibleValues.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblPossibleValues.setToolTipText("Lists all possible values for this variable which are defined in the knowledgebase");
		lblPossibleValues.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblPossibleValues.setText("Possible Values:");
		possibleValuesList = new List(GroupAddDelete, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_possibleValuesList = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_possibleValuesList.widthHint = 412;
		gd_possibleValuesList.heightHint = 223;
		possibleValuesList.setLayoutData(gd_possibleValuesList);
		possibleValuesList.setToolTipText("Lists all possible values for this variable which are defined in the knowledgebase");
		
		Label lblAskUser = new Label(GroupAddDelete, SWT.NONE);
		lblAskUser.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblAskUser.setToolTipText("Determine if the user is asked to input a value or not");
		lblAskUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblAskUser.setText("Ask User:");
		
		
		
		Group group = new Group(GroupAddDelete, SWT.NONE);
		group.setToolTipText("Determine if the user is asked to input a value or not");
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
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
		new Label(GroupAddDelete, SWT.NONE);
		
		btnSave = new Button(GroupAddDelete, SWT.NONE);
		GridData gd_btnSave = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnSave.widthHint = 74;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setToolTipText("Save changes to Knowledgebase");
		btnSave.setText("Save");
		
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
		
		setForInput(false);
		
		
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
