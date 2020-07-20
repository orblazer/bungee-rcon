package fr.orblazer.bungeeRcon;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.ArrayList;
import java.util.Collection;

public class RconCommandSender implements CommandSender {
    private final StringBuffer buffer = new StringBuffer();

    public String flush() {
        String result = buffer.toString();
        buffer.setLength(0);
        return result;
    }


    @Override
    public String getName() {
        return "Rcon";
    }

    @Override
    public void sendMessage(String message) {
        buffer.append(message).append("\n");
    }

    @Override
    public void sendMessages(String... messages) {
        for (String line : messages) {
            sendMessage(line);
        }
    }

    @Override
    public void sendMessage(BaseComponent... messages) {
        for (BaseComponent line : messages) {
            sendMessage(line);
        }
    }

    @Override
    public void sendMessage(BaseComponent message) {
        sendMessage(message.toLegacyText());
    }

    @Override
    public Collection<String> getGroups() {
        return new ArrayList<>();
    }

    @Override
    public void addGroups(String... strings) {
    }

    @Override
    public void removeGroups(String... strings) {
    }

    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public void setPermission(String s, boolean b) {
    }

    @Override
    public Collection<String> getPermissions() {
        return new ArrayList<>();
    }
}