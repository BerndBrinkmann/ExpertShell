package datatypes;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import removedClasses.UncertaintyMethod;

public class ImportExport
{
	static Component frame;
	static Rule thisRule; // a hack to get this into the description function
	
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
	                "requires input",
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
	public static Variable userSetVaraible(Variable var, Rule rule)
	{
		thisRule = rule;
			if(var instanceof NumericVariable)
			{
				JPanel panel = new JPanel();
				
				if(var.getQueryPrompt().trim().equals(""))
				{
					panel.add(new JLabel("Input a value for "+var.getName()));
				}
				else
				{
					panel.add(new JLabel(var.getQueryPrompt()));
				}
				
				JTextField field = new JTextField("0.0", 10);
				
				JButton why = new JButton("Why?");
				why.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  displayWhyMessage();
					  } 
					} );
				
				panel.add(field);
				panel.add(why);

				JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
				
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
				}
			}
			else
			{
				
				
				JPanel panel = new JPanel();
				
				if(var.getQueryPrompt().trim().equals(""))
				{
					panel.add(new JLabel("Input a value for "+var.getName()));
				}
				else
				{
					panel.add(new JLabel(var.getQueryPrompt()));
				}
				
				JComboBox combox = new JComboBox(var.getArrayOfPossibleValues());
				
				JButton why = new JButton("Why?");
				why.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						  displayWhyMessage();
					  } 
					} );
				
				panel.add(combox);
				panel.add(why);

				JOptionPane.showMessageDialog(frame, panel,"",JOptionPane.PLAIN_MESSAGE);
				Value val =  (Value) combox.getSelectedItem();
				var.userSetCurrentValue(val);
			}
		
		
		return var;
	}
	
	public static void displayWhyMessage()
	{
		StringBuilder s = new StringBuilder();
		s.append("\nI am trying to evaluate the rule\n");
		s.append(thisRule != null ? thisRule.toString() : "null");
		System.out.println(s.toString());
	}
	
	
	/**
	 * returns a variable object from a set of possible variables
	 * @param message
	 * @param possibleVariables
	 * @return
	 */
	public static Variable getVariable(String message, Variable[] possibleVariables)
	{
		Variable var;

			var = (Variable)JOptionPane.showInputDialog(
	                frame,
	                message,
	                "requires input",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                possibleVariables,
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
				if(!(result instanceof NumericVariable))
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
			for(Rule rule : howList)
			{
				System.out.println(rule.toString());
			}
		}
	}



}
