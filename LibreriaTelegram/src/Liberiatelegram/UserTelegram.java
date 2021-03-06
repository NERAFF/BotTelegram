/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Liberiatelegram;

import org.json.JSONObject;

/**
 *
 * @author lauria_luca
 */
public class UserTelegram {
    int update_id;
    int message_id;
    int chatId;
    String first_name;
    String username;
    String text;
    Boolean registrato;
    public UserTelegram(JSONObject obj)
    {
        update_id= obj.has("update_id") ? obj.getInt("update_id") : null;
        message_id= obj.getJSONObject("message").has("message_id") ? obj.getJSONObject("message").getInt("message_id") : null;
        chatId= obj.getJSONObject("message").getJSONObject("chat").has("id") ? obj.getJSONObject("message").getJSONObject("chat").getInt("id") : null;
        first_name=obj.getJSONObject("message").getJSONObject("chat").has("first_name") ? obj.getJSONObject("message").getJSONObject("chat").getString("first_name") : null;
        username=obj.getJSONObject("message").getJSONObject("chat").has("username") ? obj.getJSONObject("message").getJSONObject("chat").getString("username") : null;
        text=obj.getJSONObject("message").getJSONObject("chat").has("text") ? obj.getJSONObject("message").getJSONObject("chat").getString("text") : null;
        registrato=false;     
    }

    public Boolean getRegistrato() {
        return registrato;
    }

    public void setRegistrato(Boolean registrato) {
        this.registrato = registrato;
    }

    public int getUpdate_id() {
        return update_id;
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

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
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
        return "update_id: " + getUpdate_id()+ "  "
                + "chatId: " + getMessage_id() + "  "
                + "chatId: " + getChatId() + "  "
                + "first_name: " + getFirst_name() + "  "
                + "text: " + getText() + "\n";
    }
      
}
