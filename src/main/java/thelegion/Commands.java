package thelegion;


import thelegion.commands.channelcommands.ChannelLockCommand;
import thelegion.commands.channelcommands.ChannelPurgeChatCommand;
import thelegion.commands.channelcommands.ChannelSlowModeCommand;
import thelegion.commands.channelcommands.ChannelUnlockCommand;
import thelegion.commands.usercommands.*;
import net.dv8tion.jda.api.Permission;
import thelegion.commands.Command;

public enum Commands {
    CHANNEL_LOCK_COMMAND("lock", new ChannelLockCommand(), Permission.KICK_MEMBERS),
    CHANNEL_UNLOCK_COMMAND("unlock", new ChannelUnlockCommand(), Permission.KICK_MEMBERS),
    USER_SET_NICK_COMMAND("setnick", new UserSetNickCommand(), Permission.KICK_MEMBERS),
    USER_SET_RANK_COMMAND("setrole", new UserSetRoleCommand(), Permission.KICK_MEMBERS),
    USER_BAN_COMMAND("ban", new UserBanCommand(), Permission.BAN_MEMBERS),
    USER_MUTE_COMMAND("mute", new UserMuteCommand(), Permission.KICK_MEMBERS),
    CHANNEL_SLOW_MODE_COMMAND("slowmode", new ChannelSlowModeCommand(), Permission.KICK_MEMBERS),
    USER_ROLES_COMMAND("roles", new UserRolesCommand(), Permission.MESSAGE_READ),
    USER_UNMUTE_COMMAND("unmute", new UserUnmuteCommand(), Permission.KICK_MEMBERS),
    USER_KICK_COMMAND("kick", new UserKickCommand(), Permission.KICK_MEMBERS),
    USER_HELP_COMMAND("help", new UserHelpCommand(), Permission.MESSAGE_READ),
    USER_PURGE_USER_CHAT_COMMAND("purgeuser", new UserPurgeUserCommand(), Permission.MESSAGE_MANAGE),
    CHANNEL_PURGE_CHAT_COMMAND("purgechat", new ChannelPurgeChatCommand(), Permission.MESSAGE_MANAGE),
    USER_TIME_COMMAND("time", new UserTimeCommand(), Permission.MESSAGE_READ);


    private String commandLabel;
    private Command command;
    private Permission permission;

    Commands(String commandLabel, Command command, Permission permission) {
        this.commandLabel = commandLabel;
        this.command = command;
        this.permission = permission;
    }

    public String getCommandLabel() {
        return commandLabel;
    }

    public void setCommandLabel(String commandLabel) {
        this.commandLabel = commandLabel;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Permission getPermission() {
        return permission;
    }
}
