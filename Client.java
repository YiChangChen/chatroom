import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class Client {
	private ClientThread clientThread;
	private Socket socket;
	public BufferedReader reader;
	public PrintWriter writer;
	public JTextArea textArea;
	
	public boolean isStart = false;
	
	public Client(JTextArea textArea) {
		this.textArea = textArea;
		ConnectServer();
	}
	
	public void ConnectServer() {
		try {
			socket = new Socket("127.0.0.1", 2018);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			String message = reader.readLine();
			textArea.append(message + "\n");
			
			clientThread = new ClientThread();
			clientThread.start();
			isStart = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void CloseConnect() {
		try {
			clientThread.stop();
			if(reader!=null) {
				reader.close();
			}
			if(writer!=null) {
				writer.close();
			}
			if(socket!=null) {
				socket.close();
			}
			isStart = false;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//±µ¦¬°T®§
	class ClientThread extends Thread{
		public ClientThread() {
		}
		
		public void run() {
			String message = null;
			while(true) {
				try {
					if((message = reader.readLine()) !=null){
						textArea.append(message + "\n");
					}

				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void SendMessage(String message) {
		writer.println(message);
		writer.flush();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
