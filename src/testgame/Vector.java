package testgame;

public class Vector {
	double x,y;
	public Vector(double x,double y)
	{this.x=x;
	this.y=y;
	}
	public double mag()
	{
		return Vector.magnitude(this,new Vector(0,0));
		
		
	}
	
	public static Vector getunitVector(Vector a)
	{
		double mag=Vector.magnitude(a,new Vector(0,0));
		
		Vector unita=Vector.scalarmultiplyVector(a, 1/mag);
		return unita;
		
		
		
	}
	
	public void addVector(Vector a)
	{
		this.x+=a.x;
		this.y+=a.y;
	}
	public void subtractVector(Vector a)
	{
		this.x-=a.x;
		this.y-=a.y;
	}
	public void scalarmultiplyVector(double a)
	{
		this.x*=a;
		this.y*=a;
	}
	public static Vector addVector(Vector a,Vector b)
	{
		return new Vector(a.x+b.x,a.y+b.y);
	}
	public static Vector subtractVector(Vector a,Vector b)
	{
		return new Vector(a.x-b.x,a.y-b.y);
	}
	public static Vector scalarmultiplyVector(Vector a,double b)
	{
		return new Vector(a.x*b,a.y*b);
	}
	public static double dotproduct(Vector a, Vector b)
	{return a.x*b.x+a.y*b.y;}
	public static double crossproduct(Vector a,Vector b)
	{
		
		return (a.x*b.y-a.y*b.x);
	}
	
	public static double magnitudesquared(Vector a,Vector b)
	{
		
		return(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
	}
	
	public static double magnitude(Vector a,Vector b)
	{
		
		
		
		return (Math.pow(Vector.magnitudesquared(a, b),0.5));
		
	}
	
}
