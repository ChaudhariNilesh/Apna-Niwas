package com.example.apnaniwas.ui.home;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.apnaniwas.apnaniwasDB.connection.VariableBag;
import com.example.apnaniwas.chat.ActivityChatModule;
import com.example.apnaniwas.notification.NotificationBoard;
import com.example.apnaniwas.R;
import com.example.apnaniwas.ui.home.homepost.HomePostAdapter;
import com.example.apnaniwas.ui.home.homepost.HomePostModel;
import com.example.apnaniwas.postuploader.AddNewPost;
import com.example.apnaniwas.ui.yourpost.YourPostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeFragment extends Fragment implements HomePostAdapter.OnPostItemListener {
    private static int gCount=0;
    private NavController navController;
    private RecyclerView rv;
    HomePostAdapter padapter;
    public static ArrayList<HomePostModel> posts=new ArrayList<HomePostModel>();
    private static TextView textNoticeItemCount;
    private static final String IMAGES_URL = VariableBag.BASE_URL+"/fetchPost.php";
    private static final String JSON_ARRAY = "response";
    private static final String IMAGE_URL = "img_url";
    private String postJSON;
    private JSONArray reponseArray;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    private void getAllPost() {
        class GetAllImages extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // loading = ProgressDialog.show(getContext(), "Fetching Data","Please Wait...",true,true);
            }
            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                //loading.dismiss();
                postJSON = response;
                JSONObject postjsonObject = null;
                try {
                    postjsonObject = new JSONObject(postJSON);
                    reponseArray = postjsonObject.getJSONArray(JSON_ARRAY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    extractJSON(reponseArray);
                  //  Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }
        }
        GetAllImages gai = new GetAllImages();
        gai.execute(IMAGES_URL);
    }

    private void extractJSON(JSONArray reponseArray){
        try {
            for(int i = 0; i< reponseArray.length(); i++) {
                JSONObject jsonObject = reponseArray.getJSONObject(i);
                CustomTarget<Bitmap> img= Glide.with(getContext())
                        .asBitmap()
                        .timeout(30000)
                        .load(jsonObject.getString(IMAGE_URL))
                        .into(new CustomTarget<Bitmap>(500,500) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                // imageView.setImageBitmap(resource);
                            }
                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });
                posts.add(new HomePostModel(R.drawable.gradient_background,img,jsonObject.getString(IMAGE_URL),jsonObject.getString("member_id"),jsonObject.getString("post_id"),jsonObject.getString("post_title"), jsonObject.getString("post_desc")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rv = root.findViewById(R.id.homerecylerview);

        getAllPost();

        FloatingActionButton mFloatingActionButton = (FloatingActionButton) root.findViewById(R.id.fabAddPost);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
      //  posts.add(new HomePostModel(R.drawable.gradient_background,"Increasing Gym Timing","So in today's meeting, we all decided to increase our Gym's timing watching everyone's concern about it"));
       //posts.add(new HomePostModel(R.drawable.gradient_background, "Maha ShivRatri's Antakshri Competition Results","The #1 Prize goes to Nikul Prajapati "));
        padapter = new HomePostAdapter(posts, getActivity(), this);
        rv.setAdapter(padapter);
        padapter.notifyDataSetChanged();

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

       rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        FloatingActionButton fab = view.findViewById(R.id.fabAddPost);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), ActivityChatModule.class);
            startActivity(i);
        });
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.notification_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.notice_board);
        View actionView = menuItem.getActionView();
        textNoticeItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notice_board) {

            Intent intent = new Intent(getContext(), NotificationBoard.class);
            startActivity(intent);
           // textNoticeItemCount.setText("0");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setupBadge(int count) {
        gCount = count;
        if (textNoticeItemCount != null) {
            if (count == 0) {
                    if (textNoticeItemCount.getVisibility() != View.GONE) {
                        textNoticeItemCount.setVisibility(View.GONE);
                    }
            } else {
                textNoticeItemCount.setText(String.valueOf(Math.min(count, 99)));
                if (textNoticeItemCount.getVisibility() != View.VISIBLE) {
                    textNoticeItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    @Override
    public void onItemClicked(int position) {
        HomeFragmentDirections.ActionNavigationHomeToPostFragment action = HomeFragmentDirections.actionNavigationHomeToPostFragment();
        action.setPosition(position);
        navController.navigate(action);
    }
    @Override
    public void onStart() {
        super.onStart();
        padapter.notifyDataSetChanged();
    }
}