import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class ServerWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea_1;
	
	public Server server;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow window = new ServerWindow();
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
	public ServerWindow() {
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
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server = new Server(textArea);
				btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setBounds(513, 13, 116, 68);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(652, 13, 116, 68);
		frame.getContentPane().add(btnDisconnect);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = textField.getText();
				server.SendMessage("Server°G " + message);
			}
		});
		btnNewButton_1.setBounds(636, 464, 132, 76);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(14, 464, 608, 76);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("º–∑¢≈È", Font.PLAIN, 18));
		textArea.setBounds(160, 94, 608, 357);
		frame.getContentPane().add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(14, 94, 132, 357);
		frame.getContentPane().add(textArea_1);
	}
}
