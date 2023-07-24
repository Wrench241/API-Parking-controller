package com.api.parkingcontrol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB-parking-control")
public class ParkingModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String car;
    private String name;

    public ParkingModel(){

    }
    public ParkingModel(Integer id, String car, String name){
        this.id = id;
        this.car = car;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingModel that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCar(), that.getCar()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCar(), getName());
    }
}
