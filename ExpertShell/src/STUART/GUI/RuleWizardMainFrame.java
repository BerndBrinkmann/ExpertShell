package STUART.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import org.apache.commons.lang3.SystemUtils;

import STUART.KnowledgeBaseFileManager;
import STUART.Main;
import STUART.StuartIO;
import STUART.StuartInferenceEngine;
import STUART.ADT.KnowledgeBase;
import STUART.ADT.UncertaintyMethod;
import STUART.ADT.Variable;


public class RuleWizardMainFrame extends JFrame implements ActionListener, ChangeListener
{
	JMenuBar menuBar;
	JMenu file, run, examples, help;
	JMenuItem menuNew, menuOpen, menuSave, menuSummary, menuExit;
	JMenuItem runDefault, runFowardChaining, runBackChaining;
	JMenuItem showManual,showGuide, about;
	
	ArrayList<JMenuItem> exampleMenus;
	private final String[][] EXAMPLES= {{"Boat","Boats.stu"}, {"Boat with CF","Boat CF.stu"}, {"Forecast","Forecast Both.stu"}, {"Media Advisor","Media Advisor.stu"}};
	
	KnowledgeBase knowledgeBase;
	ConsolePanel console;
	JSplitPane splitPane;
	
	RuleWizardRulePanel rulePanel;
	RuleWizardVariablePanel varPanel;
	RuleWizardKnowledgePanel knowPanel;
	
