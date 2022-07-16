package org.discord.antiscam.bot.persistence;

import java.util.Collection;

public interface ScamMessagesRepository {

    void addMessage(String keyword);

    void removeMessage(String message);

    Collection<ScamMessage> getAllMessages();
}
