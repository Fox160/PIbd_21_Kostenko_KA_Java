import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Form1 {

	private JFrame frame;

	private Oatmeel[] oatmeel;

	private Milk[] milk;

	private Salt salt;

	private Sugar sugar;

	private Pan pan;

	private Stove stove;

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
		pan = new Pan();
		stove = new Stove();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(BorderFactory.createTitledBorder("Ингредиенты"));
		lblNewLabel.setBounds(10, 11, 233, 139);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("\u041C\u043E\u043B\u043E\u043A\u043E:");
		label.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label.setBounds(24, 44, 55, 14);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u041E\u0432\u0441\u044F\u043D\u044B\u0435 \u0445\u043B\u043E\u043F\u044C\u044F:");
		label_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_1.setBounds(24, 69, 101, 14);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u0421\u043E\u043B\u044C:");
		label_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_2.setBounds(24, 94, 55, 14);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("\u0421\u0430\u0445\u0430\u0440:");
		label_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		label_3.setBounds(24, 119, 55, 14);
		frame.getContentPane().add(label_3);

		JSpinner numericUpDownMilk = new JSpinner();
		numericUpDownMilk.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		numericUpDownMilk.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				milk = new Milk[Integer.parseInt(numericUpDownMilk.getValue().toString())];
				for (int i = 0; i < milk.length; ++i) {
					milk[i] = new Milk();
				}
			}
		});
		numericUpDownMilk.setBounds(135, 41, 87, 20);
		frame.getContentPane().add(numericUpDownMilk);

		JSpinner numericUpDownOatmeel = new JSpinner();
		numericUpDownOatmeel.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		numericUpDownOatmeel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				oatmeel = new Oatmeel[Integer.parseInt(numericUpDownOatmeel.getValue().toString())];

				for (int i = 0; i < oatmeel.length; ++i) {
					oatmeel[i] = new Oatmeel();
				}
			}
		});
		numericUpDownOatmeel.setBounds(135, 66, 87, 20);
		frame.getContentPane().add(numericUpDownOatmeel);

		JSpinner numericUpDownSalt = new JSpinner();
		numericUpDownSalt.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		numericUpDownSalt.setBounds(135, 91, 87, 20);
		frame.getContentPane().add(numericUpDownSalt);

		JSpinner numericUpDownSugar = new JSpinner();
		numericUpDownSugar.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		numericUpDownSugar.setBounds(135, 116, 87, 20);
		frame.getContentPane().add(numericUpDownSugar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(263, 122, 345, 163);
		lblNewLabel_1.setBorder(BorderFactory.createTitledBorder("Кастрюля"));
		frame.getContentPane().add(lblNewLabel_1);

		JButton buttonAddMilk = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043C\u043E\u043B\u043E\u043A\u043E");
		JButton buttonAddSugar = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0441\u0430\u0445\u0430\u0440");
		JButton buttonAddSalt = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0441\u043E\u043B\u044C");
		JButton buttonAddOatmeel = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043E\u0432\u0441\u044F\u043D\u044B\u0435 \u0445\u043B\u043E\u043F\u044C\u044F");
		JButton buttonGetOatmeel = new JButton(
				"\u041F\u043E\u043B\u0443\u0447\u0438\u0442\u044C \u043E\u0432\u0441\u044F\u043D\u043A\u0443");
		JCheckBox checkBoxOn = new JCheckBox("\u0412\u043A\u043B");

		JButton buttonAddPan = new JButton(
				"\u041F\u043E\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u043A\u0430\u0441\u0442\u0440\u044E\u043B\u044E");
		buttonAddPan.setEnabled(false);

		JButton buttonCook = new JButton("\u0413\u043E\u0442\u043E\u0432\u0438\u0442\u044C");
		buttonCook.setEnabled(false);

		JButton buttonGetPan = new JButton(
				"\u0423\u0431\u0440\u0430\u0442\u044C \u043A\u0430\u0441\u0442\u0440\u044E\u043B\u044E");
		buttonGetPan.setEnabled(false);

		buttonAddMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (milk == null || milk.length == 0) {
					JOptionPane.showMessageDialog(frame, "Молока нету!", "Ошибка логики", JOptionPane.ERROR_MESSAGE);

				} else {
					pan.AddMilk(milk);
					buttonAddSalt.setEnabled(true);
					buttonAddPan.setEnabled(true);
					JOptionPane.showMessageDialog(frame, "Молоко залили", "Кухня", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		buttonAddMilk.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAddMilk.setBounds(269, 151, 193, 23);
		frame.getContentPane().add(buttonAddMilk);

		buttonAddSugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sugar = new Sugar();
				sugar.setSugar(Double.parseDouble(numericUpDownSugar.getValue().toString()));
				if (sugar.getSugar() > 0) {
					pan.AddSugar(sugar);
					JOptionPane.showMessageDialog(frame, "Сахар добавили", "Кухня", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Сахара нет, что добавлять?", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonAddSugar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAddSugar.setBounds(269, 180, 193, 23);
		frame.getContentPane().add(buttonAddSugar);

		buttonAddSalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salt = new Salt();
				salt.setSalt(Double.parseDouble(numericUpDownSalt.getValue().toString()));
				if (salt.getSalt() > 0) {
					pan.AddSalt(salt);
					JOptionPane.showMessageDialog(frame, "Соль добавили", "Кухня", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Соли нет, что добавлять?", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonAddSalt.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAddSalt.setBounds(269, 214, 193, 23);
		frame.getContentPane().add(buttonAddSalt);

		buttonAddOatmeel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oatmeel == null || oatmeel.length == 0) {
					JOptionPane.showMessageDialog(frame, "Овсянки нет", "Ошибка логики", JOptionPane.ERROR_MESSAGE);
					return;
				}
				pan.AddOatmeel(oatmeel);

				buttonAddPan.setEnabled(true);
				buttonCook.setEnabled(true);
				JOptionPane.showMessageDialog(frame, "Овсянку положили", "Кухня", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonAddOatmeel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAddOatmeel.setBounds(269, 248, 193, 23);
		frame.getContentPane().add(buttonAddOatmeel);

		buttonGetOatmeel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pan.IsReadyOatmeel() && pan.IsReadyMilk()) {
					oatmeel = pan.GetOatmeel();
					JOptionPane.showMessageDialog(frame, "Приятного аппетита!", "Кухня",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(frame, "Овсянка ещё не готова", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		buttonGetOatmeel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonGetOatmeel.setBounds(472, 199, 125, 23);
		frame.getContentPane().add(buttonGetOatmeel);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 161, 233, 155);
		lblNewLabel_2.setBorder(BorderFactory.createTitledBorder("Плита"));
		frame.getContentPane().add(lblNewLabel_2);

		checkBoxOn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				stove.setState(checkBoxOn.isSelected());
			}
		});
		checkBoxOn.setBounds(38, 181, 60, 23);
		frame.getContentPane().add(checkBoxOn);

		buttonAddPan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stove.setPan(pan);
				buttonCook.setEnabled(true);
				JOptionPane.showMessageDialog(frame, "Кастрюля на плите", "Кухня", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonAddPan.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonAddPan.setBounds(48, 212, 163, 23);
		frame.getContentPane().add(buttonAddPan);

		buttonCook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!stove.getState()) {
					JOptionPane.showMessageDialog(frame, "Нужно включить плиту", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!stove.getPan().CheckMilk())
				{
					JOptionPane.showMessageDialog(frame, "Молока нет", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				if (!stove.getPan().IsReadyMilk()) {
					stove.Cook();
					JOptionPane.showMessageDialog(frame, "Молоко вскипело, нужно добавить соль, сахар и овсянку",
							"Кухня", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (oatmeel == null || oatmeel.length == 0) {
					JOptionPane.showMessageDialog(frame, "Овсянки нет в ингредиентах", "Ошибка логики", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if (!stove.getPan().CheckOatmeel())
	            {
					JOptionPane.showMessageDialog(frame, "Овсянки нет", "Ошибка логики", JOptionPane.ERROR_MESSAGE);
					return;
	            }
				if (!stove.getPan().IsReadyOatmeel()) {
					stove.Cook();
					JOptionPane.showMessageDialog(frame, "Овсянка готова", "Кухня", JOptionPane.INFORMATION_MESSAGE);
					buttonGetPan.setEnabled(true);
					return;
				} else {
					JOptionPane.showMessageDialog(frame, "Что-то пошло не так, овсянка не сварилась", "Ошибка логики",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		buttonCook.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonCook.setBounds(48, 246, 163, 23);
		frame.getContentPane().add(buttonCook);

		buttonGetPan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonGetOatmeel.setEnabled(true);
				JOptionPane.showMessageDialog(frame, "Убрали с плиты", "Кухня", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonGetPan.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		buttonGetPan.setBounds(48, 280, 163, 23);
		frame.getContentPane().add(buttonGetPan);
	}
}
