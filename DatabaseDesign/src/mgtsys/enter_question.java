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
public class enter_question extends JFrame {

	private JPanel panel = new JPanel();
	private JButton add = new JButton("Add Question");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	//private JTextField q_id = new JTextField();
	private JTextField q_text = new JTextField();
	private JTextField c_ans = new JTextField();
	private JTextField i_ans = new JTextField();
	//private JLabel enterqid = new JLabel("Enter question ID: ");
	private JLabel enterqtext = new JLabel("Enter question text: ");
	private JLabel enter_c_ans = new JLabel("Enter correct answers: ");
	private JLabel enter_i_ans = new JLabel("Enter incorrect answers: ");
	private JLabel ans_instr = new JLabel("  answers seperated by \";\"");
	private String p_id, c_token;
	private int t_id;
	
	public enter_question(String id, String c_token, int t_id){
		this.p_id = id;
		this.c_token = c_token;
		this.t_id = t_id;
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Add Question"));
        
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
        
        GroupLayout AddQLayout = new GroupLayout(panel);
        AddQLayout.setHorizontalGroup(
        	AddQLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddQLayout.createSequentialGroup()
        			.addGroup(AddQLayout.createParallelGroup(Alignment.LEADING)
        				//.addGroup(AddQLayout.createSequentialGroup()
        					//.addGap(140)
        					//.addComponent(enterqid)
        					//.addPreferredGap(ComponentPlacement.UNRELATED)
        					//.addComponent(q_id, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddQLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enterqtext)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(q_text, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddQLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enter_c_ans)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(c_ans, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        					.addComponent(ans_instr))
        				.addGroup(AddQLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enter_i_ans)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(i_ans, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddQLayout.createSequentialGroup()
        					.addGap(170)
        					.addGroup(AddQLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(AddQLayout.createSequentialGroup()
        							.addComponent(add, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(back)))))
        			.addContainerGap(47, Short.MAX_VALUE))
        );
        AddQLayout.setVerticalGroup(
        	AddQLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddQLayout.createSequentialGroup()
        			.addGap(35)
        			//.addGroup(AddQLayout.createParallelGroup(Alignment.BASELINE)
        				//.addComponent(enterqid)
        				//.addComponent(q_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			//.addGap(18)
        			.addGroup(AddQLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterqtext)
        				.addComponent(q_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddQLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enter_c_ans)
        				.addComponent(c_ans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(ans_instr))
        			.addGap(18)
        			.addGroup(AddQLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enter_i_ans)
        				.addComponent(i_ans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(AddQLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(add)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        AddQLayout.linkSize(SwingConstants.VERTICAL, new Component[] {add, clear, back});
        AddQLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add, clear, back});
        panel.setLayout(AddQLayout);
        
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
		//String q_id_string = q_id.getText();
		String q_text_string = q_text.getText();
		String c_ans_string[] = c_ans.getText().split(";");
		String i_ans_string[] = i_ans.getText().split(";");;
		
		/*if(q_id_string.equals("")){
			new invalid_input(14).setVisible(true);
		}*/
		//else{
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     Statement stmt1 = null;
			     Statement stmt2 = null;
			     //ResultSet rs_exists = null;
			     //ResultSet rs_instr = null;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					
					stmt.executeUpdate("INSERT INTO QUESTIONS (Q_ID, QUESTION_TEXT, T_ID) VALUES (test_seq.nextval, '" + q_text_string + "', " + t_id + ")");
					
					for(int i = 0; i < c_ans_string.length; i++){
						stmt1.executeUpdate("INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID) VALUES (test_seq.nextval, '" + c_ans_string[i] + "', 1, 1, " + t_id + ")");
					}
					
					for(int i = 0; i < i_ans_string.length; i++){
						stmt2.executeUpdate("INSERT INTO ANSWERS (A_ID, ANSWER_TEXT, IS_CORRECT, Q_ID, T_ID) VALUES (test_seq.nextval, '" + c_ans_string[i] + "', 0, 1, " + t_id + ")");
					}
						
					new add_success(4, p_id, c_token).setVisible(true);
					this.dispose();

			     } finally {
			    	    //Constants.close(rs_exists);
			    	    //Constants.close(rs_instr);
			    	    Constants.close(stmt);
			    	    Constants.close(stmt1);
			    	    Constants.close(stmt2);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		//}
	}
	
	private void clearActionPerformed(ActionEvent evt){
		//q_id.setText("");
		q_text.setText("");
		c_ans.setText("");
		i_ans.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new add_question(p_id, c_token).setVisible(true);
		this.dispose();
	}
}
