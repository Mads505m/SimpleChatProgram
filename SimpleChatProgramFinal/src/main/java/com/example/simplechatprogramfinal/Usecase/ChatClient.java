package com.example.simplechatprogramfinal.Usecase;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * ChatClient is responsible for establishing a connection to the chat server
 * and initiating communication.
 */
public class ChatClient {

    private final ReadServerConfigFile readServerConfigFile;
    public ChatClient(ReadServerConfigFile readServerConfigFile) {
        this.readServerConfigFile = readServerConfigFile;
    }

    /**
     * Starts the chat client by connecting to the specified server and port.
     */
    public void startChatClient() {
        String serverHost = readServerConfigFile.getServerHost();
        int serverPort = readServerConfigFile.getServerPort();

        try {
            ClientCommunicationHandler communicationHandler = new ClientCommunicationHandler(serverHost, serverPort);
            GlobalLogger.logInfo("Connected to the server at " + serverHost + " and Port: " + serverPort);
            communicationHandler.sendMessageToServer();
        } catch (UnknownHostException e) {
            GlobalLogger.logError("Unknown host: " + serverHost, e);
        } catch (ConnectException e) {
            GlobalLogger.logError("Connection to the server failed", e);
        } catch (IOException e) {
            GlobalLogger.logError("I/O error occurred when connecting to the server", e);
        }
    }

    /**
     * Starts the chat client.
     */
    public static void main(String[] args) {
        ReadServerConfigFile readServerConfigFile = new ReadServerConfigFile();
        ChatClient chatClient = new ChatClient(readServerConfigFile);

        chatClient.startChatClient();
    }
}