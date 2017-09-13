/**
 * Created by Азат on 27.08.2017.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by yar 09.09.2009
 */
public class ExtensionServer {

    public static void main(String[] args) throws Throwable {
        ExtensionServer server = new ExtensionServer();
    }

    ExtensionServer() throws Throwable{
        ServerSocket ss = new ServerSocket(8080);
        SocketProcessor socketProcessor = null;
        while (true) {
            Socket s = ss.accept();

            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private InputStream is;
        private OutputStream os;
        private String headers[];

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
            new Thread(this);
        }

        /*private SocketProcessor(Socket s, VkController answerObj) throws Throwable {
            this.answerObj = answerObj;
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
        }*/

        public void run() {

            try {
                processHeaders();
                //String action = getActions(dataArray[1]);
                //TimeUnit.SECONDS.sleep(10);
                //writeResponse(action);
                sendResponse();
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

        private String defaultResponse(){
            HashMap<String, String> getDataMap = utils.urlDataToMap(this.headers[1]);

            System.out.println(getDataMap.toString());
            int sleepTime = Integer.parseInt(getDataMap.getOrDefault("time", "0"));
            System.err.println("Заснул на " + sleepTime + " секунд");
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String str1 = "Я спал a ";
            String str2 = Integer.toString(sleepTime);
            String str3 = " seconddddddddddddddddd1234567891";
            String str4 = str1 + str2 + str3 ;
            return (str4);

        }


        private void sendResponse(){
            writeResponse("");
        }


        private void writeResponse(String s) {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: HTTPServer\r\n" +
                    "Content-Type: text/html;charset=UTF-8\r\n" +
                    "Content-Length: " + s.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + s;

            try {
                os.write(result.getBytes("UTF-8")); //!!!!!!!!!!!!!Спросит у Марселя, почему с UTF-8 теряется в конце n буков, если в ответе n русских букв
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

        /*private String getActions(String urlData){
            return answerObj.onUpdateReceivedVk(urlData);
        }*/

    }
}