import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSelectAircraft extends JDialog {

	DropAircraftPanel panelAircraft = new DropAircraftPanel();

	private boolean result;

	/**
	 * Create the application.
	 */
	public FormSelectAircraft(JFrame parent) {
		super(parent, true);
		initialize();
	}

	public boolean execute() {
		setVisible(true);
		return result;
	}

	public ITech getAircraft() {
		return panelAircraft.getAircraft();
	}

	/**
	 * Initialize the contents of the
	 */
	private void initialize() {
		setBounds(100, 100, 538, 288);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel labelType = new JLabel("");
		labelType.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelType.setBounds(10, 11, 145, 128);
		getContentPane().add(labelType);

		DragLabel labelCivillianAircraft = new DragLabel("\u0421\u0430\u043C\u043E\u043B\u0451\u0442");
		labelCivillianAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		labelCivillianAircraft.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelCivillianAircraft.setBounds(35, 36, 81, 33);
		getContentPane().add(labelCivillianAircraft);

		DragLabel labelFighterAircraft = new DragLabel(
				"\u0418\u0441\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044C");
		labelFighterAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		labelFighterAircraft.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelFighterAircraft.setBounds(35, 85, 81, 33);
		getContentPane().add(labelFighterAircraft);

		JLabel label = new JLabel("\u0422\u0438\u043F \u0441\u0430\u043C\u043E\u043B\u0451\u0442\u0430");
		label.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label.setBounds(18, 11, 81, 14);
		getContentPane().add(label);

		JButton buttonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		buttonAdd.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = true;
				dispose();
			}
		});
		buttonAdd.setBounds(26, 167, 106, 23);
		getContentPane().add(buttonAdd);

		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = false;
				dispose();
			}
		});
		buttonCancel.setBounds(26, 201, 106, 23);
		getContentPane().add(buttonCancel);

		JLabel label_1 = new JLabel("");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(165, 11, 172, 220);
		getContentPane().add(label_1);

		panelAircraft.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAircraft.setBounds(178, 26, 145, 128);
		getContentPane().add(panelAircraft);

		DropLabelColor labelMainColor = new DropLabelColor(
				"\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
		labelMainColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		labelMainColor.setLabelFor(panelAircraft);
		labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelMainColor.setBounds(200, 163, 101, 28);
		getContentPane().add(labelMainColor);

		DropLabelColor labelDopColor = new DropLabelColor("\u0414\u043E\u043F. \u0446\u0432\u0435\u0442");
		labelDopColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		labelDopColor.setLabelFor(panelAircraft);
		labelDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelDopColor.setBounds(200, 201, 101, 23);
		getContentPane().add(labelDopColor);

		JLabel labelColors = new JLabel("");
		labelColors.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelColors.setBounds(357, 11, 145, 220);
		getContentPane().add(labelColors);

		JLabel label_2 = new JLabel("\u0426\u0432\u0435\u0442\u0430");
		label_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_2.setBounds(364, 11, 46, 14);
		getContentPane().add(label_2);

		DragPanel panelBlack = new DragPanel();
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(374, 41, 46, 33);
		getContentPane().add(panelBlack);

		DragPanel panelRed = new DragPanel();
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(430, 41, 46, 33);
		getContentPane().add(panelRed);

		DragPanel panelGreen = new DragPanel();
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(372, 85, 47, 33);
		getContentPane().add(panelGreen);

		DragPanel panelYellow = new DragPanel();
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(429, 86, 47, 33);
		getContentPane().add(panelYellow);

		DragPanel panelCyan = new DragPanel();
		panelCyan.setBackground(Color.CYAN);
		panelCyan.setBounds(373, 129, 47, 33);
		getContentPane().add(panelCyan);

		DragPanel panelBlue = new DragPanel();
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(429, 129, 47, 33);
		getContentPane().add(panelBlue);

		DragPanel panelPink = new DragPanel();
		panelPink.setBackground(Color.MAGENTA);
		panelPink.setBounds(373, 173, 47, 33);
		getContentPane().add(panelPink);

		DragPanel panelOrange = new DragPanel();
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(429, 173, 47, 33);
		getContentPane().add(panelOrange);

	}
}
