package com.markelloww.discordbot.commands;

/*
 * @Author: Markelloww
 * Date: 09.07.2024
 */

import com.markelloww.discordbot.timetable.BotsCalendar;
import com.markelloww.discordbot.timetable.WeekSchedule;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("all")) {
            event.reply(new WeekSchedule().getWeeklySchedule()).queue();
        }
        else if (command.equals("today")) {
            event.reply(new WeekSchedule().getDayLessons(new BotsCalendar().getCurrentDayOfWeek())).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("all", "Показать полное расписание"));
        commandData.add(Commands.slash("today", "Показать расписание за сегодня"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
