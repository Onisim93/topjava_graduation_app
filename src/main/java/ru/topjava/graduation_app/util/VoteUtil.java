package ru.topjava.graduation_app.util;

import java.time.LocalTime;

public class VoteUtil {
    public static boolean checkTimeVote() {
        LocalTime lt = LocalTime.of(11, 0, 0);
        LocalTime currentLt = LocalTime.now();

        return currentLt.isBefore(lt);
    }
}
