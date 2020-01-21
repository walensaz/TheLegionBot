package thelegion;

import thelegion.events.MessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Discord {

    /** The community channel identification. */
    //public static final long BOT_CHANNEL = 478952528210296833L;
    public static final long COMMAND_CHANNEL = 528372781478313994L;
    public static final long RECRUITMENT_CHANNEL = 528380426075635715L;
    public static final long RAID_LOG_CHANNEL = 584662609039720459L;


    private static final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);

    /** The client builder. */
    public static JDA jda;

    /** Handles starting the discord bot. */
    public static void start() {
        executor.schedule(() -> {
            System.out.println("Discord bot has been started!");
        }, 10000, TimeUnit.MILLISECONDS);
        try {
            jda = new JDABuilder("NjY4OTc2OTQwMTMyMjA0NTY2.XiZHhw.WJJO6Ls3hw4qVCcGaQZkD-6nWME").build();
            jda.addEventListener(new MessageEvent());
        } catch(Exception e) {
            e.printStackTrace();
        }

        executor.scheduleAtFixedRate(Discord::updateTime, 3, 10, TimeUnit.SECONDS);

    }

    public static void updateTime() {
        jda.getTextChannelById(668285483134222391L).editMessageById(668981924639014922L, "Server Time: " + getDate()).queue();
    }



    public static void communityMessage(String message) {
        jda.getTextChannelById(COMMAND_CHANNEL).sendMessage(message);
    }

    public static void recruitmentMessage(String message) {
        jda.getTextChannelById(RECRUITMENT_CHANNEL).sendMessage(message);
    }

    public static void botMessage(String message) {
        jda.getTextChannelById(528372781478313994L).sendMessage(message);
    }

    public static Date getDate() {
        return new Date();
    }

    public static int getHour() {
        return getDate().getHours();
    }

    public static int getMinute() {
        return getDate().getMinutes();
    }



}