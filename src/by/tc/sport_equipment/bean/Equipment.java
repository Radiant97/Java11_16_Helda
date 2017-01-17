package by.tc.sport_equipment.bean;

public class Equipment {
    private String title;
    private int price;
    private Category category;

    public  Equipment(){}

    public Equipment(String title, int price, Category category){
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (price != equipment.price) return false;
        if (!title.equals(equipment.title)) return false;
        return category == equipment.category;

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + price;
        result = 31 * result + category.hashCode();
        return result;
    }
}
