/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
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
public class add_homework extends JFrame {
	
	private JPanel panel = new JPanel();
	private JButton add = new JButton("Add Homework");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	//private JTextField hw_id = new JTextField();
	private JTextField start = new JTextField();
	private JTextField end = new JTextField();
	private JTextField num_att = new JTextField();
	private JTextField selection = new JTextField();
	private JTextField q_id = new JTextField();
	private JTextField c_pts = new JTextField();
	private JTextField i_pts = new JTextField();
	//private JLabel enterhwid = new JLabel("Homework ID:         ");
	//private JLabel hwidexp = new JLabel("should be an integer");
	private JLabel enterstart = new JLabel("Start date:          ");
	private JLabel startexp = new JLabel("e.g. 2010-05-23");
	private JLabel enterend = new JLabel("End date:          ");
	private JLabel enternumatt = new JLabel("Number of attempts:           ");
	private JLabel enterselection = new JLabel("Score selection scheme: ");
	private JLabel selectexp = new JLabel("0: latest; 1: maximum; 2: average; 3: first;");
	private JLabel enterquestion = new JLabel("Question numbers: ");
	private JLabel q_exp = new JLabel("e.g. 0, 1, 2, 3");
	private JLabel entercpts = new JLabel("Correct answer points:     ");
	private JLabel enteripts = new JLabel("Incorrect answer points:  ");
	private String id, c_token;
	
	public add_homework(String id, String c_token){
		this.id = id;
		this.c_token = c_token;
		
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createTitledBorder("Add Homework"));
        
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
        
        GroupLayout AddHwLayout = new GroupLayout(panel);
        AddHwLayout.setHorizontalGroup(
        	AddHwLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddHwLayout.createSequentialGroup()
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(AddHwLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(entercpts)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(c_pts, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddHwLayout.createSequentialGroup()
        					.addGap(140)
        					.addComponent(enteripts)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(i_pts, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        				.addGroup(AddHwLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        						//.addComponent(enterhwid)
        						.addComponent(enterstart)
        						.addComponent(enterend)
        						.addComponent(enternumatt))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(num_att, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        						.addComponent(end, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        						.addGroup(AddHwLayout.createSequentialGroup()
        							.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        								//.addComponent(hw_id, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        								.addComponent(start, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        							.addGap(36)
        							.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(startexp)
        								//.addComponent(hwidexp)
        								.addComponent(selectexp)
        								.addComponent(q_exp)))))
        				.addGroup(AddHwLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(enterselection)
        						.addComponent(enterquestion))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(q_id, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        						.addComponent(selection, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(AddHwLayout.createSequentialGroup()
        					.addGap(170)
        					.addGroup(AddHwLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(AddHwLayout.createSequentialGroup()
        							.addComponent(add, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(clear, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(back)))))
        			.addContainerGap(47, Short.MAX_VALUE))
        );
        AddHwLayout.setVerticalGroup(
        	AddHwLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(AddHwLayout.createSequentialGroup()
        			.addGap(35)
        			//.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				//.addComponent(enterhwid)
        				//.addComponent(hw_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				//.addComponent(hwidexp))
        			//.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterstart)
        				.addComponent(start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(startexp))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterend)
        				.addComponent(end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enternumatt)
        				.addComponent(num_att, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterselection)
        				.addComponent(selection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(selectexp))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterquestion)
        				.addComponent(q_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(q_exp))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(entercpts)
        				.addComponent(c_pts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enteripts)
        				.addComponent(i_pts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(AddHwLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(add)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        AddHwLayout.linkSize(SwingConstants.VERTICAL, new Component[] {add, clear, back});
        AddHwLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add, clear, back});
        panel.setLayout(AddHwLayout);
        
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
	
	private java.sql.Date convertToDate(String input) {
		
		java.sql.Date sqlDate = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			java.util.Date date = format.parse(input);
			sqlDate = new java.sql.Date(date.getTime()); 
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
		
		return sqlDate;
	}
	
	private void addActionPerformed(ActionEvent evt){
		//String hw_id_string = hw_id.getText();
		String start_string = start.getText();
		String end_string = end.getText();
		String att_string = num_att.getText();
		String select_string = selection.getText();
		String qid_string = q_id.getText();
		String cpts_string = c_pts.getText();
		String ipts_string = i_pts.getText();
		
		//if(hw_id_string.equals("")){
			//new invalid_input(11).setVisible(true);
		//}
		//else{
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt = null;
			     //Statement stmt1 = null;
			     //Statement stmt2 = null;
			     //ResultSet rs_exists = null;
			     //ResultSet rs_instr = null;
			     int count_e = 0;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					//stmt1 = conn.createStatement();
					//stmt2 = conn.createStatement();
					
					//rs_exists = stmt1.executeQuery("SELECT COUNT(*) FROM ASSESSMENTS WHERE AS_ID = '" + hw_id_string + "' AND C_TOKEN = '" + c_token + "'");
					
					/*while (rs_exists.next()){
						count_e = rs_exists.getInt(1);
						System.out.println(count_e);
					}*/
					
					if(count_e == 0){
						stmt.executeUpdate("INSERT INTO ASSESSMENTS (AS_ID, RETRIES, AS_START, AS_END, PTS_CORRECT, PTS_INCORRECT, METHOD, C_TOKEN) " +
								"VALUES (test_seq.nextval," + Integer.parseInt(att_string) + ", " + convertToDate(start_string) + ", " + convertToDate(end_string) + 
								", " + Integer.parseInt(cpts_string) + ", " + Integer.parseInt(ipts_string) + ", '" + select_string + "', '" + c_token + "')");
						new add_success(3, id, c_token).setVisible(true);
						this.dispose();
					}
					else
						new invalid_input(12).setVisible(true);
			     } finally {
			    	    //Constants.close(rs_exists);
			    	    //Constants.close(rs_instr);
			    	    Constants.close(stmt);
			    	    //Constants.close(stmt1);
			    	    //Constants.close(stmt2);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	           oops.printStackTrace();
	        }
		}
	//}
	
	private void clearActionPerformed(ActionEvent evt){
		start.setText("");
		end.setText("");
		num_att.setText("");
		selection.setText("");
		q_id.setText("");
		c_pts.setText("");
		i_pts.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new prof_course_option(id, c_token).setVisible(true);
		this.dispose();
	}
}
