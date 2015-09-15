
import ch.aplu.robotsim.*;
import java.util.Random;

import java.awt.Point;

	class Exercise5
	{
		String s="Counter";
		final int triggerLevel = 130;
		Motor mot1=new Motor(MotorPort.A);
		Motor mot2=new Motor(MotorPort.B);
		TouchSensor t1=new TouchSensor(SensorPort.S3);
		UltrasonicSensor s1=new UltrasonicSensor(SensorPort.S1);
		int counter=0;
		
		public void far(){
			
			mot1.backward();
			mot2.forward();
			
				if(s1.getDistance()<triggerLevel&& (s1.getDistance()>0))
				{  	
					mot1.stop();
					mot2.stop();
					near();
				}
				
			}
		
		    
		
		
		public void near(){

			//Object Found
	       	int aux=0;
	       	System.out.print("FOUND");
	    	counter++;   	
	    	
	    	mot1.forward();
			mot2.forward();
			//Stop at touching
			while(!t1.isPressed());
			mot1.stop();
			mot2.stop();
			
			NxtContext.setStatusText(s.concat((String.valueOf(counter))));
		    	
	    	//Returning after counting
	       	aux=s1.getDistance();	    
	       	do{	
	       	mot1.backward();
	    	mot1.backward();
	    	}
	    	while(s1.getDistance()<=aux);
	       	mot2.forward();
	       	for(int i=0;i<100;i++);
	       	mot2.backward();
		}
				
	  Exercise5()
	  {
		
		 //Instance a Robot
	    NxtRobot robot = new NxtRobot();
	    //Instance a Gear
	    
	    
	    //Add parts to Robot
	    robot.addPart(mot1);
	    robot.addPart(mot2);
	    
	    
	    
	    //Sensor1
	    robot.addPart(s1);
	    //Touch1
	    robot.addPart(t1);
	    
	    
	    
	    int a=0;
	    int aux=0;
	    Random r= new Random();
	    
	    
	    while(true)
	    {

	    	far();	   
	    		
	    	Tools.delay(5000);
	           	
	    	aux=s1.getDistance();    	
	    	
	    	//Out of Range
	    	while (aux==-1)
	    	{
	    			a=r.nextInt(5);
	    			switch(a)
	    			{
	    			case 1:
	    			mot1.forward();
	    			mot2.backward();
	    			Tools.delay(10);
	    			far();
	    			break;
	    			case 2:
	    			mot1.forward();
	    			mot2.forward();
	    			Tools.delay(10);
	    			far();
	    			break;
	    			case 3:
	    			mot1.backward();
		    		mot2.backward();
		    		Tools.delay(10);
		    		far();
		    		break;
	    			case 4:
	    			mot1.backward();
		    		mot2.forward();
		    		Tools.delay(10);
		    		far();
		    		break;
	    			}
		    		aux=s1.getDistance();
	    		}
	    		far();
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

		  Point[] mesh1 = new Point[] {new Point(50, 50),new Point(150,50),
				  					   new Point(50,250),new Point(100,250)};
		  Point[] mesh2 = new Point[] {new Point(250, 250),new Point(350,350),
				  						new Point(250, 450),new Point(350,450),};
		  Point[] mesh3 = new Point[] {new Point(300, 100),new Point(400,200),
										new Point(300, 300),new Point(400,300),};
		  NxtContext.useTarget("sprites/squaretarget.gif", mesh1,100, 150);
		  NxtContext.useTarget("sprites/squaretarget.gif", mesh2,300, 300);
		  NxtContext.useTarget("sprites/squaretarget.gif", mesh3,350, 200);
	
		  NxtContext.useObstacle("sprites/squaretarget.gif",100,150);
		  NxtContext.useObstacle("sprites/squaretarget.gif",300,300);
		  NxtContext.useObstacle("sprites/squaretarget.gif",350,200);
		  NxtContext.showStatusBar(30);

	    NxtContext.setStartPosition(260, 250);
	    NxtContext.setStartDirection(45);

	  }
	}