package appdeveloper.doomsday;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class BannerArea {

    String image, tag, id;

    public BannerArea(String image, String tag, String id) {
        this.image = image;
        this.tag = tag;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
