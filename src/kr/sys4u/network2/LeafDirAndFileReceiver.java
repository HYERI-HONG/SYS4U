package kr.sys4u.network2;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

public class LeafDirAndFileReceiver implements Closeable {

	private int port = 9000;
	ServerSocket serverSocket;
	boolean initialized = false;

	public LeafDirAndFileReceiver(int port) {
		this.port = port;
	}

	private void initialize() throws IOException {
		if (initialized) {
			return;
		}
		this.serverSocket = new ServerSocket(port);
		initialized = true;

	}

	private void execute() throws IOException {
		if (!initialized) {
			initialize();
		}

		while (true) {
			try {
				System.out.println("소켓 접속 대기중");
					new ServerProcessor(serverSocket.accept()).process();
				System.out.println("소켓 접속 종료");
			} catch (IOException e) {

			}
		}
	}

	@Override
	public void close() throws IOException {
		if (!initialized) {
			initialize();
		}
		serverSocket.close();
	}

	public static void main(String args[]) throws IOException {

		LeafDirAndFileReceiver server = new LeafDirAndFileReceiver(9000);

		server.initialize();
		server.execute();
		server.close();

	}

}
