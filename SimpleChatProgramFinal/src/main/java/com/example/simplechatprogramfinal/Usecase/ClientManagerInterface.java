package com.example.simplechatprogramfinal.Usecase;

import java.io.PrintWriter;
import java.util.Map;

public interface ClientManagerInterface {

    /**
     *
     * Gets a map of all connected clients IDs to their corresponding PrintWriter
     *
     * @return All clients
     */
    Map<String, PrintWriter> getClients();

    /**
     *
     * Registers a client by adding their ID and PrintWriter to the map
     *
     * @param clientId Is the ID of the client
     * @param printWriter A printWriter associated with the client
     */
    void registerClient(String clientId, PrintWriter printWriter);

    /**
     *
     * Unregisters a client by removing their ID from the map
     *
     * @param clientId Is the ID of the client
     */
    void unregisterClient(String clientId);
}