	//creates the main frame with a default knowledge base
	public RuleWizardMainFrame()
	{
		super("S.T.U.A.R.T. Rule Wizard");
		StuartIO.setMainFrame(this);
		
		//create the default knowledge base
//		knowledgeBase = Main.createBoatKnowlegeBase();
		
		knowledgeBase = new KnowledgeBase("New");
		
		this.setTitle("S.T.U.A.R.T. Rule Wizard - "+knowledgeBase.getName());

		this.setSize(900,700);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e)
		    {
		    	int option = JOptionPane.showConfirmDialog(null, "Do you want to save the current knowledge base?", "Exit Rule Editor", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(option == 0)
				{
					knowledgeBase.validate();
					KnowledgeBaseFileManager.saveKnowledgeFile(knowledgeBase,RuleWizardMainFrame.this);
					RuleWizardMainFrame.this.dispose();
				}
				else if(option == 1)
				{
					RuleWizardMainFrame.this.dispose();
				}
				else if(option == 2){}
		    }
		});
		
		createMenuBar();

		knowPanel = new RuleWizardKnowledgePanel(knowledgeBase);
		rulePanel = new RuleWizardRulePanel(knowledgeBase);
		varPanel = new RuleWizardVariablePanel(knowledgeBase);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Knowledge Base", null, knowPanel, "Set global knowledge base settings");
		tabbedPane.addTab("Rules",null, rulePanel, "View and edit rules");
		tabbedPane.addTab("Variables", null, varPanel, "Adjust variable settings");
		tabbedPane.addChangeListener(this);
		tabbedPane.setSelectedComponent(rulePanel);
		//this.add(tabbedPane);
		
		
		ConsolePanel console = new ConsolePanel();
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, console);
		this.add(splitPane);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(400);
		this.setVisible(true);
			
	}
	
	public void createMenuBar()
	{
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		menuNew = new JMenuItem("New Knowlege Base");
		menuOpen = new JMenuItem("Open Knowlege Base");
		menuSave = new JMenuItem("Save Knowlege Base");
		menuSummary = new JMenuItem("View Summary");
		menuExit = new JMenuItem("Exit");
		
		file.add(menuNew);
		file.add(menuOpen);
		file.add(menuSave);
		file.addSeparator();
		file.add(menuSummary);
		file.addSeparator();
		file.add(menuExit);
		
		menuNew.addActionListener(this);
		menuOpen.addActionListener(this);
		menuSave.addActionListener(this);
		menuSummary.addActionListener(this);
		menuExit.addActionListener(this);
		
		
		run = new JMenu("Run");
		runDefault = new JMenuItem("Run Default");
		runFowardChaining = new JMenuItem("Run Forward Chaining");
		runBackChaining = new JMenuItem("Run Backward Chaining");
		
		run.add(runDefault);
		run.addSeparator();
		run.add(runFowardChaining);
		run.add(runBackChaining);
		
		runDefault.addActionListener(this);
		runFowardChaining.addActionListener(this);
		runBackChaining.addActionListener(this);
		
		
		examples = new JMenu("Examples");
		exampleMenus = new ArrayList<JMenuItem>();
		for(String[] ex : EXAMPLES)
		{
			JMenuItem m = new JMenuItem(ex[0]);
			exampleMenus.add(m);
			examples.add(m);
			m.addActionListener(this);
		}
		
		help = new JMenu("Help");
		showManual = new JMenuItem("Show Manual");
		showGuide = new JMenuItem("Show Quick Start Guide");
		about = new JMenuItem("About");
		
		help.add(showManual);
		help.add(showGuide);
		help.add(about);
		
		showManual.addActionListener(this);
		showGuide.addActionListener(this);
		about.addActionListener(this);
		
		
		if(SystemUtils.IS_OS_MAC)
		{
			menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
			menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
			menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
			runDefault.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.META_MASK));
			runFowardChaining.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.META_MASK));
			runBackChaining.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.META_MASK));
			showManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			showGuide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.META_MASK));
		}
		else
		{
			menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
			menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			runDefault.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
			runFowardChaining.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
			runBackChaining.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
			showManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			showGuide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		}
		
		
		
		
		menuBar.add(file);	
		menuBar.add(run);
		menuBar.add(examples);
		menuBar.add(help);
		
		this.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == menuNew)
		{
			//click handler for menu>new
			//System.out.println("click event registered for menu>new");
			
			int option = JOptionPane.showConfirmDialog(this, "Do you want to save the current knowledge base?", "New Knowledge Base", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == 0)
			{
				knowledgeBase.validate();
				KnowledgeBaseFileManager.saveKnowledgeFile(knowledgeBase,this);
				knowledgeBase = new KnowledgeBase("New");
			}
			else if(option == 1)
				knowledgeBase = new KnowledgeBase("New");
			else if(option == 2){}
			
			rulePanel.setKnowledgeBase(knowledgeBase);
        	varPanel.setKnowledgeBase(knowledgeBase);
        	knowPanel.setKnowledgeBase(knowledgeBase);
        	
//        	tabbedPane.setSelectedComponent(rulePanel);
		}
		
		if(e.getSource() == menuOpen)
		{
			//click handler for menu>open
			//System.out.println("click event registered for menu>open");
            
			KnowledgeBase know = KnowledgeBaseFileManager.loadKnowledgeFile(this);
            
            if(know != null)
            {
            	knowledgeBase = know;
            	rulePanel.setKnowledgeBase(knowledgeBase);
            	varPanel.setKnowledgeBase(knowledgeBase);
            	knowPanel.setKnowledgeBase(knowledgeBase);
            }

		}
		
		if(e.getSource() == menuSave)
		{
			//click handler for menu>save
			//System.out.println("click event registered for menu>save");
			
			knowledgeBase.validate();

			KnowledgeBaseFileManager.saveKnowledgeFile(knowledgeBase,this);
		}
		
		if(e.getSource() == menuSummary)
		{
			//click handler for menu>summary
			//System.out.println("click event registered for menu>summary");
			System.out.println(knowledgeBase);
		}
		
		if(e.getSource() == menuExit)
		{
			//click handler for menu>exit
			//System.out.println("click event registered for menu>quit");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		if(e.getSource() == runFowardChaining || e.getSource() == runDefault)
		{
			System.out.println("Running Knowledge Base: "+knowledgeBase.getName());
			
			knowledgeBase.validate();
			
			StuartInferenceEngine stu = new StuartInferenceEngine(knowledgeBase);		
			Variable result = stu.solveForwardChaining();
			
			StuartIO.displayResults(result, stu.getHowList(), knowledgeBase);
		}
		
		if(e.getSource() == runBackChaining)
		{
			//click handler for run>runBackChaining
			//System.out.println("click event registered for run>runBackChaining");
			knowledgeBase.validate();
			
			StuartInferenceEngine stu = new StuartInferenceEngine(knowledgeBase);		
			Variable result = stu.solveBackwardChaining();
			
			StuartIO.displayResults(result, stu.getHowList(), knowledgeBase);	
		}
		
		for(JMenuItem ex : exampleMenus)
		{
			if(e.getSource() == ex)
			{	
				KnowledgeBase know = KnowledgeBaseFileManager.loadKnowledgeExample(EXAMPLES[exampleMenus.indexOf(ex)][1]);
								
				if(know != null)
	            {
	            	knowledgeBase = know;
	            	rulePanel.setKnowledgeBase(knowledgeBase);
	            	varPanel.setKnowledgeBase(knowledgeBase);
	            	knowPanel.setKnowledgeBase(knowledgeBase);
	            }
			}
		}
		
		if(e.getSource() == showManual)
		{
			KnowledgeBaseFileManager.openManual();
		}
		
		if(e.getSource() == showGuide)
		{
			KnowledgeBaseFileManager.openGuide();
		}
		
		if(e.getSource() == about)
		{
			String s = "The S.T.U.A.R.T. expert system shell was developed at the University of Tasmania\n"
					+ "by Willem Olding, Ryan McKercher, Leo Kerslake, Stuart Ednie and Braedon Carter.\n"
					+ "";
			System.out.println(s);
		}
		
		this.setTitle("S.T.U.A.R.T. Rule Wizard - "+knowledgeBase.getName());

	}

	public void stateChanged(ChangeEvent e)
	{
		varPanel.updatePanel();
		knowPanel.updatePanel();
		rulePanel.updatePanel();
		this.setTitle("S.T.U.A.R.T. Rule Wizard - "+knowledgeBase.getName());

	}

}
