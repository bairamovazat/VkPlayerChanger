/**
 * Created by Азат on 14.08.2017.
 *
 * Update{updateId=643527024, message=Message{}, inlineQuery=null, chosenInlineQuery=null, callbackQuery=null,
 *        editedMessage=null, channelPost=null, editedChannelPost=null, shippingQuery=null, preCheckoutQuery=null}
 *
 * message=Message{messageId=115, from=User{...}, date=1505336720, chat=Chat{...}, forwardFrom=null,
 *                 forwardFromChat=null, forwardDate=null, text='Шо', entities=null, audio=null, document=null,
 *                 photo=null, sticker=null, video=null, contact=null, location=null, venue=null, pinnedMessage=null,
 *                 newChatMembers=null, leftChatMember=null, newChatTitle='null', newChatPhoto=null, deleteChatPhoto=null,
 *                 groupchatCreated=null, replyToMessage=null, voice=null, caption='null', superGroupCreated=null,
 *                 channelChatCreated=null, migrateToChatId=null, migrateFromChatId=null, editDate=null, game=null,
 *                 forwardFromMessageId=null, invoice=null, successfulPayment=null, videoNote=null}
 *
 * from=User{id=170717443, firstName='Azat', lastName='null', userName='bairamov_azat', languageCode='ru-RU'};
 * chat=Chat{id=170717443, type='private', title='null', firstName='Azat', lastName='null', userName='bairamov_azat',
 *           allMembersAreAdministrators=null, photo=null, description='null', inviteLink='null'}
 */


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot implements Runnable {

    private Map<String,String> map;
    private VkController control = null;
    private Thread bootThread = null;
    private Communication communication = null;
    static {
        ApiContextInitializer.init();
    }

    TelegramBot(Communication communication){
        this.communication = communication;
        this.run();
    }

    @Override
    public void run() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
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
    public void onClosing() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        sendMsg(message, communication.getResponseToTelegramMessage(message));

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