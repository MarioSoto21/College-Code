public class MicrowaveTester
{
	public static void main(String[] args)
	{
		Microwave appliance = new Microwave();
		
	appliance.increaseTime();
	appliance.increaseTime();
	appliance.increaseTime();
	appliance.switchPower();
	appliance.start();
	System.out.println("Expected: Cooking for 90 seconds at level 2");
	
	appliance.reset();
	
	appliance.increaseTime();
	appliance.switchPower();
	appliance.switchPower();
	appliance.start();
	System.out.println("Expected: Cooking for 30 seconds at level 1");
	}
}