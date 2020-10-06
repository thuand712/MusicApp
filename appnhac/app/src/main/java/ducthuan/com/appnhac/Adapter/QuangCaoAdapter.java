package ducthuan.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ducthuan.com.appnhac.Activity.DanhSachBaiHatActivity;
import ducthuan.com.appnhac.Model.QuangCao;
import ducthuan.com.appnhac.R;

public class QuangCaoAdapter extends PagerAdapter {

    Context context;
    ArrayList<QuangCao>dsquangcao;

    public QuangCaoAdapter(Context context, ArrayList<QuangCao> dsquangcao) {
        this.context = context;
        this.dsquangcao = dsquangcao;
    }

    //tra ve so quang cao
    @Override
    public int getCount() {
        return dsquangcao.size();
    }

    //tra ve view dinh hinh object ntn
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //dung de dinh hinh va gan du lieu cho moi object tuong trung cho moi pager
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_quangcao, null);

        ImageView imgBGQuangCao = view.findViewById(R.id.imgBGQuangCao);
        ImageView imgBaiHatQuangCao = view.findViewById(R.id.imgBaiHatQuangCao);
        TextView txtTitleQuangCao = view.findViewById(R.id.txtTitleQuangCaoBaiHat);
        TextView txtNoiDungQuangCao = view.findViewById(R.id.txtNoiDungQuangCao);

        Picasso.with(context).load(dsquangcao.get(position).getHinhanh()).into(imgBGQuangCao);
        Picasso.with(context).load(dsquangcao.get(position).getHinhbaihat()).into(imgBaiHatQuangCao);
        txtTitleQuangCao.setText(dsquangcao.get(position).getTenbaihat());
        txtNoiDungQuangCao.setText(dsquangcao.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",dsquangcao.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }

    //khi den cuoi cung thi remove no di, khong de bi loi
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object );
    }
}
