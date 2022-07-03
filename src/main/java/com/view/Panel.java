package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import com.Application;
import com.model.City;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Application application;
	
	public Panel(Application application) {
		super("Panel");
		this.application = application;
		
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setLayout(null);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		
		City[] cityArray = application.getGeneration().getBestSolution().getPath();
		
		for (int i = 0; i < cityArray.length; i++) {
			
			int x = 600 + 70 * ((int) cityArray[i].getLongitude() - 4);
			int y = 900 - 100 * ((int) cityArray[i].getLatitude() - 42);
			String name = cityArray[i].getName();
			
			int xB;
			int yB;
			String nameB;
			if (i != cityArray.length - 1) {
				xB = 600 + 70 * ((int) cityArray[i + 1].getLongitude() - 4);
				yB = 900 - 100 * ((int) cityArray[i + 1].getLatitude() - 42);
				nameB = cityArray[i + 1].getName();
			}
			else {
				xB = 600 + 70 * ((int) cityArray[0].getLongitude() - 4);
				yB = 900 - 100 * ((int) cityArray[0].getLatitude() - 42);
				nameB = cityArray[0].getName();
			}
			
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D rect = fm.getStringBounds(name, g);
			Rectangle2D rectB = fm.getStringBounds(nameB, g);
			
			g.setColor(Color.BLACK);
			g.drawLine(x, y - fm.getAscent(), xB + (int) rectB.getWidth() / 2, yB + (int) rectB.getHeight() / 2);
			
			g.setColor(Color.WHITE);
			g.fillRect(x, y - fm.getAscent(), (int) rect.getWidth(), (int) rect.getHeight());
			
			g.setColor(Color.BLACK);
			g.drawString(name, x, y);
		}
	}
	
}
