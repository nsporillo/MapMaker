package net.milkycraft;

import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_GASP;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Utility {

	public static BufferedImage getImage(String text, int size) {
		return Utility.getImage(text, Color.BLACK, size);
	}
	
	public static BufferedImage getImage(String text, Color color, int size) {
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, size);
		Rectangle2D bounds = f.getStringBounds(text, frc);
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(255, 255, 255, 0));
		g.fillRect(0, 0, w, h);
		g.setColor(color);
		g.setFont(f);
		RenderingHints rh = new RenderingHints(KEY_TEXT_ANTIALIASING,
				VALUE_TEXT_ANTIALIAS_GASP);
		g.setRenderingHints(rh);
		g.drawString(text, (float) bounds.getX(), (float) -bounds.getY());
		g.dispose();
		return image;
	}
}
