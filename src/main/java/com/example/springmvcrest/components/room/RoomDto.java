package com.example.springmvcrest.components.room;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class RoomDto {

    private Long id;
    @NotEmpty(message = "{com.example.springmvcrest.model.Room.NotEmpty}")
    private String city;
    @Column(unique = true)
    private int number;
    private int price;
    private String description;
    private String roomCategory;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
