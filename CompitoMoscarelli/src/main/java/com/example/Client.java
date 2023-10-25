package com.example;
import java.io.*;
import java.net.*;
/**
 * Hello world!
 *
 */
public class Client  {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;
    String num1 = "";
    String num2 = "";
    String operatore = null;
    BufferedReader tastiera;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    String risultatoRicevutoDalServer = "";

    public Socket connetti(){
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        }catch(UnknownHostException e)
        {
            System.out.println("Host sconusciuto");
                
            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
            return mioSocket;
    }


    public void comunica(){
        try {
            System.out.println("digitare il primo numero ");

            num1 = tastiera.readLine();
            
            System.out.println("invio al server");
           
            outVersoServer.writeBytes(num1+ '\n');
         
            System.out.println("inserire operatore ");
            operatore = tastiera.readLine();
           
            System.out.println("invio al server");
             outVersoServer.writeBytes(operatore + '\n');
            
            System.out.println("digitare il secondo numero");
            num2 = tastiera.readLine();
            
            System.out.println("invio al server");

            outVersoServer.writeBytes(num2 + '\n');

            risultatoRicevutoDalServer = inDalServer.readLine();

            System.out.println("Risultato ricevuto dal server :" + risultatoRicevutoDalServer +'\n');

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server !");
            System.exit(1);
            
        }
    }



    public static void main( String[] args )
    {
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
}

