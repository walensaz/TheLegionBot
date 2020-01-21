package thelegion.commands.usercommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class UserSetRoleCommand implements Command {

    @Override
    public String getCommand() {
        return "setrole";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        try {
            List<Member> membersToPromote = event.getMessage().getMentionedMembers();
            if (event.getMessage().getMentionedRoles().size() >= 1) {
                Role newRole = event.getMessage().getMentionedRoles().get(0);
                if (event.getMember().canInteract(newRole)) {
                    membersToPromote.forEach(member -> {
                        event.getGuild().addRoleToMember(member, newRole);
                        event.getChannel().sendMessage(member.getAsMention() + " **role has been successfully changed.**").queue();
                    });
                } else {
                    event.getChannel().sendMessage("**You don't have access to set others to this role!**").queue();
                }
            } else {
                String[] message = event.getMessage().getContentRaw().split(" ");
                if (event.getMember().canInteract(DiscordManager.getInstance().getRoleByString(message[message.length - 1], event.getGuild()))) {
                    membersToPromote.forEach(member -> {
                        event.getChannel().sendMessage(member.getAsMention() + " **role has been successfully changed.**").queue();
                        DiscordManager.getInstance().addToRoleByString(member, message[message.length - 1]);
                    });
                } else {
                    event.getChannel().sendMessage("**You don't have access to set others to this role!**").queue();
                }
            }
        } catch(Exception e) {
            event.getChannel().sendMessage("**Usage: ~setrole <member/s> <role>").queue();
        }
    }
}
