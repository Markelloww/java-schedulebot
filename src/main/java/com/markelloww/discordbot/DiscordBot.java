package com.markelloww.discordbot;

/*
 * @Author: Markelloww
 * Date: 07.07.2024
 */

import com.markelloww.discordbot.commands.CommandManager;
import com.markelloww.discordbot.listeners.EventListener;
import com.markelloww.discordbot.timetable.WeekSchedule;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public DiscordBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.customStatus("Решает диффуры"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();
        shardManager.addEventListener(new EventListener(), new CommandManager(), new WeekSchedule());
    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();
        } catch(LoginException e) {
            System.out.println("Error: Invalid token!");
        }
    }

}
