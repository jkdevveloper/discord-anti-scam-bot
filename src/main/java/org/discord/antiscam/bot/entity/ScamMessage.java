package org.discord.antiscam.bot.entity;

public record ScamMessage(String content) implements Comparable<String> {

    @Override
    public int compareTo(String o) {
        if(o.equals(content)){
            return 1;
        }
        return 0;
    }
}
