package org.discord.antiscam.bot.processing;

import discord4j.core.object.entity.Message;
import org.discord.antiscam.bot.processor.MessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;


@Configuration
class ProcessingConfiguration {

    @Bean
    Process process(MessageProcessor messageCreateEventProcessor,
                    Predicate<Message> commandFilteringPredicate,
                    UnaryOperator<Message> commandMessageMapper) {
        return new Process(messageCreateEventProcessor, commandFilteringPredicate, commandMessageMapper);
    }

}
