package practice_2;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Server extends Thread {
    ServerSocket serverSocket; // Определяется переменная serverSocket

    public Server() {
        try {
            /*
             * Создание объекта ServerSocket, который принимает запросы
             * соединения от клиентов через порт 1001
             */
            serverSocket = new ServerSocket(1001);
            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            fail(e, "Could not start server.");
        }
        System.out.println("Сервер запущен . . .");
        /* Стартует поток */
        this.start();
    }

    public static void fail(Exception e, String str) {
        System.out.println(str + "." + e);
    }

    public void run() {
        try {
            while (true) {
                /* Принимаются запросы от клиентов */
                Socket client = serverSocket.accept();
                /*
                * Создается объект соединение, чтобы управлять
                взаимодействием
                * кдиента с сервером
                */
                Connection con = new Connection(client);
            }
        } catch (IOException e) {
            fail(e, "Not listening");
        }
    }

    public static void main(String args[]) {
        /* Запускается сервер */
        new Server();
    }
}

class Connection extends Thread {
    /* Declare the variables */
    protected Socket netClient;
    protected BufferedReader fromClient;
    protected PrintStream toClient;

    public Connection(Socket client) {
        netClient = client;
        try {
            /* Создается входной поток, чтобы принимать данные от
            клиента */
            fromClient = new BufferedReader(new InputStreamReader(netClient.getInputStream()));
            /* Создается выходной поток, чтобы посылать данные
            клиенту */
            toClient = new PrintStream(netClient.getOutputStream());
        } catch (IOException e) {
            try {
                /* Закрывается сокет клиента */
                netClient.close();
            } catch (IOException e1) {
                System.err.println("Unable to set up streams" +
                        e1);
                return;
            }
        }
        /* Start the thread */
        this.start();
    }

    public void run() {

        // 14
        String clientA;

        double d1 = Math.pow(4, 2) - 4 * (-1);
        double x1 = (-1 - Math.sqrt(d1)) / 2 * 2;

        for (int i = 0; i < 1; i++) {
            toClient.println("A: ");

            try {
                clientA = fromClient.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Завершить соединение, когда Bye*/
            if (clientA == null || clientA.equals("Bye")) {
                System.out.println("Exit");
                break;
            }
            double A;
            try {
                A = Double.parseDouble(clientA);
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number: " + clientA);
                System.out.println("Exit");
                break;
            }
            double d2 = Math.pow(A, 2) - 4 * 2 * -(Math.pow(A, 2));
            double y1 = (-1 - Math.sqrt(d2)) / 2 * 2;

            System.out.println("Задача №14\n" + (x1 + y1) / (x1 * y1));
        }

        // 17
        String clientB;

        for (int i = 0; i < 1; i++) {
            toClient.println("Введите две вершины восьмиугольника через пробел (х1, у1, х2, у2): ");

            try {
                clientB = fromClient.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Завершить соединение, когда Bye*/
            if (clientB == null || clientB.equals("Bye")) {
                System.out.println("Exit");
                break;
            }

            String[] strArr = clientB.split(" ");
            double[] numArr = new double [strArr.length];

            for (int j = 0; j < strArr.length; j++) {
                numArr[j] = Double.parseDouble(strArr[j]);
            }

            double answer1 = Math.sqrt(Math.pow(numArr[2] - numArr[0], 2) + Math.pow(numArr[3] - numArr[1], 2));

            double answer2 = answer1 * 8;

            System.out.println("\nЗадача №17 \nРасстояние между двумя точками равен: " + answer1);
            System.out.println("Периметр восьмиугольника равен: " + answer2);
        }

        // 20
        String clientC;

        for (int i = 0; i < 1; i++) {
            toClient.println("Введите число N:");

            try {
                clientC = fromClient.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Завершить соединение, когда Bye*/
            if (clientC == null || clientC.equals("Bye")) {
                System.out.println("Exit");
                break;
            }
            double N = Double.parseDouble(clientC);

            System.out.println("\nЗадача №20");
            for (int j = 1; j < N; j++) {
                double P = 10 * Math.pow(j, 3) - 14 * Math.pow(j, 2) + 12 * j;
                double P3 = 10 * Math.pow(j + 3, 3) - 14 * Math.pow(j + 3, 2) + 12 * (j + 3);
                double answer4 = 3 * P3 * P ;
                System.out.println("Для X = " + j + " значение равно " + answer4);
            }
        }

        // 23
        String clientD;

        for (int i = 0; i < 1; i++) {
            toClient.println("Введите 10 чисел через пробел:");

            try {
                clientD = fromClient.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Завершить соединение, когда Bye*/
            if (clientD == null || clientD.equals("Bye")) {
                System.out.println("Exit");
                break;
            }

            String[] strArr = clientD.split(" ");
            Integer[] numArr = new Integer [strArr.length];

            for (int j = 0; j < strArr.length; j++) {
                numArr[j] = Integer.parseInt(strArr[j]);
            }

            System.out.println("\nЗадача №23");
            for (int j = 0; j < numArr.length; j++) {
                BigInteger bigInteger = BigInteger.valueOf(numArr[j]);
                boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(numArr[j]));
                if (probablePrime) {
                    System.out.println("Число " + numArr[j] + " простое");
                } else {
                    System.out.println("Число " + numArr[j] + " не является простым");
                }
            }
        }

        // 26
        String clientE;

        for (int i = 0; i < 1; i++) {
            toClient.println("Введите число N, затем введите целочисленный массив А через пробел:");

            try {
                clientE = fromClient.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Завершить соединение, когда Bye*/
            if (clientE == null || clientE.equals("Bye")) {
                System.out.println("Exit");
                break;
            }

            String[] strArr = clientE.split(" ");
            Integer[] numArr = new Integer [strArr.length];

            for (int j = 0; j < strArr.length; j++) {
                numArr[j] = Integer.parseInt(strArr[j]);
            }

            int answer5 = 0;
            for (int j = 1; j <= numArr[0]; j++) {
                answer5 += numArr[j];
            }
            System.out.println("\nЗадача №20\nСумма равна: " + answer5);
        }
    }
}
