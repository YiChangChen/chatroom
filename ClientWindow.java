import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JTextArea textArea;
	private JTextArea textArea_1;
	public Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
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
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client = new Client(textArea);
			}
		});
		btnNewButton.setBounds(464, 13, 140, 76);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.setBounds(628, 13, 140, 76);
		frame.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setBounds(14, 445, 590, 95);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textField.getText();
				client.SendMessage(message);
			}
		});
		btnNewButton_1.setBounds(618, 445, 150, 95);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Name\uFF1A");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(14, 27, 55, 40);
		frame.getContentPane().add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(83, 27, 220, 40);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(168, 105, 600, 327);
		frame.getContentPane().add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(14, 105, 140, 327);
		frame.getContentPane().add(textArea_1);
	}
}
