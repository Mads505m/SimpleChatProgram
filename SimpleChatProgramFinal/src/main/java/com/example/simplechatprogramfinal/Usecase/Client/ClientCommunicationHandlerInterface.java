package com.example.simplechatprogramfinal.Usecase.Client;

public interface ClientCommunicationHandlerInterface {

    /**
     * Sends messages to the server
     * reads the input
     */
    void sendMessageToServer();

    /**
     *
     * Sends the message content to the server
     *
     * @param messageContent the content of the message to send
     */
    void sendMessageContent(String messageContent);
}
