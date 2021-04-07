import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

public class Welcome extends JFrame {
	
        
    private static String dbURL = "jdbc:derby:LLC;create=true";
    private static String tableName = "customer_record";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    

	private JPanel contentPane;
	private JPanel welcome;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	private JTable custDetails;
	private JTable table1_1, table1_2, table1_3, table1_4, table1_5;// table1_6;
	private JScrollPane scrollPane1_1, scrollPane1_2, scrollPane1_3, scrollPane1_4, scrollPane1_5, scrollPane1_6;
	
	private DefaultTableModel model, model1_1, model1_2, model1_3, model1_4, model1_5, model1_6, model2_1, model2_2, model2_3, model2_4, model2_5, model2_6, model2_7;
	public String date ="";
	public String name ="";
	public String phoneNumber ="";
	public String address ="";
	public String cost ="";
	public String pending ="";
	public boolean select ;
	private JTextField glassTotal;
	private JTextField hardwareTotal;
	private JTextField pipesTotal, puttyTotal,cname,cphone;
	private JDateChooser dateChooser;
	private JTextArea caddress;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                createConnection();
        //insertRestaurants(5, "LaVals", "Berkeley");
        //selectRestaurants();
                
		 SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Welcome frame = new Welcome();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
            
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
        
        private static void insertCustDetails( String date, String name,String phoneNumber,String address,String cost,String pending )
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" +
                    date + ",'" + name + "','" + phoneNumber + "','" + address + "','" + cost + "','" + pending +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
        
        private void selectResults()
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            
            int i = 0;
            while(results.next())
		{
		String rdate = results.getString("Date");
		String rname = results.getString("Name");
		String rphoneNumber = results.getString("Phone_Number");
		String raddress = results.getString("Address"); 
		String rcost = results.getString("Cost"); 
		String rpending = results.getString("Pending");
		boolean rselect = results.getBoolean("Selection"); 
		model.addRow(new Object[]{rdate , rname, rphoneNumber, raddress, rcost, rpending, rselect});
		i++; 
		}
                if(i <1)
		{
		JOptionPane.showMessageDialog(null, "No Record Found","Error",
		JOptionPane.ERROR_MESSAGE);
		}
		if(i ==1)
		{
		System.out.println(i+" Record Found");
		}
		else
		{
		System.out.println(i+" Records Found");
		}
	}
            
