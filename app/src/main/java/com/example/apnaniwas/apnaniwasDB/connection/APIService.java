package com.example.apnaniwas.apnaniwasDB.connection;

import com.example.apnaniwas.apnaniwasDB.model.CommonResponse;
import com.example.apnaniwas.apnaniwasDB.model.forgotpasswordresponse.ForgotPasswordResponse;
import com.example.apnaniwas.apnaniwasDB.model.loginresponse.LoginResponse;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.MemberModel;
import com.example.apnaniwas.apnaniwasDB.model.signupresponse.SignResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIService {

    @FormUrlEncoded
    @POST("RegisterAPI.php")
    Observable<SignResponse> addMember(@Field("tag") String tag,
                                       @Field("member_name") String member_name,
                                       @Field("phone_no") String phnNo,
                                       @Field("email_id") String email_id,
                                       @Field("member_password") String password);
    @FormUrlEncoded
    @POST("FetchMobileAPI.php")
    Observable<CommonResponse> fetchMobile(@Field("tag") String tag,
                                       @Field("phone_no") String phone_no);


    @FormUrlEncoded
    @POST("LoginAPI.php")
    Observable<LoginResponse> authLogin(@Field("tag") String tag,
                                        @Field("phone_no") String phone_no,
                                        @Field("member_password") String member_password);

    @FormUrlEncoded
    @POST("ForgotPassword.php")
    Observable<ForgotPasswordResponse> forgotPassword(@Field("tag") String tag,
                                                      @Field("phone_no") String phone_no);
    @FormUrlEncoded
    @POST("ResetPassword.php")
    Observable<CommonResponse> resetPassword(@Field("tag") String tag,
                                             @Field("member_password") String member_password,
                                             @Field("member_id") int member_id);

    @Multipart
    @POST("UploadPost.php")
    Observable<CommonResponse> uploadFile(@Part("tag") RequestBody tag,
                                          @Part List<MultipartBody.Part> image,
                                          @Part("size") RequestBody size);
    @Multipart
    @POST("testUpload.php")
    Observable<CommonResponse> uploadFile(@Part("tag")RequestBody TAG,
                                          @Part("post_title")RequestBody title,
                                          @Part("post_desc")RequestBody desc,
                                          @Part("member_id")RequestBody id,
                                          @Part("imgCnt") RequestBody size,
                                          @Part List<MultipartBody.Part> parts);
}
