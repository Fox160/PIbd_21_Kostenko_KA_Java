import java.awt.Color;
import java.awt.Graphics;

public interface ITech {
	void moveAircraft();

	void drawAircraft(Graphics g);

	void setPosition(int x, int y);

	void loadPassenger(int count);

	int getPassenger();

	void setMainColor(Color color);
	
	String getInfo();
}
