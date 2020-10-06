package ducthuan.com.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ducthuan.com.appnhac.Activity.DanhSachBaiHatActivity;
import ducthuan.com.appnhac.Activity.DanhSachChuDeActivity;
import ducthuan.com.appnhac.Activity.DanhSachTheLoaiTheoChuDeActivity;
import ducthuan.com.appnhac.Model.ChuDe;
import ducthuan.com.appnhac.Model.TheLoai;
import ducthuan.com.appnhac.Model.TheLoaiTrongNgay;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuDe_TheLoai_Today_Fragment extends Fragment {
    View view;
    HorizontalScrollView hsvChuDe;
    TextView txtXemThemChuDe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, null);

        hsvChuDe = view.findViewById(R.id.hsvChuDe);
        txtXemThemChuDe = view.findViewById(R.id.txtXemThemChuDe);
        getData();

        txtXemThemChuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DanhSachChuDeActivity.class));
            }
        });

        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataService.getDataChuDeTheLoaiToDay();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();

                final ArrayList<ChuDe>chuDes = new ArrayList<>();
                chuDes.addAll(theLoaiTrongNgay.getChuDe());
                final ArrayList<TheLoai>theLoais = new ArrayList<>();
                theLoais.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout  = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for(int i = 0; i < chuDes.size();i++){
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDes.get(i).getHinhchude()!=null)
                    {
                        Picasso.with(getActivity()).load(chuDes.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("itemchude", chuDes.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j = 0; j < theLoais.size();j++){
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoais.get(j).getHinhtheloai()!=null)
                    {
                        Picasso.with(getActivity()).load(theLoais.get(j).getHinhtheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("itemtheloai",theLoais.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                hsvChuDe.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
