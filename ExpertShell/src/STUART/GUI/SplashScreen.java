package STUART.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import STUART.KnowledgeBaseFileManager;
import STUART.RuleWizard;
import STUART.ADT.KnowledgeBase;

public class SplashScreen extends JFrame
{
	//TODO fix this so it looks proper
	
	public SplashScreen()
	{
		super("S.T.U.A.R.T Expert System Shell");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 430);
		setLocation(400, 250);
		setResizable(false);
		setContentPane(new SplashPanel());
		this.setBackground(Color.WHITE);

		setVisible(true);
	}
	
	private class SplashPanel extends JPanel implements ActionListener, MouseListener
	{
		JButton runStuart,showGuide, showManual, quit;
		JLabel bgLabel;
		JTextArea descriptionField, authorField;
	    private BufferedImage bgImage;

		
		public SplashPanel()
		{
			runStuart = new JButton("Run Rule Wizard");
			runStuart.addActionListener(this);
			runStuart.addMouseListener(this);
			
			showManual = new JButton("Manual");
			showManual.addActionListener(this);
			showManual.addMouseListener(this);
			
			showGuide = new JButton("Quick Start Guide");
			showGuide.addActionListener(this);
			showGuide.addMouseListener(this);
			
			quit = new JButton("Quit");
			quit.addActionListener(this);
			
			descriptionField = new JTextArea();
			descriptionField.setLineWrap(true);
			descriptionField.setWrapStyleWord(true);
			descriptionField.setEditable(false);
			descriptionField.setOpaque(false);
			
			descriptionField.setForeground(Color.LIGHT_GRAY);
//			descriptionField.setText("the description of various components \n and what they do should \ngo in this field her which\nshould hopefully have enough in it by now");
			
			authorField = new JTextArea();
			authorField.setEditable(false);
			authorField.setOpaque(false);
			
			authorField.setForeground(Color.LIGHT_GRAY);
			authorField.setText("Created by:\n      Willem Olding\n      Ryan McKercher\n      Leo Kerslake\n      Stuart Ednie\n      Braedon Carter");
			
			
			
			
			try
			{
			    bgImage = ImageIO.read(getClass().getResourceAsStream("/walle.png"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			bgLabel = new JLabel(new ImageIcon(bgImage));
			
			this.setLayout(new BorderLayout());
			this.add(bgLabel,BorderLayout.CENTER);
			
			GroupLayout layout = new GroupLayout(bgLabel);
			bgLabel.setLayout(layout);
			
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			
			layout.linkSize(SwingConstants.HORIZONTAL, runStuart,showGuide, showManual);
			
			layout.setHorizontalGroup(
					layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(runStuart)
								.addComponent(showGuide)
								.addComponent(showManual)
								.addComponent(descriptionField))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 400, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(authorField)
								.addComponent(quit))
			);
			
			layout.setVerticalGroup(
					layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(runStuart)
								.addComponent(showGuide)
								.addComponent(showManual)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 250, Short.MAX_VALUE)
								.addComponent(descriptionField))
						.addGroup(layout.createSequentialGroup()
								.addComponent(authorField)
								.addGap(100)
								.addComponent(quit))
			);

			this.setVisible(true);	
			
			
			System.out.println(1.0 == Double.parseDouble("1") );
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == quit)
			{
				System.exit(0);
			}
			
			if(e.getSource() == runStuart)
			{
				new RuleWizard();
			}
			
			if(e.getSource() == showGuide)
			{
				KnowledgeBaseFileManager.openGuide();
			}
			
			if(e.getSource() == showManual)
			{
				KnowledgeBaseFileManager.openManual();
			}
		}

		
		

		@Override
		public void mouseEntered(MouseEvent e)
		{
			if(e.getSource() == runStuart)
			{
				descriptionField.setText("This will open the S.T.U.A.R.T. rule editor which will allow"
						+ " you to create and run a rule based expert system "
						+ "like that described in Michael Negnevitsky's Artificial Intelligence: A Guide to Intelligent Systems.");
			}
			if(e.getSource() == showGuide)
			{
				descriptionField.setText("This will open the S.T.U.A.R.T. Expert System Shell Quick"
						+ " Start Guide which gives a brief overview of how to create and run a knowledge base.");
			}
			if(e.getSource() == showManual)
			{
				descriptionField.setText("This will open the S.T.U.A.R.T. Expert System Shell user Manual"
						+ " which will guide you through creating and running a knowledge base.");
			}
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			descriptionField.setText("");
		}
		public void mouseClicked(MouseEvent arg0) {}

		public void mousePressed(MouseEvent arg0) {}

		public void mouseReleased(MouseEvent arg0) {}
		
		
		
	}
}
