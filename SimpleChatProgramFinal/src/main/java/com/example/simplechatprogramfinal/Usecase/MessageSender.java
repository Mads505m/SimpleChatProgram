package com.example.simplechatprogramfinal.Usecase;

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

    public MessageSender(Socket socket, ClientManager clientManager, String clientId) throws IOException {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.clientManager = clientManager;
        this.clientMessageTypeHandler = new ClientMessageTypeHandler();
        this.messageTypePromptHandler = new MessageTypePromptHandler(clientMessageTypeHandler, clientManager, clientId);


        clientManager.registerClient(clientId, new PrintWriter(socket.getOutputStream(), true));
    }

    /**
     * Handles client communication by prompting the user to select a message type
     */
    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            printWriter.println("Client ID: " + clientId);

            while (true) {
                printWriter.println("Choose message type: 1 for Text, 2 for File Transfer, 3 for Emoji:");
                String messageTypeChoice = in.readLine().trim();

                switch (messageTypeChoice) {
                    case "1":
                        messageTypePromptHandler.handleTextMessage(in, printWriter);
                        break;
                    case "2":
                        messageTypePromptHandler.handleFileTransfer(printWriter);
                        break;
                    case "3":
                        messageTypePromptHandler.handleEmojiMessage(printWriter);
                        break;
                    default:
                        printWriter.println("Invalid option. Please enter 1 for Text, 2 for File Transfer, or 3 for Emoji.");
                        break;
                }
            }
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