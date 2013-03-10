/**
 * 
 */
package mgtsys;

import java.awt.Color;
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
public class welcome extends JFrame {
	
	private JButton instr = new JButton("Instructor");
	private JButton stdnt = new JButton("Student");
	private JButton asst = new JButton("Teaching Assistant");
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	
	public welcome(){
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
        panel.setBackground(new Color(193, 210, 240));
        setContentPane(panel);
        
        instr.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        instr.addActionListener(new InstrListener());
        stdnt.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        stdnt.addActionListener(new StdntListener());
        asst.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        asst.addActionListener(new AsstListener());
        
        GroupLayout WelcomeLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(WelcomeLayout);
        
        WelcomeLayout.setHorizontalGroup(
        		WelcomeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(WelcomeLayout.createSequentialGroup()
        				.addGap(140)
        				.addGroup(WelcomeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(instr, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        	        		.addComponent(stdnt)
        	        		.addComponent(asst))
        		)
        );
        
        WelcomeLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {instr, stdnt, asst});
        
        WelcomeLayout.setVerticalGroup(
        		WelcomeLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(GroupLayout.Alignment.CENTER, WelcomeLayout.createSequentialGroup()
        				.addGap(90, 90, 90)
        				.addComponent(instr, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        				.addGap(25, 25, 25)
        				.addComponent(stdnt)
        				.addGap(25, 25, 25)
        				.addComponent(asst))
        );
        
        WelcomeLayout.linkSize(SwingConstants.VERTICAL, new Component[] {instr, stdnt, asst});
        
        pack();
	}
	
	public class InstrListener implements ActionListener{
		public void actionPerformed(ActionEvent InstrEvent){
			login check_i = new login(0);
			check_i.setVisible(true);
			dispose();
		}
	}
	
	public class StdntListener implements ActionListener{
		public void actionPerformed(ActionEvent StdntEvent){
			login check_s = new login(1);
			check_s.setVisible(true);
			dispose();
		}
	}
	
	public class AsstListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			login check_a = new login(2);
			check_a.setVisible(true);
			dispose();
		}
	}
}