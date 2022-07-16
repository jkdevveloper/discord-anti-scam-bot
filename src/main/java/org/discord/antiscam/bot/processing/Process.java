package org.discord.antiscam.bot.processing;


import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;

import org.discord.antiscam.bot.processor.MessageProcessor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


@RequiredArgsConstructor
class Process {

    private final MessageProcessor messageCreateEventProcessor;
    private final Predicate<Message> commandFilteringPredicate;
    private final UnaryOperator<Message> commandMessageMapper;

    @PostConstruct
    void listen() {
        var token = "";
        var discordClient = DiscordClient.create(token);

        discordClient.withGateway(gateway -> {

            final Publisher<?> pingPong = gateway.on(MessageCreateEvent.class, messageCreateEventProcessor);

            final Publisher<?> command = gateway.on(MessageCreateEvent.class, event ->
                    Mono.just(event.getMessage())
                            .filter(commandFilteringPredicate)
                            .map(commandMessageMapper)
                            .flatMap(Message::getChannel)
                            .flatMap(channel -> channel.createMessage("Command executed")));

            final Publisher<?> onDisconnect = gateway.onDisconnect()
                    .doOnTerminate(() -> System.out.println("Disconnected!"));

            return Mono.when(
                    pingPong,
                    onDisconnect,
                    command);
        }).block();
    }

}
