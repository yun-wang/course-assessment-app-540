/**
 * 
 */
package mgtsys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class create_success extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("New account created successfully.\n Please log in.");
	private JButton okay = new JButton("OK");
	
	public create_success(){
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		okay.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		okActionPerformed(evt);
        	}
        });
		
		GroupLayout SuccessLayout = new GroupLayout(panel);
		panel.setLayout(SuccessLayout);
		SuccessLayout.setHorizontalGroup(
        		SuccessLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(SuccessLayout.createSequentialGroup()
        				//.addGap(40, 40, 40)
        				.addComponent(label))
        				//.addContainerGap(60, Short.MAX_VALUE))
        		.addGroup(SuccessLayout.createSequentialGroup()
        				.addGap(110, 110, 110)
        				.addComponent(okay)
        				.addContainerGap(60, Short.MAX_VALUE))
        );
        
        SuccessLayout.setVerticalGroup(
            SuccessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(SuccessLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(label)
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
