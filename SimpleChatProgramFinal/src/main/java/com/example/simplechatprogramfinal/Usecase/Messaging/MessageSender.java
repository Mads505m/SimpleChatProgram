package com.example.simplechatprogramfinal.Usecase.Messaging;

import com.example.simplechatprogramfinal.Usecase.Client.ClientManager;
import com.example.simplechatprogramfinal.Usecase.Logging.GlobalLogger;

import java.io.*;
import java.net.Socket;

/**
 * Handles the communication between the server and a single client.
 */
public class MessageSender implements Runnable, MessageSenderInterface {
    private final Socket clientSocket;
    private final String clientId;
    private final ClientManager clientManager;
    private final MessageTypePromptHandler messageTypePromptHandler;
    private final ClientMessageTypeHandler clientMessageTypeHandler;
    private final MessageProtocol messageProtocol;

    public MessageSender(Socket socket, ClientManager clientManager, String clientId) throws IOException {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.clientManager = clientManager;
        this.clientMessageTypeHandler = new ClientMessageTypeHandler();
        this.messageProtocol = new MessageProtocol();
        this.messageTypePromptHandler = new MessageTypePromptHandler(clientMessageTypeHandler, clientManager, messageProtocol, clientId);

        clientManager.registerClient(clientId, new PrintWriter(socket.getOutputStream(), true));
    }

    /**
     * Uses the startChatPrompt method from MessageTypePromptHandler to handle client communication.
     */
    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            printWriter.println("Client ID: " + clientId);
            messageTypePromptHandler.startChatPrompt(in, printWriter);

        } catch (IOException e) {
            GlobalLogger.logError("Error handling client", e);
        } finally {
            clientManager.unregisterClient(clientId);
            try {
                clientSocket.close();
            } catch (IOException e) {
                GlobalLogger.logError("Failed to close client socket", e);
            }
        }
    }
}