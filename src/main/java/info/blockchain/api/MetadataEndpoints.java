package info.blockchain.api;

import info.blockchain.wallet.metadata.data.Auth;
import info.blockchain.wallet.metadata.data.Invitation;
import info.blockchain.wallet.metadata.data.Message;
import info.blockchain.wallet.metadata.data.MetadataRequest;
import info.blockchain.wallet.metadata.data.MetadataResponse;
import info.blockchain.wallet.metadata.data.MessageProcessRequest;
import info.blockchain.wallet.metadata.data.Trusted;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetadataEndpoints {

    @GET("auth")
    Call<Auth> getNonce();

    @POST("auth")
    Call<Auth> getToken(@Body HashMap<String, String> body);


    @GET("trusted")
    Call<Trusted> getTrustedList(@Header("Authorization") String jwToken);

    @GET("trusted")
    Call<Trusted> getTrusted(@Header("Authorization") String jwToken, @Query("mdid") String mdid);

    @PUT("trusted/{mdid}")
    Call<Trusted> putTrusted(@Header("Authorization") String jwToken, @Path("mdid") String mdid);

    @DELETE("trusted/{mdid}")
    Call<ResponseBody> deleteTrusted(@Header("Authorization") String jwToken, @Path("mdid") String mdid);


    @POST("messages")
    Call<Message> postMessage(@Header("Authorization") String jwToken, @Body Message body);

    @GET("messages")
    Call<List<Message>> getMessages(@Header("Authorization") String jwToken, @Query("new") Boolean onlyProcessed);

    @GET("message/{uuid}")
    Call<Message> getMessage(@Header("Authorization") String jwToken, @Path("uuid") String messageId);

    @PUT("message/{uuid}/processed")
    Call<Void> processMessage(@Header("Authorization") String jwToken, @Path("uuid") String id, @Body MessageProcessRequest body);


    @POST("share")
    Call<Invitation> postShare(@Header("Authorization") String jwToken);

    @POST("share/{uuid}")
    Call<Invitation> postToShare(@Header("Authorization") String jwToken, @Path("uuid") String uuid);

    @GET("share/{uuid}")
    Call<Invitation> getShare(@Header("Authorization") String jwToken, @Path("uuid") String uuid);

    @DELETE("share/{uuid}")
    Call<Invitation> deleteShare(@Header("Authorization") String jwToken, @Path("uuid") String uuid);


    @PUT("{addr}")
    Call<Void> putMetadata(@Path("addr") String address, @Body MetadataRequest body);

    @GET("{addr}")
    Call<MetadataResponse> getMetadata(@Path("addr") String address);

    @DELETE("{addr}")
    Call<Void> deleteMetadata(@Path("addr") String address, @Query("signature") String signature);
}