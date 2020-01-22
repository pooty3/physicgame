package testgame;

import java.awt.Color;

public class Physics {
	
	final static double G=1;
	final static double Solarmass=1.989 *Math.pow(10,30);
	final static double lightminute=299792458;
	final static double earthradius=6371000;
	
	
	
	
	public static Vector GravitionalForce(ObjectMass main,ObjectMass other)
	{	
		double distance=Vector.magnitude(main.position, other.position);
		double forcedistance=distance;
		if (distance<main.radius+other.radius)
		{
			forcedistance=main.radius+other.radius;
		}
		double Force=Physics.G*main.mass*other.mass/(Math.pow(forcedistance, 2));
		
		double Fx=Force*(other.position.x-main.position.x)/distance;
		double Fy=Force*(other.position.y-main.position.y)/distance;
		return new Vector(Fx,Fy);
		
		
	}
	
	public static void CollisionWithWall(ObjectMass tempmass)
	{
		if(((tempmass.position.x<0)&&(tempmass.vel.x<0))||((tempmass.position.x>Game.gamewidth*Setting.pixelinterval)&&(tempmass.vel.x>0)))
			tempmass.vel.x*=-1;
		
		if(((tempmass.position.y<0)&&(tempmass.vel.y<0))||((tempmass.position.y>Game.gameheight*Setting.pixelinterval)&&(tempmass.vel.y>0)))
			tempmass.vel.y*=-1;}

	
	
	public static boolean isCollided(ObjectMass mass1,ObjectMass mass2)
	{
		boolean collided=false;
		double distance=Vector.magnitude(mass1.position, mass2.position);
		if (distance<mass1.radius+mass2.radius)
			collided=true;
		
		return collided;
	}
	
	
	public static void Collision(ObjectMass mass1,ObjectMass mass2)
	{ 
		Vector changelineaxes=Vector.getunitVector(Vector.subtractVector(mass2.position, mass1.position));
		Vector nonchangelineaxes=new Vector(changelineaxes.y,changelineaxes.x*-1);
		double m1=mass1.mass;
		double m2=mass2.mass;
		double nonchangevel1=Vector.dotproduct(nonchangelineaxes, mass1.vel);
		double nonchangevel2=Vector.dotproduct(nonchangelineaxes, mass2.vel);
		double v1=Vector.dotproduct(changelineaxes, mass1.vel);
		double v2=Vector.dotproduct(changelineaxes, mass2.vel);
		
		double newchangevel1=(m1-m2)*v1/(m1+m2)+2*m2*v2/(m1+m2);
		double newchangevel2=2*m1*v1/(m1+m2)-(m1-m2)*v2/(m1+m2);
		
		Vector mass1vel=Vector.addVector(Vector.scalarmultiplyVector(changelineaxes,newchangevel1),Vector.scalarmultiplyVector(nonchangelineaxes, nonchangevel1));
		Vector mass2vel=Vector.addVector(Vector.scalarmultiplyVector(changelineaxes,newchangevel2),Vector.scalarmultiplyVector(nonchangelineaxes, nonchangevel2));
		
		
		mass1.vel=mass1vel;
		mass2.vel=mass2vel;
		
		
		
		
		
		
		
	}

	public static void inelasticCollision(ObjectMass mass1,ObjectMass mass2)
	{
		double m1=mass1.mass,m2=mass2.mass;
		double newmass=m1+m2;
		Vector newposition=Vector.addVector(Vector.scalarmultiplyVector(mass1.position, m1),Vector.scalarmultiplyVector(mass2.position, m2));
		newposition.scalarmultiplyVector(1/(m1+m2));
		Vector newvel=Vector.addVector(Vector.scalarmultiplyVector(mass1.vel, m1),Vector.scalarmultiplyVector(mass2.vel, m2));
		newvel.scalarmultiplyVector(1/(m1+m2));
		
		Color newcol=Functions.randomColor();
		
		double newrad=mass1.radius+mass2.radius;
		
		
		
		
		mass1.mass=newmass;
		mass1.position=newposition;
		mass1.vel=newvel;
		mass1.radius=newrad;
		mass1.color=newcol;
		
		
		
		
		
		
	}
	
	
	


}
