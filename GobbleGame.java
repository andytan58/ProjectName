package checkers2;

import java.applet.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class GobbleGame extends Applet {
	private static final long serialVersionUID = 1L;
	private static int keynum; // There are 3 components to each turn:
								// X-Coordinate, Y-Coordinate, and Direction.
								// This keeps track of which one the player is
								// on.
	private Point p1, p2; // These store data for each component.
	private static int whoseTurn; // -1=black, 1=yellow
	
	
	// Generates Checkerboard pattern, initializes keynum
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
			if (row <= 2) {
				for (col = 0; col < 8; col++) {
					x = col * 20;
					y = row * 20;
					if ((row % 2) == (col % 2)) {
						g.setColor(Color.black);

						g.fillOval(x + 2, y + 2, 16, 16);
					}
				}
			}
			if (row >= 5) {
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
		keynum = 1;
		whoseTurn = 1;
		addMouseListener(new HandleMouse());
	}

	// Move method and submethods
	public static void move(Graphics g, int x, int y, int xdir, int ydir) {
		delete(g, x, y);
		setChip(g, x + xdir, y + ydir);
	}

	public static void delete(Graphics g, int x, int y) {
		g.setColor(Color.red);
		g.fillRect(x - 2, y - 2, 20, 20);

	}

	public static void setChip(Graphics g, int x, int y) {
		if (whoseTurn == -1) {
			g.setColor(Color.black);
		} else if (whoseTurn == 1) {
			g.setColor(Color.yellow);
		}
		g.fillOval(x, y, 16, 16);
	}

	// Switches to next component
	public void keyChange() {
		if (keynum == 2) {
			keynum = 1;
		} else {
			keynum = 2;
		}
	}

	// Switches to next turn
	public void turnChange() {
		whoseTurn *= -1;
	}

	// Implementation of KeyListener
	class HandleMouse implements MouseListener{
		public void mouseClicked(MouseEvent arg0) {
			Point temp;
			String turn = "";
			BufferedImage b;
			
			if (whoseTurn == 1)
				turn = "Black";
			if (whoseTurn == -1)
				turn = "Yellow";
			if (keynum == 1) {
				temp = new Point(arg0.getX(), arg0.getY());
				p1 = new Point(temp.x - ((temp.x) % 20), temp.y - ((temp.y) % 20));
				System.out.println(turn + " Selected Point 1: " + p1);
				
				keyChange();
			} else if (keynum == 2) {
				temp = new Point(arg0.getX(), arg0.getY());
				p2 = new Point(temp.x - ((temp.x) % 20), temp.y - ((temp.y) % 20));
				System.out.println(turn + " Selected Point 2: " + p2);
				if ((p1.x + p1.y) % 40 == 0 && (p2.x + p2.y) % 40 == 0) {
					repaint();
					keyChange();
					turnChange();
				} else {
					System.out.println("Invalid coordinate! Retry.");
					keyChange();
				}
			} 
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void update(Graphics g) {
		int mult;
		if (p2.x > p1.x) {
			mult = 1;
		} else {
			mult = -1;
		}
		
			move(g, (p1.x) + 2, (p1.y) + 2, mult * 20, -1 * whoseTurn * 20);
	}

}
