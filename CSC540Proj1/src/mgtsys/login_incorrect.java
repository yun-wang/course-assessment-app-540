/**
 * 
 */
package mgtsys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class login_incorrect extends JFrame {

	private JButton okay = new JButton("OK");
	private JPanel panel = new JPanel();
	private JLabel warning = new JLabel("Login Incorrect!");
	
	public login_incorrect(){
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Warning"));
		
		okay.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		okActionPerformed(evt);
        	}
        });
		
		GroupLayout WarningLayout = new GroupLayout(panel);
		panel.setLayout(WarningLayout);
		WarningLayout.setHorizontalGroup(
        		WarningLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(WarningLayout.createSequentialGroup()
        				.addGap(40, 40, 40)
        				.addComponent(warning))
        		.addGroup(WarningLayout.createSequentialGroup()
        				.addGap(60, 60, 60)
        				.addComponent(okay)
        				.addContainerGap(60, Short.MAX_VALUE))
        );
        
        WarningLayout.setVerticalGroup(
            WarningLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(WarningLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(warning)
                .addGap(24, 24, 24)
                .addComponent(okay)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        
        pack();
	}
	
	private void okActionPerformed(ActionEvent evt){
		new login().setVisible(true);
		this.dispose();
	}
}
