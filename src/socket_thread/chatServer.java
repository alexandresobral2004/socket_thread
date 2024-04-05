package socket_thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class chatServer {
	/* cria uma variável para a porta 4000 */
	public static final int PORT = 4000;
	/* fica escutando na porta 4000 */

	private ServerSocket serverSocket=null;
	
	private final String SERVER_ADDRESS = "127.0.0.1";
	private BufferedReader in;
	
	
	public void start() throws IOException {
	
		serverSocket = new ServerSocket(PORT);
		System.out.println("Servidor iniciou na porta "+PORT);
		clientConnectionLoop();
	}
	
	

	private void clientConnectionLoop() throws IOException {
		while(true) {
		
			ClienteSocket clientSocket = new ClienteSocket(serverSocket.accept());
			//iniciando uma Thread
			new Thread(() -> {
				try {
					clientMessageLoop(clientSocket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		
		}
	}
	
	public void clientMessageLoop(ClienteSocket clientesocket) throws IOException {
		String msg;
		
		try {
			while((msg=clientesocket.getMessage())!=null) {
				if("sair".equalsIgnoreCase(msg)) {
					return;
				}
				System.out.printf("Msg recebida do cliente %s: %s\n",clientesocket.getRemoteSocketAddress(),msg);
			}
			
			
		}finally {
			clientesocket.close();
		}
		
	}

	public static void main(String[] args) throws IOException {
		chatServer server = new chatServer();
		server.start();

	}

}
