import models.ExtensionServer;
import models.TelegramBot;
import services.Communication;

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
        communication = new services.Communication();
        //telegram = new models.TelegramBot(communication);
        try {
            vk = new ExtensionServer(communication);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}

