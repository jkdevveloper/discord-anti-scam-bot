package org.discord.antiscam.bot.persistence;

import org.discord.antiscam.bot.persistence.entity.ScamMessage;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScamMessagesInMemoryRepository {

    private Queue<ScamMessage> scamMessages = new ConcurrentLinkedQueue<>();

    public void addMessage(String keyword) {
        scamMessages.add(new ScamMessage(keyword));
    }

    public void removeMessage(String message) {
        scamMessages.remove(new ScamMessage(message));
    }

    public Collection<ScamMessage> getAllMessages() {
        return new ConcurrentLinkedQueue<>(scamMessages);
    }
}
