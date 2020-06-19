package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class PlaceholderFragment extends Fragment {

    private AnimatorSet animatorSet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    public class ListViewBaseAdapter extends BaseAdapter {

        private int[] args =
                new int[] {1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10,
                        11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7,
                        8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13};

        private Context mContext;

        public ListViewBaseAdapter(Context context) {
            mContext = context;
        }

        @Override public int getCount() {
            return args.length;
        }

        @Override public Object getItem(int i) {
            return null;
        }

        @Override public long getItemId(int i) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
            } else {
                textView = (TextView) convertView;
            }

            textView.setText(" " + args[position]);
            textView.setTextSize(18);
            return textView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listView=getView().findViewById(R.id.list_view);
        listView.setAdapter(new ListViewBaseAdapter(getActivity()));
        listView.setAlpha(0);
        final LottieAnimationView animationView2=getView().findViewById(R.id.animation_view2);
        animationView2.playAnimation();

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animatorLottie=ObjectAnimator.ofFloat(animationView2,"alpha",1.0f,0.0f);
                animatorLottie.setDuration(1000);
                ObjectAnimator animatorListView=ObjectAnimator.ofFloat(listView,"alpha",0.0f,1.0f);
                animatorListView.setDuration(1000);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorListView,animatorLottie);
                animatorSet.start();
            }
        }, 5000);
    }
}
