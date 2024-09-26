package com.example.simplechatprogramfinal.Usecase.Client;

import com.example.simplechatprogramfinal.Usecase.Logging.GlobalLogger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientCommunicationHandler implements ClientCommunicationHandlerInterface {

    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader in;

    private String clientId;

    /**
     * Initializes the client communication handler and connects to the server.
     */

    public ClientCommunicationHandler(String serverHost, int serverPort) throws IOException {
        this.socket = new Socket(serverHost, serverPort);
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.clientId = in.readLine();
        GlobalLogger.logInfo("Connected as " + clientId);


        new Thread(this::readMessagesFromServer).start();
    }

    /**
     * Reads servers message
     * prints the message in the client console
     */

    private void readMessagesFromServer() {
        try {
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            GlobalLogger.logError("Failed to read from server", e);
        }
    }

    /**
     * Sends messages to the server
     * reads the input
     */
    @Override
    public void sendMessageToServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String message = scanner.nextLine();
                sendMessageContent(message);
            }
        } catch (Exception e) {
            GlobalLogger.logError("Failed to send message: ", e);
        }
    }

    /**
     * Sends the message content to the server.
     * @param messageContent the content of the message to send
     */
    @Override
    public void sendMessageContent(String messageContent) {
        printWriter.println(messageContent);
        printWriter.flush();
    }

    public void close(){
        try{
            if(printWriter != null){
                printWriter.close();
            } if(in != null){
                in.close();
            } if (socket != null && !socket.isClosed()){
                socket.close();
            }
            GlobalLogger.logInfo("Connection closed for client" + clientId);
        } catch (IOException e) {
            GlobalLogger.logError("Failed to close connection", e);
        }
    }
}