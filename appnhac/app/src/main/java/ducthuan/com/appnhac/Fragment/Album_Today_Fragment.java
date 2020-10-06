package ducthuan.com.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Activity.DanhSachAlbumActivity;
import ducthuan.com.appnhac.Adapter.AlbumHotAdapter;
import ducthuan.com.appnhac.Model.Album;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Album_Today_Fragment extends Fragment {
    View view;
    RecyclerView rvAlbumHot;
    TextView txtXemThemAlbumHot;
    AlbumHotAdapter albumHotAdapter;
    ArrayList<Album>albums;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_today, null);

        rvAlbumHot = view.findViewById(R.id.rvAlbumHot);
        rvAlbumHot.setHasFixedSize(true);
        txtXemThemAlbumHot = view.findViewById(R.id.txtXemThemAlbumHot);
        getData();

        txtXemThemAlbumHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DanhSachAlbumActivity.class));
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>>callback = dataService.getDataAlbumToday();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums = (ArrayList<Album>) response.body();
                albumHotAdapter = new AlbumHotAdapter(getActivity(),albums);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvAlbumHot.setLayoutManager(layoutManager);
                rvAlbumHot.setAdapter(albumHotAdapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
