
public class Oatmeel {
	private int hasReady = 0;

	public int getHasReady() {
		return hasReady;
	}

	public void GetHeat() {
		if (hasReady < 10) {
			hasReady++;
		}
	}
}
