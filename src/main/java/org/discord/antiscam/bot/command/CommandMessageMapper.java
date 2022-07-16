package org.discord.antiscam.bot.command;

import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.discord.antiscam.bot.ScamMessagesInMemoryRepository;

import java.util.function.UnaryOperator;

@Slf4j
@RequiredArgsConstructor
public class CommandMessageMapper implements UnaryOperator<Message> {

    CommandFilteringPredicate commandFilteringPredicate = new CommandFilteringPredicate();
    ScamMessagesInMemoryRepository scamMessagesInMemoryRepository = new ScamMessagesInMemoryRepository();

    @Override
    public Message apply(Message message) {
        if(commandFilteringPredicate.test(message)){
            var content = message.getContent();
            var argument = extractArgument(content);

        }

        return message;
    }

    private String extractArgument(String command){
        if(command.contains("!add")){
            return command.replaceFirst("^[^\\s]*\\s", "");

        }
        return "dupa";
    }
}
