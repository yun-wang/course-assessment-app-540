/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class edit_homework extends JFrame {

	private String id, token;
	private JPanel panel = new JPanel();
	private List<Homeworks> hw = new ArrayList<Homeworks>();
	private List<Integer> hw_num = new ArrayList<Integer>();
	private List<Integer> at_num = new ArrayList<Integer>();
	private int length = 500;
	private int height = 500;
	
	public edit_homework(String id, String token){
		this.id = id;
		this.token = token;
		
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			 String user = "ywang51";	
			 String passwd = "001037682";	
			    
			 Connection conn = null;
		     Statement stmt = null;
		     ResultSet rs = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt = conn.createStatement();
				
				/*
				 * add to hw
				 */
				rs = stmt.executeQuery("SELECT AS_ID, AT_ID FROM ATTEMPTS WHERE C_T = '" + token + "'");
				
				while (rs.next()){
					int hw_id = rs.getInt("AS_ID");
					int at_id = rs.getInt("AT_ID");
					hw_num.add(hw_id);
					at_num.add(at_id);
				}
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(stmt);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
          oops.printStackTrace();
        }
		
		int i = 0;
		while(i < hw_num.size()){
			Homeworks homework = new Homeworks();
			homework.SetHWID(hw_num.get(i));
			homework.SetAttempts(at_num.get(i));
			int next = i;
			int last = next;
			while(next != -1){
				next = hw_num.indexOf(hw_num.get(i));
				if(next != -1){
					homework.SetAttempts(at_num.get(next));
					last = next;
				}
			}
			i = last + 1;
			hw.add(homework);
		}
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout PastLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(PastLayout);
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JLabel label1 = new JLabel("List of homeworks:");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(label1);
		
		for(int i = 0; i < hw.size(); i++){
			String part1 = "HW"+hw.get(i).GetHWID();
			//System.out.println(part1);
			for(int j = 0; j < hw.get(i).GetAttempts().size(); j++){
				String part2 = "Attempt "+hw.get(i).GetAttempts().get(j).GetHWID();
				//System.out.println(part2);
				String text = part1 + "     " + part2;
				addARow(text, getContentPane(), hw.get(i).GetHWID(), hw.get(i).GetAttempts().get(j).GetHWID());
			}
		}
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JButton back = new JButton("Back");
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(back);
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		back.setMinimumSize(new Dimension(134, 30));
		back.setPreferredSize(new Dimension(134, 30));
		back.setMaximumSize(new Dimension(134, 30));
        back.addActionListener(new BackListener());
        
		pack();
	}
	
	private void addARow(String text, Container container, int hw_id, int at_id){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());

        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(260, 40));
        button.setMaximumSize(new Dimension(260, 40));
        button.setPreferredSize(new Dimension(260, 40));
        button.addActionListener(new EditListener(hw_id));
        container.add(button);
	}
	
	public class EditListener implements ActionListener{
		private int hw_id;
		public EditListener(int hw_id){
			this.hw_id = hw_id;
		}
		public void actionPerformed(ActionEvent AsstEvent){
			new edit_specific_hw(id, token, hw_id).setVisible(true);
			dispose();
		}
	}
	
	public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new prof_course_option(id, token).setVisible(true);
			dispose();
		}
	}
}
