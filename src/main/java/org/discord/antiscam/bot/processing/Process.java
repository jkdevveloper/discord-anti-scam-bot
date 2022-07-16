package org.discord.antiscam.bot.processing;


import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.MessageEvent;
import discord4j.core.event.domain.message.MessageUpdateEvent;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import org.discord.antiscam.bot.command.CommandFilteringPredicate;
import org.discord.antiscam.bot.command.CommandMessageMapper;
import org.discord.antiscam.bot.processor.MessageCreateEventProcessor;
import org.discord.antiscam.bot.processor.MessageProcessor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
class Process {

    private final MessageCreateEventProcessor messageCreateEventProcessor;

    @PostConstruct
    void listen() {
        var token = "OTk3NTc3NjY0NDA1NzAwODI5.GWVPUz.o1hutxmjBwRGuNy2wipHqEovzVGD3lGk-0urvA";
        var commandFilteringPredicate = new CommandFilteringPredicate();
        var commandMessageMapper = new CommandMessageMapper();
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
