package ducthuan.com.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ducthuan.com.appnhac.Model.BaiHat;
import ducthuan.com.appnhac.R;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat>baiHats;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_baihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHats.get(position);
        holder.txtPlayNhacTenBaiHat.setText(baiHat.getTenbaihat());
        holder.txtPlayNhacTenCaSi.setText(baiHat.getCasi());
        holder.txtPlayNhacIndex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPlayNhacIndex, txtPlayNhacTenBaiHat, txtPlayNhacTenCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlayNhacIndex = itemView.findViewById(R.id.txtPlayNhacIndex);
            txtPlayNhacTenBaiHat = itemView.findViewById(R.id.txtPlayNhacTenBaiHat);
            txtPlayNhacTenCaSi = itemView.findViewById(R.id.txtPlayNhacTenCaSi);
        }
    }
}
