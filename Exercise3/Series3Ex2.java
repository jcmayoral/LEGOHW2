import ch.aplu.robotsim.Gear;
import ch.aplu.robotsim.LightSensor;
import ch.aplu.robotsim.NxtContext;
import ch.aplu.robotsim.NxtRobot;
import ch.aplu.robotsim.SensorPort;
import ch.aplu.robotsim.TouchSensor;



public class Series3Ex2 {

	

	Series3Ex2(){

			NxtRobot robot = new NxtRobot();
			Gear gear = new Gear();
		    robot.addPart(gear);
		    TouchSensor ts = new TouchSensor(SensorPort.S3);  
		    robot.addPart(ts);
		    gear.setSpeed(30);
		    
		   
		    //just to find first wall assuming 
		    //the robot starts in front of the path 
		    
		    //find right-wall-----------------------------------------------
		    gear.right(1270);
		    int ROBOT_STEPS = 2500;
		    int ROBOT_COME_AND_GO = 1000;
		    int DEGREES = 1280;
		    
		    while(true){    	
		    	//if you rotate and you find a wall
		    	//you have already found a wall! 
		    	if(ts.isPressed()){
		    	gear.backward(ROBOT_COME_AND_GO);
		    	gear.left(DEGREES);
		    	gear.forward(ROBOT_STEPS);	
		    	break;
		    	}
		    	//if not! you will have to look for one
		    	else{
		    		gear.forward();
		    			//you found it! 
		    			if(ts.isPressed()){
		    				System.out.println("I found the first wall"); 
		    				gear.backward(ROBOT_COME_AND_GO);
		    				gear.left(DEGREES);
		    				gear.forward(ROBOT_STEPS);	
		    				break;
		    				}
		    		}

		    	}
//------------------------------------------------------------------
System.out.println("FIRST LOOP"); 

//CONTROLLER VARIABLES 
int d=0;
int CORRECTION=0;
int SMALL_ROBOT_STEPS=10;
int tmp=0;

while(true){

//CONTROLLER-------------------------------------
d++;
if(d==2){
System.out.println("I corrected my orentation");
//CORRECTION=100;
d=0;
}
else{
CORRECTION=0;	
}
//-----------------------------------------------
	
	gear.right(DEGREES+CORRECTION);
	gear.forward(ROBOT_COME_AND_GO);
	if(ts.isPressed()){
		gear.backward(ROBOT_COME_AND_GO);
		gear.left(DEGREES + CORRECTION);
		System.out.println("I found a wall!");
		//HERE IS A PROBLEM
		//what happens if it crashes against a wall!
		//while(true){
		//	gear.forward
		//}
		while(true){
		gear.forward(SMALL_ROBOT_STEPS);
		tmp = tmp+SMALL_ROBOT_STEPS;

			if(tmp==ROBOT_STEPS){
			System.out.println("NO OBSTACLES");	
			tmp=0;
			break;
			}
			else{
				if(ts.isPressed()){
					System.out.println("DANGER!");
					gear.right(DEGREES + CORRECTION);
					gear.forward(ROBOT_COME_AND_GO);
					if(ts.isPressed()){
						System.out.println("THE OTHER WAY!");
						gear.left(2*(DEGREES + CORRECTION));
					}
					else{
						System.out.println("It must be this way captain!");
						gear.forward(ROBOT_STEPS);
						break;
					}
				}
			}
		
		}
		
		}//this closes de if

	

}



}


	
	
	
	public static void main(String[] args) {

		new Series3Ex2();
		
	}


//Environment-----------------------------------------------------------------
 	
	static {
		
		NxtContext.setStartPosition(340, 460);
		NxtContext.setStartDirection(270);
		NxtContext.useObstacle("sprites/parcours.gif", 250, 250);  		
		}
	
}
