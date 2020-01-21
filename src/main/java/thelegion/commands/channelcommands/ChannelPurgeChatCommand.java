package thelegion.commands.channelcommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ChannelPurgeChatCommand implements Command {

    @Override
    public String getCommand() {
        return "purge";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        List<Message> messages = event.getChannel().getHistoryBefore(event.getMessage(), 100).complete().getRetrievedHistory();
        try {
            for (int i = 0; i < Integer.valueOf(event.getMessage().getContentRaw().split(" ")[1]); i++) {
                messages.get(i).delete().queue();
            }
            event.getMessage().delete().queue();
            event.getAuthor().openPrivateChannel().complete().sendMessage("**Chat purged!**").queue();
        } catch(Exception e) {
            event.getChannel().sendMessage("**Incorrect usage, Use ~purge [amount of msgs]**").queue();
            e.printStackTrace();
        }
    }
}
