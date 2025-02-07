package ducthuan.com.appnhac.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import ducthuan.com.appnhac.R;

public class DiaNhacFragment extends Fragment {

    View view;
    CircleImageView imgCircleDiaNhac;
    ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dianhac, container,false);

        imgCircleDiaNhac = view.findViewById(R.id.imgCircleDiaNhac);

        objectAnimator = ObjectAnimator.ofFloat(imgCircleDiaNhac,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        return view;
    }

    public void PhatNhac(String hinhanh){
        Picasso.with(getActivity()).load(hinhanh).into(imgCircleDiaNhac);
    }
}
