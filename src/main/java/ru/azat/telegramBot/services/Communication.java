package ru.azat.telegramBot.services;

import org.telegram.telegrambots.api.objects.Message;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Азат on 13.09.2017.
 */
public class Communication {
    public Communication(){

    }
    public String getResponseToTelegramMessage(Message message){
        if((message.getText().toLowerCase()).equals("тест")){
            return "Это тест и я на него отвечаю.";
        }
        return ("Вы мне отправили: \"" + message.getText() + "\"");
    }

    public String getResponseToExtension(HashMap<String, String> getDataUrl){
        return defaultResponse(getDataUrl);
    }

    private String defaultResponse(HashMap<String, String> getDataMap){
        System.out.println(getDataMap.toString());
        int sleepTime = Integer.parseInt(getDataMap.getOrDefault("time", "0"));
        System.err.println("Заснул на " + sleepTime + " секунд");
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ("Я заснул на " + sleepTime + " секунд");
    }
}
