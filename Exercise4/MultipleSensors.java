package sprites;
import ch.aplu.robotsim.*;
/**
 * 
 * @author Ali Varfan
 * The MultipleSensors Class solve the first part of 5th assignment
 *
 */
public class MultipleSensors {
	
	
	MultipleSensors(){
		
		NxtRobot robot = new NxtRobot();
		Gear gear = new Gear();
		SoundSensor ss = new SoundSensor(SensorPort.S2);
		TouchSensor ts = new TouchSensor(SensorPort.S3);
		LightSensor ls = new LightSensor(SensorPort.S1);
		int triggerLevel = 10;
		
		robot.addPart(gear);
		robot.addPart(ss);
		robot.addPart(ts);
		robot.addPart(ls);
		
		
		while(true){
			/* when the robot hear a voice louder than the trigger value starts moving */
			if(ss.getValue()>triggerLevel){
				
				for(;;){
					if (ts.isPressed()){
						gear.forward(500);
						gear.stop();
						break;
					}
						
				    if (ls.getValue() < 500)
				      gear.rightArc(0.07);
				    else
			          gear.leftArc(0.07);
					
				}
				
			}
		}
		
	}
	
	//-------------------------Environment-------------------------------
	
	static{
		NxtContext.setStartPosition(80, 270);
		NxtContext.useBackground("/home/a/workspace/Assignment2/src/sprites/background.gif");
		NxtContext.useObstacle("/home/a/workspace/Assignment2/src/sprites/wall4.gif", 270, 315);
		
		}

}
