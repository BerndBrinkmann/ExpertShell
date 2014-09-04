package datatypes;

import gui.MainScreen;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

import STUART.KnowledgeBaseFileManager;
import resources.*;
import datatypes.*;

public class FileManager
{

	static JFileChooser fileChooser = new JFileChooser();
	
	public static void saveKnowledgeFile(KnowledgeBase kb)
	{
		fileChooser.setSelectedFile(new File(kb.getName()+".kb"));
		int returnVal = fileChooser.showSaveDialog(null);
		
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            System.out.println("Saving: '" + file.getName()+"'...");
    		saveKnowledgeFile(kb,file);
            System.out.println("Done.");
        } 
        else 
        {
            System.out.println("Save command cancelled by user.");
        }
        
	}
	
	public static KnowledgeBase loadKnowledgeFile()
	{
		int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            System.out.println("Opening: '" + file.getName() + "'...");
            System.out.println("Done.");
            return loadKnowledgeFile(file);
        } 
        else 
        {
            System.out.println("Open command cancelled by user.");
            return null;
        }
	}
	
	private static void saveKnowledgeFile(KnowledgeBase kb, File f)
	{
		try
		{
			if(!f.getAbsolutePath().endsWith(".kb"))
			{
			    f = new File(f + ".kb");
			}
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			kb.setRunGui(null);
			oos.writeObject(kb);
			oos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static KnowledgeBase loadKnowledgeFile(File f)
	{
	      FileInputStream fis;
		try
		{
			fis = new FileInputStream(f);
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    KnowledgeBase kb = (KnowledgeBase) ois.readObject();
		    ois.close();
		    return kb;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static KnowledgeBase loadKnowledgeExample(String s)
	{
		try
		{
		    ObjectInputStream ois = new ObjectInputStream(FileManager.class.getResourceAsStream("/Examples/" + s));
		    KnowledgeBase kb = (KnowledgeBase) ois.readObject();
		    ois.close();
		    return kb;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public static void openManual()
	{
		if (Desktop.isDesktopSupported())
		{
			InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/Manual.pdf");
						
		    
			try
		    {
//				File manualFile = File.createTempFile("Manual", ".pdf", new File(System.getProperty("java.io.tmpdir")));
				File manualFile = new File(System.getProperty("java.io.tmpdir") + "Manual.pdf");
		        manualFile.deleteOnExit();
		        OutputStream out = new FileOutputStream(manualFile);
		        try
		        {
		        	int read = 0;
		        	byte[] bytes = new byte[1024];
		        	
		        	while((read = resource.read(bytes)) != -1)
		        	{
		        		out.write(bytes, 0, read);
		        	}
		        }
		        finally
		        {
		        	out.close();
		        }
		        
		        Desktop.getDesktop().open(manualFile);
		    }
		    catch (IOException ex)
		    {
		        // no application registered for PDFs
		    }
			finally
			{
				try
				{
					resource.close();
				} 
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	public static void openQuickStart()
	{
		//open the PDF of the manual
		if (Desktop.isDesktopSupported())
		{
			InputStream resource = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/QuickStart.pdf");
						
		    
			try
		    {
//				File manualFile = File.createTempFile("Manual", ".pdf", new File(System.getProperty("java.io.tmpdir")));
				File manualFile = new File(System.getProperty("java.io.tmpdir") + "QuickStart.pdf");
		        manualFile.deleteOnExit();
		        OutputStream out = new FileOutputStream(manualFile);
		        try
		        {
		        	int read = 0;
		        	byte[] bytes = new byte[1024];
		        	
		        	while((read = resource.read(bytes)) != -1)
		        	{
		        		out.write(bytes, 0, read);
		        	}
		        }
		        finally
		        {
		        	out.close();
		        }
		        
		        Desktop.getDesktop().open(manualFile);
		    }
		    catch (IOException ex)
		    {
		        // no application registered for PDFs
		    }
			finally
			{
				try
				{
					resource.close();
				} 
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		
	}
}
