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
	
	
	
	private Text descriptionTxt;
	private Text txtVariableName;
	private List variableList;
	private Variable currentvariable ;
	private List possibleValuesList;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	

	
	
	public VariablesGUI(Composite parent, int style,final KnowledgeBase KBase) {
		
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new GridLayout(2, false));
		
		variableList = new List(this, SWT.BORDER);
		variableList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (variableList.getSelection()[0] != null)
				{
					currentvariable = KBase.getVariable(variableList.getSelection()[0]);
					descriptionTxt.setText(currentvariable.getDescription());
					txtVariableName.setText(currentvariable.getName());
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

		GridData gd_variableList = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2);
		gd_variableList.widthHint = 141;
		variableList.setLayoutData(gd_variableList);
		
		Group GroupAddDelete = new Group(this, SWT.NONE);
		GridData gd_GroupAddDelete = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_GroupAddDelete.heightHint = 423;
		GroupAddDelete.setLayoutData(gd_GroupAddDelete);
		GroupAddDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblName = new Label(GroupAddDelete, SWT.NONE);
		lblName.setBounds(10, 10, 87, 15);
		lblName.setText("Variable Name:");
		
		Label lblDescription = new Label(GroupAddDelete, SWT.NONE);
		lblDescription.setBounds(30, 31, 66, 21);
		lblDescription.setText("Description:");
		
		Label lblAskUser = new Label(GroupAddDelete, SWT.NONE);
		lblAskUser.setBounds(42, 369, 48, 15);
		lblAskUser.setText("Ask User:");
		
		descriptionTxt = new Text(GroupAddDelete, SWT.BORDER);
		descriptionTxt.setBounds(103, 34, 378, 104);
		
		txtVariableName = new Text(GroupAddDelete, SWT.BORDER);
		txtVariableName.setBounds(103, 7, 203, 21);
		
		Group group = new Group(GroupAddDelete, SWT.NONE);
		group.setBounds(103, 369, 105, 31);
		
		Button btnRadioButtonYes = new Button(group, SWT.RADIO);
		btnRadioButtonYes.setSelection(true);
		btnRadioButtonYes.setBounds(10, 10, 39, 16);
		btnRadioButtonYes.setText("Yes");
		
		Button btnRadioButtonNo = new Button(group, SWT.RADIO);
		btnRadioButtonNo.setBounds(55, 10, 39, 16);
		btnRadioButtonNo.setText("No");
		
		Button btnAddVariable = new Button(GroupAddDelete, SWT.NONE);
		btnAddVariable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				// add button
			}
		});
		btnAddVariable.setBounds(103, 406, 75, 25);
		btnAddVariable.setText("Add ");
		
		Button btnDeleteVariable = new Button(GroupAddDelete, SWT.NONE);
		btnDeleteVariable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDeleteVariable.setBounds(181, 406, 87, 25);
		btnDeleteVariable.setText("Delete ");
		
		List possibleValuesList = new List(GroupAddDelete, SWT.BORDER);
		possibleValuesList.setBounds(103, 144, 378, 219);
		
		Label lblPossibleValues = new Label(GroupAddDelete, SWT.NONE);
		lblPossibleValues.setBounds(10, 144, 87, 15);
		lblPossibleValues.setText("Possible Values:");
		
		Button btnSave = new Button(GroupAddDelete, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// Save button was pressed
				
				
				if(txtVariableName.getText()=="")
				{
					//checks is a name for a variable was entered
					JOptionPane.showMessageDialog(null, "Please enter a Variable Name");
					txtVariableName.setFocus();
				}
				else
				{
					currentvariable.setName(txtVariableName.getText());
					currentvariable.setDescription(descriptionTxt.getText());
					KBase.saveVariable(currentvariable);
											
				}
				
			}
		});
		btnSave.setBounds(22, 406, 75, 25);
		btnSave.setText("Save");
		new Label(this, SWT.NONE);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
