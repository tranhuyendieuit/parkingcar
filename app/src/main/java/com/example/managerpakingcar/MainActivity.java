package com.example.managerpakingcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.managerpakingcar.fragment.HomeFragment;
import com.example.managerpakingcar.fragment.ProfileFragment;
import com.example.managerpakingcar.fragment.ScanrFragment;
import com.example.managerpakingcar.model.request.SignInRequestModel;


public class  MainActivity extends AppCompatActivity {
//    private ZXingScannerView mScannerView;
    private MeowBottomNavigation bottomNavigation;
    private Fragment fragment = null;

    private SignInRequestModel signInRequestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mScannerView = findViewById(R.id.scannerView);
//        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        mScannerView.startCamera();

//           Convert page QRScanner into ScanrFragment

        Intent intent = getIntent();
        if(intent.getExtras() != null){
             intent.getExtras().toString();

             String token = intent.getStringExtra("data");
             Log.d("AAA", "====> "+ token);
           // signInRequestModel = (SignInRequestModel) intent.getSerializableExtra("data");

          //  Log.e("TAG", "====> "+ signInRequestModel.getUserName());
        }
         initView();
    }

    private void initView() {
        initSetupBottomNav();
    }
    private void initSetupBottomNav(){
        bottomNavigation = findViewById(R.id.bottomNavigation);
        // init BottomNavigation

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_qr_code));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new ScanrFragment();
                        break;
                    case 3 :
                        fragment = new ProfileFragment();
                        break;
                }
                replaceFragment(fragment);
            }
        });

        // set home fragment initially selected;
        setInitFragment();
        bottomNavigation.show(1,true);
    }

    private void setInitFragment() {
        fragment = new HomeFragment();
        replaceFragment(fragment);
    }


    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        mScannerView.startCamera();          // Start camera on resume
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();           // Stop camera on pause
//    }
//
//    @Override
//    public void handleResult(Result result) {
//
//        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
//        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
//    }

}
