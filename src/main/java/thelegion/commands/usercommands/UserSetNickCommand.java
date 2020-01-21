package thelegion.commands.usercommands;

import thelegion.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserSetNickCommand implements Command {

    @Override
    public String getCommand() {
        return "setnick";
    }

    @Override
    public void process(MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");
        if(message.length > 2) {
            Member member = event.getMessage().getMentionedMembers().get(0);
            String newName = "";
            for(int i = 2; i < message.length; i++) {
                newName += message[i] + " ";
            }
            event.getGuild().modifyNickname(member, newName).queue();
            event.getAuthor().openPrivateChannel().complete().sendMessage(member.getEffectiveName() + " **nickname has been changed.**").queue();
        } else {
            event.getAuthor().openPrivateChannel().complete().sendMessage("Use **~setnick [player] [nick]**").queue();
        }
    }
}
