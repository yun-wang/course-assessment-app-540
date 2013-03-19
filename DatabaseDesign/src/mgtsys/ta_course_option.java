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
public class ta_course_option extends JFrame {
	
	private String id, token;
	private JButton add_hw = new JButton("Add Homework");
	private JButton edit_hw = new JButton("Edit Homework");
	private JButton view_hw = new JButton("View Homeworks");
	private JButton back = new JButton("Back");
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	
	public ta_course_option(String id, String c_token){
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
        view_hw.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        view_hw.addActionListener(new ViewHwListener());
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        back.addActionListener(new BackListener());
        
        GroupLayout TALayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(TALayout);
        
        TALayout.setHorizontalGroup(
        		TALayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(TALayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(TALayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(add_hw, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        	        		.addComponent(edit_hw)
        	        		.addComponent(view_hw)
        	        		.addComponent(back))
        		)
        );
        
        TALayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {add_hw, edit_hw, view_hw, back});
        
        TALayout.setVerticalGroup(
        		TALayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, TALayout.createSequentialGroup()
        				.addGap(60, 60, 60)
        				.addComponent(add_hw, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(edit_hw)
        				.addGap(25, 25, 25)
        				.addComponent(view_hw)
        				.addGap(25, 25, 25)
        				.addComponent(back))
        );
        
        TALayout.linkSize(SwingConstants.VERTICAL, new Component[] {add_hw, edit_hw, view_hw, back});
        
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
	
	public class ViewHwListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			new ta_view_hw(id, token).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new select_course(id, 2).setVisible(true);
			dispose();
		}
	}
}
