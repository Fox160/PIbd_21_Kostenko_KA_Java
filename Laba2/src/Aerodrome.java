import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Aerodrome {
	List<ClassArray<ITech>> aerodrome;

	int countPlaces = 20;
	int placeSizeWidth = 210;
	int placeSizeHeight = 80;
	int currentLevel;

	public Aerodrome(int countStages) {
		aerodrome = new ArrayList<ClassArray<ITech>>();
		for (int i = 0; i < countStages; i++) {
			aerodrome.add(new ClassArray<ITech>(countPlaces, null));
		}
		currentLevel = 0;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void levelUp() {
		if (currentLevel + 1 < aerodrome.size()) {
			currentLevel++;
		}
	}

	public void levelDown() {
		if (currentLevel > 0) {
			currentLevel--;
		}
	}

	public int putAircraftInAerodrome(ITech aircraft) {
		return aerodrome.get(currentLevel).addAircraft(aircraft);
	}

	public ITech getAircraftInAerodrome(int ticket) {
		return aerodrome.get(currentLevel).dec(ticket);
	}

	public void drawAircraft(Graphics g) {
		for (int i = 0; i < countPlaces; i++) {
			ITech aircraft = aerodrome.get(currentLevel).getPlace(i);
			if (aircraft != null) {
				aircraft.setPosition(70 + i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight * 12 / 10 + 80);
				aircraft.drawAircraft(g);
			}
		}
	}

	public void drawAerodrome(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		BasicStroke pen = new BasicStroke(3);
		gr.setStroke(pen);

		gr.setFont(new Font("Arial", Font.BOLD, 40));
		gr.setColor(Color.BLUE);
		g.drawString("L" + (currentLevel + 1), (countPlaces / 5) * placeSizeWidth - 70, 420);

		gr.setColor(Color.black);
		gr.drawRect(0, 0, (countPlaces / 5) * placeSizeWidth, 490);
		for (int i = 0; i < countPlaces / 5; i++) {
			for (int j = 0; j < 6; ++j) {
				gr.setColor(Color.black);
				gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 100, i * placeSizeWidth + 130,
						j * placeSizeHeight * 12 / 10 + 100);

				gr.setColor(Color.white);
				gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 70, i * placeSizeWidth + 130,
						j * placeSizeHeight * 12 / 10 + 70);
				gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 40, i * placeSizeWidth + 130,
						j * placeSizeHeight * 12 / 10 + 40);

				if (j < 5) {
					gr.setColor(Color.blue);
					gr.drawString(Integer.toString((i * 5 + j + 1)), i * placeSizeWidth + 50,
							j * placeSizeHeight + j * 20 + 60);
				}
			}
			gr.setColor(Color.black);
			gr.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, 485);
		}

		drawAircraft(g);
	}
}
