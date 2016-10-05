package appdeveloper.doomsday;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class SectionArea {

    String product_id, category_id, name, image, price, special, discount;

    public SectionArea(String product_id, String category_id, String name, String image, String price,
                       String special, String discount) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.special = special;
        this.discount = discount;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
