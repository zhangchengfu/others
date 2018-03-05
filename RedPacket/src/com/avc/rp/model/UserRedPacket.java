package com.avc.rp.model;

import java.io.Serializable;
import java.util.Date;

public class UserRedPacket implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer red_packet_id;

    private Integer user_id;

    private Double amount;

    private Date grab_time;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRed_packet_id() {
        return red_packet_id;
    }

    public void setRed_packet_id(Integer red_packet_id) {
        this.red_packet_id = red_packet_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getGrab_time() {
        return grab_time;
    }

    public void setGrab_time(Date grab_time) {
        this.grab_time = grab_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}