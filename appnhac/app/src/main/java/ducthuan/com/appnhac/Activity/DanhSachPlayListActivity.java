package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.DanhSachPlayListAdapter;
import ducthuan.com.appnhac.Adapter.PlayListAdapter;
import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rvDSPlaylist;
    ArrayList<PlayList>playLists;
    DanhSachPlayListAdapter danhSachPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);

        AnhXa();
        Init();
        getDataPlayLists();

    }

    private void getDataPlayLists() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>>callback = dataService.getDataPlayLists();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                playLists = (ArrayList<PlayList>) response.body();
                danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhSachPlayListActivity.this, playLists);
                rvDSPlaylist.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this,2));
                rvDSPlaylist.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayLists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPurple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarDSPlayList);
        rvDSPlaylist = findViewById(R.id.rvDSPlayList);
    }
}
