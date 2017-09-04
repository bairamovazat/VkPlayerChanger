/**
 * Created by Азат on 27.08.2017.
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Created by yar 09.09.2009
 */
public class HttpServer {

    HttpServer(VkController connect) throws Throwable{
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s, connect)).start();
        }
    }



    private static class SocketProcessor implements Runnable {

        private Socket s;
        private InputStream is;
        private OutputStream os;
        private VkController controller;

        private SocketProcessor(Socket s, VkController connect) throws Throwable {
            this.controller = connect;
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
        }


        public void run() {
            try {
                String[] dataArray = getInputHeaders();
                String action = getActions(dataArray[1]);
                writeResponse(action);
            } catch (Throwable t) {
                /*do nothing*/
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String s) throws Throwable {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: HTTPServer\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + s.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + s;
            os.write(result.getBytes());
            os.flush();
        }

        private String[] getInputHeaders() throws Throwable {
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
            return  inputDataArray;
        }

        private String getActions(String urlData){
            return controller.onUpdateReceivedVk(urlData);
        }

    }
}