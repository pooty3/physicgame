package testgame;

import java.awt.Color;
import java.awt.Graphics;

public class ObjectMass {
	double mass,radius;
	Vector position,vel;
	double speed;
	Color color;
	boolean movable;
	
	public ObjectMass(double mass,Vector position,Vector vel, Color color,double radius,boolean movable)
	{this.mass=mass;
	this.position=position;
	this.vel=vel;
	this.color=color;
	this.radius=radius;
	this.movable=movable;}
	
	public void render(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)((position.x-radius)/Setting.pixelinterval),(int)((position.y-radius)/Setting.pixelinterval),(int)(2*radius/Setting.pixelinterval),(int)(2*radius/Setting.pixelinterval));
		g.setColor(Color.BLACK);
		g.drawString(String.format(" %.3f kg",this.mass),(int)((position.x-0.5*radius)/Setting.pixelinterval),(int)((position.y-0.1*radius)/Setting.pixelinterval)-10);
		g.drawString(String.format(" %.3f pixel/s",this.speed),(int)((position.x-0.5*radius)/Setting.pixelinterval),(int)((position.y-0.1*radius)/Setting.pixelinterval));
	}
	
	
	public void tick(Handler handler)
	{	if (movable)
		{Vector TotalForce=new Vector(0,0);
		
		int i =handler.masses.size();
		for (int z=0;z<i;z++)
		{
			ObjectMass tempObject=handler.masses.get(z);
			if(tempObject!=this)
			{
				Vector Force=Physics.GravitionalForce(this, tempObject);
				TotalForce.addVector(Force);
			}
			
	
			
			
		}
		
		Vector acc=Vector.scalarmultiplyVector(TotalForce, 1/this.mass);
	
		Vector newvel=Vector.addVector(this.vel, Vector.scalarmultiplyVector(acc, Setting.playinterval));
		
		Vector changeposition=Vector.scalarmultiplyVector(Vector.addVector(this.vel, newvel), 0.5*Setting.playinterval);
		this.position.addVector(changeposition);
		this.vel=newvel;
		this.speed=this.vel.mag();
		Physics.CollisionWithWall(this);
		
		}
	else 
		this.vel=new Vector(0,0);
		
		if(this.radius>1)
		{
			double minus=(this.radius-1)/1000;
			this.radius-=minus;
			
		}
		
	this.vel.scalarmultiplyVector(1-Setting.friction/1000);
		
		
	}

}
