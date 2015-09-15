import ch.aplu.robotsim.Gear;
import ch.aplu.robotsim.LightSensor;
import ch.aplu.robotsim.NxtContext;
import ch.aplu.robotsim.NxtRobot;
import ch.aplu.robotsim.SensorPort;
import ch.aplu.robotsim.TouchSensor;



public class Series3Ex1 {

	Series3Ex1(){

			NxtRobot robot = new NxtRobot();
			Gear gear = new Gear();
		    robot.addPart(gear);
		    TouchSensor ts = new TouchSensor(SensorPort.S3);  //creates TouchSensor
		    robot.addPart(ts);  //fixes the TouchSensor to the Robot
		    gear.setSpeed(30);
	    
		    gear.forward();
		    
		    while(true){
		    
		    while (true)
		    {
		    	
		      if (ts.isPressed())  //if the TouchSensor is pressed do
		      {
		        gear.backward(1000);
		        gear.left(1250);
		        gear.forward(2000);
		        gear.left(1250);
		        gear.forward();
		        break;
		      }
		    }
		    
		    while (true){
		    	
		    
		    
		  	      if (ts.isPressed())  //if the TouchSensor is pressed do
			      {
			        gear.backward(1000);
			        gear.right(1250);
			        gear.forward(2000);
			        gear.right(1250);
			        gear.forward();
			        break;
		      }
		    }
		  }		    

	}

	public static void main(String[] args) {

		new Series3Ex1();
		
	}


//Environment-----------------------------------------------------------------
 	
	static {
		//430,60 initial
		NxtContext.setStartPosition(100, 60);
		NxtContext.setStartDirection(180);
		NxtContext.useObstacle("sprites/rectangle.gif", 250, 250);  		
		}
	
}
