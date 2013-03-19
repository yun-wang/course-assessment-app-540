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
public class view_score extends JFrame {
	
	private String id, token;
	private JPanel panel = new JPanel();
	private ArrayList<Integer> hw_num = new ArrayList<Integer>();
	private ArrayList<Integer> at_num = new ArrayList<Integer>();
	private ArrayList<Double> pts = new ArrayList<Double>();
	private ArrayList<Date> sub_time = new ArrayList<Date>();
	private int length = 500;
	private int height = 300;
	
	public view_score(String id, String token){
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
				 * add to hw_num, at_num, pts, and sub_time
				 */
				rs = stmt.executeQuery("Select as_id, at_id, SUM(pts) AS total_pts," +
				 "submission_time FROM (Select Attempts.submission_time, " + 
			     "AttemptQuestions.as_id, AttemptQuestions.at_id, CASE WHEN " + 
				 "is_correct = 1 THEN Assessments.pts_correct ELSE " + 
			     "Assessments.pts_incorrect * -1 END AS pts FROM " +
			     "AttemptQuestions JOIN Answers ON AttemptQuestions.a_id = " +
			     "Answers.a_id JOIN Assessments ON AttemptQuestions.as_id = " +
			     "Assessments.as_id JOIN Attempts ON Attempts.at_id = " +
			     "AttemptQuestions.at_id WHERE attempts.s_id = '" + id +
			     "' AND AttemptQuestions.c_t = '" + token + "') A GROUP BY " +
			     "as_id, at_id, submission_time ORDER BY submission_time");

				while (rs.next()){
					int as_id = rs.getInt("AS_ID");
					int at_id = rs.getInt("AT_ID");
					Date time = rs.getTimestamp("SUBMISSION_TIME");
					double tot_pts = rs.getDouble("TOTAL_PTS"); 
					hw_num.add(as_id);
					at_num.add(at_id);
					sub_time.add(time);
					pts.add(tot_pts);
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
		hw_num.add(1);
		sub_time.add(currenttime);
		sub_time.add(currenttime);
		at_num.add(1);
		at_num.add(2);
		pts.add(20.00);
		pts.add(40.00);*/
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Constants.color);
		setPreferredSize(new Dimension(length, height));
		setContentPane(panel);
		
		BoxLayout ScoreLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(ScoreLayout);
		
		for(int i = 0; i < hw_num.size(); i++){
			//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
			String part1 = "HW"+hw_num.get(i);
			String part2 = "Attempt "+at_num.get(i);
			String part3 = pts.get(i)+"";
			String part4 = sub_time.get(i)+"";
			String text = part1 + "          " + part2 + "           " + part3 + "           " + part4;
			addALabel(text, getContentPane());
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
	
	private void addALabel(String text, Container container){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
		JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
        //label.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        //label.setPreferredSize(new Dimension(200, 78));
	}
    
    public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new stdnt_course_option(id, token).setVisible(true);
			dispose();
		}
	}
}
