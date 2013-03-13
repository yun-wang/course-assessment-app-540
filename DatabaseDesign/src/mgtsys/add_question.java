/**
 * 
 */
package mgtsys;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Yun Wang
 *
 */
public class add_question extends JFrame {

	private String p_id, c_token;
	private ArrayList<Integer> topic_id = new ArrayList<Integer>();
	private ArrayList<String> topic_name = new ArrayList<String>();
	private JPanel panel = new JPanel();
	private int length = 500;
	private int height = 500;
	
	public add_question(String id, String c_token){
		this.p_id = id;
		this.c_token = c_token;
		
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
				
				rs = stmt.executeQuery("SELECT T.T_ID, T.T_NAME FROM TOPICS T, COURSECONSISTS C WHERE C.C_TOKEN = '" + c_token + 
							"' AND T.T_ID = C.T_ID");
				
				while (rs.next()){
					int t_id = rs.getInt("T_ID");
					String t_name = rs.getString("T_NAME");
					topic_id.add(t_id);
					topic_name.add(t_name);
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
		
		for(int i = 0; i < topic_id.size(); i++){
			//getContentPane().add(Box.createRigidArea(new Dimension(5, 0)));
			String part1 = topic_id.get(i)+"";
			String part2 = topic_name.get(i).trim();
			String text = part1 + " - " + part2;
			addAButton(text, topic_id.get(i), getContentPane());
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
	
	private void addAButton(String text, int t_id, Container container){
		container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
		JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 78));
        button.addActionListener(new ButtonListener(t_id));
	}
    
    public class ButtonListener implements ActionListener{
    	private int t_id;
    	public ButtonListener(int x){
    		this.t_id = x;
    	}
		public void actionPerformed(ActionEvent InstrEvent){
			new enter_question(p_id, c_token, t_id).setVisible(true);
			dispose();
		}
	}
    
    public class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent AsstEvent){
			new prof_course_option(p_id, c_token).setVisible(true);
			dispose();
		}
	}
}
