package ducthuan.com.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ducthuan.com.appnhac.Activity.PlayNhacActivity;
import ducthuan.com.appnhac.Adapter.PlayNhacAdapter;
import ducthuan.com.appnhac.R;

public class PlayDanhSachBaiHatFragment extends Fragment {
    View view;
    RecyclerView rvPlayBaiHat;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_baihat, container, false);
        rvPlayBaiHat = view.findViewById(R.id.rvPlayBaiHat);
        if(PlayNhacActivity.baiHats.size() > 0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.baiHats);
            rvPlayBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvPlayBaiHat.setHasFixedSize(true);
            rvPlayBaiHat.setAdapter(playNhacAdapter);
        }

        return view;
    }
}
