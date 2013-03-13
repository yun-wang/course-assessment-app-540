/**
 * 
 */
package mgtsys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Yun Wang
 *
 */
public class Questions {
	
	private int q_id, level, seed;
	private String q_text;
	private List<Answers> answers = new ArrayList<Answers>();
	
	public void SetQID(int q_id){
		this.q_id = q_id;
	}
	
	public void SetLevel(int level){
		this.level = level;
	}
	
	public void SetQText(String text){
		this.q_text = text;
	}
	
	public void SetAnswers(int a_id, String a_text, String exp, String hint, boolean b){
		Answers Ans = new Answers();
		Ans.SetAnsID(a_id);
		Ans.SetAnsText(a_text);
		Ans.SetAnsExplanation(exp);
		Ans.SetAnsHint(hint);
		Ans.SetIsCorrect(b);
		answers.add(Ans);
	}
	
	public void SetAnswers(int a_id, String a_text, String exp, String hint, boolean b, boolean s){
		Answers Ans = new Answers();
		Ans.SetAnsID(a_id);
		Ans.SetAnsText(a_text);
		Ans.SetAnsExplanation(exp);
		Ans.SetAnsHint(hint);
		Ans.SetIsCorrect(b);
		Ans.SetIsSelected(s);
		answers.add(Ans);
	}
	
	public void SetSeed(int seed){
		this.seed = seed;
	}
	
	public int GetQID(){
		return q_id;
	}
	
	public int GetLevel(){
		return level;
	}
	
	public String GetQText(){
		return q_text;
	}
	
	public List<Answers> GetAnswers(){
		return answers;
	}
	
	public Answers GetCorrectAnswer(){
		
		Answers temp = new Answers();
		
		for(int i = 0; i < answers.size(); i++){
			if(answers.get(i).GetIsCorrect())
				temp = answers.get(i);
		}
		
		return temp;
	}
	
	public Answers GetSelectedAnswer(){
		
		Answers temp = new Answers();
		
		for(int i = 0; i < answers.size(); i++){
			if(answers.get(i).GetIsSelected())
				temp = answers.get(i);
		}
		
		return temp;
	}
	
	public List<Answers> GetShuffledAnswers(){
		
		List<Answers> result = new ArrayList<Answers>();
		List<Answers> correct = new ArrayList<Answers>();
		List<Answers> in_correct = new ArrayList<Answers>();
		List<Answers> temp = new ArrayList<Answers>();
		
		for(int i = 0; i < answers.size(); i++){
			if(answers.get(i).GetIsCorrect())
				correct.add(answers.get(i));
			else
				in_correct.add(answers.get(i));
		}
		
		result.add(correct.get(new Random(seed).nextInt(correct.size())));
		temp = pickNRandom(in_correct, Constants.num_choice);
		for(int i = 0; i < Constants.num_choice; i++){
			result.add(temp.get(i));
		}
		
		Collections.shuffle(result, new Random(seed));
		
		return result;
	}
	
	public List<Answers> pickNRandom(List<Answers> lst, int n) {
	    List<Answers> copy = new ArrayList<Answers>(lst);
	    Collections.shuffle(copy, new Random(seed));
	    return copy.subList(0, n);
	}
}
