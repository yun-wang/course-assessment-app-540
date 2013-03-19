/**
 * 
 */
package mgtsys;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private ArrayList<String> ta_course_id = new ArrayList<String>();
	private ArrayList<String> ta_course_token = new ArrayList<String>();
	private ArrayList<String> ta_course_name = new ArrayList<String>();
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
		     Statement stmt1 = null;
		     Statement stmt2 = null;
		     ResultSet rs = null;
		     ResultSet rs1 = null;
		     ResultSet rs2 = null;
		     
		     try{
		    	// Get a connection from the first driver in the
		 		// DriverManager list that recognizes the URL jdbcURL
		 		conn = DriverManager.getConnection(Constants.jdbcURL, user, passwd);
		 		
		 		// Create a statement object that will be sending your
				// SQL statements to the DBMS
				stmt = conn.createStatement();
				stmt1 = conn.createStatement();
				stmt2 = conn.createStatement();
				
				if(type == 0){
					rs = stmt.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_T FROM TAKES T, COURSES C WHERE T.S_ID = '" + id + 
							"' AND T.C_T = C.C_T");
				}
				else if(type == 1){
					rs = stmt.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_T FROM TEACHES T, COURSES C WHERE T.P_ID = '" + id + 
							"' AND T.C_T = C.C_T");
				}
				else if(type == 2){
					rs1 = stmt1.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_T FROM TAKES T, COURSES C WHERE T.S_ID = '" + id + 
							"' AND T.C_T = C.C_T");
					rs2 = stmt2.executeQuery("SELECT C.C_ID, C.C_NAME, C.C_T FROM ASSISTS A, COURSES C, STUDENTS S WHERE A.S_ID = '" + id + 
							"' AND A.C_T = C.C_T AND A.S_ID = S.S_ID");
				}
				if(type == 0 || type == 1){
					while (rs.next()){
						String c_id = rs.getString("C_ID").trim();
						String c_name = rs.getString("C_NAME").trim();
						String c_token = rs.getString("C_T").trim();
						course_id.add(c_id);
						course_token.add(c_token);
						course_name.add(c_name);
					}
				}
				else if(type == 2){
					while (rs1.next()){
						String c_id = rs1.getString("C_ID").trim();
						String c_name = rs1.getString("C_NAME").trim();
						String c_token = rs1.getString("C_T").trim();
						course_id.add(c_id);
						course_token.add(c_token);
						course_name.add(c_name);
					}
					while (rs2.next()){
						String c_id = rs2.getString("C_ID").trim();
						String c_name = rs2.getString("C_NAME").trim();
						String c_token = rs2.getString("C_T").trim();
						ta_course_id.add(c_id);
						ta_course_token.add(c_token);
						ta_course_name.add(c_name);
					}
				}
				
		     } finally {
		    	    Constants.close(rs);
		    	    Constants.close(rs1);
		    	    Constants.close(rs2);
		    	    Constants.close(stmt);
		    	    Constants.close(stmt1);
		    	    Constants.close(stmt2);
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
		
		if(type == 2){
			getContentPane().add(Box.createVerticalGlue());
			getContentPane().add(Box.createHorizontalGlue());
			JLabel title1 = new JLabel("Courses Take as a Student: ");
			title1.setAlignmentX(Component.CENTER_ALIGNMENT);
			getContentPane().add(title1);
		}
		
		for(int i = 0; i < course_id.size(); i++){
			//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
			String part1 = course_id.get(i);
			String part2 = course_name.get(i);
			String text = part1 + " - " + part2;
			addAButton(text, course_token.get(i), getContentPane(), false);
		}
		
		if(type == 2){
			getContentPane().add(Box.createVerticalGlue());
			getContentPane().add(Box.createHorizontalGlue());
			JLabel title2 = new JLabel("Courses Assist as a TA: ");
			title2.setAlignmentX(Component.CENTER_ALIGNMENT);
			getContentPane().add(title2);
			
			for(int i = 0; i < ta_course_id.size(); i++){
				//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
				String part1 = ta_course_id.get(i);
				String part2 = ta_course_name.get(i);
				String text = part1 + " - " + part2;
				addAButton(text, ta_course_token.get(i), getContentPane(), true);
			}
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
	 
	private void addAButton(String text, String c_token, Container container, boolean take){  //take is only true when a ta assists a course
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
		JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 78));
        button.addActionListener(new ButtonListener(c_token, take));
	}
    
    public class ButtonListener implements ActionListener{
    	private String text;
    	private boolean b;
    	public ButtonListener(String text, boolean b){
    		this.text = text;
    		this.b = b;
    	}
		public void actionPerformed(ActionEvent InstrEvent){
			if(type == 0 || (type == 2 && !b))
				new stdnt_course_option(id, text).setVisible(true);
			else if(type == 1)
				new prof_course_option(id, text).setVisible(true);
			else if(type == 2 && b)
				new ta_course_option(id, text).setVisible(true);
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
