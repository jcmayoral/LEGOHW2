// 
import ch.aplu.robotsim.*;

class Parcours
{
	//TouchForward
    public TouchSensor leftsensor=new TouchSensor(SensorPort.S2);
    //TouchBackward
    public TouchSensor rightsensor=new TouchSensor(SensorPort.S1);
    //front,back,left,right
	public boolean flagl,flagr,out=false;
	//gear
	public Gear gear = new Gear();
	
	
	void check()
	{
	flagl=false;
	flagr=false;
	flagl=leftsensor.isPressed();
	flagr=rightsensor.isPressed();
	
	}
	
	void frontmove()
	{
		gear.forward();

	}

	
	void rightmove()
	{
		gear.backward(1000);
		gear.left(200);
		gear.forward();

	}
	
	void leftmove()
	{
		gear.backward(800);
		gear.right(300);
		gear.forward();

	}
	void backmove()
	{
		gear.backward(900);
		gear.left(200);
	}
	
  Parcours()
  {
	
	 //Instance a Robot
    NxtRobot robot = new NxtRobot();
    //Instance a Gear
    
    //Add parts to Robot
    robot.addPart(gear);
    robot.addPart(leftsensor);
    robot.addPart(rightsensor);
    
    gear.setSpeed(70);
    
    gear.forward();
    while (true)
    {
    
    check();
    
    if(flagl&&flagr)
    	backmove();   
    if(flagr&&!flagl)
    	rightmove();
    if(flagl&&!flagr)
      	leftmove();
    if(!flagr&&!flagl)
    	frontmove();
    
    }
	//gear.stop();
    //Quitting the instance    
    //robot.exit();
    
  }

  public static void main(String[] args)
  {
    new Parcours();
  }
  
  //Environment
  static
  {		
	  Obstacle obs=new Obstacle("sprites/parcours.gif");
	  NxtContext.useObstacle(obs);
	  NxtContext.setStartDirection(-90);
	  NxtContext.setStartPosition(280, 430);	  
	  }
} 