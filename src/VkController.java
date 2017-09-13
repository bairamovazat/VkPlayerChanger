import org.telegram.telegrambots.api.objects.Update;

import java.util.HashMap;

/**
 * Created by Азат on 03.09.2017.
 */
public class VkController {

    public static void main(String[] args) {
        VkController controller = new VkController();
    }

    private TelegramBot telegram = null;
    private ExtensionServer vk = null;
    private Communication communication = null;
    VkController(){
        communication = new Communication();
        telegram = new TelegramBot(communication);
        /*try {
            vk = new ExtensionServer();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
    }

}

