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
		
		if(type == 0) //empty unity id when creating a user
			warning.setText("User ID cannot be empty!");
		else if(type == 1)  //empty password when creating a user
			warning.setText("Password cannot be empty!");
		else if(type == 2)  //two passwords do not match when creating a user
			warning.setText("Password and Re-entered Password do not match!");
		else if(type == 3)  //empty first name when creating a user
			warning.setText("First Name cannot be empty!");
		else if(type == 4)  //empty last name when creating a user
			warning.setText("Last Name cannot be empty!");
		else if(type == 5)  //unity id in use
			warning.setText("User ID already in use!");
		else if(type == 6)  //incorrect unity id or password when login
			warning.setText("Login Incorrect!");
		else if(type == 7)  //no token when adding a course
			warning.setText("No Class Token entered!");
		else if(type == 8)  //invalid token when adding a course
			warning.setText("Invalid Class Token!");
		else if(type == 9)  //course passed end date when adding the course
			warning.setText("Course Over, Cannot Register!");
		else if(type == 10)  //already enrolled when adding the course
			warning.setText("You have already signed up for this class!");
		else if(type == 11)  //no hw id when adding a hw
			warning.setText("No Homework ID entered!");
		else if(type == 12)  //hw already exists when adding a hw
			warning.setText("The homework already exists!");
		else if(type == 13)  //no input when editing homework
			warning.setText("No new value entered!");
		else if(type == 14)  //no question id entered when adding a question
			warning.setText("No question ID entered!");
		else if(type == 15)  //question already exists when adding a question
			warning.setText("The question already exists!");
		else if(type == 16)  //no query entered
			warning.setText("No query entered!");
		else if(type == 17)  //no results after submitting query from report
			warning.setText("No results available!");
		else if(type == 18)  //no answer id entered when adding an answer
			warning.setText("No answer ID entered!");
		else if(type == 19)  //answer already exists when adding an answer
			warning.setText("The answer already exists!");
		else if(type == 20)  //2 queries entered in report
			warning.setText("One query at a time please.");
 		
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
