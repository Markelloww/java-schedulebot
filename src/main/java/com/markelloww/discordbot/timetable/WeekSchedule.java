package com.markelloww.discordbot.timetable;

/*
 * @Author: Markelloww
 * Date: 09.07.2024
 */

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WeekSchedule extends ListenerAdapter {

    public Map<String, DaySchedule> weekSchedule;
    LocalDate today = LocalDate.now();

    public WeekSchedule() {
        weekSchedule = new LinkedHashMap<>();
        weekSchedule.put("Понедельник", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
                ));
        weekSchedule.put("Вторник", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
        ));
        weekSchedule.put("Среда", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
        ));
        weekSchedule.put("Четверг", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
        ));
        weekSchedule.put("Пятница", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
        ));
        weekSchedule.put("Суббота", new DaySchedule(
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER"),
                new Lesson("Subject name", "TIME_FIRST", "TIME_LAST", "NUMBER")
        ));
    }

    // True - знаменатель, False - числитель
    public String getWeeklySchedule() {
        StringBuilder weeklyScheduleBuilder = new StringBuilder();
        Set<String> days = weekSchedule.keySet();

        weeklyScheduleBuilder.append("# На этой недели учимся по ");
        if (new BotsCalendar().evenWeek(today.getDayOfMonth(), today.getMonthValue())) {
            weeklyScheduleBuilder.append("знаменателю!\n");

        } else {
            weeklyScheduleBuilder.append("числителю!\n");
        }

        for (String day : days) {
            DaySchedule daySchedule = weekSchedule.get(day);
            weeklyScheduleBuilder.append("## ");
            if (day.equals(new BotsCalendar().getCurrentDayOfWeek())) {
                weeklyScheduleBuilder.append("__***");
            }
            weeklyScheduleBuilder.append(day);
            if (day.equals(new BotsCalendar().getCurrentDayOfWeek())) {
                weeklyScheduleBuilder.append("***__");
            }
            weeklyScheduleBuilder.append(": ")
                    .append("\n")
                    .append(daySchedule.getDaySchedule())
                    .append("\n");
        }
        return weeklyScheduleBuilder.toString();
    }

    public String getDayLessons(String key) {
        if (key.equals("Воскресенье")) {
            return "Сегодня отдыхаем";
        }
        StringBuilder dayScheduleBuilder = new StringBuilder();
        Set<String> days = weekSchedule.keySet();

        dayScheduleBuilder.append("# Сегодня учимся по ");
        if (new BotsCalendar().evenWeek(today.getDayOfMonth(), today.getMonthValue())) {
            dayScheduleBuilder.append("знаменателю!\n");

        } else {
            dayScheduleBuilder.append("числителю!\n");
        }

        for (String day : days) {
            if (day.equals(key)) {
                DaySchedule daySchedule = weekSchedule.get(day);
                dayScheduleBuilder.append("## ")
                        .append("__***")
                        .append(day)
                        .append("***__")
                        .append(":\n")
                        .append(daySchedule.getDaySchedule())
                        .append("\n");
            }
        }
        return dayScheduleBuilder.toString();
    }

}
