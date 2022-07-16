package org.discord.antiscam.bot;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.discord.antiscam.bot.command.CommandFilteringPredicate;
import org.discord.antiscam.bot.command.CommandMessageMapper;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class DiscordBot {

    public static void main(String[] args) {
        var token = "OTk3NTc3NjY0NDA1NzAwODI5.GXbN1Y.g-Xs_kto5QM-qYXkpDa99NJ6eGnQRdjWk9oJLA";
        var commandFilteringPredicate = new CommandFilteringPredicate();
        var commandMessageMapper = new CommandMessageMapper();
        var discordClient = DiscordClient.create(token);

        discordClient.withGateway(gateway -> {
            final Publisher<?> pingPong = gateway.on(MessageCreateEvent.class, event ->
                    Mono.just(event.getMessage())
                            .filter(message -> message.getContent().contains("SCAM"))
                            .flatMap(Message::getChannel)
                            .flatMap(channel -> channel.createMessage("Scam detected, banhammer!")));
            final Publisher<?> command = gateway.on(MessageCreateEvent.class, event ->
                    Mono.just(event.getMessage())
                            .filter(commandFilteringPredicate)
                            .map(commandMessageMapper)
                            .flatMap(Message::getChannel)
                            .flatMap(channel -> channel.createMessage("")));

            final Publisher<?> onDisconnect = gateway.onDisconnect()
                    .doOnTerminate(() -> System.out.println("Disconnected!"));

            return Mono.when(
                    pingPong,
                    onDisconnect,
                    command);
        }).block();
    }

}
