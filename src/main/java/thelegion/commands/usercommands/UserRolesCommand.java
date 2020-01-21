package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserRolesCommand implements Command {

    @Override
    public String getCommand() {
        return "roles";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        String roles = "Our roles are: ";
        for(Role role : event.getGuild().getRoles()) {
            roles += role.getName() + ", ";
        }
        event.getAuthor().openPrivateChannel().complete().sendMessage(roles).queue();
    }
}
