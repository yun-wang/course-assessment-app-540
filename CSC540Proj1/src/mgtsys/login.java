/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;

/**
 * @author Yun Wang
 *
 */
public class login extends JFrame{
	
	static final String jdbcURL 
	= "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	
	private int type;
	private JButton okay = new JButton("OK");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private JPasswordField passwordField = new JPasswordField();
	private JTextField nameField = new JTextField();
	private JLabel intro = new JLabel("Please enter your user ID and password:");
	private JLabel entername = new JLabel("User ID:         ");
	private JLabel enterpassword = new JLabel("Password:    ");
	
	public login(int type){
		this.type = type;
		initComponents();
	}
	
	public login(){		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createTitledBorder("Login"));
        
        okay.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		okActionPerformed(evt);
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
        
        GroupLayout LoginLayout = new GroupLayout(panel);
        panel.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
        		LoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(LoginLayout.createSequentialGroup()
        				.addGroup(LoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                    .addGroup(LoginLayout.createSequentialGroup()
        	                        .addGap(26, 26, 26)
        	                        .addComponent(intro))
        	                    .addGroup(LoginLayout.createSequentialGroup()
        	                        .addGap(80, 80, 80)
        	                        .addComponent(okay, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        	                        .addGap(18, 18, 18)
        	                        .addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        	                        .addGap(18, 18, 18)
        	                        .addComponent(back))
        	                    .addGroup(LoginLayout.createSequentialGroup()
        	                        .addGap(90, 90, 90)
        	                        .addComponent(entername)
        	                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        	                    .addGroup(LoginLayout.createSequentialGroup()
                	                .addGap(90, 90, 90)
                	                .addComponent(enterpassword)
                	                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        	                .addContainerGap(76, Short.MAX_VALUE))
        );
        
        LoginLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {okay, clear, back});
        
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(intro)
                .addGap(24, 24, 24)
                .addGroup(LoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(entername)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(enterpassword)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(LoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(okay)
                    .addComponent(clear)
                    .addComponent(back))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        LoginLayout.linkSize(SwingConstants.VERTICAL, new Component[] {okay, clear, back});
        
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
	
	private void okActionPerformed(ActionEvent evt){
		String name = nameField.getText();
		char[] password = passwordField.getPassword();
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
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
				stmt1 = conn.createStatement();
				stmt2 = conn.createStatement();
				
				rs_stdnt = stmt1.executeQuery("SELECT COUNT(*) FROM STUDENTS WHERE S_ID = '" + name + "' AND S_PASS = '" + String.valueOf(password) + "'");
				rs_instr = stmt2.executeQuery("SELECT COUNT(*) FROM PROFESSORS WHERE P_ID = '" + name + "' AND P_PASS = '" + String.valueOf(password) + "'");
				
				while (rs_stdnt.next()){
					count_s = rs_stdnt.getInt(1);
					//System.out.println(count_s);
				}
				while (rs_instr.next()){
					count_p = rs_instr.getInt(1);
					//System.out.println(count_p);
				}
				
				if(count_s == 1){
					new student_view(name).setVisible(true);
					this.dispose();
				}
				else if(count_p == 1){
					new professor_view().setVisible(true);
					this.dispose();
				}
				else{
					new login_incorrect().setVisible(true);
					this.dispose();
				}				
		     } finally {
	                close(rs_stdnt);
	                close(rs_instr);
	                close(stmt1);
	                close(stmt2);
	                close(conn);
	         } 
		} catch(Throwable oops) {
           oops.printStackTrace();
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
		passwordField.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		start w = new start(false);
		w.setVisible(true);
		this.dispose();
	}
}
