package thelegion.commands.channelcommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ChannelUnlockCommand implements Command {

    @Override
    public String getCommand() {
        return "unlock";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        DiscordManager.getInstance().removeLockedChannel(event.getChannel().getIdLong());
        event.getChannel().sendMessage("**Channel has been unlocked**").queue();
    }
}
