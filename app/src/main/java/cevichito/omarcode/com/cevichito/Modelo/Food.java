package cevichito.omarcode.com.cevichito.Modelo;

public class Food {

    private String Name;
    private String Description;
    private String Price;
    private String Image;
    private String Discount;
    private  String MenuId;

    public Food() {
    }

    public Food(String name, String description, String price, String image, String discount, String menuId) {
        Name = name;
        Description = description;
        Price = price;
        Image = image;
        Discount = discount;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
