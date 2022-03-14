/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Liberiatelegram;

/**
 *
 * @author lauria_luca
 */
public class UserTelegram {
    int message_id;
    int chatId;
    String first_name;
    String username;
    String text;
    public UserTelegram()
    {
        message_id=0;
        chatId=0;
        first_name="";
        username="";
        text="";
    }
    public int getMessage_id() {
        return message_id;
    }

    public int getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }
    
   @Override
    public String toString() {
        return "message_id: " + getMessage_id() + "  "
                + "chatId: " + getChatId() + "  "
                + "first_name: " + getFirst_name() + "  "
                + "username: " + getUsername() + "  "
                + "text: " + getText() + "\n";
    }
      
}
