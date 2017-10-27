import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Form1 {

	private AerodromePanel aerodromeJPanel;
	private AircraftPanel aircraftJPanel;
	private JFrame frame;

	private Aerodrome aerodrome;
	private JTextField textField;

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
		aerodrome = new Aerodrome();
		initialize();
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
		
		JButton buttonSetCivillianAircraft = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0441\u0430\u043C\u043E\u043B\u0451\u0442");
		buttonSetCivillianAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorDialog colorD = new ColorDialog(frame, true);
				colorD.setVisible(true);
	                CivillianAircraft aircraft = new CivillianAircraft(100, 4, 1000, colorD.getColor());
	                int place = aerodrome.putAircraftInAerodrome(aircraft);

	                if (place >= aerodrome.countPlaces)
	                {
	                	JOptionPane.showMessageDialog(frame, "На аэродроме нету мест", "Ошибка", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                aerodromeJPanel.updateAerodrome(aerodrome);
	                JOptionPane.showMessageDialog(frame, "Ваше место: " + place, "Аэродром", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonSetCivillianAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSetCivillianAircraft.setBounds(871, 32, 196, 47);
		frame.getContentPane().add(buttonSetCivillianAircraft);
		
		JButton buttonSetFighterAircraft = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0438\u0441\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u044C");
		buttonSetFighterAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ColorDialog colorD = new ColorDialog(frame, true);
				colorD.setVisible(true);
				ColorDialog dopColor = new ColorDialog(frame, true);
				dopColor.setVisible(true);
	                CivillianAircraft aircraft = new FighterAircraft(100, 4, 1000, colorD.getColor(), true, true, true, dopColor.getColor(), 4);
	                int place = aerodrome.putAircraftInAerodrome(aircraft);

	                if (place >= aerodrome.countPlaces)
	                {
	                	JOptionPane.showMessageDialog(frame, "На аэродроме нету мест", "Ошибка", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                aerodromeJPanel.updateAerodrome(aerodrome);
	                JOptionPane.showMessageDialog(frame, "Ваше место: " + place, "Аэродром", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonSetFighterAircraft.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonSetFighterAircraft.setBounds(871, 102, 196, 47);
		frame.getContentPane().add(buttonSetFighterAircraft);
		
		JLabel label = new JLabel("");
		label.setBorder(BorderFactory.createTitledBorder("Забрать самолёт"));
		label.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label.setBounds(855, 253, 212, 245);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u041C\u0435\u0441\u0442\u043E:");
		label_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_1.setBounds(890, 280, 46, 14);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(946, 277, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		aircraftJPanel = new AircraftPanel(null);
		aircraftJPanel.setBounds(871, 335, 185, 152);
		frame.getContentPane().add(aircraftJPanel);
		
		JButton buttonTakeAircraft = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		buttonTakeAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() != "")
	            {
	                ITech aircraft = aerodrome.getAircraftInAerodrome(Integer.parseInt(textField.getText()));

	                if (aircraft == null)
	                {
	                	JOptionPane.showMessageDialog(frame, "Самолёта нет", "Ошибка", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                aerodromeJPanel.updateAerodrome(aerodrome);
	                aircraft.setPosition(90, 90);
	                aircraftJPanel.updateAircraft(aircraft);
	            }
			}
		});
		buttonTakeAircraft.setBounds(912, 305, 89, 23);
		frame.getContentPane().add(buttonTakeAircraft);
		
		
	}
}
