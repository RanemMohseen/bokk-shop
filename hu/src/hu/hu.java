package hu;

import java.awt.EventQueue;
import java .sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class hu {

	private JFrame frame;
	private JTextField txtBookShop;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hu window = new hu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hu() {
		initialize();
		Connect();
		table_load();
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/hu", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }
	
	
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from book");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	 
	 
	 
	 
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 915, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtBookShop = new JTextField();
		txtBookShop.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtBookShop.setText("Book Shop");
		txtBookShop.setBounds(260, 10, 170, 57);
		frame.getContentPane().add(txtBookShop);
		txtBookShop.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 87, 371, 258);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 45, 114, 32);
		panel.add(lblNewLabel);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEdition.setBounds(10, 98, 114, 32);
		panel.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrice.setBounds(10, 147, 114, 32);
		panel.add(lblPrice);
		
		txtbname = new JTextField();
		txtbname.setBounds(187, 45, 133, 33);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(187, 103, 133, 32);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(187, 155, 133, 32);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				 try {
						pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						table_load();
						//table_load();
							           
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
					   }

					catch (SQLException e1) 
				 {
						
						e1.printStackTrace();
						}
				
				
				
			}
		});
		btnNewButton.setBounds(47, 363, 85, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(163, 363, 85, 36);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(283, 363, 85, 36);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(481, 100, 371, 238);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(47, 422, 386, 65);
		frame.getContentPane().add(panel_1);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblBookId);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
					 try {
				          
				            String id = txtbid.getText();

				                pst = con.prepareStatement("select name,edition,price from book where id = ?");
				                pst.setString(1, id);
				                ResultSet rs = pst.executeQuery();

				            if(rs.next()==true)
				            {
				              
				                String name = rs.getString(1);
				                String edition = rs.getString(2);
				                String price = rs.getString(3);
				                
				                txtbname.setText(name);
				                txtedition.setText(edition);
				                txtprice.setText(price);
				                
				                
				            }   
				            else
				            {
				            	txtbname.setText("");
				            	txtedition.setText("");
				                txtprice.setText("");
				                 
				            }
				            


				        } 
					
					 catch (SQLException ex) {
				           
				        }
					
					
					
					
			}
				
			
			
			
			
			
		});
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				 try {
						pst = con.prepareStatement("update book set name = ?, edition=?,price=? where id=?" );
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.setString(4, bid);

						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Update!!!!!");
						table_load();
						//table_load();
							           
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
					   }

					catch (SQLException e1) 
				 {
						
						e1.printStackTrace();
						}
				
			
			
			
			
			
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(546, 434, 85, 53);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					
					
					String bid;
					
					
					
					bid  = txtbid.getText();
					
					 try {
							pst = con.prepareStatement("delete from  book  where id=?");
				            pst.setString(1, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record delete!!!!!");
				            table_load();
				           
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
						}

			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
		
				}
				
				
				
				
				
			
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(703, 434, 85, 53);
		frame.getContentPane().add(btnDelete);
	}
}
