package thelegion.events;

import thelegion.Commands;
import thelegion.DiscordManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Member user = event.getMember();
        String message = event.getMessage().getContentRaw();
        MessageChannel messageChannel = event.getChannel();
        Long channel = event.getChannel().getIdLong();
        if(event.getAuthor().isBot()) return;
        if(!user.hasPermission(Permission.MESSAGE_MANAGE)) {
            if (DiscordManager.getInstance().getLockedChannels().contains(channel)) {
                event.getMessage().delete().submit();
                return;
            }
            if(DiscordManager.getInstance().getMutedMembers().keySet().contains(user)) {
                event.getMessage().delete().submit();
                return;
            }
        }
        if(!message.startsWith("~")) {
            return;
        }
        message = message.replace("~", "");
        for (Commands commands : Commands.values()) {
            if(message.startsWith(commands.getCommandLabel())) {
                if(event.getMember().hasPermission(commands.getPermission())) {
                    commands.getCommand().process(event);
                    return;
                } else {
                    return;
                }
            }
        }
        event.getGuild().getTextChannelById(channel).sendMessage("Command doesn't exist...").queue();

    }
}
