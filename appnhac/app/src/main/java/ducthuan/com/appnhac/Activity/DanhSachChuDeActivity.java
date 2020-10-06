package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.DanhSachChuDeAdapter;
import ducthuan.com.appnhac.Model.ChuDe;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {

    RecyclerView rvAllChuDe;
    Toolbar toolbarAllChuDe;
    ArrayList<ChuDe>chuDes;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);

        AnhXa();
        Init();
        getDataChuDe();

    }

    private void getDataChuDe() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>>callback = dataService.getDataChuDes();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDes = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this, chuDes);
                rvAllChuDe.setLayoutManager(new LinearLayoutManager(DanhSachChuDeActivity.this, RecyclerView.VERTICAL, false));
                rvAllChuDe.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbarAllChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAllChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        rvAllChuDe = findViewById(R.id.rvAllChuDe);
        toolbarAllChuDe = findViewById(R.id.toolbarAllChuDe);
    }
}