//            ResultSetMetaData rsmd = results.getMetaData();
//            int numberCols = rsmd.getColumnCount();
//            for (int i=1; i<=numberCols; i++)
//            {
//                //print Column Names
//                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
//            }
//
//            System.out.println("\n-------------------------------------------------");
//
//            while(results.next())
//            {
//                int id = results.getInt(1);
//                String restName = results.getString(2);
//                String cityName = results.getString(3);
//                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
//            }
//            results.close();
//            stmt.close();
        catch(Exception ex)
	{
	JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
	JOptionPane.ERROR_MESSAGE);
	}
        
       
    }
        
        
	/**
	 * Create the frame.
	 */
	public Welcome() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		setTitle("INVOICE BUILDER");
		setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 50, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 90, 864, 510);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		welcome = new JPanel();
		welcome.setBounds(0, 0, 864, 510);
		layeredPane.add(welcome);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 864, 510);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 864, 510);
		panel_3.setLayout(null);
		welcome.setLayout(null);
		
		JTextArea txtrWelcome = new JTextArea();
		txtrWelcome.setBounds(99, 157, 710, 153);
		txtrWelcome.setLineWrap(true);
		txtrWelcome.setBackground(SystemColor.menu);
		txtrWelcome.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrWelcome.setEditable(false);
		txtrWelcome.setText("Welcome to the front page :\r\nPress the Order button for Order details.\r\nPress the Print button for Print preview.\r\nPress the Customer button for Customer records.");
		welcome.add(txtrWelcome);
		
		scrollPane_1 = new JScrollPane(panel_1);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		model1_1 = new DefaultTableModel();
		model1_1.addColumn("Name of Glass");
	    model1_1.addColumn("Length (inches)");
	    model1_1.addColumn("Breadth (inches)");
	    model1_1.addColumn("Total sq feet");
	    model1_1.addColumn("Quantity");
	    model1_1.addColumn("Rate (sq feet)");
	    model1_1.addColumn("Cost");
	    for(int i=1;i<=11;i++)
	    model1_1.addRow(new Object[] {});
		
	  //  model1_1.isCellEditable(model1_1.getRowCount(), model1_1.getColumnCount());
	   		
		table1_1 = new JTable(model1_1)
		{ 
            public Component prepareRenderer (TableCellRenderer rendererr, int row, int column)
            {
            	Component componentt = super.prepareRenderer(rendererr,row,column);
                if(column == 3 || column == 6)//color of column is yellow (index 3,6)
                    {
                      componentt.setBackground(Color.yellow);
                    }else
                    	componentt.setBackground(null);
                return componentt;
            }
			  
			public boolean isCellEditable(int row,int column){
			    if(column == 3 || column == 6) return false;//the 4th column is not editable
			    return true;
			  }
			
			};
		table1_1.setBounds(11, 5, 820, 202);
		
		
		
		JButton bAddRows_1 = new JButton("Add 10 more rows");
		bAddRows_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=1;i<=10;i++)
				    model1_1.addRow(new Object[] {});
			}
		});
		
		model1_2 = new DefaultTableModel();
	    model1_2.addColumn("Name of Hardware");
	    model1_2.addColumn("Quantity");
	    model1_2.addColumn("Rate per Unit");
	    model1_2.addColumn("Cost");
	    for(int i=1;i<=5;i++)
	    model1_2.addRow(new Object[] {});
		
		table1_2 = new JTable(model1_2)
		{ 
            public Component prepareRenderer (TableCellRenderer rendererr, int row, int column)
            {
            	Component componentt = super.prepareRenderer(rendererr,row,column);
                if(column == 3)//color of column is yellow (index 3)
                    {
                      componentt.setBackground(Color.yellow);
                    }else
                    	componentt.setBackground(null);
                return componentt;
            }
			  
			public boolean isCellEditable(int row,int column){
			    if(column == 3) return false;//the 4th column is not editable
			    return true;
			  }
			
			};
		table1_2.setBounds(11, 238, 820, 102);
		
		JButton bAddRows_2 = new JButton("Add 5 more rows");
		bAddRows_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 for(int i=1;i<=5;i++)
					    model1_2.addRow(new Object[] {});
			}
		});
		
		model1_3 = new DefaultTableModel();
	    model1_3.addColumn("Pipes");
	    model1_3.addColumn("Length in inches");
	    model1_3.addColumn("Rate per inches");
	    model1_3.addColumn("Cost");
	    for(int i=1;i<=5;i++)
	    model1_3.addRow(new Object[] {});
		
		table1_3 = new JTable(model1_3)
		{ 
            public Component prepareRenderer (TableCellRenderer rendererr, int row, int column)
            {
            	Component componentt = super.prepareRenderer(rendererr,row,column);
                if(column == 3)//color of column is yellow (index 3)
                    {
                      componentt.setBackground(Color.yellow);
                    }else
                    	componentt.setBackground(null);
                return componentt;
            }
			  
			public boolean isCellEditable(int row,int column){
			    if(column == 3) return false;//the 4th column is not editable
			    return true;
			  }
			
			};
		table1_3.setBounds(11, 371, 820, 102);
		
		JButton bAddRows_3 = new JButton("Add 5 more rows");
		bAddRows_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 for(int i=1;i<=5;i++)
					    model1_3.addRow(new Object[] {});
			}
		});
		
		model1_4 = new DefaultTableModel();
	    model1_4.addColumn("Putty");
	    model1_4.addColumn("Kilograms");
	    model1_4.addColumn("Rate per Kilograms");
	    model1_4.addColumn("Cost");
	    for(int i=1;i<=3;i++)
	    model1_4.addRow(new Object[] {});
		
		table1_4 = new JTable(model1_4)
		{ 
            public Component prepareRenderer (TableCellRenderer rendererr, int row, int column)
            {
            	Component componentt = super.prepareRenderer(rendererr,row,column);
                if(column == 3)//color of column is yellow (index 3)
                    {
                      componentt.setBackground(Color.yellow);
                    }else
                    	componentt.setBackground(null);
                return componentt;
            }
			  
			public boolean isCellEditable(int row,int column){
			    if(column == 3) return false;//the 4th column is not editable
			    return true;
			  }
			
			};
		table1_4.setBounds(11, 510, 820, 49);
		
		model1_5 = new DefaultTableModel();
	    model1_5.addColumn("Work Type");
	    model1_5.addColumn("Cost");
	    model1_5.addRow(new Object[] {"Cutting", });
	    model1_5.addRow(new Object[] {"Grinding", });
	    model1_5.addRow(new Object[] {"Adding", });
	    model1_5.addRow(new Object[] {"Labour Charge", });
	    model1_5.addRow(new Object[] {"Freight Charge", });
	    model1_5.addRow(new Object[] {"Extra Charge", });
	    model1_5.addRow(new Object[] {"Total", });
		
		table1_5 = new JTable(model1_5);
		table1_5.setBounds(11, 575, 302, 200);
		
		  
