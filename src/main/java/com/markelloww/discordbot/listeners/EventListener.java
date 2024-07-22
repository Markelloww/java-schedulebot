package com.markelloww.discordbot.listeners;

/*
 * @Author: Markelloww
 * Date: 07.07.2024
 */

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class EventListener extends ListenerAdapter {

    private final String reactionChannelId;
    private final String mailingChannelId;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public EventListener() {
        Dotenv config = Dotenv.configure().load();
        this.reactionChannelId = config.get("REACTION_CHANNEL_ID");
        this.mailingChannelId = config.get("MAILING_CHANNEL_ID");
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        User user = event.getUser();
        if (user == null || user.isBot() || event.getUser().isBot()) {
            return;
        }

        Message message = event.getChannel().retrieveMessageById(event.getMessageId()).complete();
        if (message.getAuthor().isBot()) {
            return;
        }

        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String nicknameOwner = Objects.requireNonNull(message.getMember()).getEffectiveName();
        String channelMention = event.getChannel().getAsMention();
        String result = "**" + user.getGlobalName() + "**" + " поставил  " + emoji + "  сообщению **" + nicknameOwner + "** в " + channelMention;

        TextChannel targetChannel = event.getGuild().getTextChannelById(reactionChannelId);
        if (targetChannel != null) {
            targetChannel.sendMessage(result).queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

}
