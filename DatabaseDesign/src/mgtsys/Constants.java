/**
 * 
 */
package mgtsys;

import java.awt.Color;
import java.sql.*;

/**
 * @author Yun Wang
 *
 */
public class Constants {

	public static final String jdbcURL 
	= "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	
	public static final int num_choice = 3;
	
	public static final Color color = new Color(193, 210, 240);
	
	public static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

	public static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }
    
	public static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
