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
                                       @Field("member_password") String password,
                                       @Field("device_token") String deviceToken);
    @FormUrlEncoded
    @POST("familyMemDetails.php")
    Observable<CommonResponse> addFamliyMem(@Field("tag") String tag,
                                       @Field("mem_cnt") String memCnt,
                                       @Field("mem_name1") String mem_name1,
                                       @Field("mem_name2") String mem_name2,
                                       @Field("mem_name3") String mem_name3,
                                       @Field("mem_name4") String mem_name4,
                                       @Field("mem_name5") String mem_name5,
                                       @Field("mem_name6") String mem_name6,
                                            @Field("relation1") String relation1,
                                        @Field("relation2") String relation2,
                                        @Field("relation3") String relation3,
                                        @Field("relation4") String relation4,
                                        @Field("relation5") String relation5,
                                        @Field("relation6") String relation6,
                                       @Field("address") String address,
                                       @Field("member_id") String member_id);

    @FormUrlEncoded
    @POST("FetchMobileAPI.php")
    Observable<CommonResponse> fetchMobile(@Field("tag") String tag,
                                       @Field("phone_no") String phone_no);


    @FormUrlEncoded
    @POST("LoginAPI.php")
    Observable<LoginResponse> authLogin(@Field("tag") String tag,
                                        @Field("phone_no") String phone_no,
                                        @Field("member_password") String member_password,
                                        @Field("device_token") String deviceToken);

    @FormUrlEncoded
    @POST("ForgotPassword.php")
    Observable<ForgotPasswordResponse> forgotPassword(@Field("tag") String tag,
                                                      @Field("phone_no") String phone_no);
    @FormUrlEncoded
    @POST("ResetPassword.php")
    Observable<CommonResponse> resetPassword(@Field("tag") String tag,
                                             @Field("member_password") String member_password,
                                             @Field("member_id") int member_id);
    @FormUrlEncoded
    @POST("ChangePassword.php")
    Observable<CommonResponse> changePassword(@Field("tag") String tag,
                                             @Field("new_password") String new_password,
                                             @Field("old_password") String old_password,
                                             @Field("member_id") int member_id);

    @Multipart
    @POST("UploadPost.php")
    Observable<CommonResponse> uploadFile(@Part("tag")RequestBody TAG,
                                          @Part("post_title")RequestBody title,
                                          @Part("post_desc")RequestBody desc,
                                          @Part("member_id")RequestBody id,
                                          @Part("imgCnt") RequestBody size,
                                          @Part List<MultipartBody.Part> parts);

    @FormUrlEncoded
    @POST("DeletePost.php")
    Observable<CommonResponse> deletePost(@Field("tag") String tag,
                                        @Field("post_id") String post_id);
}
