/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Yun Wang
 *
 */
public class stdnt_course_option extends JFrame {

	private JButton view_s = new JButton("View Scores");
	private JButton attempt = new JButton("Attempt Homework");
	private JButton view_p = new JButton("View Past Submission");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	private String id, token;
	
	public stdnt_course_option(String id, String c_token){
		this.id = id;
		this.token = c_token;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
        panel.setBackground(Constants.color);
        setContentPane(panel);
        
        view_s.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        view_s.addActionListener(new ScoreListener());
        attempt.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        attempt.addActionListener(new AttemptListener());
        view_p.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        view_p.addActionListener(new PastListener());
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        back.addActionListener(new BackListener());
        
        GroupLayout StudentLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(StudentLayout);
        
        StudentLayout.setHorizontalGroup(
        		StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(StudentLayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(view_s, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        	        		.addComponent(attempt)
        	        		.addComponent(view_p)
        	        		.addComponent(back))
        		)
        );
        
        StudentLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {view_s, attempt, view_p, back});
        
        StudentLayout.setVerticalGroup(
        		StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, StudentLayout.createSequentialGroup()
        				.addGap(60, 60, 60)
        				.addComponent(view_s, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(attempt)
        				.addGap(25, 25, 25)
        				.addComponent(view_p)
        				.addGap(25, 25, 25)
        				.addComponent(back))
        );
        
        StudentLayout.linkSize(SwingConstants.VERTICAL, new Component[] {view_s, attempt, view_p, back});
        
        pack();
	}
	
	public class ScoreListener implements ActionListener{
		public void actionPerformed(ActionEvent InstrEvent){
			new view_score(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class AttemptListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new view_attempts(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class PastListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new view_past(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new select_course(id, 0).setVisible(true);
			dispose();
		}
	}
}
