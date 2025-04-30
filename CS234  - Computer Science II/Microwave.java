public class Microwave {
int time;
int level;

    public Microwave()
    {
        // microwave initialization
        level = 1;
    time = 0;
    }
    // Increases the time on the timer by 30 seconds.
    public void increaseTime()
    {
		
        // increase time by 30 secs
    time += 30;

    }
	
    // Switches the power level from low to high, or vice versa.
    public void switchPower()
    {
    if(level == 2)
    level = 1;
        if(level == 1)
    level = 2;
    }

    // Resets the microwave to its initial state.
    public void reset()
    {
        // to reset set time to 0 and level to 1
        level = 1;
    time = 0;
    }

    // Starts the microwave.
    public void start()
    {
    System.out.println("Cooking for " + time + " seconds at level " + level);
    }   
}