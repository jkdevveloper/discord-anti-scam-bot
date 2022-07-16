package org.discord.antiscam.bot.command;

import org.discord.antiscam.bot.persistence.ScamMessagesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfiguration {

    @Bean
    CommandMessageMapper commandMessageMapper(ScamMessagesRepository scamMessagesRepository) {
        return new CommandMessageMapper(new CommandFilteringPredicate(), scamMessagesRepository);
    }

    @Bean
    CommandFilteringPredicate commandFilteringPredicate() {
        return new CommandFilteringPredicate();
    }

}
