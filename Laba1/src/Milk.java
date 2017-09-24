
public class Milk {
	private int temperature = 0;

	int getTemperature() {
		return temperature;
	}

	public void GetHeat() {
		if (temperature < 100) {
			temperature++;
		}
	}
}
