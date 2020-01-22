package testgame;

import java.awt.Color;
import java.util.ArrayList;

public class Functions {

	public static Color randomColor()
	{
		return new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	
	static ObjectMass addrandommovingmass(ArrayList<ObjectMass> masses)
	{
		Color color=Functions.randomColor();
		
		
		double mass= Math.random()*100;
		Vector vel=new Vector(Math.random()*5,Math.random()*5);
		double radius=Math.random()*0.1+0.05;
		Vector position=new Vector(Math.random()*16,Math.random()*10);
		boolean	wrongposition=true;
		while (wrongposition)
		{
			wrongposition=false;
			int i=masses.size();
			for (int z=0;z<i;z++)
			{	ObjectMass existingmass=masses.get(z);
				if(Vector.magnitude(position, existingmass.position)<existingmass.radius+radius)
				{
					wrongposition=true;
					break;
				}
			}
			}
	
		return new ObjectMass(mass,position,vel,color,radius,true);
	}
	
	static boolean inside(ObjectMass mass1,ObjectMass mass2)
	{
		boolean inside=false;
		
		double distance=Vector.magnitude(mass1.position, mass2.position);
		if (distance<mass1.radius||distance<mass2.radius)
		{
			inside=true;
		}
				
		
		return inside;
		
		
		
		
	}
}
