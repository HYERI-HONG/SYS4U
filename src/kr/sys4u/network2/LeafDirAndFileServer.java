package kr.sys4u.network2;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LeafDirAndFileServer implements Closeable {

	private int port = 9000;
	ServerSocket serverSocket;
	boolean initialized = false;
	private ExecutorService executorService;

	public LeafDirAndFileServer(int port) {
		this.port = port;
		this.executorService = Executors.newFixedThreadPool(3);
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
				// 스레드 풀 생성
				executorService.execute(new ServerProcessor(serverSocket.accept()));

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
		executorService.shutdown();
		serverSocket.close();
	}

	public static void main(String args[]) throws IOException {

		LeafDirAndFileServer server = new LeafDirAndFileServer(9000);

		server.initialize();
		server.execute();
		server.close();

	}

}
