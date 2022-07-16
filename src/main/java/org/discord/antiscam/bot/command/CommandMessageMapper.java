package org.discord.antiscam.bot.command;

import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.discord.antiscam.bot.persistence.ScamMessagesRepository;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Slf4j
@RequiredArgsConstructor
class CommandMessageMapper implements UnaryOperator<Message> {

    private final Predicate<Message> commandFilteringPredicate;
    private final ScamMessagesRepository scamMessagesInMemoryRepository;

    @Override
    public Message apply(Message message) {
        if(commandFilteringPredicate.test(message)){
            var content = message.getContent();
            var argument = extractArgument(content);
            scamMessagesInMemoryRepository.addMessage(argument);
        }

        return message;
    }

    private String extractArgument(String command){
        return command.replaceFirst("^[^\\s]*\\s", "");
    }
}
