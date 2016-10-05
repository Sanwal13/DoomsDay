package appdeveloper.doomsday;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Sanwal Singh on 5/10/16.
 */

public class HorizontaListAdapter extends BaseAdapter {
    List<SectionArea> sectionAreas;
    Context context;
    LayoutInflater inflater;

    public HorizontaListAdapter(Context context, List<SectionArea> sectionAreas) {

        this.context = context;
        this.sectionAreas = sectionAreas;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sectionAreas.size();
    }

    @Override
    public Object getItem(int position) {
        return sectionAreas.get(position);
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
            convertView = inflater.inflate(R.layout.custom_horizontal_list, parent, false);

            viewHolder.img_product = (ImageView) convertView.findViewById(R.id.img_product);
            viewHolder.txt_product_name = (TextView) convertView.findViewById(R.id.txt_product_name);
            viewHolder.txt_product_off = (TextView) convertView.findViewById(R.id.txt_product_off);
            viewHolder.txt_original_price = (TextView) convertView.findViewById(R.id.txt_original_price);
            viewHolder.txt_product_price = (TextView) convertView.findViewById(R.id.txt_product_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        String image_url = sectionAreas.get(position).getImage();
        if (!image_url.equalsIgnoreCase(""))
            Picasso.with(context).load(image_url)
                    .error(R.drawable.ic_img_placehoder).fit().centerInside()
                    .into(viewHolder.img_product);

        viewHolder.txt_original_price.setPaintFlags(viewHolder.txt_original_price.getPaintFlags()
                | Paint.STRIKE_THRU_TEXT_FLAG);


        viewHolder.txt_product_off.setText(String.format("%s Off", sectionAreas.get(position).getDiscount()));
        viewHolder.txt_original_price.setText(sectionAreas.get(position).getPrice());
        viewHolder.txt_product_price.setText(sectionAreas.get(position).getSpecial());


        String productName = sectionAreas.get(position).getName();
        if (productName.contains("(")) {
            StringTokenizer tokens = new StringTokenizer(productName, "(");
            String name = tokens.nextToken();
            String finish = tokens.nextToken().replace(")", "");
            viewHolder.txt_product_name.setText(name);

        } else {
            viewHolder.txt_product_name.setText(sectionAreas.get(position).getName());
        }


        return convertView;
    }

    class ViewHolder {
        ImageView img_product;
        TextView txt_product_off, txt_product_name, txt_original_price, txt_product_price;
    }
}