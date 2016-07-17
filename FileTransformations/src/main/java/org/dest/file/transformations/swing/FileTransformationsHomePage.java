package org.dest.file.transformations.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class FileTransformationsHomePage {
	
	static JFrame transientPanel = null;
    static final JFrame mainFrame = new JFrame("My Hotel Portal");

    public static void AssignToTransientPanel(JFrame jp) {
        if(transientPanel != null)
            mainFrame.remove(transientPanel);
            transientPanel = jp;
        }
	
	public static void mainMethod()
	{

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenu Help = new JMenu("Help") ;
		Help.setMnemonic(KeyEvent.VK_H) ;
	
		JMenuItem helpHelp = new JMenuItem("Help") ;
		JMenuItem aboutHelp = new JMenuItem("About...") ;
		Help.add(helpHelp) ;
		Help.add(aboutHelp) ;
		
        JMenuItem OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Hello World");
            }
        });
        fileMenu.add(OpenFile);

        JMenuItem csvToCsv = new JMenuItem("CSV to CSV");
        csvToCsv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssignToTransientPanel((JFrame) new NewTransformation());
//                Container content = mainFrame.getContentPane();
            }
       });
       fileMenu.add(csvToCsv);
       
       JMenuItem sample = new JMenuItem("sample");
       sample.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AssignToTransientPanel((JFrame) new NewTransformation());
//               Container content = mainFrame.getContentPane();
           }
      });
      fileMenu.add(sample);

       JMenuItem reserveSeats = new JMenuItem("Reserve Seat(s)");
       reserveSeats.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AssignToTransientPanel((JFrame) new ReserveSeatsClass());
//               Container content = mainFrame.getContentPane();
//               content.removeAll();
//               content.add(transientPanel);
//               content.validate();
//               content.repaint();
           }
      });
      fileMenu.add(reserveSeats);

      JMenuItem cancelSeats = new JMenuItem("Cancel Seat(s)");
      cancelSeats.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              AssignToTransientPanel((JFrame) new CancelSeatsClass());
//              Container content = mainFrame.getContentPane();
//              content.removeAll();
//              content.add(transientPanel);
//              content.validate();
//              content.repaint();
              mainFrame.getContentPane() ;
          }
     });
     fileMenu.add(cancelSeats);
       
     JMenuItem openFile = new JMenuItem("Open...");
     openFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 AssignToTransientPanel((JFrame) new Open());
//           Container content = mainFrame.getContentPane();
//           content.removeAll();
//           content.add(transientPanel);
//           content.validate();
//           content.repaint();
           mainFrame.getContentPane() ;
         }
     });
     fileMenu.add(openFile);
     
     JMenuItem exitFile = new JMenuItem("Exit");
       exitFile.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });
       fileMenu.add(exitFile);
       
       JMenuBar mainMenu = new JMenuBar();
       mainMenu.add(fileMenu);
       mainMenu.add(Help) ;
       
       mainFrame.setJMenuBar(mainMenu);
       mainFrame.setTitle("My Hotel Portal") ;
       mainFrame.setSize(650,650) ;
       mainFrame.setLocationRelativeTo(null) ;
       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
       mainFrame.validate();
       mainFrame.setVisible(true);
   
	}
	

}
