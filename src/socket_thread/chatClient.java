package socket_thread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class chatClient {
	
	/* cria uma variável para a porta 4000 */
	public static final int PORT = 4000;
	
	/* fica escutando na porta 4000 */
	private ClienteSocket clientSocket=null;
	
	/* Constante com o ip do server*/
	private final String SERVER_ADDRESS = "127.0.0.1";
	
	private Scanner scanner;	
	public chatClient() {
		scanner = new Scanner(System.in);
	}
	
	
	public void start() throws UnknownHostException, IOException {
		/* Instancia o clientsocket passando o endereço e a port do server */
		clientSocket = new ClienteSocket(new Socket(SERVER_ADDRESS,chatServer.PORT));
		System.out.println("Cliente conectado ao servidor em "+ SERVER_ADDRESS + ":"+chatServer.PORT);
		messageLoop();
		
	}
	
	private void messageLoop() throws IOException {
		String msg = null;
		do {
			System.out.println(" Digite sua mensagem ou sair para finalizar: ");
			msg = scanner.nextLine();
			clientSocket.send(msg);
			
		}
		while(!msg.equalsIgnoreCase("sair"));
	}
	

	public static void main(String[] args){
		
		chatClient client = new chatClient();
		try {
			client.start();
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Cliente Finalizado");
		

	}

}
