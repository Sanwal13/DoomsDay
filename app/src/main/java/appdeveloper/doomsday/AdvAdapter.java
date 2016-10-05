package appdeveloper.doomsday;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sanwal Singh on 16/4/16.
 */
public class AdvAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<AdvBanners> pagerImages;

    public AdvAdapter(Context context, List<AdvBanners> pagerImages) {
        this.mContext = context;
        this.pagerImages = pagerImages;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pagerImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final ViewHolder viewHolder;

        View itemView = mLayoutInflater.inflate(R.layout.custom_pager, container, false);
        viewHolder = new ViewHolder();
        viewHolder.pgrImage = (ImageView) itemView.findViewById(R.id.custom_adv_img);

        String imageUrls = pagerImages.get(position).getImage();

        if (!imageUrls.equalsIgnoreCase(""))
            Picasso.with(mContext).load(imageUrls)
                    .placeholder(R.drawable.ic_img_placehoder)
                    .error(R.drawable.ic_img_placehoder).resize(500, 317).centerCrop()
                    .into(viewHolder.pgrImage);

        itemView.setTag(viewHolder);
        container.addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


    class ViewHolder {
        ImageView pgrImage;
    }
}
