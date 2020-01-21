package thelegion.commands.usercommands;

import thelegion.DiscordManager;
import thelegion.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserSetNotifyCommand implements Command {


    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        DiscordManager.getInstance().addToRoleByString(event.getMember(), "Notify");
        event.getAuthor().openPrivateChannel().complete().sendMessage("***You will now be notified of events***").complete();
    }
}
