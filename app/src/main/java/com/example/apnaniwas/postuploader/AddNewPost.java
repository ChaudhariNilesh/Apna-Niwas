package com.example.apnaniwas.postuploader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnaniwas.BottomNavigationBar;
import com.example.apnaniwas.R;
import com.example.apnaniwas.apnaniwasDB.connection.APIService;
import com.example.apnaniwas.apnaniwasDB.connection.RestClient;
import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.ui.home.HomeFragment;
import com.example.apnaniwas.ui.home.homepost.HomePostModel;
import com.example.apnaniwas.util.FileUtils;
import com.example.apnaniwas.util.SharedPreference;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import me.relex.circleindicator.CircleIndicator;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddNewPost extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 1;
    private final int REQUEST_CODE_PERMISSIONS  = 1;
    private final int REQUEST_CODE_READ_STORAGE = 2;
    private static APIService apiService = RestClient.createService(APIService.class);
    List<MultipartBody.Part> parts = new ArrayList<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    EditText tv_postTitle, tv_postDescription;
    Button addpostbtn;
    TextView addMoreImages;
    ImageView addImages;
    MemberModel user;
    private ArrayList<Uri> arrayList;
    private ProgressBar mProgressBar;
    private static ViewPager mPager;
    private RelativeLayout relLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_postTitle = findViewById(R.id.postTitleAddPost);
        tv_postDescription = findViewById(R.id.postDescriptionAddPost);
        addpostbtn = findViewById(R.id.addpostActivitybtn);
        mProgressBar = findViewById(R.id.progressBar);
        addImages =  findViewById(R.id.addPostImg);
        addMoreImages =  findViewById(R.id.tvAddPostImages);
        mPager = findViewById(R.id.view_pager);
        relLay = findViewById(R.id.relAddPostLay);

        addpostbtn.setOnClickListener(this);
        addMoreImages.setOnClickListener(this);
        addImages.setOnClickListener(this);
        user = SharedPreference.getInstance(this).getMember();
        arrayList = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addpostActivitybtn) {
            ArrayList<HomePostModel> newPost = HomeFragment.posts;
            String postTitle = tv_postTitle.getText().toString();
            String postDescription = tv_postDescription.getText().toString();
            String member_id = user.getMemberId();
            Usrupload(postTitle,postDescription,member_id);
            newPost.add(new HomePostModel(R.drawable.gradient_background, postTitle, postDescription));
           // finish();
        } else if (v.getId() == R.id.tvAddPostImages) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                askForPermission();
            }
            else {
                pickFromGallery();
            }

        }
        else if (v.getId() == R.id.addPostImg){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                askForPermission();
            }
            else {
                pickFromGallery();
            }

        }
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_REQUEST_CODE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
            if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    relLay.setVisibility(View.VISIBLE);
                    addImages.setVisibility(View.GONE);
                    CircleIndicator circleIndicator;
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        int currentItem = 0;
                        while (currentItem < count) {
                            Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                            currentItem = currentItem + 1;

                            Log.d("Uri Selected", imageUri.toString());

                            try {
                                arrayList.add(imageUri);
                                AddNewPostAdapter mAdaperPager = new AddNewPostAdapter(AddNewPost.this,arrayList);
                                mPager.setAdapter(mAdaperPager);
                                circleIndicator = findViewById(R.id.circleIndicator);
                                circleIndicator.setViewPager(mPager);

                            } catch (Exception e) {
                                Log.e("EXCEPTION :::", "File select error", e);
                            }
                        }
                    } else if (data.getData() != null) {

                        final Uri uri = data.getData();
                        Log.d("TAG :::>>", "Uri = " + uri.toString());
                        try {
                            arrayList.add(uri);
                            AddNewPostAdapter mAdaperPager = new AddNewPostAdapter(AddNewPost.this,arrayList);
                            mPager.setAdapter(mAdaperPager);
                            circleIndicator = findViewById(R.id.circleIndicator);
                            circleIndicator.setViewPager(mPager);

                        } catch (Exception e) {
                            Log.e("EXCEPTION :::", "File select error", e);
                        }
                    }
                }
                else{
                    relLay.setVisibility(View.GONE);
                    addImages.setVisibility(View.VISIBLE);
                }

            }
                //data.getData returns the content URI for the selected Image
              //  Uri selectedImage = data.getData();
               // addImage.setImageURI();
    }



        private void Usrupload(String pstTitle, String pstDesc, String memId) {

        List<MultipartBody.Part> parts = new ArrayList<>();

        if (arrayList != null) {
            // create part for file (photo, video, ...)
            for (int i = 0; i < arrayList.size(); i++) {
                parts.add(prepareFilePart("image"+i, arrayList.get(i)));
                Log.e("RESULT :: >> ", String.valueOf(parts.get(i)));

            }
        }
        RequestBody size = createPartFromString(""+parts.size());
        RequestBody TAG = createPartFromString("upload_post");
        RequestBody Title = createPartFromString(pstTitle);
        RequestBody Desc = createPartFromString(pstDesc);
        RequestBody Id = createPartFromString(memId);

        showProgress();
        disposable.add(
                apiService.uploadFile(TAG,Title,Desc,Id,size,parts)    //apiService.uploadFile(TAG, Title, Desc,Id,parts)
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleSuccess, this::handleError));
    }

    private RequestBody createPartFromString(String s) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), s);

    }

    private void handleSuccess(CommonResponse commonResponse) {
        hideProgress();
        if(commonResponse.getStatus() == 200)
        {
            hideProgress();
            Toast.makeText(this,"Uploaded",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BottomNavigationBar.class));

        }
        else
        {
            Toast.makeText(this,"Failed to upload try agian.",Toast.LENGTH_SHORT).show();
        }

    }

    private void handleError(Throwable error) {

        hideProgress();
        Log.e ("FETCH ERROR  ", "onError: " + error.getMessage());

    }
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create (MediaType.parse(FileUtils.MIME_TYPE_IMAGE), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    private void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        addMoreImages.setClickable(false);
        addpostbtn.setVisibility(View.GONE);
    }

    private void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        addMoreImages.setClickable(true);
        addpostbtn.setVisibility(View.VISIBLE);
    }

    private void askForPermission() {
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) +
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                != PackageManager.PERMISSION_GRANTED) {
            /* Ask for permission */
            // need to request permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Snackbar.make(this.findViewById(android.R.id.content),
                        "Please grant permissions to write data in sdcard",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        v -> ActivityCompat.requestPermissions(AddNewPost.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE_PERMISSIONS)).show();
            } else {
                /* Request for permission */
                ActivityCompat.requestPermissions(AddNewPost.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSIONS);
            }

        } else {
            pickFromGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                pickFromGallery();
            } else {
                // Permission Denied
                Toast.makeText(AddNewPost.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewPost.this);
        final AlertDialog dialog = builder.setMessage("You need to grant access to Read External Storage")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(arg0 -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(AddNewPost.this, android.R.color.holo_blue_light));
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(AddNewPost.this, android.R.color.holo_red_light));
        });

        dialog.show();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
