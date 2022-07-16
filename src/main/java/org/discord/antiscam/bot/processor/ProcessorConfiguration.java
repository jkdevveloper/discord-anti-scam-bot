package org.discord.antiscam.bot.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProcessorConfiguration {

    @Bean
    MessageCreateEventProcessor messageCreateEventProcessor() {
        return new MessageCreateEventProcessor();
    }

}
