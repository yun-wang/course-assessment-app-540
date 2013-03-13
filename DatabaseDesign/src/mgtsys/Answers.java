/**
 * 
 */
package mgtsys;

/**
 * @author Yun Wang
 *
 */
public class Answers {
	
	private int a_id;
	private String a_text, explanation, hint;
	private boolean is_correct;
	private boolean is_selected;
	
	public void SetAnsID(int a_id){
		this.a_id = a_id;
	}
	
	public void SetAnsText(String a_text){
		this.a_text = a_text;
	}
	
	public void SetAnsExplanation(String exp){
		this.explanation = exp;
	}
	
	public void SetAnsHint(String hint){
		this.hint = hint;
	}
	
	public void SetIsCorrect(boolean is_correct){
		this.is_correct = is_correct;
	}
	
	public void SetIsSelected(boolean b){
		this.is_selected = b;
	}
	
	public int GetAnsID(){
		return a_id;
	}
	
	public String GetAnsText(){
		return a_text;
	}
	
	public String GetAnsExplanation(){
		return explanation;
	}
	
	public String GetAnsHint(){
		return hint;
	}
	
	public boolean GetIsCorrect(){
		return is_correct;
	}
	
	public boolean GetIsSelected(){
		return is_selected;
	}
}
