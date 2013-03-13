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

/**
 * @author Yun Wang
 *
 */
public class add_course extends JFrame {
	
	private JButton submit = new JButton("Submit");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private JTextField TokenField = new JTextField();
	private JLabel entertoken = new JLabel("Enter Course Token:    ");
	private String id;
	private int type;
	
	public add_course(String id, int type){
		this.id = id;
		this.type = type;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createTitledBorder("Add Course"));
        
        submit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		submitActionPerformed(evt);
        	}
        });
        back.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		backActionPerformed(evt);
        	}
        });
        
        GroupLayout AddLayout = new GroupLayout(panel);
        panel.setLayout(AddLayout);
        AddLayout.setHorizontalGroup(
        		AddLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(AddLayout.createSequentialGroup()
        				.addGroup(AddLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                    .addGroup(AddLayout.createSequentialGroup()
        	                        .addGap(110, 110, 110)
        	                        .addComponent(submit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        	                        .addGap(30, 30, 30)
        	                        .addComponent(back))
        	                    .addGroup(AddLayout.createSequentialGroup()
        	                        .addGap(80, 80, 80)
        	                        .addComponent(entertoken)
        	                        .addComponent(TokenField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        	                .addContainerGap(76, Short.MAX_VALUE))
        );
        
        AddLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {submit, back});
        
        AddLayout.setVerticalGroup(
            AddLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(AddLayout.createSequentialGroup()
            	.addGap(35, 35, 35)
                .addGroup(AddLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	.addComponent(entertoken)
                    .addComponent(TokenField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(AddLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(back))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        
        AddLayout.linkSize(SwingConstants.VERTICAL, new Component[] {submit, back});
        
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
	
	private void submitActionPerformed(ActionEvent evt){
		String token = TokenField.getText();
		
		if(token.equals("")){
			new invalid_input(7).setVisible(true);
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
			     Statement stmt3 = null;
			     ResultSet rs_exists = null;
			     ResultSet rs_dup = null;
			     ResultSet rs_over = null;
			     int count = 0, count1 = 0, count2 = 0;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					stmt3 = conn.createStatement();
					
					rs_exists = stmt.executeQuery("SELECT COUNT(*) FROM COURSES WHERE C_TOKEN = '" + token + "'");
					
					while (rs_exists.next()){
						count = rs_exists.getInt(1);
						//System.out.println(count);
					}
					
					if(count == 0){
						new invalid_input(8).setVisible(true);    //course does not exists
					}
					else{
						rs_over = stmt1.executeQuery("SELECT COUNT(*) FROM COURSES WHERE C_TOKEN = '" + token + "' AND SYSDATE < C_END");
						rs_dup = stmt2.executeQuery("SELECT COUNT(*) FROM TAKES WHERE S_ID = '" + id + "' AND C_TOKEN = '" + token + "'");
						
						while (rs_over.next()){
							count2 = rs_over.getInt(1);
							//System.out.println(count2);
						}
						while (rs_dup.next()){
							count1 = rs_dup.getInt(1);
							//System.out.println(count1);
						}
						if(count2 == 0)
							new invalid_input(9).setVisible(true);    //course has ended
						else if(count1 == 1)
							new invalid_input(10).setVisible(true);    //already enrolled
						else{
							stmt3.executeUpdate("INSERT INTO TAKES (S_ID, C_TOKEN) VALUES ('" + id + "', '" + token + "')");
							new add_success(1, id, token).setVisible(true);
							this.dispose();
						}
					}
			     } finally {
		                Constants.close(rs_exists);
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
	
	private void backActionPerformed(ActionEvent evt){
		new stdnt_prof_view(id, type).setVisible(true);
		this.dispose();
	}
}
