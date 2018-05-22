package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class ParsPuppetShow extends Theatre {

    public void parce(Update update) throws IOException {
        Message msg = update.getMessage();


        Document docTeatrKurbasa = Jsoup.connect("https://ticketclub.com.ua/?cg=2&ct=1&org=139").get();
        Elements divElements = docTeatrKurbasa.getElementsByAttributeValue("class", "title");

        divElements.forEach(divElement -> {
            Element aElement = divElement;
            String urlTeatrKurbasa = aElement.attr("href");
            String titleTeatrKurbasa = aElement.text();

            sendMsg(msg, titleTeatrKurbasa + "https://ticketclub.com.ua" + urlTeatrKurbasa);


        });

    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
