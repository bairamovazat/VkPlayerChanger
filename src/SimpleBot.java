/**
 * Created by Азат on 14.08.2017.
 */

import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Map;

public class SimpleBot extends TelegramLongPollingBot {
    private Map<String,String> map;
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MyVkControlBot";
    }

    @Override
    public String getBotToken() {
        return "414951828:AAGFLuF66_sK9POy6YK7e_bbNRrL_xv9OhA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println(update.getMessage().getChatId());


        /*if (message != null && message.hasText()) {
            processingMessage(message);

            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");

            else if (message.getText().equals("Привет, подключись к вк плееру!"))
                sendMsg(message,"");
            else
                sendMsg(message, "Я не знаю что ответить на это");

        }else{

        }
        else if(message != null && message.hasPhoto()){
            String fileUrl = File.getFileUrl(this.getBotToken(),message.getPhoto().get(0).getFilePath());
            System.out.printf(fileUrl);
            sendMsg(message, "Ваше изображение сохранено");
        }else{
            System.out.printf("Пустое сообщение");
        }*/
    }
    private void processingMessage(Message message){

    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}