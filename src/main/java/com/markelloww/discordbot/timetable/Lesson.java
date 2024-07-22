package com.markelloww.discordbot.timetable;

/*
 * @Author: Markelloww
 * Date: 09.07.2024
 */

public class Lesson {

    private final String subject;
    private final String startTime;
    private final String endTime;
    private final String cabinet;

    public Lesson(String subject, String startTime, String endTime, String cabinet) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cabinet = cabinet;
    }

    public String getSubject() {
        return subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCabinet() {
        return cabinet;
    }

}
