/**
 * 
 */
package mgtsys;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Yun Wang
 *
 */
public class select_course extends JFrame {
	
	private String id;
	private int type;
	private ArrayList<String> course_id = new ArrayList<String>();
	private ArrayList<String> course_token = new ArrayList<String>();
	private ArrayList<String> course_name = new ArrayList<String>();
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	
	public select_course(String id, int type){
		this.id = id;
		this.type = type;
		
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
				
				if(type == 0){
					rs = stmt.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_TOKEN FROM TAKES T, COURSES C WHERE T.S_ID = '" + id + 
							"' AND T.C_TOKEN = C.C_TOKEN");
				}
				else if(type == 1){
					rs = stmt.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_TOKEN FROM TEACHES T, COURSES C WHERE T.P_ID = '" + id + 
							"' AND T.C_TOKEN = C.C_TOKEN");
				}
				
				while (rs.next()){
					String c_id = rs.getString("C_ID").trim();
					String c_name = rs.getString("C_NAME").trim();
					String c_token = rs.getString("C_TOKEN").trim();
					course_id.add(c_id);
					course_token.add(c_token);
					course_name.add(c_name);
				}
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(stmt);
		    	    Constants.close(conn);
	         }
		} catch(Throwable oops) {
           oops.printStackTrace();
        }
		
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(length, height));
		panel.setBackground(Constants.color);
		setContentPane(panel);
		
		BoxLayout SelectLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(SelectLayout);
		
		for(int i = 0; i < course_id.size(); i++){
			//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
			String part1 = course_id.get(i);
			String part2 = course_name.get(i);
			String text = part1 + " - " + part2;
			addAButton(text, course_token.get(i), getContentPane());
		}
		
		//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		JButton back = new JButton("Back");
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(back);
		getContentPane().add(Box.createVerticalGlue());
		getContentPane().add(Box.createHorizontalGlue());
		back.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		back.setMinimumSize(new Dimension(200, 78));
		back.setPreferredSize(new Dimension(200, 78));
		back.setMaximumSize(new Dimension(200, 78));
        back.addActionListener(new BackListener());
        
		pack();
	}
	
	private void addAButton(String text, String c_token, Container container){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
		JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 78));
        button.addActionListener(new ButtonListener(c_token));
	}
    
    public class ButtonListener implements ActionListener{
    	private String text;
    	public ButtonListener(String text){
    		this.text = text;
    	}
		public void actionPerformed(ActionEvent InstrEvent){
			if(type == 0)
				new stdnt_course_option(id, text).setVisible(true);
			else if(type == 1)
				new prof_course_option(id, text).setVisible(true);
			dispose();
		}
	}
    
    public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new stdnt_prof_view(id, type).setVisible(true);
			dispose();
		}
	}
}
