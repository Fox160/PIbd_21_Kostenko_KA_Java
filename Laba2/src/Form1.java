import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Form1 {

	private AerodromePanel aerodromeJPanel;
	private AircraftPanel aircraftJPanel;
	private JFrame frame;
	JList listLevels = new JList();

	private Aerodrome aerodrome;
	private JTextField textField;

	JButton buttonSetAircraft = new JButton(
			"\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C \u0441\u0430\u043C\u043E\u043B\u0451\u0442");

	private FormSelectAircraft dialog;

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
		aerodrome = new Aerodrome(5);
		DefaultListModel<String> model = new DefaultListModel<>();

		for (int i = 1; i < 6; i++) {
			listLevels = new JList<>(model);
			model.addElement("Уровень " + i);
		}
		initialize();
		listLevels.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		listLevels.setSelectedIndex(aerodrome.getCurrentLevel());

		buttonSetAircraft.setBounds(890, 207, 142, 60);
		frame.getContentPane().add(buttonSetAircraft);
		draw();
	}

	private void draw() {
		if (listLevels.getSelectedIndex() > -1) {
			aerodromeJPanel.updateAerodrome(aerodrome);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1093, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		aerodromeJPanel = new AerodromePanel(aerodrome);
		aerodromeJPanel.setBounds(0, 0, 845, 509);
		frame.getContentPane().add(aerodromeJPanel);

		JLabel label = new JLabel("");
		label.setBorder(BorderFactory.createTitledBorder("Забрать самолёт"));
		label.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label.setBounds(855, 295, 212, 203);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u041C\u0435\u0441\u0442\u043E:");
		label_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_1.setBounds(890, 315, 46, 14);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setBounds(946, 312, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		aircraftJPanel = new AircraftPanel(null);
		aircraftJPanel.setBounds(871, 377, 184, 110);
		frame.getContentPane().add(aircraftJPanel);

		buttonSetAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog = new FormSelectAircraft(frame);
				if (dialog.execute()) {
					ITech aircraft = dialog.getAircraft();
					if (aircraft == null) {
						JOptionPane.showMessageDialog(frame, "Самолёт не создан");
						return;
					}
					int place = aerodrome.putAircraftInAerodrome(aircraft);
					if (place != -1) {
						aerodromeJPanel.repaint();
						JOptionPane.showMessageDialog(frame, "Ваше место " + (place + 1));
					} else {
						JOptionPane.showMessageDialog(frame, "Мест нет");
					}

				}
			}
		});

		JButton buttonTakeAircraft = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		buttonTakeAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText() != "") {
						ITech aircraft = aerodrome.getAircraftInAerodrome(Integer.parseInt(textField.getText()));

						if (aircraft == null) {
							JOptionPane.showMessageDialog(frame, "Самолёта нет", "Ошибка", JOptionPane.ERROR_MESSAGE);
							return;
						}
						aerodromeJPanel.updateAerodrome(aerodrome);
						aircraft.setPosition(90, 90);
						aircraftJPanel.updateAircraft(aircraft);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Позиция самолёта не введена", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		buttonTakeAircraft.setBounds(916, 343, 89, 23);
		frame.getContentPane().add(buttonTakeAircraft);

		JButton buttonDown = new JButton("<<");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aerodrome.levelDown();
				listLevels.setSelectedIndex(aerodrome.getCurrentLevel());
				draw();
			}
		});
		buttonDown.setBounds(904, 135, 56, 23);
		frame.getContentPane().add(buttonDown);

		JButton buttonUp = new JButton(">>");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aerodrome.levelUp();
				listLevels.setSelectedIndex(aerodrome.getCurrentLevel());
				draw();
			}
		});
		buttonUp.setBounds(968, 135, 56, 23);
		frame.getContentPane().add(buttonUp);

		listLevels.setBounds(890, 29, 155, 95);
		frame.getContentPane().add(listLevels);

		JLabel label_2 = new JLabel("\u0423\u0440\u043E\u0432\u043D\u0438:");
		label_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_2.setBounds(946, 11, 59, 14);
		frame.getContentPane().add(label_2);

	}
}
