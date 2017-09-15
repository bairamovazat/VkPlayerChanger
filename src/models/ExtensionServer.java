package models;

/**
 * Created by Азат on 27.08.2017.
 */

import services.Communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ExtensionServer {

    public ExtensionServer(Communication communication) throws Throwable{
        ServerSocket ss = new ServerSocket(8080);
        SocketProcessor socketProcessor = null;
        while (true) {
            Socket s = ss.accept();

            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s, communication)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private InputStream is;
        private OutputStream os;
        private String headers[];
        private Communication communication;

        private SocketProcessor(Socket s, Communication communication) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
            this.communication = communication;
            new Thread(this);
        }

        public void run() {

            try {
                processHeaders();
                //String action = getActions(dataArray[1]);
                //TimeUnit.SECONDS.sleep(10);
                //writeResponse(action);
                HashMap<String, String> getDataMap = utils.urlDataToMap(this.headers[1]);
                String response = communication.getResponseToExtension(getDataMap);
                writeResponse(response);
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /* do nothing */
                }
            }
            System.err.println("Client processing finished");
        }




        private void writeResponse(String s) {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: HTTPServer\r\n" +
                    "Content-Type: text/html;charset=cp1251\r\n" +
                    "Content-Length: " + s.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + s;
            /*
            html doctype
             */
            try {
                byte bytes[] = result.getBytes(Charset.forName("cp1251"));
                os.write(bytes); //Спросить у Марселя, почему с UTF-8 теряется в конце n буков, если в ответе n русских букв
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void processHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputDate = "";
            String splitStr = " | ";
            while (true) {

                String s = br.readLine();
                if (s == null || s.trim().length() == 0) {
                    break;
                }
                inputDate = inputDate + s + splitStr;
            }
            String[] inputDataArray = inputDate.split(splitStr);
            this.headers = inputDataArray;
        }

    }
}