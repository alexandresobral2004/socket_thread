package socket_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class ClienteSocket {

	private PrintWriter out;
	private Socket socket;
	private BufferedReader in;
	
	
	public ClienteSocket(Socket socket) throws IOException {
		this.socket = socket;
		System.out.println("Cliente "+socket.getRemoteSocketAddress() + "  conectou");
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//PrintWritter já recebe um true como segundo parâmetro para evitar o uso do flush de envio de msg
		this.out = new PrintWriter(socket.getOutputStream(),true);
	}
	
	
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();	
		}
	
	public String getMessage() {
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public boolean send(String msg) {
		out.println(msg);
		return !out.checkError();
	}
	
	

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}
}
