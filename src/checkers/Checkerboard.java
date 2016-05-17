package checkers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.*;

public class Checkerboard extends Applet implements MouseListener {
	private Point p;
	private Graphics g;

	public void paint(Graphics g) {

		int row;
		int col;
		int x, y;

		for (row = 0; row < 8; row++) {
			for (col = 0; col < 8; col++) {
				x = col * 20;
				y = row * 20;
				if ((row % 2) == (col % 2))
					g.setColor(Color.red);
				else
					g.setColor(Color.white);
				g.fillRect(x, y, 20, 20);

			}
		}
		for (row = 0; row < 8; row++) {
			if (row <= 1) {
				for (col = 0; col < 8; col++) {
					x = col * 20;
					y = row * 20;
					if ((row % 2) == (col % 2)) {
						g.setColor(Color.black);

						g.fillOval(x + 2, y + 2, 16, 16);
					}
				}
			}
			if (row >= 6) {
				for (col = 0; col < 8; col++) {
					x = col * 20;
					y = row * 20;
					if ((row % 2) == (col % 2)) {
						g.setColor(Color.yellow);

						g.fillOval(x + 2, y + 2, 16, 16);
					}
				}
			}
		}

		// delete(g, 22,22);
		// setChip(g,42,42);
		 //move(g, 20,20, 20, 20);
		addMouseListener(this);

	}

	public static void delete(Graphics g, int x, int y) {
		g.setColor(Color.red);
		g.fillRect(x - 2, y - 2, 22, 22);

	}

	public static void setChip(Graphics g, int x, int y) {
		g.setColor(Color.black);
		g.fillOval(x, y, 16, 16);
	}

	public static void move(Graphics g, int x, int y, int xdir, int ydir) {
		delete(g, x, y);
		setChip(g, x + xdir, y + ydir);
	}

	public void mouseClicked(MouseEvent e) {
//		delete(g, 22, 22);
//		setChip(g, 42, 42);
		
		p = e.getPoint();
		System.out.println(p);
move(g, 20,20, 20, 20);
		
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
	}
	


}
