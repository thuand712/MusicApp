package ducthuan.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ducthuan.com.appnhac.Activity.DanhSachBaiHatActivity;
import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.R;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList>playLists;

    public DanhSachPlayListAdapter(Context context, ArrayList<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        holder.txtDanhSachPlayList.setText(playList.getTen());
        Picasso.with(context).load(playList.getHinhicon()).into(holder.imgDanhSachPlayList);
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtDanhSachPlayList, txtCaSiPlayList;
        ImageView imgDanhSachPlayList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCaSiPlayList = itemView.findViewById(R.id.txtCaSiPlayList);
            txtDanhSachPlayList = itemView.findViewById(R.id.txtDanhSachPlayList);
            imgDanhSachPlayList = itemView.findViewById(R.id.imgDSPlayList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", playLists.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
