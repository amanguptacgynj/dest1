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
import javax.swing.JRadioButton;

import org.dest.file.transformations.service.FTWriterService;
import org.dest.file.transformations.service.impl.FTWriterServiceImpl;

public class AddDetails extends JFrame {
	
	private static final long serialVersionUID = 524113322105380231L;

	public AddDetails(String numberOfColumns, String delimiter, String fileName, String header)
	{
		display(numberOfColumns, delimiter, fileName, header);
	}
	
	public static JFrame frameAddDetails = new JFrame("Add Details");
	
	public static void display(final String numberOfColumns, final String delimiter, final String fileName, final String header)
	{
		AddHeaders.frameAddHeaders.setVisible(false);
		
        frameAddDetails.setBounds(150,150,500,500);
        frameAddDetails.setLayout(null);
        frameAddDetails.setLocationRelativeTo(null) ;
        
        JLabel labelTrim = new JLabel("Do you want to trim all the values");
        labelTrim.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        labelTrim.setBounds(10, 10, 320, 30) ;
        frameAddDetails.add(labelTrim) ;
        
        final ButtonGroup buttonGroup = new ButtonGroup();
        final JRadioButton radioTrimYes = new JRadioButton("Yes");
        radioTrimYes.setBounds(10, 50, 100, 30);
        buttonGroup.add(radioTrimYes);
        frameAddDetails.add(radioTrimYes);
        final JRadioButton radioTrimNo = new JRadioButton("No");
        radioTrimNo.setBounds(150, 50, 100, 30);
        buttonGroup.add(radioTrimNo);
        frameAddDetails.add(radioTrimNo);
        
//        final JTextField textHeader = new JTextField();
//        textHeader.setBounds(10,35,300,30);
//        frameAddDetails.add(textHeader);
//
//        JLabel labelDelimiter = new JLabel("Enter the delimiter(e.g. | or ,");
//        labelDelimiter.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//        labelDelimiter.setBounds(10, 60, 120, 30) ;
//        frameAddDetails.add(labelDelimiter) ;
//        
//        final JTextField textDelimiter = new JTextField();
//        textDelimiter.setBounds(10,85,100,30);
//        frameAddDetails.add(textDelimiter);

        JButton buttonColumnsAndDelimiter; 
        buttonColumnsAndDelimiter = new JButton("Submit");
        buttonColumnsAndDelimiter.setBounds(10,130,100,20);
        frameAddDetails.add(buttonColumnsAndDelimiter);
        
        final DefaultListModel model = new DefaultListModel() ;
        JList list = new JList(model) ;
        list.setBounds(10, 175, 400, 150) ;
        list.setDragEnabled(true);
        frameAddDetails.add(list) ;
        
        frameAddDetails.setVisible(true);
        
        final JFrame framePopUp = new JFrame();
        framePopUp.pack();

        buttonColumnsAndDelimiter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
            	model.addElement(numberOfColumns);
            	model.addElement(delimiter);
            	model.addElement(fileName);
            	model.addElement(header);
            	String b = "no value";
				try {
					for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) {
			                b = button.getText();
			            }
			        }
					FTWriterService ftWriter = new FTWriterServiceImpl();
					ftWriter.writeToFile(numberOfColumns, delimiter, fileName, header, b);
//					for(int i=0; i<Integer.valueOf(numberOfColumns); i++)
//					{
//						String name;
//						name = JOptionPane.showInputDialog(framePopUp, "What is your name", null);
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
