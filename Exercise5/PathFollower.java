package sprites;
import ch.aplu.robotsim.*;

/**
 * 
 * @author Ali Varfan
 *
 */
public class PathFollower {
	
	public PathFollower(){
		
		String touchMessage = "Reach the Wall"; // the message that will be shown when the robot touch the wall
		NxtRobot robot = new NxtRobot();
	    Gear gear = new Gear();
	    LightSensor ls1 = new LightSensor(SensorPort.S1); // right
	    LightSensor ls2 = new LightSensor(SensorPort.S2); // left
	    TouchSensor ts = new TouchSensor(SensorPort.S3);
	    SoundSensor ss = new SoundSensor(SensorPort.S4);
	    
	    /* adding different parts to robot */
	    robot.addPart(gear);
	    robot.addPart(ls1);
	    robot.addPart(ls2);
	    robot.addPart(ts);
	    robot.addPart(ss);
	    
	    gear.setSpeed(80);
	    gear.forward();
		

	    while (true)
	    {
	    	/*The value of the right sensor is saved as rightValue*/
	      int rightValue = ls1.getValue();
	      int leftValue = ls2.getValue();
	      int d = rightValue - leftValue;
	      if (d > 100) // left dark , turn right
	        gear.rightArc(0.1);
	      if (d < -100) // right dark, turn left
	        gear.leftArc(0.1);
	      if (d > -100 && d < 100 && rightValue > 500)
	        gear.forward();
	      if (ts.isPressed()){   // when robot touch the wall it turns left
	    	  System.out.println(touchMessage);
	    	  gear.left();
	      }
	    }
	}
	
	//--------------Environment------------------------
	
	
	static{
		
		NxtContext.setStartPosition(430, 85); //set the start position of the robot
		NxtContext.useBackground("/home/a/workspace/Assignment/src/sprites/track.png");
		NxtContext.useObstacle("/home/a/workspace/Assignment/src/sprites/wall1.gif", 80, 450); // add wall
		NxtContext.useObstacle("/home/a/workspace/Assignment/src/sprites/wall2.gif", 440, 80); // add wall
	}

}
