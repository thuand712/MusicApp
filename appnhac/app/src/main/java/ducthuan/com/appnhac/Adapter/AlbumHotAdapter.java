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
import ducthuan.com.appnhac.Model.Album;
import ducthuan.com.appnhac.R;

public class AlbumHotAdapter extends RecyclerView.Adapter<AlbumHotAdapter.ViewHolder>{

    Context context;
    ArrayList<Album>albums;

    public AlbumHotAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    //Gan layout vao
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_albumhot, parent, false);
        return new ViewHolder(view);
    }

    //thuc hien gan gia tri
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.txtTenAlbumHot.setText(album.getTen());
        holder.txtTenCaSiAlbumhot.setText(album.getTencasi());
        Picasso.with(context).load(album.getHinh()).into(holder.imgAlbumhot);
    }

    //viet ra bao nhieu item
    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAlbumhot;
        TextView txtTenAlbumHot,txtTenCaSiAlbumhot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumhot = itemView.findViewById(R.id.imgAlbumhot);
            txtTenAlbumHot = itemView.findViewById(R.id.txtTenAlbumHot);
            txtTenCaSiAlbumhot = itemView.findViewById(R.id.txtTenCaSiAlbumhot);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum", albums.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
