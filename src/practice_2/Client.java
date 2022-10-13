package practice_2;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket;
        PrintStream out = null;
        BufferedReader in = null;
        try {
            /* Создается объект сокет, чтобы соединиться с сервером
             */
            clientSocket = new Socket("127.0.0.1", 1001);
            /* Создается выходной поток, чтобы посылать данные на
            сервер */
            out = new PrintStream(clientSocket.getOutputStream());
            /* Создается входной поток, чтобы принимать данные с
            сервера */
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unidentified hostname ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O ");
            System.exit(1);
        }
        /* Создается входной поток, чтобы читать данные из окна консоли
         */
        BufferedReader stdin = new BufferedReader(new InputStreamReader((System.in)));
        /* Чтение из сокета */
        String A = in.readLine();
        System.out.println(A);
        /* Прием */
        String aName = stdin.readLine();
        out.println(aName);


        /* Чтение из сокета */
        String A2 = in.readLine();
        System.out.println(A2);
        /* Прием */
        String A2A = stdin.readLine();
        out.println(A2A);

        /* Чтение из сокета */
        String A3 = in.readLine();
        System.out.println(A3);
        /* Прием */
        String A3A = stdin.readLine();
        out.println(A3A);

        /* Чтение из сокета */
        String A4 = in.readLine();
        System.out.println(A4);
        /* Прием */
        String A4A = stdin.readLine();
        out.println(A4A);

        /* Чтение из сокета */
        String A5 = in.readLine();
        System.out.println(A5);
        /* Прием */
        String A5A = stdin.readLine();
        out.println(A5A);


        String str = in.readLine();
        System.out.println(str);

        while ((str = stdin.readLine()) != null) {
            out.println(str);
            if (str.equals("Bye"))
                break;
        }
        out.close();
        in.close();
        stdin.close();
    }
}
