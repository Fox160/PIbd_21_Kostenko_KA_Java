import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
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

	public int putAircraftInAerodrome(ITech aircraft) throws AerodromeOverflowException, AerodromeAlreadyHaveException {
		return aerodrome.get(currentLevel).addAircraft(aircraft);
	}

	public ITech getAircraftInAerodrome(int ticket) throws AerodromeIndexOutOfRangeException {
		return aerodrome.get(currentLevel).dec(ticket);
	}

	public void drawAircraft(Graphics g) {
		int i = 0;
		for (ITech aircraft : aerodrome.get(currentLevel)) {
			aircraft.setPosition(70 + i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight * 12 / 10 + 80);
			aircraft.drawAircraft(g);
			i++;
		}
	}

	public void sort() {
		aerodrome.sort(null);
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

	public boolean saveData(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}

		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			try (BufferedOutputStream bs = new BufferedOutputStream(fileStream)) {
				String s = "CountLeveles:" + aerodrome.size() + System.lineSeparator();

				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				for (int i = 0; i < s.length(); i++) {
					bos.write(s.charAt(i));
				}

				byte[] info = bos.toByteArray();
				fileStream.write(info, 0, info.length);

				for (ClassArray<ITech> level : aerodrome) {
					bos = new ByteArrayOutputStream();
					s = "Level" + System.lineSeparator();

					for (int i = 0; i < s.length(); i++) {
						bos.write(s.charAt(i));
					}
					info = bos.toByteArray();
					fileStream.write(info, 0, info.length);

					for (int i = 0; i < countPlaces; i++) {
						ITech aircraft = level.getPlace(i);

						if (aircraft != null) {
							bos = new ByteArrayOutputStream();
							String aircraftInfoStr = aircraft.getClass().getName() + ":" + aircraft.getInfo()
									+ System.lineSeparator();
							for (int j = 0; j < aircraftInfoStr.length(); j++) {
								bos.write(aircraftInfoStr.charAt(j));
							}
							info = bos.toByteArray();
							fileStream.write(info, 0, info.length);
						}
					}
				}
			}
			fileStream.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	public boolean loadData(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		}

		try (FileInputStream fileStream = new FileInputStream(fileName)) {
			String s = "";
			try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {

				Path path = Paths.get(file.getAbsolutePath());
				byte[] b = new byte[fileStream.available()];
				b = Files.readAllBytes(path);

				ByteArrayInputStream bos = new ByteArrayInputStream(b);
				String value = new String(b, StandardCharsets.UTF_8);

				while (bos.read(b, 0, b.length) > 0) {
					s += value;
				}

				s = s.replace("\r", "");
				String[] strs = s.split("\n");
				if (strs[0].contains("CountLeveles")) {
					if (aerodrome != null) {
						aerodrome.clear();
					}
					aerodrome = new ArrayList<ClassArray<ITech>>();
				} else
					return false;

				int counter = -1;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("Level")) {
						counter++;
						aerodrome.add(new ClassArray<ITech>(countPlaces, null));
					} else if (strs[i].startsWith("CivillianAircraft")) {
						ITech aircraft = new CivillianAircraft(strs[i].split(":")[1]);
						try {
							int number = aerodrome.get(counter).addAircraft(aircraft);
						} catch (AerodromeOverflowException ex) {
							return false;
						} catch (AerodromeAlreadyHaveException e) {
							return false;
						}
					} else if (strs[i].startsWith("FighterAircraft")) {
						ITech aircraft = new FighterAircraft(strs[i].split(":")[1]);
						try {
							int number = aerodrome.get(counter).addAircraft(aircraft);
						} catch (AerodromeOverflowException ex) {
							return false;
						} catch (AerodromeAlreadyHaveException e) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
}
