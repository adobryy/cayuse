package com.cayuse.challenge.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ElevationInfo {

    private String status;
    private List<Elevation> results = new ArrayList<>();

    @Data
    public static class Elevation {
        private Integer elevation;
        private Integer resolution;
    }
}
