
public class Pan {
	private Milk[] milk;
	private Salt salt;
	private Sugar sugar;
	private Oatmeel[] oatmeel;

	public boolean readyToGoMilk() {
		return CheckMilk();
	}

	public boolean readyToGoOatmeel() {
		return CheckOatmeel();
	}

	public void AddMilk(Milk[] m) {
		milk = m;
	}

	public void AddSalt(Salt newSalt) {
		salt = newSalt;
	}

	public void AddSugar(Sugar newSugar) {
		sugar = newSugar;
	}

	public void AddOatmeel(Oatmeel[] oat) {
		oatmeel = oat;
	}

	private boolean CheckMilk() {
		if (milk == null || milk.length == 0) {
			return false;
		}

		for (int i = 0; i < milk.length; ++i) {
			if (milk[i] == null) {
				return false;
			}
		}
		return true;
	}

	private boolean CheckOatmeel() {
		if (oatmeel == null || oatmeel.length == 0) {
			return false;
		}

		for (int i = 0; i < oatmeel.length; ++i) {
			if (oatmeel[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void GetHeat() {
		if (!CheckMilk()) {
			return;
		}
		if (!IsReadyMilk()) {
			for (int i = 0; i < milk.length; ++i) {
				milk[i].GetHeat();
			}
		}
		if (!CheckOatmeel()) {
			return;
		}
		if (IsReadyMilk() && (!IsReadyOatmeel())) {
			for (int i = 0; i < oatmeel.length; ++i) {
				oatmeel[i].GetHeat();
			}
		}

	}

	public boolean IsReadyMilk() {
		if (milk == null) {
			return false;
		}
		for (int i = 0; i < milk.length; i++) {
			if (milk[i].getTemperature() < 100) {
				return false;
			}
		}

		return true;
	}

	public boolean IsReadyOatmeel() {
		if (oatmeel == null) {
			return false;
		}
		for (int i = 0; i < oatmeel.length; i++) {
			if (oatmeel[i].getHasReady() < 10) {
				return false;
			}
		}
		return true;
	}

	public Oatmeel[] GetOatmeel() {
		return oatmeel;
	}
}
