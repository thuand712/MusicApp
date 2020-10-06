package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.DanhSachAlbumAdapter;
import ducthuan.com.appnhac.Model.Album;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {

    Toolbar toolbarDanhSachAlbum;
    RecyclerView rvDanhSachAlbum;
    ArrayList<Album>albums;
    DanhSachAlbumAdapter danhSachAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        Init();
        getDataDanhSachAlbum();
    }

    private void getDataDanhSachAlbum() {
        DataService dataService = APIService.getService();
        Call<List<Album>>callback = dataService.getDanhSachAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums = (ArrayList<Album>) response.body();
                danhSachAlbumAdapter = new DanhSachAlbumAdapter(DanhSachAlbumActivity.this, albums);
                rvDanhSachAlbum.setHasFixedSize(true);
                rvDanhSachAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this,2));
                rvDanhSachAlbum.setAdapter(danhSachAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        toolbarDanhSachAlbum = findViewById(R.id.toolbarDanhSachAlbum);
        rvDanhSachAlbum = findViewById(R.id.rvDanhSachAlbum);
        setSupportActionBar(toolbarDanhSachAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDanhSachAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
