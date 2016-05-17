package cookie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.*;

public class cookie extends Applet implements MouseListener {
	private Point p;
	private Graphics g;
	private int i= 0;
	public void paint(Graphics g) {

		
			g.setColor(Color.red);
						g.fillOval(0, 0,50,50);
						addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		i++;
		System.out.println("+1 cookie ");
		System.out.println(i + "cookies");
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

