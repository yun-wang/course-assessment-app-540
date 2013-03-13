/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class view_submission extends JFrame {

	private String s_id, token;
	private int hw_id, at_id;
	private JPanel panel = new JPanel();
	private List<Questions> questions = new ArrayList<Questions>();
	private int length = 500;
	private int height = 500;
	
	public view_submission(String id, String token, int hw, int at){
		this.s_id = id;
		this.token = token;
		this.hw_id = hw;
		this.at_id = at;
		
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
				 * add to questions
				 */
				//rs = stmt.executeQuery("SELECT C.C_ID, C.C_NAME FROM TAKES T, COURSES C WHERE T.S_ID = '" + id + 
						//"' AND T.C_TOKEN = C.C_TOKEN");
				
				/*while (rs.next()){
					String c_id = rs.getString("C_ID");
					String c_name = rs.getString("C_NAME");
					course_id.add(c_id);
					course_name.add(c_name);
				}*/
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(stmt);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
			oops.printStackTrace();
        }
		
		//test only
		/*Questions Q1 = new Questions();
		Questions Q2 = new Questions();
		Q1.SetQText("Question 1 aosieanfaiuehfaifnsmnfasife");
		Q1.SetAnswers(0, "correct", "sjfiwejfn", "sdkjfaoief", true, false);
		Q1.SetAnswers(3, "incorrect", "sjfiwejfn", "sdkjfaoief", false, true);
		Q1.SetAnswers(4, "incorrect", "sjfiwejfn", "sdkjfaoief", false, false);
		Q1.SetAnswers(5, "incorrect", "sjfiwejfn", "sdkjfaoief", false, false);
		Q1.SetQID(0);
		Q2.SetQText("Question 2 short question");
		Q2.SetAnswers(3, "incorrect", "sjfiwejfn", "sdkjfaoief", false, false);
		Q2.SetAnswers(4, "incorrect", "sjfiwejfn", "sdkjfaoief", false, false);
		Q2.SetAnswers(2, "correct", "sjfiwejfn", "sdkjfaoief", true, true);
		Q2.SetAnswers(8, "incorrect", "sjfiwejfn", "sdkjfaoief", false, false);
		Q2.SetQID(1);
		questions.add(Q1);
		questions.add(Q2);*/
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout SubmittedLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(SubmittedLayout);
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		String text = "HW" + hw_id + " Attempt" + at_id;
		JLabel title = new JLabel(text);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(title);
        
		for(int i = 0; i < questions.size(); i++){
			addAQuestion(i, getContentPane(), questions.get(i));
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
	
	private void addAQuestion(int i, Container container, Questions que){
		
		List <Answers> temp = new ArrayList<Answers>();
		
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(1, 4));
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel1.setBackground(Constants.color);
        panel2.setBackground(Constants.color);
        panel3.setBackground(Constants.color);
        panel4.setBackground(Constants.color);
        panel5.setBackground(Constants.color);
		JLabel ques_title = new JLabel(que.GetQText());
		panel1.add(ques_title);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(panel1);
        
        temp = questions.get(i).GetAnswers();
        JLabel select = new JLabel();
        JLabel correct = new JLabel();
        JLabel exp = new JLabel();
        select.setBackground(Constants.color);
        correct.setBackground(Constants.color);
        exp.setBackground(Constants.color);
        
        for(int j = 0; j < temp.size(); j++){
        	JLabel choice = new JLabel("      " + (j+1) + ") " + temp.get(j).GetAnsText());
        	if(temp.get(j).GetIsSelected())
        		select.setText("Your answer: " + (j+1));
        	
        	if(temp.get(j).GetIsCorrect()){
        		if(temp.get(j).GetIsSelected())
        			correct.setText("Correct: Yes");
        		else
        			correct.setText("Correct: No");
        		exp.setText("Explanation: " + temp.get(j).GetAnsExplanation());
        	}
        	
            choice.setBackground(Constants.color);
            panel2.add(choice);
        }
        
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(select);
        panel4.add(correct);
        panel5.add(exp);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
        container.add(panel5);
        
        getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
        
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new view_past(s_id, token).setVisible(true);
			dispose();
		}
	}
}
