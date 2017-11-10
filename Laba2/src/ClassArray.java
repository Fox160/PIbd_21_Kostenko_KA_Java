import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassArray<T extends ITech> {

	private HashMap<Integer, T> places;
	private int maxCount;
	private T defaultValue;

	public ClassArray(int sizes, T defVal) {
		defaultValue = defVal;
		places = new HashMap<Integer, T>();
		maxCount = sizes;
	}

	public int addAircraft(T aircraft) {
		if (this.places.size() == this.maxCount) {
			return -1;
		}

		for (int i = 0; i < this.places.size(); i++) {
			if (this.CheckFreePlace(i)) {
				this.places.put(i, aircraft);
				return i;
			}
		}
		this.places.put(this.places.size(), aircraft);
		return this.places.size() - 1;
	}

	public T dec(int index) {
		if (this.places.containsKey(index)) {
			T aircraft = this.getPlace(index);
			this.places.remove(index);
			return aircraft;
		}
		return this.defaultValue;
	}

	private boolean CheckFreePlace(int index) {
		return !places.containsKey(index);
	}

	public T getPlace(int ind) {
		if (ind > -1 && ind < places.size()) {
			return places.get(ind);
		}
		return defaultValue;
	}
}
