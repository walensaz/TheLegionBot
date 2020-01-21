package thelegion.commands.channelcommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ChannelSlowModeCommand implements Command {

    @Override
    public String getCommand() {
        return "slowmode";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        if(event.getTextChannel().getSlowmode() == 0) {
            event.getTextChannel().getManager().setSlowmode(5).queue();
            DiscordManager.getInstance().addSlowedChannel(event.getChannel().getIdLong());
            event.getChannel().sendMessage("**Channel has been slowed by **" + event.getAuthor().getAsMention()).queue();

        } else {
            event.getTextChannel().getManager().setSlowmode(0).queue();
            DiscordManager.getInstance().removeSlowedChannel(event.getChannel().getIdLong());
            event.getChannel().sendMessage("**Channel is no longer in slowmode!**").queue();
        }
    }
}
