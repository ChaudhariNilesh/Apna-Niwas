package com.example.apnaniwas.ui.yourpost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.apnaniwasDB.model.loginresponse.LoginResponse;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class DetailActivity extends AppCompatActivity {

    private static APIService apiService = RestClient.createService(APIService.class);
    private CompositeDisposable disposable = new CompositeDisposable();
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public ArrayList<YourPostViewModel> data = new ArrayList<>();
    int pos;

    Toolbar toolbar;


    private ViewPager mViewPager;
    private String mPostId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);

        data = getIntent().getParcelableArrayListExtra("data");
        pos = getIntent().getIntExtra("pos", 0);
        mPostId = data.get(pos).getPostId();

        setTitle(data.get(pos).getImgTitle());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), data);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(pos);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                setTitle(data.get(position).getImgTitle());
                mPostId = data.get(position).getPostId();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_post) {
            deleteYourPost(mPostId);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteYourPost(String post_id) {
            disposable.add(
                    apiService.deletePost("delete_post",post_id)
                            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(CommonResponse commonResponse) {
        if (commonResponse.getStatus() == 200) {
            Toast.makeText(this,commonResponse.getMesssage(),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Failed to delete post try again.",Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable throwable) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public ArrayList<YourPostViewModel> data = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<YourPostViewModel> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position, data.get(position).getImgTitle(), data.get(position).getImgUrl());
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position).getImgTitle();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        String name, url;
        int pos;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_IMG_TITLE = "image_title";
        private static final String ARG_IMG_URL = "image_url";

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            this.pos = args.getInt(ARG_SECTION_NUMBER);
            this.name = args.getString(ARG_IMG_TITLE);
            this.url = args.getString(ARG_IMG_URL);
        }

        public static PlaceholderFragment newInstance(int sectionNumber, String name, String url) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_IMG_TITLE, name);
            args.putString(ARG_IMG_URL, url);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onStart() {
            super.onStart();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            final ImageView imageView = (ImageView) rootView.findViewById(R.id.detail_image);

            Glide.with(getActivity()).load(url).thumbnail(0.1f).into(imageView);

            return rootView;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
