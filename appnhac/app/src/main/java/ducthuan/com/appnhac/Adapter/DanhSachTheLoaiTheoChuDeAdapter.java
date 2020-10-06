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
import ducthuan.com.appnhac.Model.TheLoai;
import ducthuan.com.appnhac.R;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai>theLoais;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoais) {
        this.context = context;
        this.theLoais = theLoais;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoais.get(position);
        holder.txtTheLoaiTheoChuDe.setText(theLoai.getTentheloai());
        Picasso.with(context).load(theLoai.getHinhtheloai()).into(holder.imgTheLoaiTheoChuDe);
    }

    @Override
    public int getItemCount() {
        return theLoais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTheLoaiTheoChuDe;
        TextView txtTheLoaiTheoChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoaiTheoChuDe = itemView.findViewById(R.id.txtTheLoaiTheoChuDe);
            imgTheLoaiTheoChuDe = itemView.findViewById(R.id.imgTheLoaiTheoChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemtheloai",theLoais.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
