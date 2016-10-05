package appdeveloper.doomsday;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Home";
    Context context = MainActivity.this;
    ViewPager pager;
    ExpandableHeightGridView cat_grid;
    Button see_more;
    LinearLayout recent_layout;
    HorizontalView list_gallery;
    NestedListView list;
    ProgressBar progress_list;
    ProgressDialog dialog;
    String url_home = "http://192.168.1.195/woodenstreet/index.php?route=api/api_1_7_4/NewHomePage2";
    List<AdvBanners> advBannerses;
    List<Icons> iconses;
    List<SectionArea> sectionAreas;
    List<BannerArea> bannerAreas;
    List<Wrapper> wrappers;
    boolean isButtonClicked = false;
    HomeGridAdapter homeGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        advBannerses = new ArrayList<AdvBanners>();
        iconses = new ArrayList<Icons>();

        wrappers = new ArrayList<Wrapper>();


        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isButtonClicked = !isButtonClicked;
                if (isButtonClicked) {
                    see_more.setText(getString(R.string.less));
                } else {
                    see_more.setText(String.format(getString(R.string.more), (iconses.size() - 6)));
                }

                homeGridAdapter = new HomeGridAdapter(context, iconses, isButtonClicked);
                Log.d("Clicked", "Adapter set");
                cat_grid.setAdapter(homeGridAdapter);
                cat_grid.setExpanded(true);
                homeGridAdapter.notifyDataSetChanged();
            }
        });

        getHomeItems("0", "50");
    }

    public void findViewById() {
        pager = (ViewPager) findViewById(R.id.pager);
        cat_grid = (ExpandableHeightGridView) findViewById(R.id.cat_grid);
        see_more = (Button) findViewById(R.id.see_more);
        recent_layout = (LinearLayout) findViewById(R.id.recent_layout);
        list_gallery = (HorizontalView) findViewById(R.id.list_gallery);
        list = (NestedListView) findViewById(R.id.list);
        progress_list = (ProgressBar) findViewById(R.id.progress_list);
    }


    /*private void getHomeItems(final String start, final String limit) {

        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();
        String strTAG = "home_item";


        final StringRequest chk_Req = new StringRequest(Request.Method.POST, url_home,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        Log.e(TAG, "Response : " + response);
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean status = object.getBoolean("status");
                            if (status) {
                                // Code to get all banners in AdvBanners list
                                JSONObject bannerObj = object.getJSONObject("banner");
                                Log.e(TAG, "Response banner obj : " + bannerObj.toString());
                                Iterator<String> key = bannerObj.keys();

                                while (key.hasNext()) {
                                    String item = key.next();
                                    JSONObject json_item = bannerObj.getJSONObject(item);
                                    String category_id = json_item.getString("category_id");
                                    String image = json_item.getString("image");
                                    String tag = json_item.getString("tag");

                                    AdvBanners advBanners = new AdvBanners(image, category_id, tag);
                                    advBannerses.add(advBanners);
                                }


                                // Code to get all icons in Icons list
                                JSONObject iconObj = object.getJSONObject("icon");

                                Iterator<String> key_grid = iconObj.keys();
                                while (key_grid.hasNext()) {
                                    String grid_item = key_grid.next();
                                    JSONObject jsonObject1 = iconObj.getJSONObject(grid_item);
                                    String category_id = jsonObject1.getString("category_id");
                                    String icon_image = jsonObject1.getString("icon_image");
                                    String category_name = jsonObject1.getString("category_name");

                                    Icons icons = new Icons(category_id, category_name, icon_image);
                                    iconses.add(icons);
                                }

                                // Code to get all wrapper in Icons list
                                JSONObject wrapperObj = object.getJSONObject("wrapper");
                                Iterator<String> key_main = wrapperObj.keys();
                                while (key_main.hasNext()) {
                                    String all_item = key_main.next();
                                    JSONObject json_items = wrapperObj.getJSONObject(all_item);

                                    String section_image = json_items.getString("section_image");
                                    String category_id = json_items.getString("category_id");
                                    String category_name = json_items.getString("category_name");
                                    String banner_title = json_items.getString("banner_title");

                                    JSONObject json_sectnarea = json_items.getJSONObject("sectionarea");

                                    Iterator<String> key_items = json_sectnarea.keys();
                                    while (key_items.hasNext()) {
                                        String item = key_items.next();
                                        JSONObject jsonObj_items = json_sectnarea.getJSONObject(item);

                                        String product_id = jsonObj_items.getString("product_id");
                                        String sec_category_id = jsonObj_items.getString("category_id");
                                        String name = jsonObj_items.getString("name");
                                        String image = jsonObj_items.getString("image");
                                        String price = jsonObj_items.getString("price");
                                        String special = jsonObj_items.getString("special");
                                        String discount = jsonObj_items.getString("discount");

                                        SectionArea sectionArea = new SectionArea(product_id, sec_category_id, name,
                                                image, price, special, discount);
                                        sectionAreas.add(sectionArea);
                                    }

                                    JSONObject json_banner_area = json_items.getJSONObject("bannerarea");
                                    Iterator<String> key_banners = json_banner_area.keys();
                                    while (key_banners.hasNext()) {
                                        String str = key_banners.next();
                                        JSONObject jsonObj_ = json_banner_area.getJSONObject(str);
                                        String image = jsonObj_.getString("image");
                                        String tag = jsonObj_.getString("tag");
                                        String id = jsonObj_.getString("id");

                                        BannerArea bannerArea = new BannerArea(image, tag, id);
                                        bannerAreas.add(bannerArea);
                                    }

                                    Wrapper wrapper = new Wrapper(section_image, category_id, category_name,
                                            banner_title, sectionAreas, bannerAreas);
                                    wrappers.add(wrapper);
                                }
                            }

                            pager.setAdapter(new AdvAdapter(context, advBannerses));
                            if (iconses.size() == 6) {
                                see_more.setVisibility(View.GONE);
                            } else {
                                see_more.setVisibility(View.VISIBLE);
                                see_more.setText(String.format(getString(R.string.more), (iconses.size() - 6)));
                            }

                            homeGridAdapter = new HomeGridAdapter(context, iconses, isButtonClicked);
                            cat_grid.setAdapter(homeGridAdapter);
                            cat_grid.setExpanded(true);

                            list.setAdapter(new HomePageAdapter(context, wrappers, sectionAreas));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("limit", limit);
                params.put("start", start);
                Log.e(TAG, "Homepage : Param===>" + params);
                return params;
            }
        };
        chk_Req.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError error) throws VolleyError {
            }

            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }
        });
        chk_Req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(chk_Req, strTAG);
    }*/

    private void getHomeItems(final String start, final String limit) {

        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();
        String strTAG = "home_item";


        StringRequest chk_Req = new StringRequest(Request.Method.POST, url_home,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        Log.e(TAG, "Response : " + response);
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean status = object.getBoolean("status");
                            if (status) {
                                // Code to get all banners in AdvBanners list
                                // JSONObject bannerObj = object.getJSONObject("banner");
                                JSONArray bannerArray = object.getJSONArray("banner");
                                for (int i = 0; i < bannerArray.length(); i++) {
                                    JSONObject bannerObj = bannerArray.getJSONObject(i);
                                    String image = bannerObj.getString("image");
                                    String category_id = bannerObj.getString("category_id");
                                    String tag = bannerObj.getString("tag");

                                    AdvBanners advBanners = new AdvBanners(image, category_id, tag);
                                    advBannerses.add(advBanners);
                                }

                                // Code to get all icons in Icons list
                                //  JSONObject iconObj = object.getJSONObject("icon");
                                JSONArray iconArray = object.getJSONArray("icon");
                                for (int i = 0; i < iconArray.length(); i++) {
                                    JSONObject iconObj = iconArray.getJSONObject(i);
                                    String category_id = iconObj.getString("category_id");
                                    String category_name = iconObj.getString("category_name");
                                    String icon_image = iconObj.getString("icon_image");

                                    Icons icons = new Icons(category_id, category_name, icon_image);
                                    iconses.add(icons);
                                }

                                // Code to get all wrapper in Icons list
                                JSONArray wrapperArray = object.getJSONArray("wrapper");
                                for (int i = 0; i < wrapperArray.length(); i++) {

                                    sectionAreas = new ArrayList<SectionArea>();
                                    bannerAreas = new ArrayList<BannerArea>();
                                    JSONObject wrapperObj = wrapperArray.getJSONObject(i);

                                    String section_image = wrapperObj.getString("section_image");
                                    String category_id = wrapperObj.getString("category_id");
                                    String category_name = wrapperObj.getString("category_name");
                                    String banner_title = wrapperObj.getString("banner_title");

                                    JSONArray sectioArray = wrapperObj.getJSONArray("sectionarea");
                                    for (int j = 0; j < sectioArray.length(); j++) {

                                        JSONObject jsonObject = sectioArray.getJSONObject(j);

                                        String product_id = jsonObject.getString("product_id");
                                        String sec_category_id = jsonObject.getString("category_id");
                                        String name = jsonObject.getString("name");
                                        String image = jsonObject.getString("image");
                                        String price = jsonObject.getString("price");
                                        String special = jsonObject.getString("special");
                                        String discount = jsonObject.getString("discount");

                                        SectionArea sectionArea = new SectionArea(product_id, sec_category_id, name, image, price, special, discount);
                                        sectionAreas.add(sectionArea);

                                    }

                                    JSONArray bannerAreaArray = wrapperObj.getJSONArray("bannerarea");
                                    for (int k = 0; k < bannerAreaArray.length(); k++) {


                                        JSONObject jsonObject = bannerAreaArray.getJSONObject(k);
                                        String image = jsonObject.getString("image");
                                        String tag = jsonObject.getString("tag");
                                        String id = jsonObject.getString("id");

                                        BannerArea bannerArea = new BannerArea(image, tag, id);
                                        bannerAreas.add(bannerArea);
                                    }

                                    Wrapper wrapper = new Wrapper(section_image, category_id, category_name, banner_title,
                                            sectionAreas, bannerAreas);
                                    wrappers.add(wrapper);

                                }

                            }
                            pager.setAdapter(new AdvAdapter(context, advBannerses));
                            if (iconses.size() == 6) {
                                see_more.setVisibility(View.GONE);
                            } else {
                                see_more.setVisibility(View.VISIBLE);
                                see_more.setText(String.format(getString(R.string.more), (iconses.size() - 6)));
                            }

                            homeGridAdapter = new HomeGridAdapter(context, iconses, isButtonClicked);
                            cat_grid.setAdapter(homeGridAdapter);
                            cat_grid.setExpanded(true);

                            list.setAdapter(new HomePageAdapter(context, wrappers));


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("limit", limit);
                params.put("start", start);
                Log.e(TAG, "Homepage : Param===>" + params);
                return params;
            }
        };
        chk_Req.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError error) throws VolleyError {
            }

            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }
        });
        chk_Req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(chk_Req, strTAG);
    }
}
