package com.advarra.badminton.dtos.tournament_dtos.ongoing;

import java.util.List;
import java.util.Map;

public class MatchGroupDTO {
    private Map<String, List<MatchDTO>> groups;

    public MatchGroupDTO() {
    }

    public MatchGroupDTO(Map<String, List<MatchDTO>> groups) {
        this.groups = groups;
    }

    public Map<String, List<MatchDTO>> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, List<MatchDTO>> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "MatchGroupDTO{" +
                "groups=" + groups +
                '}';
    }
}
