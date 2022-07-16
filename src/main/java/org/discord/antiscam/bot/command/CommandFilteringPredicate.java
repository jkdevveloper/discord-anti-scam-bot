package org.discord.antiscam.bot.command;

import discord4j.core.object.entity.Message;

import java.util.function.Predicate;

class CommandFilteringPredicate implements Predicate<Message> {

    @Override
    public boolean test(Message s) {
        var x = s.getContent();
        return switch (x) {
            case "!add", "!remove", "!list" -> true;
            default -> false;
        };
    }
}
