package com.example.simplechatprogramfinal.Usecase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageTypePromptHandler implements MessageTypePromptHandlerInterface {
    private final ClientMessageTypeHandler clientMessageTypeHandler;
    private final ClientManager clientManager;
    private final String clientId;

    public MessageTypePromptHandler(ClientMessageTypeHandler clientMessageTypeHandler, ClientManager clientManager, String clientId) {
        this.clientMessageTypeHandler = clientMessageTypeHandler;
        this.clientManager = clientManager;
        this.clientId = clientId;
    }

    /**
     * Method for text type, either text one client or text all clients
     *
     * @throws IOException
     */
    @Override
    public void handleTextMessage(BufferedReader in, PrintWriter printWriter) throws IOException {
        printWriter.println("Press 1 for Unicast message or 2 for Broadcast message:");
        String textMessageType = in.readLine().trim();

        if ("1".equals(textMessageType)) {
            printWriter.println("Enter the target client ID for unicast:");
            String targetClientID = in.readLine().trim();
            printWriter.println("Enter your message:");
            String unicastMessage = in.readLine().trim();
            String formattedMessage = MessageProtocol.createUnicastTextMessage(clientId, unicastMessage);
            clientMessageTypeHandler.messageTypeUnicast(clientId, formattedMessage, targetClientID, clientManager.getClients());
        } else if ("2".equals(textMessageType)) {
            printWriter.println("Enter your message for broadcast:");
            String broadcastMessage = in.readLine().trim();
            String formattedMessage = MessageProtocol.createBroadcastMessage(clientId, broadcastMessage);
            clientMessageTypeHandler.messageTypeBroadcastMessage(clientId, formattedMessage, clientManager.getClients());
        } else {
            printWriter.println("Invalid option. Please enter 1 for Unicast or 2 for Broadcast.");
        }
    }

    /**
     * File transfer feature is not implemented yet.
     *
     * @throws IOException
     */
    @Override
    public void handleFileTransfer(PrintWriter printWriter) throws IOException {
        printWriter.println("File transfer feature is not implemented yet.");
    }

    /**
     * Emoji support feature is not implemented yet
     *
     * @throws IOException
     */
    @Override
    public void handleEmojiMessage(PrintWriter printWriter) throws IOException {
        printWriter.println("Emoji support feature is not implemented yet");
    }
}
