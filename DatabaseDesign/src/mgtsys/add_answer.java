/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
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
public class add_answer extends JFrame {

	private String id, token;
	private JPanel panel = new JPanel();
	private List<Topics> topics = new ArrayList<Topics>();
	private int length = 500;
	private int height = 500;
	
	public add_answer(String id, String c_token){
		this.id = id;
		this.token = c_token;
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
		     Statement stmt = null;
		     Statement stmt1 = null;
		     ResultSet rs_topic = null;
		     ResultSet rs_que = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt = conn.createStatement();
				stmt1 = conn.createStatement();
				
				/*
				 * add to topics
				 */
				rs_topic = stmt.executeQuery("SELECT TOPICS.T_ID, T_NAME FROM TOPICS JOIN COURSECONSISTS " +
						"ON TOPICS.T_ID = COURSECONSISTS.T_ID WHERE C_TOKEN = '" + token + "'");
				
				while (rs_topic.next()){
					Topics t = new Topics();
					int t_id = rs_topic.getInt("T_ID");
					String t_name = rs_topic.getString("T_NAME");
					t.SetTID(t_id);
					t.SetTName(t_name);
					rs_que = stmt1.executeQuery("SELECT Q_ID, QUESTION_TEXT FROM QUESTIONS WHERE T_ID = " + t_id);
					while (rs_que.next()){
						int q_id = rs_que.getInt("Q_ID");
						String q_text = rs_que.getString("QUESTION_TEXT");
						t.SetQuestions(q_id, q_text);
					}
					topics.add(t);
				}
		     } finally {
		    	    Constants.close(rs_topic);
		    	    Constants.close(rs_que);
		    	    Constants.close(stmt);
		    	    Constants.close(stmt1);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
        oops.printStackTrace();
        }
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout AddAnsLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(AddAnsLayout);
        
		for(int i = 0; i < topics.size(); i++){
			AddATopic(i, getContentPane(), topics.get(i));
		}
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JPanel buttons = new JPanel();
		buttons.setBackground(Constants.color);
		JButton back = new JButton("Back");
		back.setMinimumSize(new Dimension(134, 30));
		back.setPreferredSize(new Dimension(134, 30));
		back.setMaximumSize(new Dimension(134, 30));
		back.addActionListener(new BackListener());
		buttons.add(back);
		buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(buttons);
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
        
		pack();
	}
	
	public void AddATopic(int i, Container container, Topics topic){
		
		List <Questions> temp = new ArrayList<Questions>();
		
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        String s = "Topic" + topic.GetTID() + " " + topic.GetTName();
		JLabel title = new JLabel(s);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(title);
        
        temp = topic.GetQuestions();
        
        for(int j = 0; j < temp.size(); j++){
			String part1 = "Q" + temp.get(j).GetQID() + " ";
			String part2 = temp.get(j).GetQText();
			String text = part1 + " - " + part2;
			addAButton(text, topic.GetTID(), temp.get(j).GetQID(), container);
		}
	}
	
	private void addAButton(String text, int t_id, int q_id, Container container){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
		JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 78));
        button.addActionListener(new ButtonListener(t_id, q_id));
	}
    
    public class ButtonListener implements ActionListener{
    	private int topic_id, ques_id;
    	public ButtonListener(int x, int y){
    		this.topic_id = x;
    		this.ques_id = y;
    	}
		public void actionPerformed(ActionEvent InstrEvent){
			new enter_answer(id, token, topic_id, ques_id).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new prof_course_option(id, token).setVisible(true);
			dispose();
		}
	}
}
