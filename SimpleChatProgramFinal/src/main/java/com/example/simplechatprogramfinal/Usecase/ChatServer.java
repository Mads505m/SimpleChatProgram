package com.example.simplechatprogramfinal.Usecase;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ChatServer is responsible for managing client connections and message dispatching.
 */
public class ChatServer {
    private final ExecutorService threadpool;
    private int clientCounter = 1;
    private final ClientManager clientManager;

    private final ReadServerConfigFile readServerConfigFile;

    /**
     * Initializes the ChatServer with a thread pool and client manager.
     */
    public ChatServer(ReadServerConfigFile readServerConfigFile) {
        this.readServerConfigFile = readServerConfigFile;
        threadpool = Executors.newCachedThreadPool();
        clientManager = new ClientManager();
    }

    /**
     * Starts the server
     * listens for incoming client connections.
     */
    public void ServerStart() {

        int serverPort = readServerConfigFile.getServerPort();
        String serverHost = readServerConfigFile.getServerHost();

        try (ServerSocket serverSocket = new ServerSocket(serverPort, 50, InetAddress.getByName(serverHost))) {
            GlobalLogger.logInfo("ChatServer has been started on " + serverHost + ":" + serverPort);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientId = "client" + clientCounter++;
                GlobalLogger.logInfo("Client connected with ID: " + clientId);
                threadpool.execute(new MessageSender(clientSocket, clientManager, clientId));
            }
        } catch (UnknownHostException u) {
            GlobalLogger.logError("Server not found", u);
        } catch (IOException e) {
            GlobalLogger.logError("Server failed to start", e);
        }
    }

    /**
     * Start ChatServer.
     */
    public static void main(String[] args) {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        ChatServer server = new ChatServer(readServerConfigFile);
        server.ServerStart();
    }
}