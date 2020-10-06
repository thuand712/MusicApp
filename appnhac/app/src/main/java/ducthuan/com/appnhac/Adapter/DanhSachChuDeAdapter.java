package ducthuan.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ducthuan.com.appnhac.Activity.DanhSachTheLoaiTheoChuDeActivity;
import ducthuan.com.appnhac.Model.ChuDe;
import ducthuan.com.appnhac.R;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe>chuDes;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDes) {
        this.context = context;
        this.chuDes = chuDes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_chude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDes.get(position);
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.imgAllChuDe);
    }

    @Override
    public int getItemCount() {
        return chuDes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAllChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllChuDe = itemView.findViewById(R.id.imgAllChuDe);
            imgAllChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("itemchude", chuDes.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
