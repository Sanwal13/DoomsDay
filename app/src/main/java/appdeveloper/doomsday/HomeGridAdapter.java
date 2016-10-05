package appdeveloper.doomsday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sanwal Singh on 1/6/16.
 */
public class HomeGridAdapter extends BaseAdapter {

    boolean isButtonClicked;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Icons> list;

    public HomeGridAdapter(Context context, List<Icons> list, boolean isButtonClicked) {
        this.mContext = context;
        this.list = list;
        this.isButtonClicked = isButtonClicked;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (list.size() > 6) {
            if (isButtonClicked) {
                return list.size();
            } else {
                return 6;
            }
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int location) {
        return list.get(location);
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
            convertView = inflater.inflate(R.layout.custom_homegrid, parent, false);
            viewHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_cat_name);
            viewHolder.imge = (ImageView) convertView.findViewById(R.id.img_grid);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_name.setText(list.get(position).getCategory_name());
        String imgUrl = list.get(position).getIcon_image();
        if (!imgUrl.equalsIgnoreCase(""))
            Picasso.with(mContext).load(imgUrl)
                    .error(R.drawable.ic_img_placehoder).fit().centerInside()
                    .into(viewHolder.imge);
        return convertView;
    }

    class ViewHolder {
        TextView txt_name;
        ImageView imge;
    }
}
