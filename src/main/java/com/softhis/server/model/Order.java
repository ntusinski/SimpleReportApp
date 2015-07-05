package com.softhis.server.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @Column(name = "CLIENT", nullable = false)
    private Client client;

    @Column(name = "ORDER_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalTime orderDate;

    @OneToMany
    private List<OrderElement> orderElements;

    public Order() {
    }

    public Order(Client client, LocalTime orderDate, List<OrderElement> orderElements) {
        this.client = client;
        this.orderDate = orderDate;
        this.orderElements = orderElements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(List<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    public int getOrderAmount() {
        int amount = 0;
        for (OrderElement element : orderElements) {
            amount += element.getAmount();
        }
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        return !(orderElements != null ? !orderElements.equals(order.orderElements) : order.orderElements != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderElements != null ? orderElements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderElements=" + orderElements +
                '}';
    }
}
