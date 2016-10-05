package appdeveloper.doomsday;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class HomePageAdapter extends BaseAdapter {

    String product_id, category_id, name, image, price, special, discount;
    private List<Wrapper> wrappers;
    private Context mContext;
    private LayoutInflater inflater;
    private List<SectionArea> sectionAreas;
    public HomePageAdapter(Context context, List<Wrapper> wrappers) {
        this.mContext = context;
        this.wrappers = wrappers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return wrappers.size();
    }

    @Override
    public Object getItem(int location) {
        return wrappers.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.custom_listrow, parent, false);
            viewHolder.ll_sectn = (LinearLayout) convertView.findViewById(R.id.ll_sectn);
            viewHolder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
            viewHolder.txt_cat_ttl = (TextView) convertView.findViewById(R.id.txt_cat_ttl);
            viewHolder.txt_viewall = (TextView) convertView.findViewById(R.id.txt_viewall);
            viewHolder.item_list = (HorizontalView) convertView.findViewById(R.id.item_list);
            viewHolder.img_bannr1 = (ImageView) convertView.findViewById(R.id.img_bannr1);
            viewHolder.img_bannr2 = (ImageView) convertView.findViewById(R.id.img_bannr2);
            viewHolder.img_bannr3 = (ImageView) convertView.findViewById(R.id.img_bannr3);
            viewHolder.img_cat_bnnr = (ImageView) convertView.findViewById(R.id.img_cat_bnnr);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            String banner_url_1 = wrappers.get(position).getBannerAreas().get(0).getImage();
            String banner_url_2 = wrappers.get(position).getBannerAreas().get(1).getImage();
            String banner_url_3 = wrappers.get(position).getBannerAreas().get(2).getImage();
            String cat_image = wrappers.get(position).getSection_image();

            if (!banner_url_1.equalsIgnoreCase("")) {
                Picasso.with(mContext).load(banner_url_1).placeholder(R.drawable.ic_img_placehoder)
                        .error(R.drawable.feedback_main_smile).fit().centerCrop()
                        .into(viewHolder.img_bannr1);
            } else {
                // viewHolder.img_bannr1.setVisibility(View.GONE);
            }

            if (!banner_url_2.equalsIgnoreCase("")) {
                Picasso.with(mContext).load(banner_url_2).placeholder(R.drawable.ic_img_placehoder)
                        .error(R.drawable.feedback_main_smile).fit().centerCrop()
                        .into(viewHolder.img_bannr2);
            } else {
                //  viewHolder.img_bannr2.setVisibility(View.GONE);
            }


            if (!banner_url_3.equalsIgnoreCase("")) {
                Picasso.with(mContext).load(banner_url_3).placeholder(R.drawable.ic_img_placehoder)
                        .error(R.drawable.feedback_main_smile).fit().centerCrop()
                        .into(viewHolder.img_bannr3);
            } else {
                // viewHolder.img_bannr3.setVisibility(View.GONE);
            }

            if (!cat_image.equalsIgnoreCase(""))
                Picasso.with(mContext).load(cat_image).placeholder(R.drawable.ic_img_placehoder)
                        .error(R.drawable.ic_img_placehoder).fit().centerCrop()
                        .into(viewHolder.img_cat_bnnr);

            String title = wrappers.get(position).getCategory_name();
            viewHolder.txt_cat_ttl.setText(title);


            Log.e(TAG, "Title : " + title);
            Log.e(TAG, "banner url 1 : " + banner_url_1);
            Log.e(TAG, "banner url 2 : " + banner_url_2);
            Log.e(TAG, "banner url 3 : " + banner_url_3);
            Log.e(TAG, "cat image    : " + cat_image);

            sectionAreas = wrappers.get(position).getSectionAreas();

            viewHolder.item_list.setAdapter(new HorizontaListAdapter(mContext, sectionAreas));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    class ViewHolder {
        LinearLayout ll_sectn;
        RelativeLayout rl;
        TextView txt_cat_ttl, txt_viewall;
        HorizontalView item_list;
        ImageView img_bannr1, img_bannr2, img_bannr3, img_cat_bnnr;


    }
}
