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

public class view_questions extends JFrame {

	private String id, token;
	private int hw_num, at_num, seed;
	private JPanel panel = new JPanel();
	private List<Questions> questions = new ArrayList<Questions>();
	private List<ButtonGroup> button_group = new ArrayList<ButtonGroup>();
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
        panel1.setBackground(Constants.color);
        panel2.setBackground(Constants.color);
		JLabel ques_title = new JLabel(que.GetQText());
		panel1.add(ques_title);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(panel1);
        
        temp = questions.get(i).GetShuffledAnswers();
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        
        for(int j = 0; j < temp.size(); j++){
        	JRadioButton choice = new JRadioButton((j+1) + ") " + temp.get(j).GetAnsText());
            choice.setActionCommand(i+" "+j);
            choice.setBackground(Constants.color);
            group.add(choice);
            panel2.add(choice);
        }
        
        button_group.add(group);
        container.add(panel2);
        
        getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
        
	}
	
	public class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			for(int i = 0; i < button_group.size(); i++){
				System.out.println(button_group.get(i).getSelection().getActionCommand());
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
