/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class view_attempts extends JFrame {
	
	private String id, token;
	private JPanel panel = new JPanel();
	private ArrayList<Integer> hw_num = new ArrayList<Integer>();
	private ArrayList<Date> due_time = new ArrayList<Date>();
	private ArrayList<Integer> retry_left = new ArrayList<Integer>();
	private int length = 500;
	private int height = 300;
	
	public view_attempts(String id, String token){
		this.id = id;
		this.token = token;
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
		     Statement stmt = null;
		     ResultSet rs = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt = conn.createStatement();
				
				/*
				 * add to hw_num, due_time, and retry_left
				 */
				rs = stmt.executeQuery("SELECT B.AS_ID, AS_END, CASE WHEN ATTEMPTS != NULL THEN RETRIES - ATTEMPTS " +
						"ELSE RETRIES END AS ATTEMPTS_LEFT FROM (SELECT COUNT(AS_ID) AS ATTEMPTS, AS_ID FROM ATTEMPTS " +
						"WHERE S_ID = '" + id + "' GROUP BY AS_ID) A RIGHT OUTER JOIN (SELECT RETRIES, AS_ID, AS_END FROM " +
								"ASSESSMENTS WHERE c_token = '" + token + "') B ON A.as_id = B.as_id ");
				
				while (rs.next()){
					int hw_id = rs.getInt("AS_ID");
					Date due = rs.getDate("AS_END");
					int retries = rs.getInt("ATTEMPTS_LEFT");
					hw_num.add(hw_id);
					due_time.add(due);
					retry_left.add(retries);
				}
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(stmt);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
          oops.printStackTrace();
        }
		
		//test only
		/*Date currenttime = new Date(0);
		hw_num.add(1);
		hw_num.add(2);
		due_time.add(currenttime);
		due_time.add(currenttime);
		retry_left.add(3);
		retry_left.add(0);*/
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Constants.color);
		setPreferredSize(new Dimension(length, height));
		setContentPane(panel);
		
		BoxLayout AttemptLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(AttemptLayout);
		
		for(int i = 0; i < hw_num.size(); i++){
			//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
			String part1 = "HW"+hw_num.get(i);
			String part2 = due_time.get(i)+"";
			int retry = retry_left.get(i);
			String text = part1 + "        Due: " + part2;
			addARow(text, retry, getContentPane(), hw_num.get(i), retry);
		}
		
		//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JButton back = new JButton("Back");
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(back);
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		back.setMinimumSize(new Dimension(134, 30));
		back.setPreferredSize(new Dimension(134, 30));
		back.setMaximumSize(new Dimension(134, 30));
        back.addActionListener(new BackListener());
        
		pack();
	}
	
	private void addARow(String text, int retry, Container container, int hw, int left){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        JPanel sub_panel = new JPanel();
        sub_panel.setBackground(Constants.color);
		JLabel label = new JLabel(text);
		sub_panel.add(label);
		if(retry >= 1){
			JLabel label2 = new JLabel("    ");
			sub_panel.add(label2);
			JButton button = new JButton("Open Homework");
			button.addActionListener(new OpenListener(hw, left));
			sub_panel.add(button);
		}
		else{
			JLabel label2 = new JLabel("            ");
			sub_panel.add(label2);
			JLabel label1 = new JLabel("No more attempts");
			sub_panel.add(label1);
		}
        sub_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
        container.add(sub_panel);
	}
	
	public class OpenListener implements ActionListener{
		int hw, left;
		public OpenListener(int hw, int left){
			this.hw = hw;
			this.left = hw;
		}
		public void actionPerformed(ActionEvent AsstEvent){
			new view_questions(id, token, hw, left).setVisible(true);
			dispose();
		}
	}
    
    public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new stdnt_course_option(id, token).setVisible(true);
			dispose();
		}
	}
}
