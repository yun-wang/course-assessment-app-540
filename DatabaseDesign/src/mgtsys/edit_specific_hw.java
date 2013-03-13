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
public class edit_specific_hw extends JFrame {

	private String p_id, token;
	private int hw_id;
	private JPanel panel = new JPanel();
	private int length = 580;
	private int height = 730;
	private JButton start = new JButton("Edit Start Date");
	private JButton end = new JButton("Edit End Date");
	private JButton num_att = new JButton("Edit Number of Attempts");
	private JButton selection = new JButton("Edit Score Selection");
	private JButton q_num = new JButton("Edit Question Numbers");
	private JButton c_pts = new JButton("Edit Correct Answer Points");
	private JButton i_pts = new JButton("Edit Incorrect Answer Points");
	private JButton back = new JButton("Back");
	
	public edit_specific_hw(String id, String token, int hw){
		this.p_id = id;
		this.token = token;
		this.hw_id = hw;
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
        panel.setBackground(Constants.color);
        setContentPane(panel);
        
        start.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        start.addActionListener(new ClickListener(0));
        end.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        end.addActionListener(new ClickListener(1));
        num_att.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        num_att.addActionListener(new ClickListener(2));
        selection.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        selection.addActionListener(new ClickListener(3));
        q_num.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        q_num.addActionListener(new ClickListener(4));
        c_pts.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        c_pts.addActionListener(new ClickListener(5));
        i_pts.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        i_pts.addActionListener(new ClickListener(6));
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        back.addActionListener(new BackListener());
        
        GroupLayout ProfLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(ProfLayout);
        
        ProfLayout.setHorizontalGroup(
        		ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(ProfLayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(start, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        	        		.addComponent(end)
        	        		.addComponent(num_att)
        	        		.addComponent(selection)
        	        		.addComponent(q_num)
        	        		.addComponent(c_pts)
        	        		.addComponent(i_pts)
        	        		.addComponent(back))
        		)
        );
        
        ProfLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {start, end, num_att, selection, q_num, c_pts, i_pts, back});
        
        ProfLayout.setVerticalGroup(
        		ProfLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, ProfLayout.createSequentialGroup()
        				.addGap(60, 60, 60)
        				.addComponent(start, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(end)
        				.addGap(25, 25, 25)
        				.addComponent(num_att)
        				.addGap(25, 25, 25)
        				.addComponent(selection)
        				.addGap(25, 25, 25)
        				.addComponent(q_num)
        				.addGap(25, 25, 25)
        				.addComponent(c_pts)
        				.addGap(25, 25, 25)
        				.addComponent(i_pts)
        				.addGap(25, 25, 25)
        				.addComponent(back))
        );
        
        ProfLayout.linkSize(SwingConstants.VERTICAL, new Component[] {start, end, num_att, selection, q_num, c_pts, i_pts, back});
        
        pack();
	}
	
	public class ClickListener implements ActionListener{
		private int type;
		public ClickListener(int type){
			this.type = type;
		}
		public void actionPerformed(ActionEvent InstrEvent){
			new enter_new_value(p_id, token, hw_id, type).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new edit_homework(p_id, token).setVisible(true);
			dispose();
		}
	}
}
