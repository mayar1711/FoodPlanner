package com.example.foodplanner.ui.onbording;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int[] animations = {R.raw.cook , R.raw.dounat};
    int[] headerText = {R.string.headerOne_onboarding , R.string.headerTwo_onboarding};
    int[] descriptionText = {R.string.descriptionOne_onboarding , R.string.descriptionTwo_onboarding};
    public ViewPagerAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return animations.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onbording_layout,container,false);
        LottieAnimationView slideLotti = view.findViewById(R.id.splash_lotti);
        TextView header = view.findViewById(R.id.tv_header);
        TextView description = view.findViewById(R.id.tv_description);
        slideLotti.setAnimation(animations[position]);
        header.setText(headerText[position]);
        description.setText(descriptionText[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
