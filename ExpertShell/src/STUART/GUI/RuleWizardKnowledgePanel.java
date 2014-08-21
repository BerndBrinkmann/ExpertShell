package STUART.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import STUART.ADT.ConflictResolutionMethod;
import STUART.ADT.InferenceMethod;
import STUART.ADT.KnowledgeBase;
import STUART.ADT.UncertaintyMethod;

public class RuleWizardKnowledgePanel extends JPanel implements ActionListener, DocumentListener
{

	KnowledgeBase knowledgeBase;
	
	JLabel nameLabel, descriptionLabel, uncertaintyLabel, conflictLabel;
	JTextField nameField;
	JTextArea descriptionField;
	JRadioButton  uncertaintyNone, uncertaintyCF, uncertaintyBayes, conflictNone, conflictSpecific;
	ButtonGroup uncertaintyGroup, conflictGroup;
	
	GroupLayout layout;
	
	public RuleWizardKnowledgePanel(KnowledgeBase kb)
	{
		super();
		
		knowledgeBase = kb;
		
		createComponents();
		setToolTips();
		layoutComponents();
		
	}
	
	private void createComponents()
	{
		nameLabel = new JLabel("Name:");
		descriptionLabel = new JLabel("Description:");
		uncertaintyLabel = new JLabel("Uncertainty management:");
		conflictLabel = new JLabel("Conflict Resolution Method:");
		
		nameField = new JTextField(30);
		nameField.getDocument().addDocumentListener(this);
		
		descriptionField = new JTextArea();
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setBorder(UIManager.getBorder("TextField.border"));
		descriptionField.getDocument().addDocumentListener(this);
		
		uncertaintyNone = new JRadioButton("None");
		uncertaintyCF = new JRadioButton("Certainty factor");
		uncertaintyBayes = new JRadioButton("Bayesian");

		conflictNone = new JRadioButton("None");
		conflictSpecific = new JRadioButton("More specific first");
		
		uncertaintyGroup = new ButtonGroup();
		uncertaintyGroup.add(uncertaintyNone);
		uncertaintyGroup.add(uncertaintyCF);
		uncertaintyGroup.add(uncertaintyBayes);
		
		conflictGroup = new ButtonGroup();
		conflictGroup.add(conflictNone);
		conflictGroup.add(conflictSpecific);
		
		uncertaintyNone.addActionListener(this);
		uncertaintyCF.addActionListener(this);
		uncertaintyBayes.addActionListener(this);
		conflictNone.addActionListener(this);
		conflictSpecific.addActionListener(this);
		
		updatePanel();
	}
	
	private void setToolTips()
	{
		// .setToolTipText("");
		nameLabel.setToolTipText("Set the name of the knowledge base");
		descriptionLabel.setToolTipText("Give a description to the knowledge base");
		uncertaintyLabel.setToolTipText("Set the uncertainty paradigm used by the inference engine and allow uncertainty entry in the rule editor");
		conflictLabel.setToolTipText("EXPERIMENTAL FEATURE - adjust the firing order of rules to effect conflict resolution");
		
		uncertaintyNone.setToolTipText("Nothing is uncertain, everything is known. Kind of.");
		uncertaintyCF.setToolTipText("Sets the inference engine to use a certainty factor model");
		uncertaintyBayes.setToolTipText("Sets the inference engine to use a Bayesian probability model - Use if you understand, otherwise avoid");
		
		conflictNone.setToolTipText("Rule ordering is left as entered");
		conflictSpecific.setToolTipText("Rules are re-ordered based on how specific it is - the more AND antecedents, the more specific");
	}
	
	private void layoutComponents()
	{
		layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.linkSize(SwingConstants.HORIZONTAL, nameLabel, descriptionLabel, uncertaintyLabel, conflictLabel);
		layout.linkSize(SwingConstants.HORIZONTAL, nameField, descriptionField);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(nameField, 100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
						.addComponent(descriptionLabel)
						.addComponent(descriptionField))
				.addGroup(layout.createSequentialGroup()
						.addComponent(uncertaintyLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(uncertaintyNone)
								.addComponent(uncertaintyCF)
								.addComponent(uncertaintyBayes)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(conflictLabel)
						.addGroup(layout.createParallelGroup()
								.addComponent(conflictNone)
								.addComponent(conflictSpecific)))
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(descriptionLabel)
						.addComponent(descriptionField, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(uncertaintyLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(uncertaintyNone)
								.addComponent(uncertaintyCF)
								.addComponent(uncertaintyBayes)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup()
						.addComponent(conflictLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(conflictNone)
								.addComponent(conflictSpecific)))		
		);
	}
	
	public void updatePanel()
	{
		nameField.setText(knowledgeBase.getName());
		
		descriptionField.setText(knowledgeBase.getDescription());
		
		if(knowledgeBase.getConflictResolutionMethod() != null)
		{
			switch(knowledgeBase.getConflictResolutionMethod())
			{
				case MORE_SPECIFIC_FIRST:
					conflictSpecific.setSelected(true);
					break;
				case NONE:
					conflictNone.setSelected(true);
					break;
			}
		}
		else
		{
			conflictGroup.clearSelection();
		}
		
		if(knowledgeBase.getUncertaintyMethod() != null)
		{
			switch(knowledgeBase.getUncertaintyMethod())
			{
				case NONE:
					uncertaintyNone.setSelected(true);
					break;
				case CERTAINTY_FACTOR:
					uncertaintyCF.setSelected(true);
					break;
				case BAYESIAN:
					uncertaintyBayes.setSelected(true);
					break;
			}
		}
		else
		{
			uncertaintyGroup.clearSelection();
		}
			
	}
	
	public void setKnowledgeBase(KnowledgeBase kb)
	{
		knowledgeBase = kb;
		updatePanel();
		repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
//		if(e.getSource() == nameField)
//		{
//			if(!nameField.getText().trim().equals(""))
//				knowledgeBase.setName(nameField.getText().trim());
//			repaint();
//		}
		
		if(e.getSource() == conflictNone)
		{
			knowledgeBase.setConflictResolutionMethod(ConflictResolutionMethod.NONE);
		}
		if(e.getSource() == conflictSpecific)
		{
			knowledgeBase.setConflictResolutionMethod(ConflictResolutionMethod.MORE_SPECIFIC_FIRST);
		}
		
		if(e.getSource() == uncertaintyNone)
		{
			knowledgeBase.setUncertaintyMethod(UncertaintyMethod.NONE);
		}
		if(e.getSource() == uncertaintyCF)
		{
			knowledgeBase.setUncertaintyMethod(UncertaintyMethod.CERTAINTY_FACTOR);
		}
		if(e.getSource() == uncertaintyBayes)
		{
			knowledgeBase.setUncertaintyMethod(UncertaintyMethod.BAYESIAN);
		}
		
		updatePanel();
	}

	public void insertUpdate(DocumentEvent e) 
	{
		Document doc = e.getDocument();
		
		if(doc == descriptionField.getDocument())
		{
			try
			{
				knowledgeBase.setDescription(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		}
		
		if(doc == nameField.getDocument())
		{
			try
			{
				knowledgeBase.setName(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		}
	}
 
	public void removeUpdate(DocumentEvent e)
	{
		Document doc = e.getDocument();
		if(doc == descriptionField.getDocument())
		{
			try
			{
				knowledgeBase.setDescription(doc.getText(0, doc.getLength()));
			} catch (BadLocationException e1)
			{
				e1.printStackTrace();
			}
		}
		
		if(doc == nameField.getDocument())
		{
			try
			{
				knowledgeBase.setName(doc.getText(0, doc.getLength()));
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
