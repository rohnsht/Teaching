package np.com.rohanshrestha.firstproject.rest;

import java.util.ArrayList;

import np.com.rohanshrestha.firstproject.models.Flower;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by legen on 25/10/2017.
 */

public interface ApiInterface {

    @GET("feeds/flowers.json")
    Call<ArrayList<Flower>> getFlowers();


    /*@POST("feeds/flowers")
    Call<String> postFlower(@Body Flower flower);*/

    @FormUrlEncoded
    @POST("feeds/flowers")
    Call<String> postFlower(@Field("name") String nam, @Field("categoey") String category);

}
