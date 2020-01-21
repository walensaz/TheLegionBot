package thelegion.commands.usercommands;

import thelegion.Discord;
import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserTimeCommand implements Command {


    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        event.getAuthor().openPrivateChannel().complete().sendMessage(Discord.getDate().toString()).complete();
    }
}
