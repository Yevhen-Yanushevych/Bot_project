package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class Button extends Bot {

    public void button(Update update) throws IOException {

      //  if (update.hasMessage() && update.getMessage().hasText()) {
////            String message_text = update.getMessage().getText();
////            long chat_id = update.getMessage().getChatId();
////            if (update.getMessage().getText().equals("/start")) {
////
////
////                SendMessage message = new SendMessage() // Create a message object object
////                        .setChatId(chat_id)
////                        .setText("You send /start");
////                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
////                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
////                List<InlineKeyboardButton> rowInline = new ArrayList<>();
////
////                rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
////                // Set the keyboard to the markup
////                rowsInline.add(rowInline);
////                // Add it to the message
////                markupInline.setKeyboard(rowsInline);
////                message.setReplyMarkup(markupInline);
////                try {
////                    execute(message); // Sending our message object to user
////                } catch (TelegramApiException e) {
////                    e.printStackTrace();
////                }
////            } else {
////
////            }
//
//        } else if (update.hasCallbackQuery()) {
//            // Set variables
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//
//
//            if (call_data.equals("update_msg_text"))  {
//
//
//
//
//            }
//
//        }


        if (update.getMessage().getText().equals("Кінопалац")) {



            Document docMultiplex = Jsoup.connect("http://kinopalace.lviv.ua").get();
            Elements aElements = docMultiplex.getElementsByAttributeValue("class", "poster-title");


            aElements.forEach(aElement -> {
                Element cElement = aElement.child(0);
                String urlMultiplex = cElement.attr("href");
                String titleMultiplex = cElement.text();

                long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("--------");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                rowInline.add(new InlineKeyboardButton().setText(titleMultiplex).setUrl(urlMultiplex).setCallbackData("update_msg_text"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            });
        }

}
}

