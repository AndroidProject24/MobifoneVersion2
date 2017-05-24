package com.toan_itc.mobifone.data.service;

import com.toan_itc.mobifone.mvp.model.JsonArray;
import com.toan_itc.mobifone.mvp.model.congno.ListCongno;
import com.toan_itc.mobifone.mvp.model.khoso.CheckSdt;
import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;
import com.toan_itc.mobifone.mvp.model.login.Exit;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.model.register.Register;
import com.toan_itc.mobifone.mvp.model.theloai.Theloai;
import com.toan_itc.mobifone.mvp.model.upanh.Upanh;
import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */

public interface RestApi {
    String BASE_URL = "http://n3t.top/test/api/";

    /*LOGIN*/
    @FormUrlEncoded
    @POST("dangnhap")
    Observable<Login> getLogin(@Field("user") String user, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("edituser")
    Observable<Login> changePassword(@Field("auth_code") String auth_code,@Field("username") String username,@Field("iduser") String id,@Field("old") String passOld, @Field("new") String passNew);

    /*EXIT*/
    @FormUrlEncoded
    @POST("dangxuat")
    Observable<Exit> exit(@Field("auth_code") String auth_code, @Field("iduser") String iduser);

    /*REGISTER*/
    @FormUrlEncoded
    @POST("edituser")
    Observable<Login> updateProfile(@Field("auth_code") String auth_code, @Field("username") String username,
                                           @Field("iduser") String id, @Query("email") String email,@Field("phone") String phone,
                                           @Field("first_name") String first_name, @Field("last_name") String last_name);

    @FormUrlEncoded
    @POST("dangkyuser")
    Observable<Register> getRegister(@Field("email") String email, @Field("user") String user, @Field("pass") String password);

    /*UPANH*/
    @Multipart
    @POST("upanh/tratruoc")
    Observable<Upanh> postImageCanhanTratruoc(@Part("sdt") RequestBody sdt, @Part("dichvu") RequestBody dichvu,
                                              @Part MultipartBody.Part file1,
                                              @Part MultipartBody.Part file2,
                                              @Part MultipartBody.Part file3);

    @Multipart
    @POST("upanh/trasaucanhan")
    Observable<Upanh> postImageCanhanTrasau(@Part(value = "sdt") String sdt, @Part(value = "dichvu") String dichvu, @Part("cmnd_mt\"; filename=\"image1.png\" ") RequestBody photo1,
                                            @Part("cmnd_ms\"; filename=\"image2.png\" ") RequestBody photo2, @Part("hopdong_mt\"; filename=\"image3.png\" ") RequestBody photo3, @Part("hopdong_ms\"; filename=\"image4.png\" ") RequestBody photo4, @Part("phuluc4\"; filename=\"image5.png\" ") RequestBody photo5);

    @Multipart
    @POST("upanh/trasaudoanhnghiep")
    Observable<Upanh> postImageDoanhnghiep(@Part(value = "sdt") String sdt, @Part(value = "dichvu") String dichvu, @Part("cmnd_mt\"; filename=\"image1.png\" ") RequestBody photo1,
                                           @Part("cmnd_ms\"; filename=\"image2.png\" ") RequestBody photo2, @Part("hopdong_mt\"; filename=\"image3.png\" ") RequestBody photo3, @Part("hopdong_ms\"; filename=\"image4.png\" ") RequestBody photo4,
                                           @Part("phuluc4\"; filename=\"image5.png\" ") RequestBody photo5, @Part("gpkd\"; filename=\"image6.png\" ") RequestBody photo6);
    /*KHUYEMAI*/
    @GET("theloai/{IDtheloai}")
    Observable<JsonArray<Theloai>> getThutuc(@Path("IDtheloai") int IDtheloai);

    @GET("theloai/{IDtheloai}")
    Observable<JsonArray<com.toan_itc.mobifone.mvp.model.khoso.Theloai>> getTheLoai(@Path("IDtheloai") int IDtheloai);

    /*Khoso*/
    @GET("timsim")
    Observable<Khoso> getKhoso(@Query("search") String search, @Query("kho") String kho, @Query("dau") String dau,@Query("dang") String dang);

    @GET
    Observable<Khoso> getLoadmore(@Url String url);

    @GET("dangsim")
    Observable<JsonArray<Dangsim>> getDangSim(@Query("noibat") String noibat);


    /*CONGNO*/
    @FormUrlEncoded
    @POST("congno")
    Observable<ListCongno> getCongno(@Field("auth_code") String auth_code, @Field("iduser") String iduser);

    /*VAS*/
    @GET("captcha")
    Observable<Vas> getCaptcha(@Query("auth_code") String auth_code, @Query("iduser") String iduser);

    @GET("thongtingoicuoc")
    Observable<JsonArray<Goicuoc>> getGoiCuoc();

    @FormUrlEncoded
    @POST("banvas")
    Observable<Vas> getVas(@Field("auth_code") String auth_code, @Field("iduser") String iduser, @Field("sdt") String sdt, @Field("captcha") String captcha, @Field("magoi") String magoi);

    @GET("smsvas")
    Observable<Response<ResponseBody>> checkVas(@Query("auth_code") String auth_code, @Query("iduser") String iduser,@Query("sdt") String sdt,@Query("from") String from,@Query("to") String to);

    @FormUrlEncoded
    @POST("sendchecksdt")
    Observable<CheckSdt> checkSDT(@Field("auth_code") String auth_code, @Field("iduser") String iduser, @Field("sdt") String sdt);
}
