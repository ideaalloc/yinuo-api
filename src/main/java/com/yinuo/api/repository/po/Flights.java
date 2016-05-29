package com.yinuo.api.repository.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-13
 */
public class Flights implements Serializable {
    Long id;
    String origin;
    String destination;
    String airline;
    String flightNumber;
    String traveler;

    public Flights() {
    }

    public Flights(Long id, String origin, String destination, String airline, String flightNumber, String traveler) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.traveler = traveler;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "航班ID", required = true)
    public Long getId() {
        return id;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "出发地", required = true)
    public String getOrigin() {
        return origin;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "抵达地", required = true)
    public String getDestination() {
        return destination;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "航线", required = true)
    public String getAirline() {
        return airline;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "航班号", required = true)
    public String getFlightNumber() {
        return flightNumber;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "旅行支票", required = true)
    public String getTraveler() {
        return traveler;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", airline='" + airline + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", traveler='" + traveler + '\'' +
                '}';
    }
}
