package com.example.simplechatprogramfinal.Usecase.Messaging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Makes messages go through a protocol
 */
public class MessageProtocol implements MessageProtocolInterface {

    private static final String SEPARATOR = "|";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * Creates a unicast text message.
     */
    @Override
    public  String createUnicastTextMessage(String clientId, String message) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return String.format(clientId + SEPARATOR + timestamp + SEPARATOR + "Unicast message: " + message);
    }

    /**
     * Creates a broadcast message.
     */
    @Override
    public String createBroadcastMessage(String clientId, String message) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        return String.format(clientId + SEPARATOR + timestamp + SEPARATOR + "Broadcast message: " + message);
    }
    @Override
    public String createFileTransferMessage(String clientId, String fileName, long fileSize) {
        String timestamp = dateFormat.format(new Date());
        return String.format(clientId + SEPARATOR + timestamp + SEPARATOR + "File Transfer: " + SEPARATOR + fileName + SEPARATOR +fileSize);
    }

    public String createEmojiMessage(String clientId, String emojiCode) {
        String timestamp = dateFormat.format(new Date());
        return String.format(clientId + SEPARATOR + timestamp + SEPARATOR + "EMOJI" + SEPARATOR + emojiCode);
    }



}