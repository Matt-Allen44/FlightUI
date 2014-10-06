package com.mallen.flightui.instruments;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import com.mallen.flightui.ui.modules.Theme;
import com.mallen.flightui.wrapper.FLUI_GLOBAL;
import com.mallen.flightui.wrapper.FLUI_READER;
import com.mallen.flightui.wrapper.flui.FLUIAircraft;

public class ArtificialHorizon {
	int x = 0, y = 0;
	int height = 0, width = 0;
	
	public ArtificialHorizon(int horizonX, int horizonY, int horizonWidth, int horizonHeight, Color colS, Color colG, Color colI){
		x = horizonX;
		y = horizonY;
		height = horizonHeight;
		width = horizonWidth;
		f = new Font("Verdana", Font.PLAIN, height/100);
		
		colSky = colS;
		colGround = colG;
		colInd = colI;
		
	}
	
	public void setSize(int w, int h){
		width = w;
		height = h;
		f = new Font("Verdana", Font.PLAIN, height/50);
	}
	
	Font f;
	Color colSky = new Color(25, 110, 255);
	Color colGround = new Color(155, 90, 0);
	Color colInd = new Color(0, 0, 0);

	private double pitch;
	private double drawOffset;
	private double roll;
	
	public void loop(){
		while(true){
		}
	}
	
	public void draw(Graphics g, ImageObserver io){
		
		//////////
		
		Theme.setTheme(new Color(50, 100, 250), new Color(80, 50, 50), new Color(250, 250, 250), new Color(205, 205, 205), new Color(55, 55, 55), new Color(50, 190, 90), new Color(220, 200, 0),new Color(190, 50, 50));
		Theme.vhGround = new Color(126, 92, 52);
		Theme.vhSky = new Color(0, 102, 253);
		
		drawOffset = (height/180*(180-pitch+10));
		pitch = FLUI_GLOBAL.pitch;
		roll = FLUI_GLOBAL.roll;

		Graphics2D g2d = (Graphics2D)g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(f);
		
	    Rectangle vhGround = new Rectangle(x-width, y-height, width*3, height*3);
		Rectangle vhSky = new Rectangle(x-width, y-height, width*3, (int) (height+drawOffset));
		
		//Set orange as background
		g2d.rotate(Math.toRadians(roll), x+(width/2), y+(height/2));
		g2d.setColor(colGround);
		g2d.fill(vhGround);
		
		g2d.setColor(colSky);
		g2d.fill(vhSky);
		
		//Drawing UI Markers for pitch
		g.setColor(colInd);
		
		for(int i = -60; i <= 60; i += 10){
			g.fillRect(x + width/2 - width/8, (int) ((int) y + (drawOffset - height/180*i-1)), width/4, 2);
			g.drawString("" + i, x + width/2 - width/8 - 50, (int) ((int) y + (drawOffset - height/180*i)));
		}
		
		for(int i2 = -25; i2 <= 25; i2 += 10){
			g.fillRect(x + width/2 - (width/8)/2, (int) ((int) y + (drawOffset - height/180*i2-1)), width/8, 2);
			g.drawString("" + i2, x + width/2 + width/8 + 25, y + (int) (drawOffset - height/180*i2));
	
		}
		
		/**
		 * INSERT DOUBLE BUFFERING TO SORT OUT THE GLITCHY RENDERING
		 */
		
		g2d.rotate(Math.toRadians(-roll), x+width/2, y+height/2);
		
		//Onscreen Indicator to show center of screen
		g.fillRect(x + width/2-width/8, y + height/2-4, width/4, height/120);
		g.fillRect(x + width/2-width/8, y + height/2-4, height/120, height/30);
		g.fillRect(x + width/2-width/8+width/4-height/120, y + height/2-4, height/120, height/30);
			
		
		//BLACKING OUT OVERDRAW
		g.setColor(Color.BLACK);
		g.fillRect(x-width, y-height, width, height*3);
		g.fillRect(x+width, y-height, width, height*3);
		
		g.fillRect(x, y-height, width*3, height);
		g.fillRect(x, y+height, width*3, height);
		
		
		////////////////
		Font TextFont = new Font("Verdana", Font.BOLD, 12);
		

		g.setColor(Color.DARK_GRAY);
		g.fillRect(10, 10, 80, 1004);
		
		g.setColor(Color.black);
		g.fillRect(15, 15, 70, 1000);
		
		g.setColor(Color.RED);
		g.fillRect(0, 500-1, 300, 2);
		
		
		g.setFont(TextFont);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D stringRect = TextFont.getStringBounds("1000", fm.getFontRenderContext());
		
		g.setColor(Color.WHITE);
		
		int alt = FLUIAircraft.AltFeet();
		for(int i = -10; i < 10; i++){
			
				int offset = (alt - ((alt/100)*100))/2;
				g.drawString("" + (((alt/100)*100)-(100*i)), (int) (15 + 35 - stringRect.getWidth()/2), (int) (15 - stringRect.getHeight()) + 1000/2 + (i * 1000/20) + offset);
		}
		
		////////////////
	}
}
