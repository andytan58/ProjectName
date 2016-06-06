package cookie;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int[][] pieces = { { 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { -1, 0, -1, 0, -1, 0, -1, 0 },
			{ 0, -1, 0, -1, 0, -1, 0, -1 }, { -1, 0, -1, 0, -1, 0, -1, 0 } };
	public int[][] board = { { 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 } };
	public int squareSize = 50;
	public int turn = 1;
	public Point selector = new Point(8, 8);
	public int phase = 1;
	public Point moveTo = new Point(0, 0);

	public void paint(Graphics g) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 1) {
					g.setColor(Color.black);
					g.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
				} else if (pieces[r][c] == 0) {
					g.setColor(Color.white);
					g.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
				}

			}
		}

		if (phase == 1) {
			g.setColor(Color.yellow);
			g.fillOval(selector.x * squareSize + 1, selector.y * squareSize + 1, squareSize - 2, squareSize - 2);
		}
		for (int r = 0; r < pieces.length; r++) {
			for (int c = 0; c < pieces[r].length; c++) {
				if ((r + c) % 2 == 1) {
					if (pieces[r][c] == 1) {
						g.setColor(Color.red);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == -1) {
						g.setColor(Color.blue);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == 0) {
						g.setColor(Color.black);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == 2) {
						g.setColor(Color.magenta);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == -2) {
						g.setColor(Color.cyan);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					}
				}

			}
		}

		addMouseListener(new HandleMouse());
	}

	public void turnChange() {
		turn *= -1;
	}

	public void phaseChange() {
		phase += 1;
		if (phase == 3)
			phase = 1;
	}

	class HandleMouse implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Point raw = arg0.getPoint();
			Point tempDiv = new Point((raw.x / squareSize), (raw.y / squareSize));
			Point second = new Point(8, 8);
			if (pieces[tempDiv.y][tempDiv.x] == turn || pieces[tempDiv.y][tempDiv.x] == turn * 2) {
				phase = 1;
				selector = tempDiv;
				repaint();
			} else if (((tempDiv.y < selector.y && turn < 0) || (tempDiv.y > selector.y && turn > 0))
					|| Math.abs(pieces[selector.y][selector.x]) == 2) {
				if (pieces[tempDiv.y][tempDiv.x] == 0 && (Math.abs(tempDiv.y - selector.y) == 1)
						&& Math.abs(tempDiv.x - selector.x) == 1) {
					phaseChange();
					moveTo = tempDiv;
					pieces[moveTo.y][moveTo.x] = pieces[selector.y][selector.x];
					pieces[selector.y][selector.x] = 0;
					if (second.y != 8) {
						moveTo = new Point(second.x, second.y);
					}

					System.out.println(pieces[moveTo.y][moveTo.x]);

					if ((turn == -1 && moveTo.y == 0) || (turn == 1 && moveTo.y == 7)) {
						pieces[moveTo.y][moveTo.x] = 2 * turn;
					}
					repaint();
					turnChange();
					selector = new Point(8, 8);
					phaseChange();
				} else if (pieces[tempDiv.y][tempDiv.x] == 0 && Math.abs(tempDiv.y - selector.y) == 2
						&& Math.abs(tempDiv.x - selector.x) == 2
						&& (pieces[(tempDiv.y + selector.y) / 2][(tempDiv.x + selector.x) / 2] == turn * (-1)
								|| pieces[(tempDiv.y + selector.y) / 2][(tempDiv.x + selector.x) / 2] == turn * (-2))) {
					phaseChange();
					moveTo = tempDiv;
					pieces[moveTo.y][moveTo.x] = pieces[selector.y][selector.x];
					pieces[(moveTo.y + selector.y) / 2][(moveTo.x + selector.x) / 2] = 0;
					//
//						if ((pieces[moveTo.y + 1][moveTo.x + 1] == turn * (-1)
//								|| pieces[moveTo.y + 1][moveTo.x + 1] == turn * (-2))
//								&& pieces[moveTo.y + 2][moveTo.x + 2] == 0) {
//							pieces[moveTo.y + 1][moveTo.x + 1] = 0;
//							pieces[moveTo.y + 2][moveTo.x + 2] = turn;
//							pieces[moveTo.y][moveTo.x] = 0;
//							second = new Point(moveTo.x + 2, moveTo.y + 2);
//							moveTo = new Point(second.x, second.y);
//						} else if ((pieces[moveTo.y - 1][moveTo.x + 1] == turn * (-1)
//								|| pieces[moveTo.y - 1][moveTo.x + 1] == turn * (-2))
//								&& pieces[moveTo.y - 2][moveTo.x + 2] == 0) {
//							pieces[moveTo.y - 1][moveTo.x + 1] = 0;
//							pieces[moveTo.y - 2][moveTo.x + 2] = turn;
//							pieces[moveTo.y][moveTo.x] = 0;
//							second = new Point(moveTo.x - 2, moveTo.y + 2);
//							moveTo = new Point(second.x, second.y);
//						} else if ((pieces[moveTo.y - 1][moveTo.x - 1] == turn * (-1)
//								|| pieces[moveTo.y - 1][moveTo.x - 1] == turn * (-2))
//								&& pieces[moveTo.y - 2][moveTo.x - 2] == 0) {
//							pieces[moveTo.y - 1][moveTo.x - 1] = 0;
//							pieces[moveTo.y - 2][moveTo.x - 2] = turn;
//							pieces[moveTo.y][moveTo.x] = 0;
//							second = new Point(moveTo.x - 2, moveTo.y - 2);
//							moveTo = new Point(second.x, second.y);
//						} else if ((pieces[moveTo.y + 1][moveTo.x - 1] == turn * (-1)
//								|| pieces[moveTo.y + 1][moveTo.x - 1] == turn * (-2))
//								&& pieces[moveTo.y + 2][moveTo.x - 2] == 0) {
//							pieces[moveTo.y + 1][moveTo.x - 1] = 0;
//							pieces[moveTo.y + 2][moveTo.x - 2] = turn;
//							pieces[moveTo.y][moveTo.x] = 0;
//							second = new Point(moveTo.x + 2, moveTo.y - 2);
//							moveTo = new Point(second.x, second.y);
//						}
//						pieces[moveTo.y][moveTo.x] = pieces[selector.y][selector.x];
						//
					pieces[selector.y][selector.x] = 0;
					if ((turn == -1 && moveTo.y == 0) || (turn == 1 && moveTo.y == 7)) {
						pieces[moveTo.y][moveTo.x] = 2 * turn;
					}
					System.out.println(pieces[moveTo.y][moveTo.x]);
					repaint();
					turnChange();
					selector = new Point(8, 8);
					phaseChange();
				}
			}

		}

		public void mouseEntered(MouseEvent e) {
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
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 1) {
					g.setColor(Color.black);
					g.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
				} else if (pieces[r][c] == 0) {
					g.setColor(Color.white);
					g.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
				}

			}
		}

		if (phase == 1) {
			g.setColor(Color.yellow);
			g.fillOval(selector.x * squareSize + 1, selector.y * squareSize + 1, squareSize - 2, squareSize - 2);
		}
		for (int r = 0; r < pieces.length; r++) {
			for (int c = 0; c < pieces[r].length; c++) {
				if ((r + c) % 2 == 1) {
					if (pieces[r][c] == 1) {
						g.setColor(Color.red);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == -1) {
						g.setColor(Color.blue);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == 0) {
						g.setColor(Color.black);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == 2) {
						g.setColor(Color.magenta);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					} else if (pieces[r][c] == -2) {
						g.setColor(Color.cyan);
						g.fillOval(c * squareSize + 4, r * squareSize + 4, squareSize - 8, squareSize - 8);
					}
				}

			}
		}

	}
}
