import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Азат on 14.08.2017.
 */
public class utils {
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args){
        HashMap<String, String> map = urlDataToMap("/?u=userLogin%p=md5PasswordHesh");
        System.out.printf(map.get("u"));
        /*try {
            downloadFile("https://api.telegram.org/file/bot414951828:AAGFLuF66_sK9POy6YK7e_bbNRrL_xv9OhA/photos/5226947952415713413.jpg","src\\images\\");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println(httpConn.getRequestProperty("Content-Disposition"));
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    public static byte[] MD5(String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return digest;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    public static String getRandomKey(){
        String[] values = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".split("");
        Random random = new Random();
        String returnStr = "";
        for(int i = 0; i < 32; i++){
            returnStr = returnStr + values[random.nextInt(values.length)];
        }
        return returnStr;
    }

    public static HashMap<String,String> urlDataToMap(String urlData){
        urlData = urlData.replaceAll("\\?|\\/","");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String[] urlDataArray = urlData.split("&");
        for(String data : urlDataArray){
            String[] dataString = data.split("=");
            if(dataString.length > 1) {
                hashMap.put(dataString[0], dataString[1]);
            }
        }
        return hashMap;
    }

    public String getActionsInJson(LinkedList<String> actionList){
        if(actionList.isEmpty()){
            return "[]";
        }
        String returnedStr = "[";
        for (String action : actionList){
            returnedStr = returnedStr + action + ",";
        }
        returnedStr = returnedStr.substring(0, returnedStr.length() - 1); // костыль!!!
        returnedStr = returnedStr + "]";
        return returnedStr;
    }
}
