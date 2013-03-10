/**
 * 
 */
package mgtsys;

import java.awt.Dimension;
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
public class invalid_input extends JFrame {
	private JButton okay = new JButton("OK");
	private JPanel panel = new JPanel();
	private JLabel warning = new JLabel();
	private int type;

	public invalid_input(int type){
		
		this.type = type;
		
		if(type == 0) //empty unity id
			warning.setText("User ID cannot be empty!");
		else if(type == 1)  //empty password
			warning.setText("Password cannot be empty!");
		else if(type == 2)  //two passwords do not match
			warning.setText("Password and Re-entered Password do not match!");
		else if(type == 3)  //empty first name
			warning.setText("First Name cannot be empty!");
		else if(type == 4)  //empty last name
			warning.setText("Last Name cannot be empty!");
		else if(type == 5)  //unity id in use
			warning.setText("User ID already in use!");
		else if(type == 6)  //incorrect unity id or password when login
			warning.setText("Login Incorrect!");
 		
		initComponents();
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createTitledBorder("Warning"));
		
		//okay.setAlignmentY(CENTER_ALIGNMENT);
		okay.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		okActionPerformed(evt);
        	}
        });
		
		GroupLayout WarningLayout = new GroupLayout(panel);
		panel.setLayout(WarningLayout);
		WarningLayout.setHorizontalGroup(
        		WarningLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		.addGroup(WarningLayout.createSequentialGroup()
        				.addGap(10, 10, 10)
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
		if(type == 6)
			new login().setVisible(true);
		this.dispose();
	}
}
