package com.example.navigation.apinavi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.stream.Location;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    @JsonProperty("origin")
    private Location origin;

    @JsonProperty("destination")
    private Location destination;

    @JsonProperty("waypoints")
    private List<Location> waypoints;


    @Data
    public static class Location {
        @JsonProperty("name")
        String name;

        @JsonProperty("x")
        Double x;

        @JsonProperty("y")
        Double y;

    }
}
