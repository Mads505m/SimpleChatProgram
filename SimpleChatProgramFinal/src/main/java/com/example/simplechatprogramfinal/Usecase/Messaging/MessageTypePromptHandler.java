package com.example.simplechatprogramfinal.Usecase.Messaging;

import com.example.simplechatprogramfinal.Usecase.Client.ClientManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class MessageTypePromptHandler implements MessageTypePromptHandlerInterface {
    private final ClientMessageTypeHandler clientMessageTypeHandler;
    private final ClientManager clientManager;
    private final String clientId;

    private final MessageProtocol messageProtocol;

    public MessageTypePromptHandler(ClientMessageTypeHandler clientMessageTypeHandler, ClientManager clientManager, MessageProtocol messageProtocol,String clientId) {
        this.clientMessageTypeHandler = clientMessageTypeHandler;
        this.clientManager = clientManager;
        this.clientId = clientId;
        this.messageProtocol = messageProtocol;

    }


    public void startChatPrompt(BufferedReader in, PrintWriter printWriter) throws IOException {
        while (true) {
            printWriter.println("Choose message type: 1 for Text, 2 for File Transfer, 3 for Emoji:");
            String messageTypeChoice = in.readLine().trim();

            switch (messageTypeChoice) {
                case "1":
                    handleTextMessage(in, printWriter);
                    break;
                case "2":
                    handleFileTransfer(in,printWriter);
                    break;
                case "3":
                    handleEmojiMessage(in,printWriter);
                    break;
                default:
                    printWriter.println("Invalid option. Please enter 1 for Text, 2 for File Transfer, or 3 for Emoji.");
                    break;
            }
        }
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
            String formattedMessage = messageProtocol.createUnicastTextMessage(clientId, unicastMessage);
            clientMessageTypeHandler.messageTypeUnicast(clientId, formattedMessage, targetClientID, clientManager.getClients());
        } else if ("2".equals(textMessageType)) {
            printWriter.println("Enter your message for broadcast:");
            String broadcastMessage = in.readLine().trim();
            String formattedMessage = messageProtocol.createBroadcastMessage(clientId, broadcastMessage);
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
    public void handleFileTransfer(BufferedReader in, PrintWriter printWriter) throws IOException {
       

    }


    /**
     * Emojitext message sends emojimessage to specefic user
     *
     * @throws IOException
     */
    @Override
    public void handleEmojiMessage(BufferedReader in, PrintWriter printWriter) throws IOException {
            printWriter.println("Enter the target client ID for emoji-text:");
            String targetClientID = in.readLine().trim();
            printWriter.println("Enter your message:");
            String emojiMessage = in.readLine().trim();
            String formattedMessage = messageProtocol.createEmojiMessage(clientId, emojiMessage);
            clientMessageTypeHandler.messageTypeUnicast(clientId, formattedMessage, targetClientID, clientManager.getClients());
    }
}
