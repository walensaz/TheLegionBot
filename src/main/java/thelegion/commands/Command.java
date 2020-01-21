package thelegion.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {

    String getCommand();

    void process(MessageReceivedEvent event);

}
