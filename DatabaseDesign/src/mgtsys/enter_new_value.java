/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
public class enter_new_value extends JFrame {

	private JPanel panel = new JPanel();
	private JButton update = new JButton("Update");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");
	private JTextField input = new JTextField();
	private JLabel enterinput = new JLabel();
	private JLabel inputexp = new JLabel();
	private JLabel old_value = new JLabel();
	private JLabel old_value_intro = new JLabel("Old value: ");
	private String id, c_token;
	private int hw_id, type;
	List <Integer> q_id = new ArrayList<Integer>();
	
	public enter_new_value(String id, String c_token, int hw, int type){
		this.id = id;
		this.c_token = c_token;
		this.hw_id = hw;
		this.type = type;
		
		if(type == 0){  //start date
			enterinput.setText("Enter new start date: ");
			inputexp.setText("   e.g. 2010-05-23");
		}
		else if(type == 1){  //end date
			enterinput.setText("Enter new end date: ");
			inputexp.setText("   e.g. 2010-05-23");
		}
		else if(type == 2){  //number of attempts
			enterinput.setText("Enter new number of attempts: ");
			inputexp.setText("   should be an integer");
		}
		else if(type == 3){  //score selection schema
			enterinput.setText("Enter new score selection method: ");
			inputexp.setText("   0: latest; 1: maximum; 2: average; 3: first");
		}
		else if(type == 4){  //question numbers
			enterinput.setText("Enter new question numbers: ");
			inputexp.setText("   e.g. 0, 1, 2, 3");
		}
		else if(type == 5){  //correct answer points
			enterinput.setText("Enter new correct answer points: ");
		}
		else if(type == 6){  //incorrect answer points
			enterinput.setText("Enter new incorrect answer points: ");
		}
		
		old_value.setText(fetchResult(type));
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Enter New Value"));
		
		update.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		updateActionPerformed(evt);
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
        
        GroupLayout NewValueLayout = new GroupLayout(panel);
        NewValueLayout.setHorizontalGroup(
        	NewValueLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(NewValueLayout.createSequentialGroup()
        			.addGroup(NewValueLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(NewValueLayout.createSequentialGroup()
                			.addGap(50)
                			.addComponent(old_value_intro)
                			.addPreferredGap(ComponentPlacement.UNRELATED)
                			.addComponent(old_value))
        				.addGroup(NewValueLayout.createSequentialGroup()
        					.addGap(50)
        					.addComponent(enterinput)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(input, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        					.addComponent(inputexp))
        				.addGroup(NewValueLayout.createSequentialGroup()
        					.addGap(140)
        					.addGroup(NewValueLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(NewValueLayout.createSequentialGroup()
        							.addComponent(update, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        							.addGap(24)
        							.addComponent(clear)
        							.addGap(24)
        							.addComponent(back)))))
        			.addContainerGap(47, Short.MAX_VALUE))
        );
        NewValueLayout.setVerticalGroup(
        	NewValueLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(NewValueLayout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(NewValueLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(old_value_intro)
        				.addComponent(old_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(NewValueLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(enterinput)
        				.addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(inputexp))
        			.addGap(18)
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(NewValueLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(update)
        				.addComponent(clear)
        				.addComponent(back))
        			.addGap(34))
        );
        NewValueLayout.linkSize(SwingConstants.VERTICAL, new Component[] {update, clear, back});
        NewValueLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {update, clear, back});
        panel.setLayout(NewValueLayout);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.CENTER)
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
	
	private String fetchResult(int type){
		String result = null;
		Date start = null, end = null;
		int retries = 0, cpts = 0, ipts = 0;
		String method = null;
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
		     Statement stmt = null;
		     Statement stmt1 = null;
		     //Statement stmt2 = null;
		     ResultSet rs_ass = null;
		     ResultSet rs_que = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt = conn.createStatement();
				stmt1 = conn.createStatement();
				//stmt2 = conn.createStatement();
				
				rs_ass = stmt.executeQuery("SELECT AS_START, AS_END, RETRIES, METHOD, PTS_CORRECT, PTS_INCORRECT FROM ASSESSMENTS " +
						"WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
				
				while (rs_ass.next()){
					start = rs_ass.getDate("AS_START");
					end = rs_ass.getDate("AS_END");
					retries = rs_ass.getInt("RETRIES");
					method = rs_ass.getString("METHOD");
					cpts = rs_ass.getInt("PTS_CORRECT");
					ipts = rs_ass.getInt("PTS_INCORRECT");
				}
				
				rs_que = stmt1.executeQuery("SELECT Q_ID FROM ASSESSMENTHAS WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
				
				while (rs_que.next()){
					q_id.add(rs_que.getInt(1));
				}
				
		     } finally {
		    	    Constants.close(rs_ass);
		    	    Constants.close(rs_que);
		    	    Constants.close(stmt);
		    	    Constants.close(stmt1);
		    	    //Constants.close(stmt2);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
          oops.printStackTrace();
       }
		
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		if(type == 0){  //start date
			result = df.format(start);
		}
		else if(type == 1){  //end date
			result = df.format(end);
		}
		else if(type == 2){  //number of attempts
			result = Integer.toString(retries);
		}
		else if(type == 3){  //score selection schema
			result = method;
		}
		else if(type == 4){  //question numbers
			for (int i : q_id){
			    result += i + "; ";
			}
		}
		else if(type == 5){  //correct answer points
			result = Integer.toString(cpts);
		}
		else if(type == 6){  //incorrect answer points
			result = Integer.toString(ipts);
		}
		
		return result;
	}
	
	private void updateActionPerformed(ActionEvent evt){
		String input_string = input.getText();
		
		if(input_string.equals("")){
			new invalid_input(13).setVisible(true);
		}
		else{
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
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt = conn.createStatement();
					//stmt1 = conn.createStatement();
					//stmt2 = conn.createStatement();
					
					if(type == 0){  //start date
						stmt.executeUpdate("UPDATE ASSESSMENTS SET AS_START = '" + new SimpleDateFormat("YYYY-MM-DD").parse(input_string) +
								"' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					else if(type == 1){  //end date
						stmt.executeUpdate("UPDATE ASSESSMENTS SET AS_END = '" + new SimpleDateFormat("YYYY-MM-DD").parse(input_string) + 
								"'' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					else if(type == 2){  //number of attempts
						stmt.executeUpdate("UPDATE ASSESSMENTS SET RETRIES = '" + Integer.parseInt(input_string) + 
								"'' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					else if(type == 3){  //score selection schema
						stmt.executeUpdate("UPDATE ASSESSMENTS SET METHOD = '" + input_string +
								"'' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					else if(type == 4){  //question numbers
						String question[] = input_string.split(",");
						for(int i = 0; i < question.length; i++){
							stmt.executeUpdate("UPDATE ASSESSMENTHAS SET Q_ID = '" + Integer.parseInt(question[i]) + 
									"' ' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
						}
					}
					else if(type == 5){  //correct answer points
						stmt.executeUpdate("UPDATE ASSESSMENTS SET PTS_CORRECT = '" + Integer.parseInt(input_string) +
								"'' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					else if(type == 6){  //incorrect answer points
						stmt.executeUpdate("UPDATE ASSESSMENTS SET PTS_INCORRECT = '" + Integer.parseInt(input_string) +
								"'' WHERE AS_ID = " + hw_id + "AND C_TOKEN = '" + c_token);
					}
					
					new add_success(6, id, c_token).setVisible(true);
					this.dispose();
						
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
	}
	
	private void clearActionPerformed(ActionEvent evt){
		input.setText("");
	}
	
	private void backActionPerformed(ActionEvent evt){
		new edit_specific_hw(id, c_token, hw_id).setVisible(true);
		this.dispose();
	}
}
