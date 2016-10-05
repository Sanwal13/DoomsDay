package appdeveloper.doomsday;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class Icons {

    String category_id, category_name, icon_image;


    public Icons(String category_id, String category_name, String icon_image) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.icon_image = icon_image;
    }


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }
}
