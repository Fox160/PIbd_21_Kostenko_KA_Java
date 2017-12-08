import java.awt.*;
import java.util.Random;

public class CivillianAircraft extends Aircraft {

	public CivillianAircraft(int maxSpeed, int maxCountPassenger, int weight, Color color) {
		setMaxSpeed(maxSpeed);
		setMaxCountPassengers(maxCountPassenger);
		setColorBody(color);
		setWeight(weight);
		countPassengers = 0;

		startPosX = 75;
		startPosY = 90;
	}
	
	public CivillianAircraft(String info) {
		String[] strs = info.split(";");
		
		if (strs.length == 4) {
			setMaxSpeed(Integer.parseInt(strs[0]));
			setMaxCountPassengers(Integer.parseInt(strs[1]));
			setWeight(Integer.parseInt(strs[2]));
			
			int r = Integer.parseInt(strs[3].split(",")[0]);
			int g = Integer.parseInt(strs[3].split(",")[1]);
			int b = Integer.parseInt(strs[3].split(",")[2]);
			setColorBody(new Color(r, g, b));
		}
		this.countPassengers = 0;
		startPosX = 75;
		startPosY = 90;
	}

	@Override
	protected void setMaxSpeed(int value) {
		if (value > 0 && value < 300) {
			super.setMaxSpeed(value);
		} else {
			super.setMaxSpeed(150);
		}
	}

	@Override
	public void setMaxCountPassengers(int value) {
		if (value > 0 && value < 5) {
			super.setMaxCountPassengers(value);
		} else {
			super.setMaxCountPassengers(4);
		}
	}

	@Override
	public void setWeight(int value) {
		if (value > 500 && value < 1500) {
			super.setWeight(value);
		} else {
			super.setWeight(1000);
		}
	}

	@Override
	public void setMaxHeight(int value) {
		if (value > 10 && value < 1000) {
			super.setMaxHeight(value);
		} else {
			super.setMaxHeight(500);
		}
	}

	@Override
	public void moveAircraft() {
		startPosX += (int) (getMaxSpeed() * 50 / getWeight() / (countPassengers == 0 ? 1 : countPassengers));
	}

	@Override
	public void drawAircraft(Graphics g) {
		drawCivillianAircraft(g);
	}

	protected void drawCivillianAircraft(Graphics g) {
		g.setColor(Color.BLACK);
		// крыло верхнее
		int x = startPosX;
		int y = startPosY;
		int[] xPoints = new int[] { x + 8, x - 9, x - 30, x - 5, x + 8 };
		int[] yPoints = new int[] { y - 49, y - 49, y - 75, y - 69, y - 49 };
		g.fillPolygon(xPoints, yPoints, xPoints.length);

		// хвост
		xPoints = new int[] { x - 50, x - 77, x - 55, x - 35, x - 50 };
		yPoints = new int[] { y - 30, y - 75, y - 70, y - 40, y - 30 };
		g.fillPolygon(xPoints, yPoints, xPoints.length);

		// тело
		g.fillOval(startPosX - 5, startPosY - 60, 30, 18);
		g.setColor(getColorBody());
		g.fillOval(startPosX - 50, startPosY - 49, 100, 40);
		g.setColor(Color.BLACK);
		g.fillOval(startPosX + 46, startPosY - 25, 10, 35);
		g.fillOval(startPosX + 46, startPosY - 69, 10, 35);
		g.setColor(Color.RED);
		g.fillOval(startPosX + 46, startPosY - 35, 11, 11);

		// крыло нижнее
		g.setColor(Color.BLACK);
		xPoints = new int[] { x + 4, x - 23, x - 7, x + 8, x + 4 };
		yPoints = new int[] { y + 5, y + 15, y - 29, y - 29, y + 5 };
		g.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	public String getInfo() {
		return getMaxSpeed() + ";" + getMaxCountPassengers() + ";" + getWeight() + ";" + getColorBody().getRed() + "," + getColorBody().getGreen() + "," + getColorBody().getBlue(); 
	}
}
