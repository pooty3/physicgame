package testgame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Setting extends JPanel implements Runnable{
	static double playinterval=0.001;
	static double pixelinterval=1;
	JButton resetbutton;
	JButton addrandommass;
	JButton addfriction;
	JButton reducefriction;
	JLabel frictioncount;
	JLabel masscount;
	JButton resetscreen;
	Handler handle;
	static double friction=0;
	public Setting()
	{}
	public Setting(int width,int height,Handler h)
	{
		super();
		this.setSize(new Dimension(width,height));
		this.setLayout(null);
		
		this.handle=h;
		
		
		
		
		
	}
	
	public void tick()
	{
		
		frictioncount.setText("Friction: "+Setting.friction);
		masscount.setText("Number of masses: "+handle.masses.size());
		
	}
	@Override
	public void run() {
		resetbutton=new JButton("reset");
		resetbutton.setSize(100,20);
		resetbutton.setLocation(0,0);
		resetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				handle.masses=new ArrayList<ObjectMass>();
				Game.refreshscreen=true;
				
			}});
		
		resetscreen=new JButton("reset");
		resetscreen.setSize(100,20);
		resetscreen.setLocation(100,0);
		resetscreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				
				Game.refreshscreen=true;
				
			}});
		
		
		addrandommass=new JButton("add random mass");
		addrandommass.setSize(100,20);
		addrandommass.setLocation(0,30);
		addrandommass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{	
				if (handle.masses.size()<10)
				handle.addMass(Functions.addrandommovingmass(handle.masses));
				
			}});
		
		masscount=new JLabel();
		masscount.setSize(200,20);
		masscount.setLocation(0,60);
		
		frictioncount=new JLabel();
		frictioncount.setSize(200,20);
		frictioncount.setLocation(0,120);
		
		addfriction=new JButton("add friction");
		addfriction.setSize(100,20);
		addfriction.setLocation(0,90);
		addfriction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				Setting.friction+=0.1;
				
			}});
		
		reducefriction=new JButton("reduce frictio");
		reducefriction.setSize(100,20);
		reducefriction.setLocation(100,90);
		reducefriction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{	
				if (friction>0.1)
				Setting.friction-=0.1;
				else
				Setting.friction=0;
				
			}});
		
		
		this.add(resetscreen);
		this.add(reducefriction);
		this.add(frictioncount);
		this.add(addfriction);
		this.add(addrandommass);
		this.add(resetbutton);
		this.add(masscount);
		
		while (true)
		{try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tick();
		}
	}
}
