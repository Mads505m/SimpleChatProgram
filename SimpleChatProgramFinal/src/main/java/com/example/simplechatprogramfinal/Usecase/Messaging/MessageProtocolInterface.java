package com.example.simplechatprogramfinal.Usecase.Messaging;

public interface MessageProtocolInterface {

    /**
     * Creates a unicast text message.
     *
     * @param clientId The ID of the client sending the message.
     * @param message  The content of the message.
     * @return A formatted unicast text message string.
     */
    String createUnicastTextMessage(String clientId, String message);

    /**
     * Creates a broadcast message.
     *
     * @param clientId The ID of the client sending the message.
     * @param message  The content of the message.
     * @return A formatted broadcast message string.
     */
     String createBroadcastMessage(String clientId, String message);
    /**
     * Creates a file transfer message.
     *
     * @param clientId The ID of the client sending the file.
     * @param fileName The name of the file being transferred.
     * @param fileSize The size of the file in bytes.
     * @return A formatted file transfer message string.
     */
     String createFileTransferMessage(String clientId, String fileName, long fileSize);

    /**
     * Creates an emoji message.
     *
     * @param clientId  The ID of the client sending the emoji.
     * @param emojiCode The code of the emoji being sent.
     * @return A formatted emoji message string.
     */
    String createEmojiMessage(String clientId, String emojiCode);
}
