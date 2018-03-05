package com.avc.rp.model;

import java.io.Serializable;
import java.util.Date;

public class RedPacket implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer user_id;

    private Double amount;

    private Date send_date;

    private Integer total;

    private Double unit_amount;

    private Integer stock;

    private Integer version;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(Double unit_amount) {
        this.unit_amount = unit_amount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}