/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author Yun Wang
 *
 */
public class new_course extends JFrame {

	private JPanel panel = new JPanel();
	private JButton add = new JButton("Add Course");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	//private JTextField hw_id = new JTextField();
	private JTextField c_id = new JTextField();
	private JTextField c_name = new JTextField();
	private JTextField start = new JTextField();
	private JTextField end = new JTextField();
	private JLabel enterstart = new JLabel("Start date:          ");
	private JLabel startexp = new JLabel("e.g. 2010-05-23");
	private JLabel enterend = new JLabel("End date:          ");
	private JLabel entercid = new JLabel("Course ID:           ");
	private JLabel entername = new JLabel("Course Name: ");
		private String id, c_token;
	
	public new_course(String id, String c_token){
		this.id = id;
		this.c_token = c_token;
		
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createTitledBorder("Create New Course"));
        
        add.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		addActionPerformed(evt);
        	}
        });
        clear.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		clearActionPerformed(evt);
        	}
        });
        back.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		backActionPerformed(evt);
        	}
        });
        
        GroupLayout AddCLayout = new GroupLayout(panel);
        AddCLayout.setHorizontalGroup(
        	AddCLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddCLayout.createSequentialGroup()
        			.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(AddCLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(entercid)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(c_id, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddCLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(entername)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(c_name, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddCLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(enterstart)
        						.addComponent(enterend))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(end, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        						.addGroup(AddCLayout.createSequentialGroup()
        							.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(start, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        							.addGap(36)
        							.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(startexp)))))
        				.addGroup(AddCLayout.createSequentialGroup()
        					.addGap(80)
        					.addGroup(AddCLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(AddCLayout.createSequentialGroup()
        							.addComponent(add, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(back)))))
        			.addContainerGap(47, Short.MAX_VALUE))
        );
        AddCLayout.setVerticalGroup(
        	AddCLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddCLayout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(AddCLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(entercid)
        				.addComponent(c_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddCLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(entername)
        				.addComponent(c_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddCLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterstart)
        				.addComponent(start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(startexp))
        			.addGap(18)
        			.addGroup(AddCLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterend)
        				.addComponent(end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(AddCLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(add)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        AddCLayout.linkSize(SwingConstants.VERTICAL, new Component[] {add, clear, back});
        AddCLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add, clear, back});
        panel.setLayout(AddCLayout);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(55)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(55)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(64, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        
        pack();
	}
	
	private void addActionPerformed(ActionEvent evt){
		String start_string = start.getText();
		String end_string = end.getText();
		String cid_string = c_id.getText();
		String cname_string = c_name.getText();
		//String qid_string[] = q_id.getText().split(",");
		
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     Statement stmt1 = null;
			     ResultSet rs = null;
			     //ResultSet rs_instr = null;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					
					stmt.executeUpdate("INSERT INTO COURSES (C_ID, C_T, C_NAME, C_START, C_END) " +
							"VALUES ('" + cid_string + "', '" + c_token + "', '" + cname_string + "', " +
									"TO_DATE('" + start_string + "','YYYY-MM-DD'), TO_DATE('" + end_string + "','YYYY-MM-DD'))");
					
					stmt1.executeUpdate("INSERT INTO TEACHES (P_ID, C_T) VALUES ('" + id + "', '" + c_token + "')");
						
					/*int topic = 0;
					for(int i = 0; i < qid_string.length; i++){
						rs = stmt2.executeQuery("SELECT t_id FROM Questions WHERE q_id = " + qid_string[i]);
						while (rs.next()){
							topic = rs.getInt(1);
						}
						stmt1.executeUpdate("INSERT INTO ASSESSMENTHAS(AS_ID, C_T, Q_ID, T_ID) " +
								"VALUES (" + next + ", '" + c_token + "', " + qid_string[i] + ", " + topic + ")");
					}*/
					
					new add_success(7, id, c_token).setVisible(true);
					this.dispose();
			     } finally {
			    	    Constants.close(rs);
			    	    //Constants.close(rs_instr);
			    	    Constants.close(stmt);
			    	    Constants.close(stmt1);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
	}
	
	private void clearActionPerformed(ActionEvent evt){
		start.setText("");
		end.setText("");
		c_id.setText("");
		c_name.setText("");
		//q_id.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new add_course(id, 1).setVisible(true);
		this.dispose();
	}
}
