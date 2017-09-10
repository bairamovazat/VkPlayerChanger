import org.telegram.telegrambots.api.objects.Update;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Азат on 03.09.2017.
 */
public class VkController {

    public static void main(String[] args) {

    }

    TelegramBot telegram = null;
    HttpServer vk = null;
    VkController(){
        telegram = new TelegramBot(this);
        try {
            vk = new HttpServer(this);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    public String onUpdateReceivedTelegram(Update data){
        /*if(data.getMessage().equals("/login")){

        }*/
        return "";
    }

    public String onUpdateReceivedVk(String urlData) {//тут выдаём json массив из действий
        return "";
    }

}

