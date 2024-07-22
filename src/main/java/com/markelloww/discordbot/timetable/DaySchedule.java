package com.markelloww.discordbot.timetable;

/*
 * @Author: Markelloww
 * Date: 09.07.2024
 */

public class DaySchedule {

    private final Lesson[] lessons;

    public DaySchedule(Lesson... lessons) {
        this.lessons = lessons;
    }

    public String getDaySchedule() {
        StringBuilder schedule = new StringBuilder();
        for (Lesson lesson : lessons) {
            schedule.append(" - **")
                    .append(lesson.getSubject())
                    .append(":** ")
                    .append(lesson.getStartTime())
                    .append(" - ")
                    .append(lesson.getEndTime())
                    .append(". *Кабинет: ")
                    .append(lesson.getCabinet())
                    .append("*")
                    .append("\n");
        }
        return schedule.toString();
    }

}
