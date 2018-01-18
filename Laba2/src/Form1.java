import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Form1 {

	private AerodromePanel aerodromeJPanel;
	private AircraftPanel aircraftJPanel;
	private JFrame frame;
	JList listLevels = new JList();

	private Aerodrome aerodrome;
	private JTextField textField;

	private static Logger log;

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

		log = Logger.getLogger(Form1.class.getName());
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("D:\\JavaLog.txt");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.addHandler(fileHandler);

		DefaultListModel<String> model = new DefaultListModel<>();
		for (int i = 1; i < 6; i++) {
			listLevels = new JList<>(model);
			model.addElement("Уровень " + i);
		}
		initialize();

		listLevels.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		listLevels.setSelectedIndex(aerodrome.getCurrentLevel());

		buttonSetAircraft.setBounds(890, 185, 155, 35);
		frame.getContentPane().add(buttonSetAircraft);

		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aerodrome.sort();
				JOptionPane.showMessageDialog(frame, "Отсортировано", "", JOptionPane.INFORMATION_MESSAGE);
				draw();
			}
		});
		btnSort.setBounds(890, 231, 155, 35);
		frame.getContentPane().add(btnSort);
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

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Файл");

		JMenuItem saveMenu = new JMenuItem("Сохранить");
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt", "text");
				fileChooser.setFileFilter(filter);

				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String path = file.getAbsolutePath();

					if (!file.getAbsolutePath().endsWith(".txt")) {
						path += ".txt";
					}
					if (aerodrome.saveData(path)) {
						JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно", "",
								JOptionPane.INFORMATION_MESSAGE);

						log.log(Level.INFO, "Сохранение файла в " + path);
						return;
					} else {
						JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл", "",
								JOptionPane.ERROR_MESSAGE);

						log.log(Level.WARNING, "Неудача при сохранении файла");
						return;
					}
				}
			}
		});
		fileMenu.add(saveMenu);

		JMenuItem loadMenu = new JMenuItem("Загрузить");
		loadMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt", "text");
				fileChooser.setFileFilter(filter);

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (aerodrome.loadData(file.getAbsolutePath())) {
						JOptionPane.showMessageDialog(frame, "Загрузили", "", JOptionPane.INFORMATION_MESSAGE);

						log.log(Level.INFO, "Загрузка файла из " + file.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл", "",
								JOptionPane.ERROR_MESSAGE);
						log.log(Level.WARNING, "Неудача при сохранении файла");
					}
					draw();
				}
			}
		});

		fileMenu.add(loadMenu);
		menuBar.add(fileMenu);

		frame.setJMenuBar(menuBar);

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

					log.info("Открыто окно с заказом самолёта");

					ITech aircraft = dialog.getAircraft();
					log.log(Level.INFO, "Создание самолёта с параметрами " + dialog.getAircraft().getInfo());
					if (aircraft == null) {
						JOptionPane.showMessageDialog(frame, "Самолёт не создан");
						return;
					}
					try {
						int place = aerodrome.putAircraftInAerodrome(aircraft);
						if (place != -1) {
							aerodromeJPanel.repaint();
							JOptionPane.showMessageDialog(frame, "Ваше место " + (place + 1));

							log.log(Level.INFO, "Поставили самолет на место: " + place);
						}
					} catch (AerodromeOverflowException ex) {
						JOptionPane.showMessageDialog(frame, "Мест нет", "Ошибка переполнения",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JButton buttonTakeAircraft = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		buttonTakeAircraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText() != "") {
						try {
							ITech aircraft = aerodrome.getAircraftInAerodrome(Integer.parseInt(textField.getText()));

							if (aircraft == null) {
								JOptionPane.showMessageDialog(frame, "Самолёта нет", "Ошибка",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							aerodromeJPanel.updateAerodrome(aerodrome);
							aircraft.setPosition(90, 90);
							aircraftJPanel.updateAircraft(aircraft);

							log.log(Level.INFO, "Забрали самолёт с места: " + textField.getText());
						} catch (AerodromeIndexOutOfRangeException ex) {
							JOptionPane.showMessageDialog(frame, "Неверный номер", "Ошибка номера",
									JOptionPane.ERROR_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(frame, "Общая ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
						}

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

				log.log(Level.INFO, "Переход на уровень ниже. Текущий уровень: " + aerodrome.getCurrentLevel());

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

				log.log(Level.INFO, "Переход на уровень выше. Текущий уровень: " + aerodrome.getCurrentLevel());

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
