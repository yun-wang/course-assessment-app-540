/**
 * 
 */
package mgtsys;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class professor_view extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("Welcome, professor!");
	
	public professor_view(){
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GroupLayout ProfessorLayout = new GroupLayout(panel);
		panel.setLayout(ProfessorLayout);
		ProfessorLayout.setHorizontalGroup(
        		ProfessorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(ProfessorLayout.createSequentialGroup()
        				.addGap(40, 40, 40)
        				.addComponent(label)
        				.addContainerGap(60, Short.MAX_VALUE))
        );
        
        ProfessorLayout.setVerticalGroup(
            ProfessorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(ProfessorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(label)
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
}
