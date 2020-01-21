package thelegion;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiscordManager {

    private ArrayList<Long> lockedChannels;
    private ArrayList<Long> slowedChannels;

    ScheduledExecutorService scheduler;

    private HashMap<Member, Future<?>> mutedMembers;

    private static DiscordManager instance;

    public static DiscordManager getInstance() {
        return instance == null ? instance = new DiscordManager() : instance;
    }

    private DiscordManager() {
        lockedChannels = new ArrayList<>();
        slowedChannels = new ArrayList<>();
        mutedMembers = new HashMap<>();
        scheduler = Executors.newScheduledThreadPool(1000);
    }

    public ArrayList<Long> getLockedChannels() {
        return lockedChannels;
    }

    public ArrayList<Long> getSlowedChannels() {
        return slowedChannels;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public void addLockedChannel(long id) {
        lockedChannels.add(id);
    }

    public void removeLockedChannel(long id) {
        lockedChannels.remove(id);
    }

    public void addSlowedChannel(long id) {
        slowedChannels.add(id);
    }

    public void addMutedMember(Member member, int minutes, MessageReceivedEvent event) {
        Future<?> future;
        if(minutes != -1) {
            future = DiscordManager.getInstance().getScheduler().schedule(() ->
                            DiscordManager.getInstance().removeMutedMember(member)
                    , minutes, TimeUnit.MINUTES);
            event.getChannel().sendMessage(event.getMessage().getMentionedMembers().get(0).getAsMention() + " **has been muted for " + minutes + " minutes!**").queue();
        } else {
            future = null;
            event.getChannel().sendMessage(event.getMessage().getMentionedMembers().get(0).getAsMention() + " **has been muted indefinitely!**").queue();
        }
        addToRoleByString(member, "muted");
        List<Role> roles = event.getGuild().getRolesByName("muted", true);
        mutedMembers.put(member, future);
    }

    public void removeMutedMember(Member member) {
        removeRoleByString(member, "muted");
        if(mutedMembers.get(member) != null) {
            mutedMembers.get(member).cancel(true);
        }
        if(mutedMembers.containsKey(member)) {
            mutedMembers.remove(member);
        }
    }

    public void removeSlowedChannel(long id) {
        slowedChannels.remove(id);
    }

    public HashMap<Member, Future<?>> getMutedMembers() {
        return mutedMembers;
    }

    public Role getRoleByString(String role, Guild guild) {
        return guild.getRolesByName(role, true).get(0);
    }

    public void addToRoleByString(Member member, String role) {
        List<Role> roles = member.getGuild().getRolesByName(role, true);
        if(roles.size() >= 1) {
            member.getGuild().addRoleToMember(member, roles.get(0)).queue();
        } else member.getUser().openPrivateChannel().complete().sendMessage("The server does not have a " + role + " role, contact admins.").queue();
    }

    public void removeRoleByString(Member member, String role) {
        List<Role> roles = member.getGuild().getRolesByName(role, true);
        if(roles.size() >= 1) {
            if(member.getRoles().contains(roles.get(0))) {
                member.getGuild().removeRoleFromMember(member, roles.get(0)).queue();
            }
        } else member.getUser().openPrivateChannel().complete().sendMessage("The server does not have a " + role + " role, contact admins.").queue();
    }

}
