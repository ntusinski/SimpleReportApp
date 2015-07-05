package com.softhis.shared.dto;

import org.joda.time.LocalTime;

import java.io.Serializable;

public class ReportOrderDto implements Serializable {
    LocalTime orderDate;

    int amount;

    public ReportOrderDto() {
    }

    public ReportOrderDto(LocalTime orderDate, int amount) {
        this.orderDate = orderDate;
        this.amount = amount;
    }

    public LocalTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportOrderDto that = (ReportOrderDto) o;

        if (amount != that.amount) return false;
        return !(orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null);

    }

    @Override
    public int hashCode() {
        int result = orderDate != null ? orderDate.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "ReportOrderDto{" +
                "orderDate=" + orderDate +
                ", amount=" + amount +
                '}';
    }
}
