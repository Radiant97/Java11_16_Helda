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
}
