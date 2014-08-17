package gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class userInterface extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public userInterface(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Group grpKnowledgeBaseSelected = new Group(this, SWT.NONE);
		GridData gd_grpKnowledgeBaseSelected = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_grpKnowledgeBaseSelected.heightHint = 68;
		gd_grpKnowledgeBaseSelected.widthHint = 222;
		grpKnowledgeBaseSelected.setLayoutData(gd_grpKnowledgeBaseSelected);
		grpKnowledgeBaseSelected.setText("Knowledge Base Selected");
		
		text = new Text(grpKnowledgeBaseSelected, SWT.BORDER);
		text.setBounds(10, 25, 208, 21);
		
		Group grpSelectRunMethod = new Group(this, SWT.NONE);
		GridData gd_grpSelectRunMethod = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpSelectRunMethod.heightHint = 63;
		gd_grpSelectRunMethod.widthHint = 155;
		grpSelectRunMethod.setLayoutData(gd_grpSelectRunMethod);
		grpSelectRunMethod.setText("Select Run Method");
		
		Button btnDefault = new Button(grpSelectRunMethod, SWT.RADIO);
		btnDefault.setBounds(10, 20, 90, 16);
		btnDefault.setText("Default");
		
		Button btnForwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnForwardChaining.setBounds(10, 42, 141, 16);
		btnForwardChaining.setText("Forward Chaining");
		
		Button btnBackwardChaining = new Button(grpSelectRunMethod, SWT.RADIO);
		btnBackwardChaining.setText("Backward Chaining");
		btnBackwardChaining.setBounds(10, 65, 141, 16);
		
		Group grpSelectReasoningMethod = new Group(this, SWT.NONE);
		grpSelectReasoningMethod.setText("Select Reasoning Method");
		
		Button btnCertaintyFactor = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnCertaintyFactor.setText("Certainty Factor");
		btnCertaintyFactor.setBounds(10, 66, 141, 16);
		
		Button btnBayesianReasoning = new Button(grpSelectReasoningMethod, SWT.RADIO);
		btnBayesianReasoning.setText("Bayesian Reasoning");
		btnBayesianReasoning.setBounds(10, 43, 141, 16);
		
		Button button_2 = new Button(grpSelectReasoningMethod, SWT.RADIO);
		button_2.setText("Default");
		button_2.setBounds(10, 21, 90, 16);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
