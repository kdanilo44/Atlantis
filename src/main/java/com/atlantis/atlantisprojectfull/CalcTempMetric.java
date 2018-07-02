/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.atlantisprojectfull;

import java.io.Serializable;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "temperature_metrics")
public class CalcTempMetric {

    @Id
    @Column(name = "id_metric")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_metric;

    @Column(name = "id_device")
    private String id_device;

    @Column(name = "date_temp")
    @Temporal(TemporalType.DATE)
    private Date date_temp;

    @Column(name = "min_temp")
    private float min_temp;

    @Column(name = "max_temp")
    private float max_temp;

    @Column(name = "average_temp")
    private float average_temp;

    @Column(name = "type_calc")
    private int type_calc;

    public int getType_calc() {
        return type_calc;
    }

    public void setType_calc(int type_calc) {
        this.type_calc = type_calc;
    }

    public Long getId_metric() {
        return id_metric;
    }

    public void setId_metric(Long id_metric) {
        this.id_metric = id_metric;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }

    public Date getDate_temp() {
        return date_temp;
    }

    public void setDate_temp(Date date_temp) {
        this.date_temp = date_temp;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getAverage_temp() {
        return average_temp;
    }

    public void setAverage_temp(float average_temp) {
        this.average_temp = average_temp;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id_metric", this.id_metric)
                .add("id_device", this.id_device)
                .add("created", this.date_temp.toString())
                .add("min", this.min_temp)
                .add("max", this.max_temp)
                .add("average", this.average_temp)
                .add("type", this.type_calc)
                .build();
    }

}
