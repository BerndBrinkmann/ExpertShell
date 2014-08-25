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


public class VariablesGUI extends Composite {
	

	private String Name;
	 private Text descriptionTxt;
	 private Text txtVariableName;
	 private List variableList;
	 private List possibleValuesList;
	 private Variable currentvariable = new Variable();
	 private KnowledgeBase KBase;
	 private Text QuestionPrompt;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */

	
	public VariablesGUI(Composite parent, int style,  KnowledgeBase kb) {
		
		super(parent, style);

		KBase = kb;
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		setLayout(new GridLayout(2, false));
		variableList = new List(this, SWT.BORDER);
		
		setVariableList();
		Group GroupAddDelete = new Group(this, SWT.NONE);
		possibleValuesList = new List(GroupAddDelete, SWT.BORDER);
		possibleValuesList.setBounds(112, 215, 369, 148);
		
		variableList.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (variableList.getSelection()[0] != null)
				{

					possibleValuesList.removeAll();
					currentvariable = KBase.getVariable(variableList.getSelection()[0]);
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
		GroupAddDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label lblName = new Label(GroupAddDelete, SWT.NONE);
		lblName.setBounds(26, 25, 80, 15);
		lblName.setText("Variable Name:");
		
		Label lblDescription = new Label(GroupAddDelete, SWT.NONE);
		lblDescription.setBounds(43, 52, 63, 21);
		lblDescription.setText("Description:");
		
		Label lblAskUser = new Label(GroupAddDelete, SWT.NONE);
		lblAskUser.setBounds(42, 369, 48, 15);
		lblAskUser.setText("Ask User:");
		
		descriptionTxt = new Text(GroupAddDelete, SWT.BORDER);
		descriptionTxt.setBounds(112, 49, 369, 104);
		

		
		txtVariableName = new Text(GroupAddDelete, SWT.BORDER);
		txtVariableName.setBounds(113, 22, 192, 21);
		
		Group group = new Group(GroupAddDelete, SWT.NONE);
		group.setBounds(103, 369, 105, 31);
		
		Button btnRadioButtonYes = new Button(group, SWT.RADIO);
		btnRadioButtonYes.setSelection(true);
		btnRadioButtonYes.setBounds(10, 10, 39, 16);
		btnRadioButtonYes.setText("Yes");
		
		Button btnRadioButtonNo = new Button(group, SWT.RADIO);
		btnRadioButtonNo.setBounds(55, 10, 39, 16);
		btnRadioButtonNo.setText("No");
		
		Label lblPossibleValues = new Label(GroupAddDelete, SWT.NONE);
		lblPossibleValues.setBounds(19, 219, 87, 15);
		lblPossibleValues.setText("Possible Values:");
		
		Button btnSave = new Button(GroupAddDelete, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// Save button was pressed

				Variable TempVariable = new Variable();
				if(txtVariableName.getText()=="")
				{
					//checks is a name for a variable was entered
					JOptionPane.showMessageDialog(null, "Please enter a Variable Name");
					txtVariableName.setFocus();
				}
				else
				{
					for (int i = 0;i< KBase.getVariablesArray().size();i++)
					{
						if (KBase.getVariablesArray().get(i).getName().equals(txtVariableName.getText()))
						{
						TempVariable = KBase.getVariablesArray().get(i);
						}
					}
					TempVariable.setName(txtVariableName.getText());
					TempVariable.setDescription(descriptionTxt.getText());
					TempVariable.setQueryPrompt(QuestionPrompt.getText());
					KBase.saveVariable(TempVariable);
					variableList.removeAll();
					for (Variable v: KBase.getVariablesArray())
					{
						variableList.add(v.getName());
					}
				}
				
			}
		});
		btnSave.setBounds(103, 406, 75, 25);
		btnSave.setText("Save");
		
		QuestionPrompt = new Text(GroupAddDelete, SWT.BORDER);
		QuestionPrompt.setBounds(113, 159, 368, 50);
		
		Label lblQuestionPrompt = new Label(GroupAddDelete, SWT.NONE);
		lblQuestionPrompt.setBounds(10, 159, 97, 15);
		lblQuestionPrompt.setText("Question Prompt:");
		
		new Label(this, SWT.NONE);
		


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
}
