
public class StudentTester{
    public static void main(String[] args) {
        Student john = new Student(); 
        Student emma = new Student(); 

        john.setName("John"); 
        emma.setName("Emma"); 
        emma.addScore(8.5); 
        emma.addScore(9.5); 
        System.out.printf("Student: %s\n", emma.getName()); 
        System.out.printf("Total score: %.2f\n", emma.getTotalScore()); 
        System.out.printf("Average score: %.2f\n", emma.getAverageScore()); 
        System.out.printf("Higher score: %.2f\n", emma.getHighScore()); 

        john.addScore(9.3); 
        john.addScore(7.4); 
        john.addScore(8.5); 
        john.addScore(6.6); 
        System.out.printf("Student: %s\n", john.getName()); 
        System.out.printf("Total score: %.2f\n", john.getTotalScore()); 
        System.out.printf("Average score: %.2f\n", john.getAverageScore()); 
        System.out.printf("Higher score: %.2f\n", john.getHighScore()); 
    }
}