
public class Stove {
	private Pan pan;

	private boolean state = false;

	public void setState(boolean value) {
		this.state = value;
	}

	public boolean getState() {
		return state;
	}

	public void setPan(Pan value) {
		pan = value;
	}

	public Pan getPan() {
		return pan;
	}

	public void Cook() {
		if (getState()) {
			if (!pan.IsReadyMilk()) {
				while (!pan.IsReadyMilk())
					pan.GetHeat();
			} else {
				if (!pan.IsReadyOatmeel()) {
					while (!pan.IsReadyOatmeel())
						pan.GetHeat();
				}
			}
		}
	}
}
