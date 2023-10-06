package com.advarra.badminton.dtos.tournament_dtos.upcoming;

import java.util.*;

public class InitialCreateScheduleManual {
    private Map<String, List<String>> groups = new HashMap<>();

    public InitialCreateScheduleManual() {
    }

    public InitialCreateScheduleManual(Map<String, List<String>> groups) {
        this.groups = groups;
    }

    public Map<String, List<String>> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, List<String>> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "InitialCreateScheduleManual{" +
                "groups=" + groups +
                '}';
    }

    public void addGroup(String group, List<String> teams){
        this.groups.put(group, teams);
    }
}
