import java.awt.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassArray<T> implements Comparable<ClassArray<T>>, Iterable<T>, Iterator<T> {

	private HashMap<Integer, T> places;
	private int maxCount;
	private T defaultValue;

	private int currentIndex;

	public ClassArray(int sizes, T defVal) {
		defaultValue = defVal;
		places = new HashMap<Integer, T>();
		maxCount = sizes;
	}

	public int addAircraft(T aircraft) throws AerodromeOverflowException, AerodromeAlreadyHaveException {
		if (this.places.size() == this.maxCount) {
			throw new AerodromeOverflowException();
		}

		boolean isFighterAircraft = aircraft instanceof FighterAircraft;
		int index = places.size();
		for (int i = 0; i < this.places.size(); i++) {
			if (CheckFreePlace(i)) {
				index = i;
			}

			if (aircraft.getClass().equals(places.get(i).getClass())) {
				if (aircraft instanceof FighterAircraft) {
					if (((FighterAircraft) aircraft).equals(places.get(i)))
						throw new AerodromeAlreadyHaveException();
				} else if (((CivillianAircraft) aircraft).equals(places.get(i)))
					throw new AerodromeAlreadyHaveException();
			}
		}
		if (index != places.size()) {
			places.put(index, aircraft);
			return index;
		}
		this.places.put(this.places.size(), aircraft);
		return this.places.size() - 1;
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= places.size()) {
			reset();
			return false;
		}
		currentIndex++;
		return true;
	}

	@Override
	public T next() {
		return places.get(currentIndex);
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public int compareTo(ClassArray<T> other) {
		if (this.places.size() > other.places.size()) {
			return -1;
		} else if (this.places.size() < other.places.size()) {
			return 1;
		} else {
			Integer[] thisKeys = this.places.keySet().toArray(new Integer[this.places.size()]);
			Integer[] otherKeys = other.places.keySet().toArray(new Integer[other.places.size()]);
			for (int i = 0; i < this.places.size(); i++) {
				if (this.places.get(thisKeys[i]).getClass().equals(CivillianAircraft.class)
						&& other.places.get(otherKeys[i]).getClass().equals(FighterAircraft.class)) {
					return 1;
				}
				if (this.places.get(thisKeys[i]).getClass().equals(FighterAircraft.class)
						&& other.places.get(otherKeys[i]).getClass().equals(CivillianAircraft.class)) {
					return -1;
				}
				if (this.places.get(thisKeys[i]).getClass().equals(CivillianAircraft.class)
						&& other.places.get(otherKeys[i]).getClass().equals(CivillianAircraft.class)) {
					return ((CivillianAircraft) this.places.get(thisKeys[i]))
							.compareTo((CivillianAircraft) other.places.get(otherKeys[i]));
				}
				if (this.places.get(thisKeys[i]).getClass().equals(FighterAircraft.class)
						&& other.places.get(otherKeys[i]).getClass().equals(FighterAircraft.class)) {
					return ((FighterAircraft) this.places.get(thisKeys[i]))
							.compareTo((FighterAircraft) other.places.get(otherKeys[i]));
				}
			}
		}
		return 0;
	}

	private void reset() {
		currentIndex = -1;
	}

	public T dec(int index) throws AerodromeIndexOutOfRangeException {
		if (this.places.containsKey(index)) {
			T aircraft = this.getPlace(index);
			this.places.remove(index);
			return aircraft;
		}
		throw new AerodromeIndexOutOfRangeException();
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
