package org.dest.file.transformations.swing;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewTransformation extends JFrame {
	
	private static final long serialVersionUID = 162865771874263873L;

	public NewTransformation()
	{
		display();
	}

	public static JFrame frameNewTransformation = new JFrame("New Transformation");
	
	public static void display()
	{

        frameNewTransformation.setBounds(150,150,600,600);
        frameNewTransformation.setLayout(null);
        frameNewTransformation.setLocationRelativeTo(null) ;
        
        JLabel labelNumberOfColumns = new JLabel("Number of input columns/fields per record");
        labelNumberOfColumns.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        labelNumberOfColumns.setBounds(10, 10, 320, 30) ;
        frameNewTransformation.add(labelNumberOfColumns) ;
        
        final JTextField textNumberOfColumns = new JTextField();
        textNumberOfColumns.setBounds(10,35,100,30);
        frameNewTransformation.add(textNumberOfColumns);

        JLabel labelDelimiter = new JLabel("Enter the input file delimiter(e.g. | or ,)");
        labelDelimiter.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        labelDelimiter.setBounds(10, 60, 320, 30) ;
        frameNewTransformation.add(labelDelimiter) ;
        
        final JTextField textDelimiter = new JTextField();
        textDelimiter.setBounds(10,85,100,30);
        frameNewTransformation.add(textDelimiter);
        
        JButton buttonColumnsAndDelimiter; 
        buttonColumnsAndDelimiter = new JButton("Submit");
        buttonColumnsAndDelimiter.setBounds(10,170,100,20);
        frameNewTransformation.add(buttonColumnsAndDelimiter);
        
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setBounds(10, 200, 500, 400);
        fileChooser.setCurrentDirectory(new File("D:/Projects- Softwares/FileTransformations/input files"));
        frameNewTransformation.add(fileChooser);
        
//        final DefaultListModel model = new DefaultListModel() ;
//        JList list = new JList(model) ;
//        list.setBounds(10, 400, 50, 150) ;
//        frameNewTransformation.add(list) ;
        
        frameNewTransformation.setVisible(true);

        buttonColumnsAndDelimiter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
           	 String numberOfColumns = textNumberOfColumns.getText() ;
           	 String delimiter = textDelimiter.getText() ;
           	 String fileName = fileChooser.getSelectedFile().toString();
				try {
//					model.addElement("number of columns '"+numberOfColumns+"'");
//					model.addElement("delimiter '"+delimiter+"'");
					new AddHeaders(numberOfColumns, delimiter, fileName);
				}
				catch (Exception e1) {
//					model.addElement(e1.getMessage());
					e1.printStackTrace();
				}   
        }
     });
	}

}
