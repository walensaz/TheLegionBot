package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class UserBanCommand implements Command {

    @Override
    public String getCommand() {
        return "ban";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        List<Member> members = event.getMessage().getMentionedMembers();
        int time = 1000;
        try {
            time = Integer.parseInt(event.getMessage().getContentRaw().split(" ")[2]);
        } catch(NumberFormatException exception) {
            event.getAuthor().openPrivateChannel().complete().sendMessage("No time specified so user banned indefinitely").queue();
        }
        int finalTime = time;
        members.forEach(member -> {
            event.getGuild().ban(member, finalTime);
        });
    }
}
