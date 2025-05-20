package org.example.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageService {

    private final TelegramLongPollingBot bot;

    public MessageService(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public void sendPlainText(Long chatId, String text) {
        sendMessage(chatId, text, false);
    }

    public void sendMarkdown(Long chatId, String text) {
        String textCopyable = "`" + text + "`";
        sendMessage(chatId, textCopyable, true);
    }

    private void sendMessage (Long chatId, String text, boolean enableMarkdown) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        if (enableMarkdown) {
            sendMessage.enableMarkdown(true);
        }

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }

}