//		model1_6 = new DefaultTableModel();
//		model1_6.addColumn("");
//		model1_6.addColumn("");
//		model1_6.addRow(new Object[] {"Name of Customer", });
//		model1_6.addRow(new Object[] {"Address", });
//		model1_6.addRow(new Object[] {"Contact Number", });
//		model1_6.addRow(new Object[] {"Remarks", });
//		model1_6.addRow(new Object[] {"Date & Time of Order", });
//		model1_6.addRow(new Object[] {"Signature", });
//		
//		table1_6 = new JTable(model1_6);
//		table1_6.setBounds(501, 575, 302, 200);
		
		JButton bSaveData = new JButton("Save Data");
		bSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		JButton bPrint = new JButton("Print");
		bPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
				
		scrollPane1_1 = new JScrollPane(table1_1);
		
		scrollPane1_2 = new JScrollPane(table1_2);
		
		scrollPane1_3 = new JScrollPane(table1_3);
		
		scrollPane1_4 = new JScrollPane(table1_4);
				
		scrollPane1_5 = new JScrollPane(table1_5);
		
//		scrollPane1_6 = new JScrollPane(table1_6);
//		scrollPane1_6.setBounds(501, 600, 302, 105);
		
		glassTotal = new JTextField();
		glassTotal.setColumns(10);
		
		JLabel glassLabel = new JLabel("Total Cost");
		
		hardwareTotal = new JTextField();
		hardwareTotal.setColumns(10);
		
		JLabel hardwareLabel = new JLabel("Total Cost");
		
		pipesTotal = new JTextField();
		pipesTotal.setColumns(10);
		
		JLabel pipesLabel = new JLabel("Total Cost");
		
		puttyTotal = new JTextField();
		puttyTotal.setColumns(10);
		
		JLabel puttyLabel = new JLabel("Total Cost");
	
		cname = new JTextField();
		cname.setColumns(10);
		cname.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lName = new JLabel("Name ");
		
		cphone = new JTextField();
		cphone.setColumns(10);
		cphone.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lPhone = new JLabel("Phone ");
		
		caddress = new JTextArea();
		caddress.setBorder(BorderFactory.createLineBorder(Color.black));
		//cname.setColumns(10);
		
		JLabel lAddress = new JLabel("Address ");
		
		JLabel lDate = new JLabel("Date ");
		
		dateChooser = new JDateChooser();
		dateChooser.setBorder(BorderFactory.createLineBorder(Color.gray));
		dateChooser.setDateFormatString("dd-MM-yyyy");
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane1_1, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(bAddRows_1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(409)
					.addComponent(glassLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(glassTotal, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane1_2, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(bAddRows_2, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(323)
					.addComponent(hardwareLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(hardwareTotal, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane1_3, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(bAddRows_3, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(323)
					.addComponent(pipesLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(pipesTotal, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane1_4, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane1_5, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lAddress, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(lDate, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(174)
							.addComponent(puttyLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(puttyTotal, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(cname, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lPhone, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(cphone, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addComponent(caddress, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(79)
					.addComponent(bSaveData, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(251)
					.addComponent(bPrint, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane1_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bAddRows_1)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(glassLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(glassTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(5)
					.addComponent(scrollPane1_2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bAddRows_2)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(hardwareLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(hardwareTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addComponent(scrollPane1_3, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bAddRows_3)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(pipesLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(pipesTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addComponent(scrollPane1_4, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane1_5, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(25)
							.addComponent(lName)
							.addGap(11)
							.addComponent(lAddress)
							.addGap(51)
							.addComponent(lDate))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(puttyLabel)
								.addComponent(puttyTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(cname, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lPhone)
								.addComponent(cphone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addComponent(caddress, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bSaveData)
						.addComponent(bPrint)))
		);
		panel_1.setLayout(gl_panel_1);
		layeredPane.add(scrollPane_1);
		
//		
		
		scrollPane_3 = new JScrollPane(panel_3);
		
		String[] columnNames = {"Date", "Name", "Phone Number"
				 , "Address", "Cost", "Pending", "Select" };
//		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames()); 
		//table = new JTable(model);
		
			
	//	custDetails = new JTable(data,columnNames);
		//DefaultTableModel model = new DefaultTableModel(data, columnNames);
		custDetails = new JTable(model){
//
            private static final long serialVersionUID = 1L;

//            @Override
//            public Class getColumnClass(int column) {
//            return getValueAt(0, column).getClass();
//            }
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    default:
                        return Boolean.class;
                }
             }
        };
        custDetails.setPreferredScrollableViewportSize(custDetails.getPreferredSize());
		//custDetails.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		//custDetails.setColumnSelectionAllowed(true);
		//custDetails.setCellSelectionEnabled(true);
		
		custDetails.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		custDetails.setFillsViewportHeight(true);
		
		JScrollPane p3 = new JScrollPane(custDetails);
		
		//JScrollPane scroll = new JScrollPane(table);
		p3.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p3.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//		"Date", "Name", "Phone Number"
//		 , "Address", "Cost", "Pending", "Select" };
//		String date ="";
//		String name ="";
//		String phoneNumber ="";
//		String address ="";
//		String cost ="";
//		String pending ="";
//		boolean select ;
		//		String textvalue = textbox.getText();
//		String roll= "";
//		String name= "";
//		String cl = "";
//		String sec = "";
		
	
		
		p3.setBounds(10, 10, 822, 300);
		panel_3.add(p3);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel model=(DefaultTableModel)custDetails.getModel();
			        try
			        {
			            int    SelectRowIndex=custDetails.getSelectedRow();
			            model.removeRow(SelectRowIndex);
			        }
			        catch(Exception ex)
			        {
			             JOptionPane.showMessageDialog(null,"Delected Succefully");
			           
			        }
			}
		});
		search.setBounds(10, 350, 220, 25);
		panel_3.add(search);
		
		JButton sort = new JButton("Sort By");
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		sort.setBounds(305, 350, 220, 25);
		panel_3.add(sort);
		
		JButton delete = new JButton("Delete Selected");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				 DefaultTableModel model=(DefaultTableModel)custDetails.getModel();
			        try
			        {
			            int    SelectRowIndex=custDetails.getSelectedRow();
			            model.removeRow(SelectRowIndex);
			        }
			        catch(Exception ex)
			        {
			             JOptionPane.showMessageDialog(null,"Delected Succefully");
			           
			        }
			}
		});
			
//				Connection conn = null;
//				   Statement stmt = null;
//				   try{
//				      //STEP 2: Register JDBC driver
//				      Class.forName("com.mysql.jdbc.Driver");
//
//				      //STEP 3: Open a connection
//				      System.out.println("Connecting to a selected database...");
//				      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice_builder","root","");
//				      System.out.println("Connected database successfully...");
//				      
//				      //STEP 4: Execute a query
//				      System.out.println("Creating statement...");
//				      stmt = (Statement) conn.createStatement();
//				      String sql = "DELETE FROM customer_record " +
//				                   "WHERE Selection=0";
//				      stmt.executeUpdate(sql);
//
//				      // Now you can extract all the records
//				      // to see the remaining records
//				      
//				     	}
//						catch(Exception ex)
//						{
//						JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
//						JOptionPane.ERROR_MESSAGE);
//						}
//					finally{
//				      //finally block used to close resources
//				      try{
//				         if(stmt!=null)
//				            conn.close();
//				      }catch(SQLException se){
//				      }// do nothing
//				      try{
//				         if(conn!=null)
//				            conn.close();
//				      }catch(SQLException se){
//				         se.printStackTrace();
//				      }//end finally try
//				   }
//			}
//		});
		delete.setBounds(610, 350, 220, 25);
		panel_3.add(delete);
		layeredPane.add(scrollPane_3, "name_157999855343900");
		
		JButton odrDetails = new JButton("Order Details");
		odrDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(scrollPane_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		odrDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		odrDetails.setBounds(10, 20, 424, 35);
		contentPane.add(odrDetails);
		
		JButton custRecord = new JButton("Customer Record");
		custRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPane.removeAll();
				layeredPane.add(scrollPane_3);
				layeredPane.repaint();
				layeredPane.revalidate();
                                
                                
				selectResults();
//				try
//				{ 
//				Class.forName("com.mysql.jdbc.Driver"); 
//				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice_builder","root","");
//				String sql = "select * from customer_record; ";
//				PreparedStatement ps = con.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				int i = 0;
//				while(rs.next())
//				{
//				date = rs.getString("Date");
//				name = rs.getString("Name");
//				phoneNumber = rs.getString("Phone_Number");
//				address = rs.getString("Address"); 
//				cost = rs.getString("Cost"); 
//				pending = rs.getString("Pending");
//				select = rs.getBoolean("Selection"); 
//				model.addRow(new Object[]{date , name, phoneNumber, address, cost, pending, select});
//				i++; 
//				}
//				if(i <1)
//				{
//				JOptionPane.showMessageDialog(null, "No Record Found","Error",
//				JOptionPane.ERROR_MESSAGE);
//				}
//				if(i ==1)
//				{
//				System.out.println(i+" Record Found");
//				}
//				else
//				{
//				System.out.println(i+" Records Found");
//				}
//				}
//				catch(Exception ex)
//				{
//				JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
//				JOptionPane.ERROR_MESSAGE);
//				}
				
				
			}
		});
		custRecord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		custRecord.setBounds(450, 20, 424, 35);
		contentPane.add(custRecord);
	}
  }

