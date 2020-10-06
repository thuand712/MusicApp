package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import ducthuan.com.appnhac.Adapter.MainViewPagerAdapter;
import ducthuan.com.appnhac.Fragment.TimKiemFragment;
import ducthuan.com.appnhac.Fragment.TrangChuFragment;
import ducthuan.com.appnhac.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    SwipeDisabledViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        //khoi tao
        Init();
    }

    private void Init() {
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new TrangChuFragment(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new TimKiemFragment(), "Tìm kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
