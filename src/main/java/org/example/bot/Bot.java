package org.example.bot;

import org.example.service.ConvertService;
import org.example.service.MessageService;
import org.example.util.BotMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private static final String BOT_USERNAME = System.getenv("BOT_USERNAME");

    private final MessageService messageService = new MessageService(this);

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void onUpdateReceived(Update update) {
        executorService.submit(() -> {
            try {
                processUpdate(update);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void processUpdate (Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (message.startsWith("/start")) {
                messageService.sendPlainText(chatId, BotMessages.GREETING);
            } else {

                try {
                    double number = Double.parseDouble(String.format(message.replace(",", "."), "%.2f"));

                    if (number < -999999999999.99D || number > 999999999999.99D) {
                        messageService.sendPlainText(chatId, BotMessages.TOO_LARGE);
                    } else {
                        String text = String.valueOf(ConvertService.convertAuto(number));
                        text = text.substring(0, 1).toUpperCase() + text.substring(1);
                        String sendText = text;
                        messageService.sendMarkdown(chatId, sendText);
                    }

                } catch (NumberFormatException e) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    messageService.sendPlainText(chatId, BotMessages.INVALID_SYMBOLS);
                }

            }
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
