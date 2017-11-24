import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AerodromePanel extends JPanel {
	private Aerodrome aerodrome;

	public AerodromePanel(Aerodrome aerodrome) {
		updateAerodrome(aerodrome);
	}

	public void updateAerodrome(Aerodrome aerodrome) {
		this.aerodrome = aerodrome;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		aerodrome.drawAerodrome(g);
	}
}
