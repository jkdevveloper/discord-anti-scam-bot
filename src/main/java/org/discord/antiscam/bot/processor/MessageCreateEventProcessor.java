package org.discord.antiscam.bot.processor;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

class MessageCreateEventProcessor implements MessageProcessor {

    @Override
    public Publisher<Message> apply(MessageCreateEvent messageEvent) {
        return Mono.just(messageEvent.getMessage())
                .filter(message -> message.getContent().contains("SCAM"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Scam detected, banhammer!"));
    }
}
