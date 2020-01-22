package testgame;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	ArrayList<ObjectMass> masses;
	
	
	public Handler() 
	
	{	
		masses=new ArrayList<ObjectMass>();
		
		
	}
	
	public void addMass(ObjectMass objectmass)
	{masses.add(objectmass);}
	
	public void removeMass(ObjectMass objectmass)
	{masses.remove(objectmass);}
	public synchronized void tick()
	{
		int i=masses.size();
		for(int z=0;z<i;z++)
		{
			ObjectMass tempObject= masses.get(z);
			tempObject.tick(this);
		}
		ArrayList<ObjectMass> toberemoved=new ArrayList<ObjectMass>();
		
		for(int z=0;z<i;z++)
		{
			for(int z1=z+1;z1<i;z1++)
			{
				if(Physics.isCollided(masses.get(z), masses.get(z1)))
				{	 
					
					
					if(Functions.inside(masses.get(z), masses.get(z1)))
					{	System.out.println(masses.get(z).mass);
						System.out.println(masses.get(z1).mass);
						Physics.inelasticCollision(masses.get(z), masses.get(z1));
						toberemoved.add(masses.get(z1));
						System.out.println(masses.get(z).mass);
						System.out.println("merged!");
					
					}
					else
						Physics.Collision(masses.get(z), masses.get(z1));
				}
				
			}
			
		}
		
		
		masses.removeAll(toberemoved);
	}
	
	public void render(Graphics g)
	{	
		int i=masses.size();
		for(int z=0;z<i;z++)
		{
			masses.get(z).render(g);
			
		}
		
		
		
	}
	
	
	
	
}
