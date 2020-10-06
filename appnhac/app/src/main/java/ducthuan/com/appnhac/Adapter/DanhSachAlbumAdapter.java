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

public class DanhSachAlbumAdapter extends RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album>albums;

    public DanhSachAlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.txtTenDanhSachAlbum.setText(album.getTen());
        holder.txtTenCaSiDanhSachAlbum.setText(album.getTencasi());
        Picasso.with(context).load(album.getHinh()).into(holder.imgDanhSachAlbum);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDanhSachAlbum;
        TextView txtTenDanhSachAlbum, txtTenCaSiDanhSachAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDanhSachAlbum = itemView.findViewById(R.id.imgDanhSachAlbum);
            txtTenDanhSachAlbum = itemView.findViewById(R.id.txtTenDanhSachAlbum);
            txtTenCaSiDanhSachAlbum = itemView.findViewById(R.id.txtTenCaSiDanhSachAlbumt);

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
