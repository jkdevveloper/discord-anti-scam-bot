package org.discord.antiscam.bot.processor;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;

import java.util.function.Function;

public interface MessageProcessor extends Function<MessageCreateEvent, Publisher<Message>> {
}
