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
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
public class view_past extends JFrame {
	
	private String id, token;
	private JPanel panel = new JPanel();
	private List<Homeworks> hw = new ArrayList<Homeworks>();
	private List<Integer> hw_num = new ArrayList<Integer>();
	private List<Date> due_time = new ArrayList<Date>();
	private List<Integer> at_num = new ArrayList<Integer>();
	private List<Integer> scores = new ArrayList<Integer>();
	private int length = 500;
	private int height = 500;
	
	public view_past(String id, String token){
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
				 * add to hw
				 */
				String s = "SELECT AS_ID, AS_END, AT_ID, SUM(PTS) FROM (SELECT ATTEMPTQUESTIONS.AS_ID, " +
						"ATTEMPTQUESTIONS.AT_ID, Assessments.AS_END, CASE WHEN IS_CORRECT = 1 THEN ASSESSMENTS.PTS_CORRECT ELSE " +
						"ASSESSMENTS.PTS_INCORRECT * -1 END AS PTS FROM ATTEMPTQUESTIONS JOIN ANSWERS " +
						"ON ATTEMPTQUESTIONS.A_ID = ANSWERS.A_ID JOIN ASSESSMENTS ON ATTEMPTQUESTIONS.AS_ID = ASSESSMENTS.AS_ID " +
						"WHERE S_ID = '" + id + "' AND ATTEMPTQUESTIONS.C_T = '" + token + "') A GROUP BY AS_ID, AT_ID, AS_END";
				rs = stmt.executeQuery(s);
				
				while (rs.next()){
					int hw_id = rs.getInt("AS_ID");
					int at_id = rs.getInt("AT_ID");
					Date due = rs.getDate("AS_END");
					int pts = rs.getInt("SUM(PTS)");
					hw_num.add(hw_id);
					due_time.add(due);
					at_num.add(at_id);
					scores.add(pts);
				}
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(stmt);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
          oops.printStackTrace();
        }
		
		int i = 0;
		while(i < hw_num.size()){
			boolean found = false;
			int foundIndex = -1;
			for (int j = 0; j < hw.size(); j++) {
				if (hw.get(j).GetHWID() == hw_num.get(i)) {
					found = true;
					foundIndex = j;
					break;
				}
			}
			
			if (!found){
				Homeworks homework = new Homeworks();
				homework.SetHWID(hw_num.get(i));
				homework.SetEnd(due_time.get(i));
				homework.SetAttempts(at_num.get(i), scores.get(i));
				
				hw.add(homework);
			} else {
				hw.get(foundIndex).SetAttempts(at_num.get(i), scores.get(i));
			}
			i = i+1;
		}
		
		//test only
		/*Homeworks HW1 = new Homeworks();
		HW1.SetHWID(1);
		HW1.SetAttempts(1, 100);
		HW1.SetAttempts(2, 70);
		Calendar today = Calendar.getInstance();  
		today.add(Calendar.DATE, -1);  
		Date yesterday = new Date(today.getTimeInMillis());
		HW1.SetEnd(yesterday);
		Homeworks HW2 = new Homeworks();
		HW2.SetHWID(2);
		HW2.SetAttempts(1, 1000);
		HW2.SetAttempts(2, 700);
		Calendar today1 = Calendar.getInstance();  
		today1.add(Calendar.DATE, +1);  
		Date tomorrow = new Date(today1.getTimeInMillis());
		HW2.SetEnd(tomorrow);
		hw.add(HW1);
		hw.add(HW2);*/
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout PastLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(PastLayout);
		
		List<Homeworks> hw_before = new ArrayList<Homeworks>();
		List<Homeworks> hw_now = new ArrayList<Homeworks>();
		
		Date temp = new Date();
		
		for(int i = 0; i < hw.size(); i++){
			if(temp.compareTo(hw.get(i).GetEnd()) <= 0)
				hw_now.add(hw.get(i));
			else
				hw_before.add(hw.get(i));
		}
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JLabel label1 = new JLabel("Homework past due date:");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(label1);
		
		for(int i = 0; i < hw_before.size(); i++){
			String part1 = "HW"+hw_before.get(i).GetHWID();
			//System.out.println(part1);
			for(int j = 0; j < hw_before.get(i).GetAttempts().size(); j++){
				String part2 = "Attempt "+hw_before.get(i).GetAttempts().get(j).GetHWID()+"     "+hw_before.get(i).GetAttempts().get(j).GetScore();
				//System.out.println(part2);
				String text = part1 + "     " + part2;
				addARow(text, getContentPane(), hw_before.get(i).GetHWID(), hw_before.get(i).GetAttempts().get(j).GetHWID());
			}
		}
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JLabel label2 = new JLabel("Homework within due date:");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(label2);
		
		for(int i = 0; i < hw_now.size(); i++){
			String part1 = "HW"+hw_now.get(i).GetHWID();
			//System.out.println(part1);
			for(int j = 0; j < hw_now.get(i).GetAttempts().size(); j++){
				String part2 = "Attempt "+hw_now.get(i).GetAttempts().get(j).GetHWID()+"     "+hw_now.get(i).GetAttempts().get(j).GetScore();
				//System.out.println(part2);
				String text = part1 + "     " + part2;
				addARow(text, getContentPane(), hw_now.get(i).GetHWID(), hw_now.get(i).GetAttempts().get(j).GetHWID());
			}
		}
		
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
	
	private void addARow(String text, Container container, int hw_id, int at_id){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());

        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(260, 40));
        button.setMaximumSize(new Dimension(260, 40));
        button.setPreferredSize(new Dimension(260, 40));
        button.addActionListener(new ViewListener(hw_id, at_id));
        container.add(button);
	}
	
	public class ViewListener implements ActionListener{
		private int hw_id, at_id;
		public ViewListener(int hw_id, int at_id){
			this.hw_id = hw_id;
			this.at_id = at_id;
		}
		public void actionPerformed(ActionEvent AsstEvent){
			new view_submission(id, token, hw_id, at_id).setVisible(true);
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
