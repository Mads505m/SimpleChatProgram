package com.example.simplechatprogramfinal.Usecase.Messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface MessageTypePromptHandlerInterface {
    /**
     *
     * Handling messages from the client for either unicast or broadcast
     *
     * @param in A BufferedReader to read input from the client
     * @param printWriter A PrintWriter to send messages back to the client
     * @throws IOException If an input or output exception occurs
     */
    void handleTextMessage(BufferedReader in, PrintWriter printWriter) throws IOException;

    /**
     *
     * Handling files from the client for either unicast og broadcast. It's not implemented yet
     *
     * @param printWriter A PrintWriter to send messages back to the client
     * @throws IOException If an error occurs
     */

    void handleFileTransfer(BufferedReader in, PrintWriter printWriter) throws IOException;

    /**
     *
     * Handling emotes from the client for either unicast og broadcast. It's not implemented yet
     *
     * @param printWriter A PrintWriter to send messages back to the client
     * @throws IOException If an error occurs
     */
    void handleEmojiMessage(BufferedReader in, PrintWriter printWriter) throws IOException;
}
