package org.dest.file.transformations.swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

class MyHotelUI {
	
    static JFrame transientPanel = null;
    static final JFrame mainFrame = new JFrame("My Hotel Portal");

    public static void AssignToTransientPanel(JFrame jp) {
        if(transientPanel != null)
            mainFrame.remove(transientPanel);
            transientPanel = jp;
        }
    public static void main(String[] args) {
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

        JMenuItem seatingArrangement = new JMenuItem("Seating Arrangement");
        seatingArrangement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssignToTransientPanel((JFrame) new SeatingArrangementClass());
//                Container content = mainFrame.getContentPane();
//                content.removeAll();
//                content.add(transientPanel);
//                content.validate();
//                content.repaint();
            }
       });
       fileMenu.add(seatingArrangement);

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

class SeatingArrangementClass extends JFrame {
    public SeatingArrangementClass() {
        JFrame frame_seat_arrang = new JFrame("Seating Arrangement");
        frame_seat_arrang.setBounds(150,150,520,600);
        frame_seat_arrang.setLayout(null);
        frame_seat_arrang.setLocationRelativeTo(null) ;
        
        JLabel label1 = new JLabel("Enter Seat Type: ");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        label1.setBounds(10, 10, 120, 30) ;
        frame_seat_arrang.add(label1) ;
        
        final JTextField text1 = new JTextField();
        text1.setBounds(10,35,100,30);
        frame_seat_arrang.add(text1);

        JLabel label2 = new JLabel("Enter Seat Count: ");
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        label2.setBounds(10, 60, 120, 30) ;
        frame_seat_arrang.add(label2) ;
        
        final JTextField text2 = new JTextField();
        text2.setBounds(10,85,100,30);
        frame_seat_arrang.add(text2);

        JButton button1; 
        button1 = new JButton("Submit");
        button1.setBounds(10,130,100,20);
        frame_seat_arrang.add(button1);
        
        final DefaultListModel model = new DefaultListModel() ;
        JList list = new JList(model) ;
        list.setBounds(10, 175, 300, 150) ;
        frame_seat_arrang.add(list) ;
        
        JButton button2 ;
        button2 = new JButton("Show my Seating Arrangement");
        button2.setBounds(10,350,220,20);
        frame_seat_arrang.add(button2);

        frame_seat_arrang.setVisible(true);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ae) {
           	 String x = text1.getText() ;
           	 String y = text2.getText() ;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(
					                     "jdbc:oracle:thin:@localhost:1521:xe",
					                     "system",
					                     "tiger");
					Statement stmt = con.createStatement() ;
					ResultSet rs = stmt.executeQuery("INSERT INTO type_info VALUES ("+x+","+y+")") ;
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
        }
     });
        button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(
					                     "jdbc:oracle:thin:@localhost:1521:xe",
					                     "system",
					                     "tiger");
					Statement stmt = con.createStatement() ;
					ResultSet rs = stmt.executeQuery("select * from type_info") ;
					while (rs.next()) {
						long y = rs.getLong("table_type") ;
						long x = rs.getLong("count") ;
						model.addElement("table_type is "+y+" and its count is "+x) ;
					}
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
			}
		});
          }
}

class ReserveSeatsClass extends JFrame {
    JButton reserveSeatButton = new JButton("Reserve seat(s)");
    public ReserveSeatsClass() {
        add(reserveSeatButton);
//        reserveSeatButton.addActionListener(new ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent ae) {
//         }
//      });
   }
}

class CancelSeatsClass extends JFrame {
    public CancelSeatsClass() {
        JFrame frame_cancel = new JFrame("Cancel Seat(s)") ;
        frame_cancel.setBounds(150, 150, 520, 600) ;
        frame_cancel.setLayout(null) ;
        frame_cancel.setLocationRelativeTo(null);

        JLabel label1 = new JLabel("Enter Booking ID");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        label1.setBounds(10, 10, 120, 30) ;
        frame_cancel.add(label1) ;
        
        final JTextField text1 = new JTextField();
        text1.setBounds(10,35,100,30);
        frame_cancel.add(text1);
            
        JButton button1; 
        button1 = new JButton("Submit");
        button1.setBounds(10,75,100,20);
        frame_cancel.add(button1);

        JLabel label2 = new JLabel("Details: ");
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        label2.setBounds(10, 100, 120, 30) ;
        frame_cancel.add(label2) ;
        
        final DefaultListModel model = new DefaultListModel() ;
        JList list = new JList(model) ;
        list.setBounds(10, 125, 420, 150) ;
        frame_cancel.add(list) ;

        JButton button2 ;
        button2 = new JButton("Cancel Seat");
        button2.setBounds(10,350,120,20);
        frame_cancel.add(button2);
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = text1.getText() ;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(
					                     "jdbc:oracle:thin:@localhost:1521:xe",
					                     "system",
					                     "tiger");
					Statement stmt = con.createStatement() ;
					ResultSet rs = stmt.executeQuery("SELECT * FROM reservations WHERE booking_id = "+s) ;
					while (rs.next()) {
						long y = rs.getLong("mobile_num") ;
//						System.out.print("mobile number is: "+y);
						String x = rs.getString("booking_time") ;
//						System.out.print(" and its booking time is: "+x);
						long w = rs.getLong("booking_id") ;
//						System.out.println(" and booking ID is: "+w);
						model.addElement("Booking ID: "+w) ;
						model.addElement("Mobile Number: "+y) ;
						model.addElement("Booking Time: "+x) ;
						model.addElement(" ") ;
					}
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
			}
		});
        button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String s = text1.getText() ;
				System.out.println(s+" has been cancelled.");
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(
					                     "jdbc:oracle:thin:@localhost:1521:xe",
					                     "system",
					                     "tiger");
					Statement stmt = con.createStatement() ;
					ResultSet rs = stmt.executeQuery("DELETE FROM reservations WHERE booking_id = "+s) ;
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
			}
		});
        frame_cancel.setVisible(true);
   }
}
class Open extends JFrame {
	public Open() {
        JFrame frame_open = new JFrame("Open details") ;
        frame_open.setBounds(150, 150, 520, 600) ;
        frame_open.setLayout(null) ;
        frame_open.setLocationRelativeTo(null);
        
        JLabel label1 = new JLabel("Enter Mobile Number: ");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        label1.setBounds(10, 10, 120, 30) ;
        frame_open.add(label1) ;
        
        final JTextField text1 = new JTextField();
        text1.setBounds(10,35,100,30);
        frame_open.add(text1);
            
        JButton button1; 
        button1 = new JButton("Submit");
        button1.setBounds(10,75,100,20);
        frame_open.add(button1);
        
        final DefaultListModel model = new DefaultListModel() ;
        JList list = new JList(model) ;
        list.setBounds(10, 175, 450, 150) ;
        frame_open.add(list) ;
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = text1.getText() ;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(
					                     "jdbc:oracle:thin:@localhost:1521:xe",
					                     "system",
					                     "tiger");
					Statement stmt = con.createStatement() ;
					ResultSet rs = stmt.executeQuery("SELECT * FROM reservations WHERE mobile_num="+s) ;
					while (rs.next()) {
						long y = rs.getLong("mobile_num") ;
						String x = rs.getString("booking_time") ;
						long w = rs.getLong("booking_id") ;
						model.addElement("Mobile number: "+y) ;
						model.addElement("Booking date and time: "+x) ;
						model.addElement("Booking ID: "+w) ;
					}
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
			}
		});
        frame_open.setVisible(true);
   }
}