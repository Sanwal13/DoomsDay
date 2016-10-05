package appdeveloper.doomsday;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class AdvBanners {

    String image, category_id, tag;

    public AdvBanners(String image, String category_id, String tag) {
        this.image = image;
        this.category_id = category_id;
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
