package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GestoreClient extends Thread {
    private final Socket clientSocket;

    public GestoreClient(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            int num = (int) (Math.random() * 100) + 1;

            int nRicevuto;

                do {
                nRicevuto = Integer.parseInt(in.readLine());
    
                if (nRicevuto > num) {
                    out.writeBytes("1\n");
                } else if (nRicevuto < num) {
                    out.writeBytes("2\n");
                }
    
            } while (nRicevuto != num);
    
            out.writeBytes("3\n");

            clientSocket.close();
            System.out.println("Connessione chiusa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}