/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
public class report extends JFrame {

	private JPanel panel = new JPanel();
	private JButton submit = new JButton("Submit");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	private JTextField query = new JTextField();
	private JLabel enterquery = new JLabel("Enter the query: ");
	private String p_id, c_token;
	
	public report(String id, String token){
		this.p_id = id;
		this.c_token = token;
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Add Question"));
        
        submit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		submitActionPerformed(evt);
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
        
        GroupLayout ReportLayout = new GroupLayout(panel);
        ReportLayout.setHorizontalGroup(
        	ReportLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(ReportLayout.createSequentialGroup()
        			.addGroup(ReportLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(ReportLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enterquery)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(query, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(ReportLayout.createSequentialGroup()
        					.addGap(170)
        					.addGroup(ReportLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(ReportLayout.createSequentialGroup()
        							.addComponent(submit, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(back)))))
        			.addContainerGap(47, Short.MAX_VALUE))
        );
        ReportLayout.setVerticalGroup(
        	ReportLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(ReportLayout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(ReportLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterquery)
        				.addComponent(query, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(ReportLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(submit)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        ReportLayout.linkSize(SwingConstants.VERTICAL, new Component[] {submit, clear, back});
        ReportLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {submit, clear, back});
        panel.setLayout(ReportLayout);
        
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
	
	private void submitActionPerformed(ActionEvent evt){
		String query_string = query.getText();
		
		if(query_string.equals("")){
			new invalid_input(16).setVisible(true);
		}
		else{
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     //Statement stmt2 = null;
			     ResultSet rs = null;
			     //ResultSet rs_instr = null;
			     String part, result = null;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					
					rs = stmt.executeQuery(query_string);
					
					while (rs.next()){
						part = rs.getString(1);
						System.out.println(part);
						result += part;
					}
					
					if(result != null){
						//new add_success(4, p_id, c_token).setVisible(true);
						this.dispose();
					}
					else{
						new invalid_input(17).setVisible(true);
					}
					
			     } finally {
			    	    Constants.close(rs);
			    	    Constants.close(stmt);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		}
	}
	
	private void clearActionPerformed(ActionEvent evt){
		query.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new prof_course_option(p_id, c_token).setVisible(true);
		this.dispose();
	}
}
