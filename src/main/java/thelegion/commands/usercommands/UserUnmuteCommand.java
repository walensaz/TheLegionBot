package thelegion.commands.usercommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class UserUnmuteCommand implements Command {

    @Override
    public String getCommand() {
        return "unmute";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        List<Member> members = event.getMessage().getMentionedMembers();
        members.forEach(member -> {
            if(DiscordManager.getInstance().getMutedMembers().containsKey(member)) {
                DiscordManager.getInstance().removeMutedMember(member);
            } else {
                DiscordManager.getInstance().removeRoleByString(member, "muted");
            }
            event.getChannel().sendMessage(member.getAsMention() + " **has been unmuted.**").queue();
        });

    }
}
