package com.hzqiyunkeji.core.net;

import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 傅令杰 on 2017/4/2
 */
public interface RestService {

    @GET
    Call<String> get( @Url String url,@Header("X-Request-Token") String token, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url,@Header("X-Request-Token") String token, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Call<String> postRaw(@Url String url,@Header("X-Request-Token") String token, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url,@Header("X-Request-Token") String token, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    Call<String> putRaw(@Url String url,@Header("X-Request-Token") String token, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url,@Header("X-Request-Token") String token, @QueryMap WeakHashMap<String, Object> params);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Header("X-Request-Token") String token,@Part MultipartBody.Part file);
}
