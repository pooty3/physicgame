package testgame;

import java.awt.Dimension;
import javax.swing.*;
public class Window extends JFrame{
	public Window( String title, Game game) 
	{super(title);
	this.setPreferredSize(new Dimension(Game.windowwidth,Game.windowheight));
	this.setMaximumSize(new Dimension(Game.windowwidth,Game.windowheight));

	this.setMinimumSize(new Dimension(Game.windowwidth,Game.windowheight));
	
	JPanel panel=new JPanel();
	this.setLayout(null);
	game.setSize(new Dimension(Game.gamewidth,Game.gameheight));
	game.setLocation(0,0);
	this.add(game);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setLocationRelativeTo(null);

	}
	
}
