package ducthuan.com.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ducthuan.com.appnhac.Activity.DanhSachBaiHatActivity;
import ducthuan.com.appnhac.Activity.DanhSachPlayListActivity;
import ducthuan.com.appnhac.Adapter.PlayListAdapter;
import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.R;
import ducthuan.com.appnhac.Service.APIRetrofitClient;
import ducthuan.com.appnhac.Service.APIService;
import ducthuan.com.appnhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListFragment extends Fragment {
    View view;
    ListView lvPlayList;
    TextView txtTitlePlayList;
    TextView txtMorePlayList;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);

        lvPlayList = view.findViewById(R.id.lvPlayList);
        txtTitlePlayList = view.findViewById(R.id.txtTitlePlayList);
        txtMorePlayList = view.findViewById(R.id.txtMorePlayList);

        getData();

        txtMorePlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DanhSachPlayListActivity.class));
            }
        });

        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.getDataPlayListForToday();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1,mangplaylist);
                lvPlayList.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvPlayList);
                lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(i));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }


    //căn chỉnh lại chiều cao cho listview
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < ((ListAdapter) listAdapter).getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
