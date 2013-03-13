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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author Yun Wang
 *
 */
public class create_user extends JFrame{
	
	private JButton create = new JButton("Create Account");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField re_passwordField = new JPasswordField();
	private JTextField idField = new JTextField();
	private JTextField firstNameField = new JTextField();
	private JTextField lastNameField = new JTextField();
	//private JTextField email = new JTextField();
	private JLabel enterid = new JLabel("Choose a Unique User ID: ");
	private JLabel enterfirstname = new JLabel("First Name:                           ");
	private JLabel enterlastname = new JLabel("Last Name:                           ");
	//private JLabel enteremail = new JLabel("Email Address:                    ");
	private JLabel enterpassword = new JLabel("Password:                            ");
	private JLabel reenterpassword = new JLabel("Re-enter Password:           ");
	
	public create_user(){
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createTitledBorder("Create User"));
        
        create.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		createActionPerformed(evt);
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
        
        GroupLayout CreateLayout = new GroupLayout(panel);
        CreateLayout.setHorizontalGroup(
        	CreateLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(CreateLayout.createSequentialGroup()
        			.addGap(0, 0, Short.MAX_VALUE)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(CreateLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enterpassword)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(CreateLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(reenterpassword)
        					.addGap(18)
        					.addComponent(re_passwordField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(CreateLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(CreateLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(enterid)
        						.addComponent(enterfirstname))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(CreateLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        						.addComponent(idField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(CreateLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enterlastname)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(CreateLayout.createSequentialGroup()
        					.addGap(80)
        					.addComponent(create, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        					.addGap(24)
        					.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        					.addGap(24)
        					.addComponent(back)))
        			.addGap(76))
        );
        CreateLayout.setVerticalGroup(
        	CreateLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(CreateLayout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterid)
        				.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterfirstname)
        				.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterlastname)
        				.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterpassword)
        				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(reenterpassword)
        				.addComponent(re_passwordField))
        			.addGap(35)
        			.addGroup(CreateLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(create)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        CreateLayout.linkSize(SwingConstants.VERTICAL, new Component[] {create, clear, back});
        CreateLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {create, clear, back});
        panel.setLayout(CreateLayout);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        
        pack();
	}
	
	private void createActionPerformed(ActionEvent evt){
		String unity_id = idField.getText();
		String first_name = firstNameField.getText();
		String last_name = lastNameField.getText();
		//String email_addr = email.getText();
		char[] password = passwordField.getPassword();
		char[] re_password = re_passwordField.getPassword();
		
		if(unity_id.equals("")){
			new invalid_input(0).setVisible(true);
		}
		else if(password.equals("")){
			new invalid_input(1).setVisible(true);
		}
		else if(!(String.valueOf(password).equals(String.valueOf(re_password)))){
			new invalid_input(2).setVisible(true);
		}
		else if(first_name.equals("")){
			new invalid_input(3).setVisible(true);
		}
		else if(last_name.equals("")){
			new invalid_input(4).setVisible(true);
		}
		else{
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     Statement stmt1 = null;
			     Statement stmt2 = null;
			     ResultSet rs_stdnt = null;
			     ResultSet rs_instr = null;
			     int count_s = 0, count_p = 0;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					
					rs_stdnt = stmt1.executeQuery("SELECT COUNT(*) FROM STUDENTS WHERE S_ID = '" + unity_id + "'");
					rs_instr = stmt2.executeQuery("SELECT COUNT(*) FROM PROFESSORS WHERE P_ID = '" + unity_id + "'");
					
					while (rs_stdnt.next()){
						count_s = rs_stdnt.getInt(1);
						System.out.println(count_s);
					}
					while (rs_instr.next()){
						count_p = rs_instr.getInt(1);
						System.out.println(count_p);
					}
					
					if(count_s == 0 && count_p == 0){
						stmt.executeUpdate("INSERT INTO STUDENTS (S_ID, S_PASS) VALUES ('" + unity_id + "', '" + String.valueOf(password) + "')");
						new add_success(0, null, null).setVisible(true);
						this.dispose();
					}
					else
						new invalid_input(5).setVisible(true);
			     } finally {
			    	    Constants.close(rs_stdnt);
			    	    Constants.close(rs_instr);
			    	    Constants.close(stmt);
			    	    Constants.close(stmt1);
			    	    Constants.close(stmt2);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		}
	}
	
	private void clearActionPerformed(ActionEvent evt){
		idField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		//email.setText("");
		passwordField.setText("");
		re_passwordField.setText("");
	}
	
	public void resetPassword(){
		passwordField.setText("");
		re_passwordField.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new start(false).setVisible(true);
		this.dispose();
	}
}
