package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;

public interface ClientMessageTypeHandlerInterface {

    /**
     *
     * Sends a unicast message to a specific client
     *
     * @param senderID Is the ID of the client who sends the message
     * @param message Is the message from the client
     * @param targetClientID Is the ID of the target client who will receive the message
     * @param clients A map of all connected clients IDs to their corresponding PrintWriter
     */
    void messageTypeUnicast(String senderID, String message, String targetClientID, Map<String, PrintWriter> clients);

    /**
     *
     * Send a broadcast message to every online client
     *
     * @param senderID Is the ID of the client who sends the message
     * @param message Is the message from the client
     * @param clients A map of all connected clients IDs to their corresponding PrintWriter
     */
    void messageTypeBroadcastMessage(String senderID, String message, Map<String, PrintWriter> clients);

    void fileTransfer();

    void emojiSupportText();
}
