package thelegion.commands.usercommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class UserMuteCommand implements Command {

    @Override
    public String getCommand() {
        return "mute";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        try {
            List<Member> members = event.getMessage().getMentionedMembers();
            int time = -1;
            String[] content = event.getMessage().getContentRaw().split(" ");
            try {
                time = Integer.parseInt(content[content.length - 1]);
            } catch (Exception error) {
                event.getAuthor().openPrivateChannel().complete().sendMessage("**No time specified so user muted indefinitely**").queue();
            }
            int finalTime = time;
            members.forEach(member -> {
                DiscordManager.getInstance().addMutedMember(member, finalTime, event);
            });
        } catch(Exception e) {
            event.getChannel().sendMessage("**Usage: ~mute <user/s> <time in minutes>").queue();
        }
    }
}

