package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    ServerSocket server = null;
    Socket client = null;
    int risultato = 0;
    int num1 = 0;
    int num2 = 0;
    String operatore = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient; 
   
 

    public Socket attendi(){
        try {
            System.out.println("1 SERVER partito in esecuzione ...");

            server = new ServerSocket(6789);

            client = server.accept();

            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza del server !");
            System.exit(1);

        }
        return client;

    }

    public void comunica(){
        try {
            
            System.out.println("benvenuto client");
            
            num1 = Integer.parseInt(inDalClient.readLine());
        
            operatore = inDalClient.readLine(); 
            
            num2 = Integer.parseInt(inDalClient.readLine());
    
            if(operatore.equals("+")){
                risultato = num1 + num2; 


            }
            if(operatore.equals("-")){
                risultato = num1 - num2; 

            }
            if(operatore.equals("/")){
                risultato = num1 / num2; 

            }
            if(operatore.equals("*")){
                risultato = num1 * num2; 

            }
        
            outVersoClient.writeBytes(String.valueOf(risultato));
            System.out.println(risultato);
            System.out.println("Fine lavorazione ");

            client.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

public static void main (String args[]){

    Server servente = new Server();
    servente.attendi();
    servente.comunica();
}

}

