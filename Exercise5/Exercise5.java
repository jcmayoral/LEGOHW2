
import ch.aplu.robotsim.*;
import java.util.Random;

import java.awt.Point;

	class Exercise5
	{
		final int triggerLevel = 300;
		Motor mot1=new Motor(MotorPort.A);
		Motor mot2=new Motor(MotorPort.B);
		TouchSensor t1=new TouchSensor(SensorPort.S3);
		
		public void far(){

		    mot1.forward();
		    mot2.backward();
		    
		}
		
		public void near(){
		
		mot2.forward();
		
		while(!t1.isPressed());
		
		mot1.stop();
		mot2.stop();
		
		
		}
				
	  Exercise5()
	  {
		String s="Counter";
		 //Instance a Robot
	    NxtRobot robot = new NxtRobot();
	    //Instance a Gear
	    
	    
	    //Add parts to Robot
	    robot.addPart(mot1);
	    robot.addPart(mot2);
	    UltrasonicSensor s1=new UltrasonicSensor(SensorPort.S1);
	    
	    
	    //Sensor1
	    robot.addPart(s1);
	    //Touch1
	    robot.addPart(t1);
	    
	    far();
	    int counter=0;
	    int aux=0;
	    Random r= new Random();
	    
	    
	    while(true)
	    {
	    	   
	    	aux=s1.getDistance();
	    	if(aux==-1)
	    	{
	    		NxtContext.setStatusText(s.concat((String.valueOf(aux))));
	    		while(aux==1){
	    			System.out.println(aux);
	    			switch(r.nextInt(5)){
	    			case 1:
	    			mot1.forward();
	    			mot2.backward();
	    			Tools.delay(1000);
	    			break;
	    			case 2:
	    			mot1.forward();
	    			mot2.forward();
	    			Tools.delay(1000);
	    			break;
	    			case 3:
	    			mot1.backward();
		    		mot2.backward();
		    		Tools.delay(1000);
		    		break;
	    			case 4:
	    			mot1.backward();
		    		mot2.forward();
		    		Tools.delay(1000);
		    		break;
	    			}
	    		aux=s1.getDistance();
	    		}
	    	
	    	s="Counter: ";
		    NxtContext.setStatusText(s.concat((String.valueOf(counter))));
	       	if(aux<triggerLevel&&aux>0)
	    	{
	    	s="Distance";
	    	near();
	    	counter++;
	    	
	    	do{	
	       	mot1.backward();
	    	mot2.backward();
	    	Tools.delay(10);
	    	}
	    	while(s1.getDistance()!=aux);
	
	 
	    	
	
	 
//	    	else
//	    	far();}
	    	aux=s1.getDistance();
	    	}
	    	}
	    }
	    //robot.exit();
	  }

	  public static void main(String[] args)
	  {
	    new Exercise5();
	  }
	  
	  //Environment
	  // ------------------ Environment --------------------------
	  static
	  {
		  Point[] mesh = new Point[] {new Point(50, 50),new Point(60,60),
				  new Point(50, 60),new Point(60,50),
				  
				  };
		  Point[] mesh1 = new Point[] {new Point(50, 50),new Point(60,60)};
		  Point[] mesh2 = new Point[] {new Point(300, 300),new Point(300,300)};
		  NxtContext.useTarget("sprites/squaretarget.gif", mesh,355, 355);
		  NxtContext.useTarget("sprites/squaretarget.gif", mesh1,405, 405);
		 // NxtContext.useTarget("sprites/squaretarget.gif", mesh2,300, 300);
		  NxtContext.useObstacle("sprites/squaretarget.gif",355,355);
		  NxtContext.useObstacle("sprites/squaretarget.gif",405,405);
	//	  NxtContext.useObstacle("sprites/squaretarget.gif",55,55);
		  NxtContext.showStatusBar(30);

	    NxtContext.setStartPosition(3, 410);
	    NxtContext.setStartDirection(45);

	  }
	}