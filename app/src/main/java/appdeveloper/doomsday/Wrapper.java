package appdeveloper.doomsday;

import java.util.List;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class Wrapper {

    String section_image, category_id, category_name, banner_title;
    List<SectionArea> sectionAreas;
    List<BannerArea> bannerAreas;


    public Wrapper(String section_image, String category_id, String category_name, String banner_title,
                   List<SectionArea> sectionAreas, List<BannerArea> bannerAreas) {
        this.section_image = section_image;
        this.category_id = category_id;
        this.category_name = category_name;
        this.banner_title = banner_title;
        this.sectionAreas = sectionAreas;
        this.bannerAreas = bannerAreas;
    }

    public String getSection_image() {
        return section_image;
    }

    public void setSection_image(String section_image) {
        this.section_image = section_image;
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

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public List<SectionArea> getSectionAreas() {
        return sectionAreas;
    }

    public void setSectionAreas(List<SectionArea> sectionAreas) {
        this.sectionAreas = sectionAreas;
    }

    public List<BannerArea> getBannerAreas() {
        return bannerAreas;
    }

    public void setBannerAreas(List<BannerArea> bannerAreas) {
        this.bannerAreas = bannerAreas;
    }
}
