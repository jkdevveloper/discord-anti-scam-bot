package org.discord.antiscam.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandMessageMapperTest {

    @Test
    void testFiltering(){
        String command = "!add some keyword or sentence";
        String x = command.replaceFirst("^[^\\s]*\\s", "");

        Assertions.assertEquals("some keyword or sentence", x);
    }

}
