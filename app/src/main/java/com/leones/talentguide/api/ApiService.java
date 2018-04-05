package com.leones.talentguide.api;

/**
 * Created by Diegormz on 04/04/2018.
 */

import com.leones.talentguide.model.Devices;
import com.leones.talentguide.response.GetDashBoardResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //Get devices from ap cisco
    @GET("devices/{mA}/clients")
    Call<List<Devices>> getDevices(@Header("X-Cisco-Meraki-API-Key") String token, @Header("Content-Type") String type, @Path("mA") String macAddress, @Query("timespan") String timeSpan);

}
