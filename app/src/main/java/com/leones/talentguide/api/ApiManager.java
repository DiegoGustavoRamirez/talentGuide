package com.leones.talentguide.api;

import com.leones.talentguide.model.Devices;
import com.leones.talentguide.response.GetDashBoardResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;


//References interface methods

public class ApiManager {

    public Call<List<Devices>> getDevices(String token, String contentType, String macAddress, String timeSpan) {
        ApiService apiService = ServiceGenerator.dashBoard();
        return apiService.getDevices(token, contentType, macAddress, timeSpan);
    }

}
