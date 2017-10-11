import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Form1 {

	private AircraftPanel myPanel;
	private JFrame frame;
	private JTextField textBoxMaxCountPassenger;
	private JTextField textBoxMaxSpeed;
	private JTextField textBoxWeight;
	private JButton buttonSelectColor = new JButton("Color");
	private JButton buttonSelectDopColor = new JButton("Color");

	Color color;
	Color dopColor;
	int maxSpeed;
	int maxCountPass;
	int weight;
	int ammu;
	private ITech inter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1 window = new Form1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Form1() {
		initialize();
		color = Color.WHITE;
		dopColor = Color.YELLOW;
		maxSpeed = 150;
		maxCountPass = 4;
		weight = 1500;
		buttonSelectColor.setBackground(color);

		buttonSelectDopColor.setBackground(dopColor);
	}

	private boolean checkFields() {
		try {
			maxSpeed = Integer.parseInt(textBoxMaxSpeed.getText());
			maxCountPass = Integer.parseInt(textBoxMaxCountPassenger.getText());
			weight = Integer.parseInt(textBoxWeight.getText());
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 672, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		myPanel = new AircraftPanel(inter);
		myPanel.setBounds(10, 11, 636, 279);
		frame.getContentPane().add(myPanel);

		JLabel lblMaxSpeed = new JLabel("Max speed:");
		lblMaxSpeed.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblMaxSpeed.setBounds(10, 306, 107, 14);
		frame.getContentPane().add(lblMaxSpeed);

		JLabel lblMaxCountPassenger = new JLabel("Max count passenger:");
		lblMaxCountPassenger.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblMaxCountPassenger.setBounds(10, 338, 107, 14);
		frame.getContentPane().add(lblMaxCountPassenger);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblWeight.setBounds(228, 306, 52, 14);
		frame.getContentPane().add(lblWeight);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblColor.setBounds(228, 338, 52, 14);
		frame.getContentPane().add(lblColor);

		textBoxMaxCountPassenger = new JTextField();
		textBoxMaxCountPassenger.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		textBoxMaxCountPassenger.setText("4");
		textBoxMaxCountPassenger.setBounds(127, 335, 78, 20);
		frame.getContentPane().add(textBoxMaxCountPassenger);
		textBoxMaxCountPassenger.setColumns(10);

		textBoxMaxSpeed = new JTextField();
		textBoxMaxSpeed.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		textBoxMaxSpeed.setText("150");
		textBoxMaxSpeed.setColumns(10);
		textBoxMaxSpeed.setBounds(127, 301, 78, 20);
		frame.getContentPane().add(textBoxMaxSpeed);

		textBoxWeight = new JTextField();
		textBoxWeight.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		textBoxWeight.setText("1500");
		textBoxWeight.setColumns(10);
		textBoxWeight.setBounds(277, 303, 78, 20);
		frame.getContentPane().add(textBoxWeight);

		JCheckBox checkBoxStarWings = new JCheckBox(
				"\u0417\u0432\u0435\u0437\u0434\u044B \u043D\u0430 \u043A\u0440\u044B\u043B\u044C\u044F\u0445");
		checkBoxStarWings.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		checkBoxStarWings.setBounds(371, 302, 128, 23);
		frame.getContentPane().add(checkBoxStarWings);

		JCheckBox checkBoxStarTale = new JCheckBox(
				"\u0417\u0432\u0435\u0437\u0434\u0430 \u043D\u0430 \u0445\u0432\u043E\u0441\u0442\u0435");
		checkBoxStarTale.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		checkBoxStarTale.setBounds(371, 334, 128, 23);
		frame.getContentPane().add(checkBoxStarTale);

		JCheckBox checkBoxStarBody = new JCheckBox(
				"\u0417\u0432\u0435\u0437\u0434\u0430 \u043D\u0430 \u0444\u044E\u0437\u0435\u043B\u044F\u0436\u0435");
		checkBoxStarBody.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		checkBoxStarBody.setBounds(501, 302, 145, 23);
		frame.getContentPane().add(checkBoxStarBody);

		buttonSelectColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorDialog colorD = new ColorDialog(frame, true);
				colorD.setVisible(true);
				color = colorD.getColor();
				buttonSelectColor.setBackground(color);
			}
		});

		buttonSelectDopColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorDialog colorD = new ColorDialog(frame, true);
				colorD.setVisible(true);
				dopColor = colorD.getColor();
				buttonSelectDopColor.setBackground(dopColor);
			}
		});

		buttonSelectColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSelectColor.setBounds(277, 334, 78, 23);
		frame.getContentPane().add(buttonSelectColor);

		JButton buttonSetCivillianAircraft = new JButton(
				"\u0417\u0430\u0434\u0430\u0442\u044C \u0441\u0430\u043C\u043E\u043B\u0435\u0442");
		buttonSetCivillianAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFields()) {
					inter = new CivillianAircraft(maxSpeed, maxCountPass, weight, color);
					myPanel.updateAircraft(inter);
				}

			}
		});
		buttonSetCivillianAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSetCivillianAircraft.setBounds(53, 380, 128, 23);
		frame.getContentPane().add(buttonSetCivillianAircraft);

		JButton buttonSetFighterAircraft = new JButton(
				"\u0417\u0430\u0434\u0430\u0442\u044C \u0438\u0441\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044C");
		buttonSetFighterAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFields()) {
					inter = new FighterAircraft(maxSpeed, maxCountPass, weight, color, checkBoxStarBody.isSelected(),
							checkBoxStarTale.isSelected(), checkBoxStarWings.isSelected(), dopColor, (int) ammu);
					myPanel.updateAircraft(inter);
				}
			}
		});
		buttonSetFighterAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSetFighterAircraft.setBounds(210, 380, 145, 23);
		frame.getContentPane().add(buttonSetFighterAircraft);

		buttonSelectDopColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSelectDopColor.setBounds(505, 334, 78, 23);
		frame.getContentPane().add(buttonSelectDopColor);

		JButton buttonMove = new JButton("\u0414\u0432\u0438\u0436\u0435\u043D\u0438\u0435");
		buttonMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inter != null) {
					inter.moveAircraft();
					myPanel.updateAircraft(inter);
				}
			}
		});
		buttonMove.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonMove.setBounds(410, 380, 89, 23);
		frame.getContentPane().add(buttonMove);
	}
}
