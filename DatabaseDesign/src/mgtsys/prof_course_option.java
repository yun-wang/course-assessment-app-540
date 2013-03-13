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
public class prof_course_option extends JFrame {

	private JButton add_hw = new JButton("Add Homework");
	private JButton edit_hw = new JButton("Edit Homework");
	private JButton add_ques = new JButton("Add Question");
	private JButton add_ans = new JButton("Add Answer");
	private JButton report = new JButton("Reports");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 600;
	private String id, token;
	
	public prof_course_option(String id, String c_token){
		this.id = id;
		this.token = c_token;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
        panel.setBackground(Constants.color);
        setContentPane(panel);
        
        add_hw.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add_hw.addActionListener(new AddHwListener());
        edit_hw.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        edit_hw.addActionListener(new EditHwListener());
        add_ques.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add_ques.addActionListener(new AddQListener());
        add_ans.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add_ans.addActionListener(new AddAnsListener());
        report.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        report.addActionListener(new ReportListener());
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        back.addActionListener(new BackListener());
        
        GroupLayout ProfLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(ProfLayout);
        
        ProfLayout.setHorizontalGroup(
        		ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(ProfLayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(add_hw, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        	        		.addComponent(edit_hw)
        	        		.addComponent(add_ques)
        	        		.addComponent(add_ans)
        	        		.addComponent(report)
        	        		.addComponent(back))
        		)
        );
        
        ProfLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add_hw, edit_hw, add_ques, add_ans, report, back});
        
        ProfLayout.setVerticalGroup(
        		ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, ProfLayout.createSequentialGroup()
        				.addGap(60, 60, 60)
        				.addComponent(add_hw, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(edit_hw)
        				.addGap(25, 25, 25)
        				.addComponent(add_ques)
        				.addGap(25, 25, 25)
        				.addComponent(add_ans)
        				.addGap(25, 25, 25)
        				.addComponent(report)
        				.addGap(25, 25, 25)
        				.addComponent(back))
        );
        
        ProfLayout.linkSize(SwingConstants.VERTICAL, new Component[] {add_hw, edit_hw, add_ques, add_ans, report, back});
        
        pack();
	}
	
	public class AddHwListener implements ActionListener{
		public void actionPerformed(ActionEvent InstrEvent){
			new add_homework(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class EditHwListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new edit_homework(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class AddQListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new add_question(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class AddAnsListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new add_answer(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class ReportListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new report(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new select_course(id, 1).setVisible(true);
			dispose();
		}
	}
}
