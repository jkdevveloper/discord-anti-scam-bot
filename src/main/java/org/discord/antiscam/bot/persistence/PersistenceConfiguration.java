package org.discord.antiscam.bot.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    ScamMessagesInMemoryRepository scamMessagesInMemoryRepository() {
        return new ScamMessagesInMemoryRepository();
    }

}
