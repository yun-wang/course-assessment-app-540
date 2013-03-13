/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

/**
 * @author Yun Wang
 *
 */
public class enter_answer extends JFrame {

	private JPanel panel = new JPanel();
	private JButton add = new JButton("Add Answer");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	private JTextField a_id = new JTextField();
	private JLabel enteraid = new JLabel("Enter answer ID: ");
	private JTextField ans = new JTextField();
	private JLabel enterans = new JLabel("Enter answer: ");
	private ButtonGroup group = new ButtonGroup();
	private String p_id, c_token;
	private int t_id, q_id;
	
	public enter_answer(String id, String c_token, int t_id, int q_id){
		this.p_id = id;
		this.c_token = c_token;
		this.t_id = t_id;
		this.q_id = q_id;
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Add Answer"));
        
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
       
        JRadioButton rdbtnCorrect = new JRadioButton("correct");
        JRadioButton rdbtnIncorrect = new JRadioButton("incorrect");
        
        rdbtnCorrect.setActionCommand(1+"");
        rdbtnCorrect.setBackground(Constants.color);
        rdbtnIncorrect.setActionCommand(0+"");
        rdbtnIncorrect.setBackground(Constants.color);
        group.add(rdbtnCorrect);
        group.add(rdbtnIncorrect);
        
        GroupLayout AddAnsLayout = new GroupLayout(panel);
        AddAnsLayout.setHorizontalGroup(
        	AddAnsLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddAnsLayout.createSequentialGroup()
        			.addGroup(AddAnsLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(AddAnsLayout.createSequentialGroup()
        					.addGap(45)
        					.addComponent(add, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        					.addGap(30)
        					.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        					.addGap(34)
        					.addComponent(back))
        				.addGroup(AddAnsLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(AddAnsLayout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(AddAnsLayout.createParallelGroup(Alignment.LEADING)
        							.addComponent(enterans)
        							.addComponent(enteraid))
        						.addComponent(rdbtnCorrect))
        					.addGroup(AddAnsLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(AddAnsLayout.createSequentialGroup()
        							.addGap(18)
        							.addGroup(AddAnsLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(ans, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        								.addComponent(a_id, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(AddAnsLayout.createSequentialGroup()
        							.addGap(45)
        							.addComponent(rdbtnIncorrect)))))
        			.addContainerGap(156, Short.MAX_VALUE))
        );
        AddAnsLayout.setVerticalGroup(
        	AddAnsLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddAnsLayout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(AddAnsLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enteraid)
        				.addComponent(a_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddAnsLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(rdbtnIncorrect)
        				.addComponent(rdbtnCorrect))
        			.addGap(15)
        			.addGroup(AddAnsLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterans)
        				.addComponent(ans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
        			.addGroup(AddAnsLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(clear)
        				.addComponent(back)
        				.addComponent(add))
        			.addGap(34))
        );
        AddAnsLayout.linkSize(SwingConstants.VERTICAL, new Component[] {add, clear, back});
        AddAnsLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add, clear, back});
        panel.setLayout(AddAnsLayout);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(56)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(44)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(44, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        
        pack();
	}
	
	private void addActionPerformed(ActionEvent evt){
		String ans_string = ans.getText();
		String ans_id = a_id.getText();
		String c_or_not = group.getSelection().getActionCommand();
		
		if(ans_id.equals("")){
			new invalid_input(14).setVisible(true);
		}
		else{
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     Statement stmt1 = null;
			     //Statement stmt2 = null;
			     ResultSet rs_exists = null;
			     //ResultSet rs_instr = null;
			     int count_e = 0;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					//stmt2 = conn.createStatement();
					
					//rs_exists = stmt1.executeQuery("SELECT COUNT(*) FROM ASSESSMENTS WHERE AS_ID = '" + hw_id_string + "' AND C_TOKEN = '" + c_token + "'");
					
					while (rs_exists.next()){
						count_e = rs_exists.getInt(1);
						System.out.println(count_e);
					}
					
					if(count_e == 0){
						//stmt.executeUpdate("INSERT INTO STUDENTS (S_ID, S_PASS) VALUES ('" + unity_id + "', '" + String.valueOf(password) + "')");
						new add_success(5, p_id, c_token).setVisible(true);
						this.dispose();
					}
					else
						new invalid_input(15).setVisible(true);
			     } finally {
			    	    Constants.close(rs_exists);
			    	    //Constants.close(rs_instr);
			    	    Constants.close(stmt);
			    	    Constants.close(stmt1);
			    	    //Constants.close(stmt2);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		}
	}
	
	private void clearActionPerformed(ActionEvent evt){
		ans.setText("");
		a_id.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new add_answer(p_id, c_token).setVisible(true);
		this.dispose();
	}
}
