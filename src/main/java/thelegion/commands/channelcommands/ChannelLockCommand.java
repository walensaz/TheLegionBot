package thelegion.commands.channelcommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ChannelLockCommand implements Command {

    public void process(MessageReceivedEvent event) {
        DiscordManager.getInstance().addLockedChannel(event.getChannel().getIdLong());
        event.getChannel().sendMessage("**This channel has been locked by **" + event.getAuthor().getAsMention()).queue();
    }

    @Override
    public String getCommand() {
        return "lock";
    }
}
