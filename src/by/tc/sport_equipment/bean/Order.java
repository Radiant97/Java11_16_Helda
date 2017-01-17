package by.tc.sport_equipment.bean;

import java.util.List;

public class Order {
    private User user;
    private List<Equipment> equipments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Order(User user, List<Equipment> equipments) {
        this.user = user;
        this.equipments = equipments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!user.equals(order.user)) return false;
        return equipments.equals(order.equipments);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + equipments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user.toString() +
                ", equipments=" + equipments.toString() +
                '}';
    }
}
