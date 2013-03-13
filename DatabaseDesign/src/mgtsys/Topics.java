/**
 * 
 */
package mgtsys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yun Wang
 *
 */
public class Topics {

	private int t_id;
	private String t_name;
	private List<Questions> questions = new ArrayList<Questions>();
	
	public void SetTID(int id){
		this.t_id = id;
	}
	
	public void SetTName(String name){
		this.t_name = name;
	}
	
	public void SetQuestions(int q_id, String q_text){
		Questions q = new Questions();
		q.SetQID(q_id);
		q.SetQText(q_text);
		questions.add(q);
	}
	
	public int GetTID(){
		return t_id;
	}
	
	public String GetTName(){
		return t_name;
	}
	
	public List<Questions> GetQuestions(){
		return questions;
	}
}
