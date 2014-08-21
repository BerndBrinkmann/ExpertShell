package STUART.GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import STUART.ADT.Antecedent;
import STUART.ADT.Consequent;
import STUART.ADT.Rule;

public class MultiLineCellRenderer extends JTextArea implements ListCellRenderer 
{
    public MultiLineCellRenderer() 
    {
        super();
    	setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) 
    {

        Border border = null;
    	
    	
    	setEditable(false);
    	setText(value.toString());

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index)
        {
            background = UIManager.getColor("List.dropCellBackground");
            foreground = UIManager.getColor("List.dropCellForeground");
            
            isSelected = true;
            
        // unselected, and not the DnD drop location, check to see if the Rule is invalid
        } else 
        {
            boolean invalid = false;
        	
            if(((Rule)value).getNumberOfConsequents() < 1 || ((Rule)value).getNumberOfAntecedents() < 1)
            	invalid = true;
            
            for(Antecedent a : ((Rule) value).getAntecedentArray())
            {
            	if(!a.getIsNumeric() && (a.getValue() != null && a.getValue().getName().equals("") || a.getValue().getName().equals("default")))
            		invalid = true;
            }
            for(Consequent c : ((Rule) value).getConsequentArray())
            {
            	if(!c.getIsNumeric() && (c.getValue() != null && c.getValue().getName().equals("") || c.getValue().getName().equals("default")))
            		invalid = true;
            }
            
        	
        	if(invalid)
        		background = new Color(255, 150, 150);
        	else
            	background = list.getBackground();
        	
        	if (isSelected) 
            {
                background = list.getSelectionBackground();
                foreground = list.getSelectionForeground();
            }
        	
        	if(isSelected && invalid)
        		background = new Color(210, 180, 200);
            
            foreground = list.getForeground();
        }

        setBackground(background);
        setForeground(foreground);
        
        if (cellHasFocus) 
        {
        	if (isSelected) 
        	{
        		border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
        	}
        	if (border == null) 
        	{
        		border = UIManager.getBorder("List.focusCellHighlightBorder");
        	}
        } else
        {
        	//border = UIManager.getBorder("List.noFocusBorder");
        	//border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        	border = BorderFactory.createMatteBorder(0, 0, 1, 0, list.getSelectionBackground());
        }
        setBorder(border);

        return this;
    }
}