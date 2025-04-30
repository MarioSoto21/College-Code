/* Mario Soto
Lab 5 create class Student that set the name, get the name, add a score, get
the total of scores, average score, and highest score*/
import java.util.ArrayList;
public class Student {
    private String name;
	
    // list of doubles for scores
    private ArrayList<Double> scores;
	
    // constructor
    public Student() {
        this.name = "";
        this.scores = new ArrayList<Double>();
    }
    
    // set method for name
    public void setName(String name) {
        this.name = name;
    }
    
    // method for get name
    public String getName() {
        return this.name;
    }

    // method to add a score to the scores list
    public void addScore(double score) {
        this.scores.add(score);
    }

    // method to get total of scores
    public double getTotalScore() {
        double total = 0;
        for (int i = 0; i < this.scores.size(); i++) {
            total += this.scores.get(i);
        }
        return total;
    }

    // method to get average of scores
    public double getAverageScore() {
        return this.getTotalScore() / this.scores.size();
    }   

    // method to get highest score
    public double getHighScore() {
        double highest = 0;
        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.get(i) > highest) {
                highest = this.scores.get(i);
            }
        }
        return highest;
    }

}