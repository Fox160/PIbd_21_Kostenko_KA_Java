
public class Stove {
	private Pan pan;

	private boolean state = false;
	
	public void setState(boolean value) {
		this.state = value;
		}
	
	public boolean getState() {
		return state;
	}
        public void setPan(Pan value)
        {
            pan = value;
        }
        public Pan getPan()
        {
            return pan;
        }
        
    public void CookMilk()
    {
        if (getState())
        {
            while (!pan.IsReadyMilk())
            {
                pan.GetHeatMilk();
            }

        }
    }

    public void CookOatmeel()
    {
        while (!pan.IsReadyOatmeel())
        {
            pan.GetHeatOatmeel();
        }
    }
}
