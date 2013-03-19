package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class view_questions extends JFrame {

	private String id, token;
	private int hw_num, at_num;
	private int seed = (int)(Math.random()*100);
	private JPanel panel = new JPanel();
	private List<Questions> questions = new ArrayList<Questions>();
	private List<ButtonGroup> button_group = new ArrayList<ButtonGroup>();
	private List<String> justifications = new ArrayList<String> ();
	private int length = 500;
	private int height = 500;
	
	public view_questions(String id, String token, int hw, int at) {
		this.id = id;
		this.token = token;
		this.hw_num = hw;
		this.at_num = at;
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
		     Statement stmt1 = null;
		     Statement stmt2 = null;
		     ResultSet rs_q = null;
		     ResultSet rs_a = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt1 = conn.createStatement();
				stmt2 = conn.createStatement();
				
				/*
				 * add to questions
				 */
				rs_q = stmt1.executeQuery("SELECT QUESTIONS.Q_ID, QUESTION_TEXT FROM ASSESSMENTHAS JOIN " +
						"QUESTIONS ON ASSESSMENTHAS.Q_ID = QUESTIONS.Q_ID WHERE AS_ID = '" + hw_num + "'");
				
				while (rs_q.next()){
					Questions q = new Questions();
					String q_text = rs_q.getString("QUESTION_TEXT");
					int q_id = rs_q.getInt("Q_ID");
					q.SetSeed(seed);
					q.SetQID(q_id);
					q.SetQText(q_text);
					rs_a = stmt2.executeQuery("SELECT A_ID, ANSWER_TEXT, EXPLANATION, HINT, IS_CORRECT FROM " +
							"ANSWERS WHERE Q_ID = '" + q_id + "'");
					while(rs_a.next()){
						int ans_id = rs_a.getInt("A_ID");
						String ans_text = rs_a.getString("ANSWER_TEXT");
						String ans_exp = rs_a.getString("EXPLANATION");
						String ans_hint = rs_a.getString("HINT");
						int is_corr = rs_a.getInt("IS_CORRECT");
						boolean is_correct;
						if(is_corr == 0)
							is_correct = false;
						else
							is_correct = true;
						q.SetAnswers(ans_id, ans_text, ans_exp, ans_hint, is_correct);
					}
					questions.add(q);
				}
		     } finally {
		    	    Constants.close(rs_q);
		    	    Constants.close(rs_a);
		    	    Constants.close(stmt1);
		    	    Constants.close(stmt2);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
         oops.printStackTrace();
        }
		
		//test only
		/*Questions Q1 = new Questions();
		Questions Q2 = new Questions();
		Q1.SetQText("Question 1 aosieanfaiuehfaifnsmnfasife");
		Q1.SetSeed(seed);
		Q1.SetAnswers(0, "lamp", "sjfiwejfn", "sdkjfaoief", true);
		Q1.SetAnswers(1, "code", "sjfiwejfn", "sdkjfaoief", true);
		Q1.SetAnswers(2, "online", "sjfiwejfn", "sdkjfaoief", true);
		Q1.SetAnswers(3, "loooooooooooooooog", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetAnswers(4, "process", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetAnswers(5, "tired", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetAnswers(6, "use", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetAnswers(7, "time", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetAnswers(8, "late", "sjfiwejfn", "sdkjfaoief", false);
		Q1.SetQID(0);
		Q2.SetQText("Question 2 short question");
		Q2.SetSeed(seed);
		Q2.SetAnswers(0, "x<1", "sjfiwejfn", "sdkjfaoief", true);
		Q2.SetAnswers(1, "1<x<2", "sjfiwejfn", "sdkjfaoief", true);
		Q2.SetAnswers(2, "2<x<3", "sjfiwejfn", "sdkjfaoief", true);
		Q2.SetAnswers(3, "3<x<4", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetAnswers(4, "4<x<5", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetAnswers(5, "5<x<6", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetAnswers(6, "6<x<7", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetAnswers(7, "7<x<8", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetAnswers(8, "8<x<9", "sjfiwejfn", "sdkjfaoief", false);
		Q2.SetQID(1);
		questions.add(Q1);
		questions.add(Q2);*/
				
		Collections.shuffle(questions, new Random(seed));
		
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout QuestionLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(QuestionLayout);
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		String text = "HW" + hw_num + " questions";
		JLabel title = new JLabel(text);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(title);
        
		for(int i = 0; i < questions.size(); i++){
			addAQuestion(i, getContentPane(), questions.get(i));
		}
		
		//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JPanel buttons = new JPanel();
		buttons.setBackground(Constants.color);
		JButton submit = new JButton("Submit");
		submit.setMinimumSize(new Dimension(134, 30));
		submit.setPreferredSize(new Dimension(134, 30));
		submit.setMaximumSize(new Dimension(134, 30));
		submit.addActionListener(new SubmitListener());
		JButton back = new JButton("Back");
		back.setMinimumSize(new Dimension(134, 30));
		back.setPreferredSize(new Dimension(134, 30));
		back.setMaximumSize(new Dimension(134, 30));
		back.addActionListener(new BackListener());
		buttons.add(submit);
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
        panel1.setBackground(Constants.color);
        panel2.setBackground(Constants.color);
        panel3.setBackground(Constants.color);
		JLabel ques_title = new JLabel(que.GetQText());
		JTextField justification = new JTextField();
		panel1.add(ques_title);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(justification);
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(panel1);
        
        temp = questions.get(i).GetShuffledAnswers();
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        
        for(int j = 0; j < temp.size(); j++){
        	JRadioButton choice = new JRadioButton((j+1) + ") " + temp.get(j).GetAnsText());
            choice.setActionCommand(questions.get(i).GetQID()+";"+temp.get(j).GetAnsID());
            choice.setBackground(Constants.color);
            group.add(choice);
            panel2.add(choice);
        }
        
        button_group.add(group);
        container.add(panel2);
        
        justifications.add(justification.getText());
        container.add(panel3);
        
        getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
        
	}
	
	public class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			try{
				 Class.forName("oracle.jdbc.driver.OracleDriver");

				 String user = "ywang51";	
				 String passwd = "001037682";	
				    
				 Connection conn = null;
			     Statement stmt1 = null;
			     Statement stmt2 = null;
			     
			     try{
			    	// Get a connection from the first driver in the
			 		// DriverManager list that recognizes the URL jdbcURL
			 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
			 		
			 		// Create a statement object that will be sending your
					// SQL statements to the DBMS
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					
					/*
					 * add to questions
					 */
					stmt1.executeUpdate("INSERT INTO ATTEMPTS (AT_ID, S_ID, AS_ID, C_TOKEN, SEED) " +
							"VALUES (" + at_num + ", '" + id + "', " + hw_num + ", '" + token + "', " + seed + ")");
					
					for(int i = 0; i < button_group.size(); i++){
						String selection[] = button_group.get(i).getSelection().getActionCommand().split(";");
						stmt2.executeUpdate("INSERT INTO ATTEMPTQUESTIONS (AT_ID, Q_ID, A_ID, AS_ID, S_ID, C_TOKEN, JUSTIFICATION) " +
								"VALUES (" + at_num + ", " + selection[0] + ", " + selection[1] + ", " + hw_num + ", '" + id + "', '" + token + "', '" + justifications.get(i) + "')");
						//System.out.println(button_group.get(i).getSelection().getActionCommand());
					}
					
			     } finally {
			    	    Constants.close(stmt1);
			    	    Constants.close(stmt2);
			    	    Constants.close(conn);
		         }
			} catch(Throwable oops) {
	         oops.printStackTrace();
	        }
			
			new add_success(2, id, token).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new view_attempts(id, token).setVisible(true);
			dispose();
		}
	}
}
