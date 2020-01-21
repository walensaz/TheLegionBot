package thelegion.events;

import thelegion.DiscordManager;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ShutdownEvent extends ListenerAdapter {

    @Override
    public void onDisconnect(@Nonnull DisconnectEvent event) {
        DiscordManager.getInstance().getMutedMembers().forEach((user, future) -> {
            if(future == null) {
                return;
            }
            if(!future.isCancelled()) {
                future.cancel(true);
            }
        });
    }
}
