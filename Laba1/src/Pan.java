
public class Pan {
	private Milk[] milk;
	private Salt salt;
	private Sugar sugar;
	private Oatmeel[] oatmeel;

	public boolean getReadyToGoMilk() {
		return CheckMilk();
	}

	public boolean getReadyToGoOatmeel() {
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

		if (oatmeel.length == 0) {
			return false;
		}

		for (int i = 0; i < oatmeel.length; ++i) {
			if (oatmeel[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void GetHeatOatmeel() {
		GetHeatMilk();

		if (!CheckOatmeel()) {
			return;
		}

		for (int i = 0; i < oatmeel.length; ++i) {
			oatmeel[i].GetHeat();
		}
	}

	public void GetHeatMilk() {
		if (!CheckMilk()) {
			return;
		}

		if (milk.length > 0) {
			if (milk[0].getTemperature() < 100) {
				for (int i = 0; i < milk.length; ++i) {
					milk[i].GetHeat();
				}
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
			if (oatmeel[i].getHas_Ready() < 10) {
				return false;
			}
		}
		return true;
	}

	public Oatmeel[] GetOatmeel() {
		return oatmeel;
	}
}
