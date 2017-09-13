import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Азат on 13.09.2017.
 */
public class Communication {
    Communication(){

    }
    public String getResponseToTelegramMessage(Message message){
        if((message.getText().toLowerCase()).equals("тест")){
            return "Это тест и я на него отвечаю.";
        }
        return ("Вы мне отправили: \"" + message.getText() + "\"");
    }
}
