package ducthuan.com.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.R;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(Context context, int resource, List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtTenPlayList;
        ImageView imgBgPlayList, imgPlayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlayList = convertView.findViewById(R.id.txtTenPlayList);
            viewHolder.imgBgPlayList = convertView.findViewById(R.id.imgBgPlayList);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imgPlayList);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhnen()).into(viewHolder.imgBgPlayList);
        Picasso.with(getContext()).load(playList.getHinhicon()).into(viewHolder.imgPlayList);
        viewHolder.txtTenPlayList.setText(playList.getTen());

        return convertView;
    }
}
