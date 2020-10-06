package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import ducthuan.com.appnhac.Model.ChuDe;
import ducthuan.com.appnhac.Model.TheLoai;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    Toolbar toolbarTheLoaiTheoChuDe;
    RecyclerView rvTheLoaiTheoChuDe;
    ChuDe chuDe;
    ArrayList<TheLoai>theLoais;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);

        GetIntent();
        Init();
        getDataTheLoaiTheoChuDe();


    }

    private void Init() {
        toolbarTheLoaiTheoChuDe = findViewById(R.id.toolbarTheLoaiTheoChuDe);
        setSupportActionBar(toolbarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenchude());
        toolbarTheLoaiTheoChuDe.setTitleTextColor(getResources().getColor(R.color.colorPurple));

        toolbarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rvTheLoaiTheoChuDe = findViewById(R.id.rvTheLoaiTheoChuDe);
    }

    private void getDataTheLoaiTheoChuDe() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>>callback = dataService.getTheLoaisTheoChuDe(chuDe.getIdchude());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                theLoais = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this, theLoais);
                rvTheLoaiTheoChuDe.setHasFixedSize(true);
                rvTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,2));
                rvTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("itemchude")){
            chuDe = (ChuDe) intent.getSerializableExtra("itemchude");
        }
    }
}
