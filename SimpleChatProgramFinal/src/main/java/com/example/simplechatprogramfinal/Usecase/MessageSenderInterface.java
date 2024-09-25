package com.example.simplechatprogramfinal.Usecase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface MessageSenderInterface {

    /**
     * Handles client communication by prompting the user to select a message type
     */
    void run();

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
    void handleFileTransfer(PrintWriter printWriter) throws IOException;

    /**
     *
     * Handling emotes from the client for either unicast og broadcast. It's not implemented yet
     *
     * @param printWriter A PrintWriter to send messages back to the client
     * @throws IOException If an error occurs
     */
    void handleEmojiMessage(PrintWriter printWriter) throws IOException;
}
