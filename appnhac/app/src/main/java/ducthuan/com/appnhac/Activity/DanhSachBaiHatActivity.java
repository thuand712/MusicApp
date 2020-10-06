package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.DanhSachBaiHatAdapter;
import ducthuan.com.appnhac.Model.Album;
import ducthuan.com.appnhac.Model.BaiHat;
import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.Model.QuangCao;
import ducthuan.com.appnhac.Model.TheLoai;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity
{

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView rvDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    QuangCao quangCao;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    ImageView imgDanhSachBaiHat;
    ArrayList<BaiHat>baiHats;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        AnhXa();
        Init();
        if(quangCao!=null && !quangCao.getTenbaihat().equals("")){
            setValueInView(quangCao.getTenbaihat(),quangCao.getHinhbaihat());
            getDataQuangCao(quangCao.getId());

        }
        if(playList!=null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(),playList.getHinhicon());
            getDataPlayList(playList.getId());
        }
        if(theLoai!=null && !theLoai.getTentheloai().equals(""))
        {
            setValueInView(theLoai.getTentheloai(),theLoai.getHinhtheloai());
            getDataTheLoai(theLoai.getIdtheloai());
        }
        if(album!=null && !album.getTen().equals("")){
            setValueInView(album.getTen(),album.getHinh());
            getDataAlbum(album.getId());
        }

    }

    private void getDataAlbum(String id) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>callback = dataService.getDanhSachBaiHatTheoAlbum(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baiHats);
                LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvDanhSachBaiHat.setHasFixedSize(true);
                rvDanhSachBaiHat.setLayoutManager(layoutManager);
                rvDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idtheloai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>callback = dataService.getDanhSachBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baiHats);
                LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvDanhSachBaiHat.setHasFixedSize(true);
                rvDanhSachBaiHat.setLayoutManager(layoutManager);
                rvDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>callback = dataService.getDanhSachBaiHatTheoPlayList(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baiHats);
                LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvDanhSachBaiHat.setHasFixedSize(true);
                rvDanhSachBaiHat.setLayoutManager(layoutManager);
                rvDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void getDataQuangCao(String idquangcao)
    {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>callback = dataService.getDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baiHats);
                LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvDanhSachBaiHat.setHasFixedSize(true);
                rvDanhSachBaiHat.setLayoutManager(layoutManager);
                rvDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh)
    {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinh).into(imgDanhSachBaiHat);
    }

    private void Init()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //sau khi co du lieu moi bat len true
        floatingActionButton.setEnabled(false);

    }

    private void AnhXa()
    {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbarDanhSachBaiHat);
        rvDanhSachBaiHat = findViewById(R.id.rvDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imgDanhSachBaiHat = findViewById(R.id.imgDanhSachBaiHat);
    }

    private void DataIntent()
    {
        Intent intent = getIntent();
        if(intent!=null) {
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("itemtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("itemtheloai");
            }
            if (intent.hasExtra("itemalbum")){
                album = (Album) intent.getSerializableExtra("itemalbum");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("baihats", baiHats);
                startActivity(intent);
            }
        });
    }
}
