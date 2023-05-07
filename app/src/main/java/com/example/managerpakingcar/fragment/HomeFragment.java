package com.example.managerpakingcar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.managerpakingcar.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//      Your code
        initSetupSlideImage();
//      End code;

        return view;
    }
    private void initSetupSlideImage() {
        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);
    }

}