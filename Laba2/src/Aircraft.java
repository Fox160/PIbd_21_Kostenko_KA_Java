import java.awt.*;

public abstract class Aircraft implements ITech {
	protected int startPosX;
	protected int startPosY;
	protected int maxSpeed;

	protected int countPassengers;
	protected int maxH;

	private Color colorBody;
	private int weight;

	public int getMaxCountPassengers() {
		return countPassengers;
	}

	protected void setMaxCountPassengers(int countPassengers) {
		this.countPassengers = countPassengers;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	protected void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getMaxHeight() {
		return maxH;
	}

	protected void setMaxHeight(int maxH) {
		this.maxH = maxH;
	}

	public Color getColorBody() {
		return colorBody;
	}

	protected void setColorBody(Color color) {
		this.colorBody = color;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public abstract void moveAircraft();

	public abstract void drawAircraft(Graphics g);

	public void setPosition(int x, int y) {
		startPosX = x;
		startPosY = y;
	}

	public void loadPassenger(int count) {
		if (countPassengers + count < getMaxCountPassengers())
			countPassengers += count;
	}

	public int getPassenger() {
		int count = countPassengers;
		countPassengers = 0;
		return count;
	}

	public void setMainColor(Color color) {
		colorBody = color;
	}

	public abstract String getInfo();
}
