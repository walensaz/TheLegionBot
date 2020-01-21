package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class UserPurgeUserCommand implements Command {

    @Override
    public String getCommand() {
        return "purgeuser";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        try {
            List<Member> members = event.getMessage().getMentionedMembers();
            members.forEach(member -> {
                member.getGuild().getTextChannels().forEach(channel -> {
                            List<Message> messagesToDelete = channel.getHistory().retrievePast(100).complete().stream().filter(message -> message.getMember().getIdLong() == member.getIdLong()).collect(Collectors.toList());
                            event.getChannel().purgeMessages(messagesToDelete);
                        });
                event.getAuthor().openPrivateChannel().complete().sendMessage(member.getEffectiveName() + " **messages have been purged!**").queue();
            });
        } catch(Exception e) {
            event.getChannel().sendMessage("**Usage: ~purgeuser <user/s>").queue();
        }
    }
}
