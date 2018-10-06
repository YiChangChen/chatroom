import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private ServerThread serverThread;
	
	private ArrayList<ClientThread> clients;
	public JTextArea textArea;
	
	private int Port = 2018;
	public boolean isStart = false;
	
	public Server(JTextArea textArea) {
		this. textArea = textArea;
		ServerStart(Port);
		textArea.append("等待連線...\n");
	}
	
	public void ServerStart(int port) {
		try {
			serverSocket = new ServerSocket(Port);
			serverThread = new ServerThread(serverSocket);
			serverThread.start();
			isStart = true;
			clients = new ArrayList<ClientThread>();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void CloseServer() {
		try {
			if(serverThread != null) {
				serverThread.stop();
			}
			for(ClientThread temp:clients) {
				temp.stop();
				temp.getwriter().close();
				temp.getreader().close();
				temp.getSocket().close();
				clients.remove(temp);
			}
			if(serverSocket != null) {
				serverSocket.close();
			}
			if(socket!=null) {
				socket.close();
			}
			isStart = false;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	class ServerThread extends Thread{
		private ServerSocket serverSocket;
		
		public ServerThread(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}
		
		public void run() {
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					ClientThread client = new ClientThread(socket);
					client.start();
					clients.add(client);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class ClientThread extends Thread{
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		private String name;
		
		public Socket getSocket() {
			return socket;
		}
		public BufferedReader getreader() {
			return reader;
		}
		public PrintWriter getwriter() {
			return writer;
		}
		
		public ClientThread(Socket socket) {
			try {
				this.socket = socket;
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream());
				name = socket.getLocalAddress().getHostName();
				
				writer.println(name+" 連線成功!");
				writer.flush();
				
				SendMessage(name +" is coming!");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			String message = null;
			while(true) {
				try {
					if((message = reader.readLine()) != null) {
						SendMessage(name+ "：" + message);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void SendMessage(String message) {
		textArea.append(message+"\n");
		for(ClientThread temp:clients) {
			temp.getwriter().println(message);
			temp.getwriter().flush();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
