package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserHelpCommand implements Command {


    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        event.getAuthor().openPrivateChannel().complete().sendMessage("Tickets\n" +
                "rename command \n" +
                "remove player command\n" +
                "add player command\n" +
                "rename command \n" +
                "ticket logs \n" +
                "\n" +
                "Basics\n" +
                "channel lock command\n" +
                "channel unlock command\n" +
                "channel slow mode command\n" +
                "role command\n" +
                "role all command\n" +
                "nickname command \n" +
                "suggestion commands (suggestions embedded)\n" +
                "\n" +
                "Moderation\n" +
                "Ban\n" +
                "Kick\n" +
                "Mute\n" +
                "Warn\n" +
                "Purge Chat (clears all messages)\n" +
                "User Purge chat (clears messages for a player)\n" +
                "\n" +
                "Administrator\n" +
                "Announce Command (Announcment embedded)\n" +
                "promote command (promotes them to trial helper, then helper etc)\n" +
                "demoted command (demotes them back to member rank or last known rank before any staff rank)").queue();

        event.getAuthor().openPrivateChannel().complete().sendMessage("Working commands: lock, purgeuser, purgechat, slowmode, unlock, ban, kick, mute, roles, setnick, setrole, unmute");
    }
}
