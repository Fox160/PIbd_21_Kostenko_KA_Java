import java.awt.*;

public class FighterAircraft extends CivillianAircraft {
	private boolean starBody;

	private boolean starTale;

	private boolean starWings;

	private int ammu;

	private Color dopColor;

	public FighterAircraft(int maxSpeed, int maxCountPassenger, int weight, Color color, boolean starBody,
			boolean starTale, boolean starWings, Color dopColor, int ammu) {
		super(maxSpeed, maxCountPassenger, weight, color);
		this.starBody = starBody;
		this.starTale = starTale;
		this.starWings = starWings;
		this.dopColor = dopColor;
	}

	protected int getAmmunition() {
		return ammu;
	}

	public boolean attack() {
		if (ammu != 0) {
			ammu--;
			return true;
		}
		return false;
	}

	private void drawStar(Graphics g, int x, int y) {
		g.setColor(dopColor);
		int[] xPoints = new int[] { x - 6, x, x + 6, x - 6, x + 6, x - 6 };
		int[] yPoints = new int[] { y + 6, y - 6, y + 6, y, y, y + 6 };
		g.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	@Override
	protected void drawCivillianAircraft(Graphics g) {
		super.drawCivillianAircraft(g);
		if (starBody) {
			drawStar(g, startPosX - 30, startPosY - 29);
		}
		if (starWings) {
			// верхнее крыло
			drawStar(g, startPosX - 7, startPosY - 61);
			// нижнее крыло
			drawStar(g, startPosX - 5, startPosY - 3);
		}

		if (starTale) {
			drawStar(g, startPosX - 56, startPosY - 60);
		}
	}
}
