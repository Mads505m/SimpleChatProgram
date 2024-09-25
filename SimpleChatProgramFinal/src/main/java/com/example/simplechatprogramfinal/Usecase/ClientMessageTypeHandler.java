package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;

/**
 * Handles different types of client messages including unicast and broadcast messages.
 */
public class ClientMessageTypeHandler implements ClientMessageTypeHandlerInterface{

    /**
     * Sends an unicast message to a specific client.
     */
    @Override
    public void messageTypeUnicast(String senderID, String message, String targetClientID, Map<String, PrintWriter> clients) {
        GlobalLogger.logInfo("UnicastMessage called with senderID: " + senderID + ", message: " + message + ", targetClientID: " + targetClientID);

        PrintWriter senderWriter = clients.get(senderID);
        PrintWriter targetClientWriter = clients.get(targetClientID);

        if (senderID.equals(targetClientID)) {
            senderWriter.println("Cannot send message to yourself");
            senderWriter.flush();
            GlobalLogger.logInfo("Sender " + senderID + " attempted to send a message to themselves.");
            return;
        }

        if (targetClientWriter != null) {
            targetClientWriter.println(message);
            targetClientWriter.flush();
            GlobalLogger.logInfo("Unicast message sent to " + targetClientID);
        } else {
            senderWriter.println("Client " + targetClientID + " not found");
            senderWriter.flush();
            GlobalLogger.logInfo("Client " + targetClientID + " not found");
        }

        if (targetClientWriter !=null) {
            senderWriter.println("Your message: " + message);
            senderWriter.flush();
        }
    }

    /**
     * Sends message to all connected clients
     */

    @Override
    public void messageTypeBroadcastMessage (String senderID, String message, Map<String, PrintWriter> clients){
        clients.forEach((clientId, clientWriter) -> {

            if (clientId.equals(senderID)) {
                clientWriter.println("Your message: " + message);
            } else {

                clientWriter.println(message);
            }
            clientWriter.flush();
        });
    }

    @Override
    public void fileTransfer(){

    }

    @Override
    public void emojiSupportText(){

    }

}
