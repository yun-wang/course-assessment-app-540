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
public class stdnt_prof_view extends JFrame {

	//private JPanel panel = new JPanel();
	//private JLabel label = new JLabel("Welcome, student!");
	private JButton select = new JButton("Select Course");
	private JButton add = new JButton("Add Course");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	private String id;
	private int type;
	
	public stdnt_prof_view(String id, int type){
		this.id = id;
		this.type = type;   //0 for student, 1 for professor
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
        panel.setBackground(Constants.color);
        setContentPane(panel);
        
        select.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        select.addActionListener(new SelectListener());
        add.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        add.addActionListener(new AddListener());
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        back.addActionListener(new BackListener());
        
        GroupLayout StudentLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(StudentLayout);
        
        StudentLayout.setHorizontalGroup(
        		StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(StudentLayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(select, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        	        		.addComponent(add)
        	        		.addComponent(back))
        		)
        );
        
        StudentLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {select, add, back});
        
        StudentLayout.setVerticalGroup(
        		StudentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, StudentLayout.createSequentialGroup()
        				.addGap(90, 90, 90)
        				.addComponent(select, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(add)
        				.addGap(25, 25, 25)
        				.addComponent(back))
        );
        
        StudentLayout.linkSize(SwingConstants.VERTICAL, new Component[] {select, add, back});
        
        pack();
	}
	
	public class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent InstrEvent){
			new select_course(id, type).setVisible(true);
			dispose();
		}
	}
	
	public class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new add_course(id, type).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new login().setVisible(true);
			dispose();
		}
	}
}
