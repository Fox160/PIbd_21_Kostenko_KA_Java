
public class Oatmeel {
	private int has_ready = 0;

	public int getHas_Ready() {
		return has_ready;
	}

	public void GetHeat() {
		if (has_ready < 10) {
			has_ready++;
		}
	}
}
