//package com.example.apnaniwas.ApnaNiwasDB;
//
//import android.util.Log;
//
//import com.example.apnaniwas.ApnaNiwasDB.connection.APIService;
//import com.example.apnaniwas.ApnaNiwasDB.connection.RestClient;
//import com.example.apnaniwas.ApnaNiwasDB.model.SignUp.MemberModel;
//import com.example.apnaniwas.ApnaNiwasDB.model.SignUp.MemberResponse;
//import com.example.apnaniwas.signup.Signup;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.observers.DisposableSingleObserver;
//import io.reactivex.schedulers.Schedulers;
//
//public class CurdOperation {
//
//    private static APIService apiService = RestClient.createService(APIService.class);
//
//    public static void insertMember(String member_name, String phone_no, String email_id,String member_password) {
//        apiService.addMember("add_member", member_name, phone_no, email_id
//                , member_password)
//
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<MemberModel>() {
//
//                    @Override
//                    public void onSuccess(MemberModel memberModel) {
//                        MemberModel member = new MemberModel(
//                                memberModel.getMemberId(),
//                                memberModel.getMemberName(),
//                                memberModel.getPhoneNo(),
//                                memberModel.getEmailId(),
//                                memberModel.getMemberPassword());
//                        SharedPreference.getInstance(Signup.getContext()).userRegister(member);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("ERROR", "onError: " + e.getMessage());
//                    }
//                });
//    }
//}
