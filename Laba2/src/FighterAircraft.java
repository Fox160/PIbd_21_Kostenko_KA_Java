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

	public FighterAircraft(String info) {
		super(info);
		String[] strs = info.split(";");

		if (strs.length == 8) {
			setMaxSpeed(Integer.parseInt(strs[0]));
			setMaxCountPassengers(Integer.parseInt(strs[1]));
			setWeight(Integer.parseInt(strs[2]));

			int r = Integer.parseInt(strs[3].split(",")[0]);
			int g = Integer.parseInt(strs[3].split(",")[1]);
			int b = Integer.parseInt(strs[3].split(",")[2]);
			setColorBody(new Color(r, g, b));

			starBody = Boolean.parseBoolean(strs[4]);
			starTale = Boolean.parseBoolean(strs[5]);
			starWings = Boolean.parseBoolean(strs[6]);

			r = Integer.parseInt(strs[7].split(",")[0]);
			g = Integer.parseInt(strs[7].split(",")[1]);
			b = Integer.parseInt(strs[7].split(",")[2]);
			setDopColor(new Color(r, g, b));
		}

	}

	public boolean isStarBody() {
		return starBody;
	}

	public boolean isStarTale() {
		return starTale;
	}

	public boolean isStarWings() {
		return starWings;
	}

	public Color getDopColor() {
		return dopColor;
	}

	@Override
	public int compareTo(CivillianAircraft other) {
		int res = super.compareTo(other);
		if (res != 0)
			return res;

		FighterAircraft otherAircraft = (FighterAircraft) other;

		if (starBody != otherAircraft.isStarBody())
			return new Boolean(starBody).compareTo(new Boolean(otherAircraft.isStarBody()));
		if (starTale != otherAircraft.isStarTale())
			return new Boolean(starTale).compareTo(new Boolean(otherAircraft.isStarTale()));
		if (starWings != otherAircraft.isStarWings())
			return new Boolean(starWings).compareTo(new Boolean(otherAircraft.isStarWings()));
		if (dopColor.getRGB() != otherAircraft.getDopColor().getRGB())
			return new Integer(dopColor.getRGB()).compareTo(new Integer(otherAircraft.getDopColor().getRGB()));
		return 0;
	}

	public boolean equals(FighterAircraft other) {
		boolean res = equals((CivillianAircraft) other);
		if (!res)
			return res;
		if (starBody != other.isStarBody())
			return false;
		if (starTale != other.isStarTale())
			return false;
		if (starWings != other.isStarWings())
			return false;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof FighterAircraft))
			return false;
		FighterAircraft aircraftObj = (FighterAircraft) obj;
		return equals(aircraftObj);
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

	public void setDopColor(Color color) {
		dopColor = color;
	}

	public String getInfo() {
		return getMaxSpeed() + ";" + getMaxCountPassengers() + ";" + getWeight() + ";" + getColorBody().getRed() + ","
				+ getColorBody().getGreen() + "," + getColorBody().getBlue() + ";" + starBody + ";" + starTale + ";"
				+ starWings + ";" + dopColor.getRed() + "," + dopColor.getGreen() + "," + dopColor.getBlue();
	}
}
