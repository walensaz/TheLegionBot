package thelegion.game;

import thelegion.Discord;

import java.util.List;

public enum Events {

    ;


    private List<Time> times;

    Events(List<Time> times) {
        this.times = times;
    }

    public int isTime() {
        if(times.stream().anyMatch(time -> time.hour == Discord.getHour())) {
            if(times.stream().anyMatch(time -> time.minute > Discord.getMinute() && time.minute < Discord.getMinute() + 20)) {
                return 1;
            } else if(times.stream().anyMatch(time -> time.minute - 5 > Discord.getMinute() && time.minute < Discord.getMinute())) {
                return 2;
            }
        }
        return 0;
    }


    class Time {
        private int hour;
        private int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }
    }



}
