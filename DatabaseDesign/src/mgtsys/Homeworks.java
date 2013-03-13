/**
 * 
 */
package mgtsys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yun Wang
 *
 */
public class Homeworks {

	private int hw_num, retries, retry_left, pts;
	private Date start, end, submit;
	private List<Homeworks> attempts = new ArrayList<Homeworks>();
	private List<Questions> qus = new ArrayList<Questions>();
	
	public void SetHWID(int num){
		this.hw_num = num;
	}
	
	public void SetStart(Date t){
		this.start = t;
	}
	
	public void SetEnd(Date t){
		this.end = t;
	}
	
	public void SetSubmit(Date t){
		this.submit = t;
	}
	
	public void SetRetryTotal(int retries){
		this.retries = retries;
	}
	
	public void SetRetryLeft(int retry_left){
		this.retry_left = retry_left;
	}
	
	public void SetScore(int pts){
		this.pts = pts;
	}
	
	public void SetQuestions(int q_id, int level, String q_text){
		Questions q = new Questions();
		q.SetQID(q_id);
		q.SetQText(q_text);
		qus.add(q);
	}
	
	public void SetAttempts(int at_num, int pts){
		Homeworks att = new Homeworks();
		att.SetHWID(at_num);
		att.SetScore(pts);
		//att.SetQuestions(q_id, level, q_text)
		attempts.add(att);
	}
	
	public int GetHWID(){
		return hw_num;
	}
	
	public Date GetStart(){
		return start;
	}
	
	public Date GetEnd(){
		return end;
	}
	
	public Date GetSubmit(){
		return submit;
	}
	
	public int GetRetryTotal(){
		return retries;
	}
	
	public int GetRetryLeft(){
		return retry_left;
	}
	
	public int GetScore(){
		return pts;
	}
	
	public List<Homeworks> GetAttempts(){
		return attempts;
	}
}
