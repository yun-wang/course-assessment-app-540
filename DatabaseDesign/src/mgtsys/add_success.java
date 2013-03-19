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
public class add_success extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();
	private JButton okay = new JButton("OK");
	private int type;
	private String id, token;
	
	public add_success(int type, String id, String token){
		
		this.type = type;
		this.id = id;
		this.token = token;
		
		if(type == 0)  //create user successfully
			label.setText("New account created successfully. Please log in.");
		else if(type == 1)    //add course successfully
			label.setText("You are now successfully enrolled for the course.");
		else if(type == 2)    //homework submitted correctly
			label.setText("The homework is submitted successfully.");
		else if(type == 3)   //homework added correctly
			label.setText("The homework is added successfully.");
		else if(type == 4)  //question added correctly
			label.setText("The question is added successfully.");
		else if(type == 5)  //answer added correctly
			label.setText("The answer is added successfully.");
		else if(type == 6)  //homework edited correctly
			label.setText("The homework is edited successfully.");
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
		if(type == 0)
			new login().setVisible(true);
		else if(type == 1 || type == 2)
			new stdnt_course_option(id, token).setVisible(true);
		else if(type == 3 || type == 4 || type == 5 || type == 6)
			new prof_course_option(id, token).setVisible(true);
		this.dispose();
	}
}
