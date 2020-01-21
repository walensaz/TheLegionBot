package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserKickCommand implements Command {

    @Override
    public String getCommand() {
        return "kick";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        event.getMessage().getMentionedMembers().forEach(member -> {
            member.kick("You have been kicked from MineVerge").queue();
        });
        event.getChannel().sendMessage(event.getAuthor().getAsMention() + " **has been kicked by **" + event.getAuthor().getAsMention()).queue();
    }
}
