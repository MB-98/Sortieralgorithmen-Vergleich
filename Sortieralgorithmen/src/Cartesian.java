
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

class CartesianPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataObject do_;

	CartesianPanel(DataObject do_) {
		this.do_ = do_;
	}

	// x-axis coord constants
	public static final int X_AXIS_FIRST_X_COORD = 50;
	public static final int X_AXIS_SECOND_X_COORD = 1450;
	public static final int X_AXIS_Y_COORD = 550;

	// y-axis coord constants
	public static final int Y_AXIS_FIRST_Y_COORD = 50;
	public static final int Y_AXIS_SECOND_Y_COORD = 550;
	public static final int Y_AXIS_X_COORD = 50;

	// arrows of axis are represented with "hipotenuse" of
	// triangle
	// now we are define length of cathetas of that triangle
	public static final int FIRST_LENGHT = 10;
	public static final int SECOND_LENGHT = 5;

	// size of start coordinate lenght
	public static final int ORIGIN_COORDINATE_LENGHT = 6;

	// distance of coordinate strings from axis
	public static final int AXIS_STRING_DISTANCE = 20;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// x-axis
		g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		// y-axis
		g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

		// x-axis arrow
		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD - SECOND_LENGHT, X_AXIS_SECOND_X_COORD,
				X_AXIS_Y_COORD);
		g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD + SECOND_LENGHT, X_AXIS_SECOND_X_COORD,
				X_AXIS_Y_COORD);

		// y-axis arrow
		g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD,
				Y_AXIS_FIRST_Y_COORD);
		g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD,
				Y_AXIS_FIRST_Y_COORD);

		// draw origin Point
		g2.fillOval(X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2), ORIGIN_COORDINATE_LENGHT,
				ORIGIN_COORDINATE_LENGHT);

		// draw text "X" and draw text "Y"
		g2.drawString("Arraygröße", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
				X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		g2.drawString("Zeit", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
				(Y_AXIS_FIRST_Y_COORD - 20) + AXIS_STRING_DISTANCE / 2);
		g2.drawString("(Zeit in Nanosek./ Arraygröße)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
				Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);

		// numerate axis
		int xCoordNumbers = 7;
		int yCoordNumbers = 5;
		int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordNumbers;
		int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / yCoordNumbers;

		// draw x-axis numbers
		for (int i = 1; i < xCoordNumbers; i++) {
			g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT,
					X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
			g2.drawString(Integer.toString(i * 100), (X_AXIS_FIRST_X_COORD - 8) + (i * xLength) - 3,
					X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		}

		// draw y-axis numbers
		for (int i = 1; i < yCoordNumbers; i++) {
			g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_SECOND_Y_COORD - (i * yLength),
					Y_AXIS_X_COORD + SECOND_LENGHT * 240, Y_AXIS_SECOND_Y_COORD - (i * yLength));
			g2.drawString(Integer.toString(i * 100000), (Y_AXIS_X_COORD - 28) - AXIS_STRING_DISTANCE,
					(Y_AXIS_SECOND_Y_COORD + 5) - (i * yLength));
		}

		drawLegende(1400, 270, 10, Color.red, "Bubble-Sort", g2);
		drawLegende(1400, 290, 10, Color.blue, "Quick-Sort", g2);
		drawLegende(1400, 310, 10, Color.green, "Insertion-Sort", g2);
		drawLegende(1400, 330, 10, Color.ORANGE, "Selection-Sort", g2);

		// drawPoint(170, 500, Color.red, g2);

		ArrayList<Integer> bsList = this.do_.getBsList();
		ArrayList<Integer> qsList = this.do_.getQsList();
		ArrayList<Integer> isList = this.do_.getIsList();
		ArrayList<Integer> ssList = this.do_.getSsList();
		ArrayList<Integer> runList = this.do_.getAnzahlDurchlaeufe();

		for (int i = 0; i < bsList.size(); i++) {

			int x = runList.get(i);
			int ybs = bsList.get(i);
			int yqs = qsList.get(i);
			int yis = isList.get(i);
			int yss = ssList.get(i);

			x = (50 + (2 * (i + 1)));

			ybs = (548 - (ybs / 1000));
			yqs = (548 - (yqs / 1000));
			yis = (548 - (yis / 1000));
			yss = (548 - (yss / 1000));

			System.out.println(" ");

			drawPoint(x, ybs, Color.RED, g2);
			drawPoint(x, yqs, Color.BLUE, g2);
			drawPoint(x, yis, Color.GREEN, g2);
			drawPoint(x, yss, Color.ORANGE, g2);

			// drawPoint(550-(ybs /1000), x , Color.red, g2);
			// drawPoint(x, 550-(yqs /1000), Color.GREEN, g2);
		}

	}

	public void drawLegende(int x, int y, int b, Color c, String st, Graphics2D g) {
		Color act = g.getColor();
		g.setColor(c);
		g.fillRect(x, y, b, b);
		g.drawString(st, x + 15, y + 10);
		g.setColor(act);
	}

	public void drawLine(ArrayList<Integer> x_, ArrayList<Integer> y_, Color color, Graphics2D g) {
		g.setColor(color);
		for (int i = 0; i < x_.size() - 1; i++) {
			int x0 = (548 - (45 * (i + 1)));
			int x1 = (548 - (45 * (i + 2)));
			System.out.println(x0 + " / " + x1);
			int y0 = y_.get(i);
			y0 = 50 + (y0 / 100000);
			int y1 = y_.get(i + 1);
			y1 = 50 + (y1 / 100000);
			g.drawLine(y0, x0, y1, x1);
		}
	}

	public void drawPoint(int x, int y, Color c, Graphics2D g) {
		System.out.println(x + " **** " + y);

		if (x >= 0 && y >= 0) {
			Color act = g.getColor();
			g.setColor(c);
			g.fillOval(x, y, 3, 3);
			g.setColor(act);
		}
	}
}