package ru.nickly.bot.tgmodel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private Integer message_id;
    private User from;
    private Integer date;
    private Chat chat;
    private User forward_from;
    private Chat forward_from_chat;
    private Integer forward_from_message_id;
    private String forward_signature;
    private Integer forward_date;
    private Message reply_to_message;
    private Integer edit_date;
    private String author_signature;
    private String text;
}
