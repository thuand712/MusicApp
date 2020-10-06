package ducthuan.com.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Adapter.SearchBaiHatAdapter;
import ducthuan.com.appnhac.Model.BaiHat;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemFragment extends Fragment {
    View view;
    Toolbar toolbarSearch;
    RecyclerView rvSearch;
    TextView txtKhongCoDuLieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    ArrayList<BaiHat>baiHats;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem,container,false);

        toolbarSearch = view.findViewById(R.id.toolbarSearch);
        rvSearch = view.findViewById(R.id.rvSearch);
        txtKhongCoDuLieu = view.findViewById(R.id.txtKhongCoDuLieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearch);
        toolbarSearch.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String tukhoa){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>callback = dataService.getSearchBaiHat(tukhoa);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();
                if(baiHats.size()>0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),baiHats);
                    rvSearch.setHasFixedSize(true);
                    rvSearch.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
                    rvSearch.setAdapter(searchBaiHatAdapter);
                    txtKhongCoDuLieu.setVisibility(View.GONE);
                    rvSearch.setVisibility(View.VISIBLE);
                }else {
                    rvSearch.setVisibility(View.GONE);
                    txtKhongCoDuLieu.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
