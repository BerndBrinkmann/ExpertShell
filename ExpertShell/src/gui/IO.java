package gui;

import java.awt.Component;


import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;


import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.JTextField;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datatypes.*;
public class IO
{
	static Component frame;
	static Rule thisRule; // a hack to get this into the description function
	static Composite compQ;
	static ScrolledComposite sc1;
	static ScrolledComposite sc;
	
	public static void setMainFrame(Component cpm)
	{
		frame = null;
	}
	
	public static String getString()
	{
		return getString("");
	}
	
	public static String getString(String message)
	{
		return getString(message,null);
	}
	
	public static String getString(String message, String[] possibleValues)
	{
		String s;

			s = (String)JOptionPane.showInputDialog(
	                frame,
	                message,
	                "requires input",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                possibleValues,
	                "");

		
		return s;
	}
	
	/**
	 * Returns a value object from a set of possible values
	 * @param message
	 * @param possibleValues
	 * @return
	 */
	public static Value getValue(String message, Value[] possibleValues)
	{
		Value val;

			val = (Value)JOptionPane.showInputDialog(
	                frame,
	                message,
	                " requires input",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                possibleValues,
	                "");

		return val;
	}
	
	
	/**
	 * grabs a value for a variable from the user. Works on numeric or linguistic variables.
	 * @param var
	 * @return
	 */
	
	
		
	/**
	 * returns a variable object from a set of possible variables
	 * @param message
	 * @param variables
	 * @return
	 */
	public static Variable getVariable(String message, Variable[] variables)
	{
		Variable var;

			var = (Variable)JOptionPane.showInputDialog(
	                frame,
	                message,
	                "requires input",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                variables,
	                "");

			
		
		return var;
	}
	
	
	/**
	 * displays a dialog that reports on a run
	 */
	public static void displayResults(Variable result, final ArrayList<Rule> howList, KnowledgeBase kb)
	{
		String summary;
		if(result != null)
		{
			if(kb.getUncertaintyMethod() == KBSettings.UncertaintyManagement.CF)
			{
				summary = result.getCertaintyValuesString();
			}
			else if(kb.getUncertaintyMethod() == KBSettings.UncertaintyManagement.BAYESIAN)
			{
				summary = result.getBeliefValuesString();

			}
			else
			{
				if(!(result.isNumeric))
				{
					if(result.getCurrentValue() != null)
					{
						summary =  result + " is " + result.getCurrentValue();
					}
					else
					{
						summary = "A conclusion could not be made from the information provided";
					}
				}
				else
				{
					if(result.getNumVal() != null)
					{
						summary = result + " is " + result.getNumVal();
					}
					else
					{
						summary = "A conclusion could not be made from the information provided";
					}
				}
			}
		}
		else
		{
			summary = "A conclusion could not be made from the information provided";
		}
		
		System.out.println("\n"+summary);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("Inference Complete"));
		
		JButton how = new JButton("How?");
		how.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  displayHowMessage(howList);
			  } 
			} );
		
		panel.add(how);

		JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.CLOSED_OPTION);
	}
	
	public static void displayHowMessage(ArrayList<Rule> howList)
	{
		if(howList.isEmpty())
		{
			System.out.println("\nA result was not reached\n");
		}
		else
		{
		
			System.out.println("\nThe result was reached by firing these rules in this order\n");
			for(Rule r : howList)
			{
				System.out.println(r.toString());
			}
		}
	}
	
	/**
	 * returns a value for a certainty factor
	 * @param message
	 * @return
	 */
	public static Double getCertainty(String message)
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel(message));
		JTextField cfField = new JTextField("0.5", 10);
		
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable.put( new Integer( 50 ), new JLabel("0.5") );
		labelTable.put( new Integer( 100 ), new JLabel("1.0") );
		
		JSlider slider = new JSlider(0,100,50);
		slider.setLabelTable( labelTable );
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		
		class SliderListener implements ChangeListener
		{
			JSlider s; JTextField f;
			public SliderListener(JSlider s, JTextField f)
			{
				this.s = s; this.f = f;
			}
			public void stateChanged(ChangeEvent e)
			{
				f.setText("" + ((double)s.getValue())/100);
			}
		}
		class FieldListener implements ActionListener
		{
			JSlider s; JTextField f;
			public FieldListener(JSlider s, JTextField f)
			{
				this.s = s; this.f = f;
			}
			public void actionPerformed(ActionEvent e)
			{
				s.setValue((int) (Double.parseDouble(f.getText()) * 100));
			}
		}
		
		slider.addChangeListener(new SliderListener(slider, cfField));
		cfField.addActionListener(new FieldListener(slider, cfField));

		panel.add(slider);
		panel.add(cfField);

		JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
		return  (Double)(((double)slider.getValue())/100);
	}
	
/*	public static Variable AskUserForInput(Variable var, Rule rule, KnowledgeBase kb, Composite CompQ, ScrolledComposite scrolledComposite_1, ScrolledComposite scrolledComposite, InferenceEngine Inference)
	{
	compQ = CompQ;
	sc1= scrolledComposite_1;
	sc = scrolledComposite;
	Group questionGroup;
	questionGroup = UserFactoryGUI.createQuestionGroup(CompQ);
	questionGroup.getParent().getParent().layout(true);
	
	
	/*	
	QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference);
	askCFQuestion.addQuestion("Input a value for "+var.getName());
	scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	CompQ.layout();
		*/	
	//	thisRule = rule;
//TODO
		/*	
		boolean invalid = true;
		while(invalid)
		{
			try
			{	
				var.userSetCurrentValue(Double.parseDouble(field.getText()));
				invalid = false;
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Please enter a valid number.");
				invalid = true;
				JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
			}	
		} */
		
			
	
			//btnCertainityFactor.getSelection();
		   // btnRun.getSelection();
/*		if(var.getQueryPrompt().trim().equals(""))
		{

			if (kb.getUncertaintyMethod()==KBSettings.UncertaintyManagement.CF){
				QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference,questionGroup,"Input a value for "+var.getName());
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
				
			}
			else
			{
				QuestionGUI askQuestion = new QuestionGUI(CompQ, Inference,questionGroup,"Input a value for "+var.getName());
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
			}
		}
		else
		{
			if (kb.getUncertaintyMethod()==KBSettings.UncertaintyManagement.CF)
			{
				QuestionCFGUI askCFQuestion = new QuestionCFGUI(CompQ, Inference,questionGroup,var.getQueryPrompt());
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
			}
			else
			{
				QuestionGUI askQuestion = new QuestionGUI(CompQ, Inference,questionGroup,var.getQueryPrompt());
				scrolledComposite.setMinSize(CompQ.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				CompQ.layout();
				
			}
			
			CompQ.update();
			
		}
	return var;
	}
*/

}
