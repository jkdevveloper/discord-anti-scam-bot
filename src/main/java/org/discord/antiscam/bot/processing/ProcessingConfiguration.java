package org.discord.antiscam.bot.processing;

import org.discord.antiscam.bot.processor.MessageCreateEventProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class ProcessingConfiguration {

    @Bean
    Process process(MessageCreateEventProcessor messageCreateEventProcessor) {
        return new Process(messageCreateEventProcessor);
    }

}
