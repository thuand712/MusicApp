package ducthuan.com.appnhac.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.QuangCaoAdapter;
import ducthuan.com.appnhac.Model.QuangCao;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIRetrofitClient;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuangCaoFragment extends Fragment {

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    QuangCaoAdapter quangCaoAdapter;

    //sau 1 khoang thoi gian thuc hien
    Runnable runnable;
    Handler handler;

    int currentItem;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quangcao, container, false);

        AnhXa();
        getData();
        return view;
    }

    private void AnhXa() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }

    private void getData() {
        DataService dataService = APIService.getService();
        //gui du lieu len
        Call<List<QuangCao>> callback = dataService.GetDataQuangCao();
        //su kien lang nghe du lieu tra ve
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                //lay du lieu ra .body();
                ArrayList<QuangCao>dsquangcao = (ArrayList<QuangCao>) response.body();

                quangCaoAdapter = new QuangCaoAdapter(getActivity(), dsquangcao);
                viewPager.setAdapter(quangCaoAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                //thuc hien hanh dong khi handler goi
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem>=viewPager.getAdapter().getCount()){
                            currentItem=0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);

            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });

    }
}
