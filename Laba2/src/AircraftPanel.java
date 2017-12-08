import java.awt.Graphics;
import javax.swing.JPanel;

public class AircraftPanel extends DropAircraftPanel {
	private ITech aircraft;

	public AircraftPanel() {
		this.aircraft = null;
	}

	public AircraftPanel(ITech aircraft) {
		updateAircraft(aircraft);
	}

	public void updateAircraft(ITech aircraft) {
		this.aircraft = aircraft;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (aircraft != null) {
			aircraft.drawAircraft(g);
		}
	}
}
