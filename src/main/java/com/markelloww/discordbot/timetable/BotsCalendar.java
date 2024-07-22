package com.markelloww.discordbot.timetable;

/*
 * @Author: Markelloww
 * Date: 09.07.2024
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class BotsCalendar {

    public String getCurrentDayOfWeek() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return translateDayOfWeek(dayOfWeek);
    }

    private String translateDayOfWeek(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "Понедельник";
            case TUESDAY -> "Вторник";
            case WEDNESDAY -> "Среда";
            case THURSDAY -> "Четверг";
            case FRIDAY -> "Пятница";
            case SATURDAY -> "Суббота";
            case SUNDAY -> "Воскресенье";
        };
    }

    public Boolean evenWeek(int dayOfMonth, int month) {
        LocalDate date = YearMonth.now().atDay(dayOfMonth).withMonth(month);
        int weekOfYear = date.get(WeekFields.of(Locale.getDefault()).weekOfYear());
        return weekOfYear % 2 == 0;
    }

}
