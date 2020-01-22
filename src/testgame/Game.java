package testgame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
private Thread thread;
	public boolean going;
	public Handler handler=new Handler();
	public static int gamewidth=1600,gameheight=1000,windowwidth=1800,windowheight=1000;
	public Setting settings=new Setting();
	public static boolean refreshscreen;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Game();

	}
	public Game() {
		Window mainwindow=new Window("game",this);
		
		start();
		settings=new Setting(200,1000,handler);
		settings.setLocation(1600,0);
		
		mainwindow.add(settings);
		Thread settingthread=new Thread(settings);
		settingthread.start();
		
		
	}
	
	private void start()
	{going=true;
	thread=new Thread(this);
	thread.start();
		
	}
	private void stop()
	{
		going=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run() {
		
		Setting.pixelinterval=0.01;
		
		handler.masses.add(new ObjectMass(50,new Vector(8,5),new Vector(0,0),Color.RED,0.1,false));
		handler.masses.add(new ObjectMass(50,new Vector(13,5),new Vector(0,-2),Color.RED,0.1,true));
		
		
		
		
		
		  this.requestFocus();
		  long lastTime = System.nanoTime();
		  double amountOfTicks = 1000.0;
		  double ns = 1000000000 / amountOfTicks;
		  double delta = 0;
		  long timer = System.currentTimeMillis();
		  int frames = 0;
		  while(going) {
		   long now = System.nanoTime();
		   delta += (now - lastTime) / ns;
		   lastTime = now;
		   while(delta >= 1) {
		    tick();
		    //updates++;
		    
		 
		    delta--;
		
		   }
		   render();
		   frames++;
		   if(System.currentTimeMillis() - timer > 1000) {
		    timer += 1000;
		    frames = 0;
		    //updates = 0;
		   }
		  }
		  stop();
		 }
	
	
	public void tick()
	{
		
		handler.tick();
		
		
	}

	public void render() 
	{
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,gamewidth,gameheight);
		Game.refreshscreen=true;
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, gamewidth-1, gameheight);
		handler.render(g);
		
		g.dispose();
		bs.show();
		
		
		
		
	}

}
