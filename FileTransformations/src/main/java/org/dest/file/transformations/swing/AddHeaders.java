package org.dest.file.transformations.swing;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddHeaders extends JFrame{
	
	public AddHeaders(String numberOfColumns, String delimiter, String fileName)
	{
		display(numberOfColumns, delimiter, fileName);
	}
	
	public static JFrame frameAddHeaders = new JFrame("Add Headers");
	
	public static void display(final String numberOfColumns, final String delimiter, final String fileName)
	{

		NewTransformation.frameNewTransformation.setVisible(false);
		
//		for(int i=0; i<(Integer.parseInt(numberOfColumns)); i++)
//		{
//			
//		}
		
        frameAddHeaders.setBounds(150,150,500,500);
        frameAddHeaders.setLayout(null);
        frameAddHeaders.setLocationRelativeTo(null) ;
        
        JLabel labelHeaderYesNo = new JLabel("Do you want to add headers to the output file");
        labelHeaderYesNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        labelHeaderYesNo.setBounds(10, 10, 320, 30) ;
        frameAddHeaders.add(labelHeaderYesNo) ;
        
        final ButtonGroup buttonGroup = new ButtonGroup();
        final JRadioButton radioHeaderYes = new JRadioButton("Yes");
        radioHeaderYes.setBounds(10, 50, 100, 30);
        buttonGroup.add(radioHeaderYes);
        frameAddHeaders.add(radioHeaderYes);
        final JRadioButton radioHeaderNo = new JRadioButton("No");
        radioHeaderNo.setBounds(150, 50, 100, 30);
        buttonGroup.add(radioHeaderNo);
        frameAddHeaders.add(radioHeaderNo);
        
        JLabel labelHeader = new JLabel("Enter the desired header format");
        labelHeader.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        labelHeader.setBounds(10, 90, 320, 30) ;
        frameAddHeaders.add(labelHeader) ;
        
        final JTextField textHeader = new JTextField();
        textHeader.setBounds(10,130,300,30);
        
        JButton buttonHeaderYesNo; 
        buttonHeaderYesNo = new JButton("Submit");
        buttonHeaderYesNo.setBounds(10,170,100,20);
        frameAddHeaders.add(buttonHeaderYesNo);
        
        final DefaultListModel model = new DefaultListModel() ;
        JList list = new JList(model) ;
        list.setBounds(10, 200, 400, 150) ;
        list.setDragEnabled(true);
        frameAddHeaders.add(list) ;
        
        frameAddHeaders.setVisible(true);
        
        final JFrame framePopUp = new JFrame();
        framePopUp.pack();

        buttonHeaderYesNo.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
            	String b = "no value";
				try {
					for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) {
			                b = button.getText();
			            }
			        }
					if(b.equals("Yes"))
					{
						String header="no header";
						header = JOptionPane.showInputDialog(framePopUp, "What is your name", null);
						model.addElement(header);
						new AddDetails(numberOfColumns, delimiter, fileName, header);
					}
					else
					{
						new AddDetails(numberOfColumns, delimiter, fileName, "no header");
					}
//					FTWriterService ftWriter = new FTWriterServiceImpl();
//					ftWriter.writeToFile(numberOfColumns, delimiter, header, fileName, b);
//					for(int i=0; i<Integer.valueOf(numberOfColumns); i++)
//					{
//						String name;
//						name = JOptionPane.showInputDialog(framePopUp, "What is your name", null);
//						System.out.println("name is "+name);
//					}
				}
				catch (Exception e1) {
					model.addElement(e1.getMessage());
					e1.printStackTrace();
				}   
        }
     });
        
	}

}
