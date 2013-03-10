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

/**
 * @author Yun Wang
 *
 */
public class createUser extends JFrame{
	
	static final String jdbcURL 
	= "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	
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
	
	public createUser(){
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
        panel.setLayout(CreateLayout);
        CreateLayout.setHorizontalGroup(
        		CreateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(CreateLayout.createSequentialGroup()
        				.addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                    .addGroup(CreateLayout.createSequentialGroup()
        	                        .addGap(80, 80, 80)
        	                        .addComponent(create, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        	                        .addGap(24, 24, 24)
        	                        .addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        	                        .addGap(24, 24, 24)
        	                        .addComponent(back))
        	                    .addGroup(CreateLayout.createSequentialGroup()
        	                        .addGap(140, 140, 140)
        	                        .addComponent(enterid)
        	                        .addComponent(idField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        	                    .addGroup(CreateLayout.createSequentialGroup()
        	                        .addGap(140, 140, 140)
        	                        .addComponent(enterfirstname)
        	                        .addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        	                    .addGroup(CreateLayout.createSequentialGroup()
        	                    	.addGap(140, 140, 140)
        	                        .addComponent(enterlastname)
        	                        .addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        	                    /*.addGroup(CreateLayout.createSequentialGroup()
        	                    	.addGap(140, 140, 140)
        	                        .addComponent(enteremail)
        	                        .addComponent(email, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))*/
        	                    .addGroup(CreateLayout.createSequentialGroup()
        	                    	.addGap(140, 140, 140)
                	                .addComponent(enterpassword)
                	                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                	            .addGroup(CreateLayout.createSequentialGroup()
                	            	.addGap(140, 140, 140)
                	                .addComponent(reenterpassword)
                	                .addComponent(re_passwordField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        	                .addContainerGap(76, Short.MAX_VALUE))
        );
        
        CreateLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {create, clear, back});
        
        CreateLayout.setVerticalGroup(
            CreateLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(CreateLayout.createSequentialGroup()
            	.addGap(35, 35, 35)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enterid)
                    .addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enterfirstname)
                    .addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enterlastname)
                    .addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                /*.addGap(18, 18, 18)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enteremail)
                    .addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))*/
                .addGap(18, 18, 18)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enterpassword)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(reenterpassword)
                    .addComponent(re_passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(CreateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(create)
                    .addComponent(clear)
                    .addComponent(back))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        CreateLayout.linkSize(SwingConstants.VERTICAL, new Component[] {create, clear, back});
        
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
			new re_create(0).setVisible(true);
		}
		else if(password.equals("")){
			new re_create(1).setVisible(true);
		}
		else if(!(String.valueOf(password).equals(String.valueOf(re_password)))){
			new re_create(2).setVisible(true);
		}
		else if(first_name.equals("")){
			new re_create(3).setVisible(true);
		}
		else if(last_name.equals("")){
			new re_create(4).setVisible(true);
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
			 		conn = DriverManager.getConnection(jdbcURL, user, passwd);
			 		
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
						new create_success().setVisible(true);
						this.dispose();
					}
					else
						new re_create(5).setVisible(true);
			     } finally {
		                close(rs_stdnt);
		                close(rs_instr);
		                close(stmt);
		                close(stmt1);
		                close(stmt2);
		                close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		}
	}
	
	static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }
    
    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
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
