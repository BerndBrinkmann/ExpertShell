package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CBanner;
import swing2swt.layout.BoxLayout;
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
import gui.*;
import datatypes.*;

public class VariablesGUI extends Composite {
	private Text descriptionTxt;
	private Text txtVariableName;
	private String Name;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	// Get KnowledgeBase
	 
	
	
	public VariablesGUI(Composite parent, int style,final KnowledgeBase KBase) {
		
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		setLayout(new GridLayout(2, false));
		
		final List variableList = new List(this, SWT.BORDER);
		variableList.setToolTipText("Lists all variables used in the current Knowledge Base");
		GridData gd_variableList = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2);
		gd_variableList.heightHint = 398;
		gd_variableList.widthHint = 141;
		variableList.setLayoutData(gd_variableList);
		
		Group GroupAddDelete = new Group(this, SWT.NONE);
		GridData gd_GroupAddDelete = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_GroupAddDelete.heightHint = 328;
		GroupAddDelete.setLayoutData(gd_GroupAddDelete);
		GroupAddDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label lblName = new Label(GroupAddDelete, SWT.NONE);
		lblName.setBounds(10, 10, 87, 15);
		lblName.setText("Variable Name:");
		
		Label lblDescription = new Label(GroupAddDelete, SWT.NONE);
		lblDescription.setBounds(30, 31, 66, 21);
		lblDescription.setText("Description:");
		
		Label lblAskUser = new Label(GroupAddDelete, SWT.NONE);
		lblAskUser.setBounds(43, 243, 48, 15);
		lblAskUser.setText("Ask User:");
		
		descriptionTxt = new Text(GroupAddDelete, SWT.BORDER);
		descriptionTxt.setToolTipText("Enter a discribtion of the variable in this feld");
		descriptionTxt.setBounds(103, 34, 378, 188);
		
		txtVariableName = new Text(GroupAddDelete, SWT.BORDER);
		txtVariableName.setBounds(103, 7, 203, 21);
		
		Group group = new Group(GroupAddDelete, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		group.setToolTipText("");
		group.setBounds(104, 243, 97, 31);
		
		Button btnRadioButtonYes = new Button(group, SWT.RADIO);
		btnRadioButtonYes.setToolTipText("Ask User for varualbe value");
		btnRadioButtonYes.setSelection(true);
		btnRadioButtonYes.setBounds(10, 10, 39, 16);
		btnRadioButtonYes.setText("Yes");
		
		Button btnRadioButtonNo = new Button(group, SWT.RADIO);
		btnRadioButtonNo.setToolTipText("Don't Ask User for varualbe value");
		btnRadioButtonNo.setBounds(55, 10, 39, 16);
		btnRadioButtonNo.setText("No");
		
		Button btnAddVariable = new Button(GroupAddDelete, SWT.NONE);
		btnAddVariable.setToolTipText("Adds a Variable to the Variable List of the KnowledgeBase");
		btnAddVariable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if(txtVariableName.getText()=="")
				{
					//checks is a name for a variable was entered
					JOptionPane.showMessageDialog(null, "Please enter a Variable Name");
					txtVariableName.setFocus();
					
				}
				else
				{
				
					Name = txtVariableName.getText();
				  variableList.add(Name);
				 
				  
				  
				}
			}
		});
		btnAddVariable.setBounds(103, 285, 75, 25);
		btnAddVariable.setText("Add ");
		
		Button btnDeleteVariable = new Button(GroupAddDelete, SWT.NONE);
		btnDeleteVariable.setToolTipText("Deletes a Variable from the Variable List of the KnowledgeBase");
		btnDeleteVariable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (variableList.getSelectionIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Please select Variable");
				}
				else
				{
					variableList.remove(variableList.getSelectionIndex());
				}
			}
		});
		btnDeleteVariable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDeleteVariable.setBounds(181, 285, 87, 25);
		btnDeleteVariable.setText("Delete ");
		
		Group composite_2 = new Group(this, SWT.NONE);
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_2.heightHint = 37;
		composite_2.setLayoutData(gd_composite_2);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
