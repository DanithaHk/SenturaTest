package lk.ijse.senturatest.service;

import lk.ijse.senturatest.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.*;

@Service
public class UserService {

    @Value("${weavy.api.base-url}")
    private  String BASE_URL ;
    @Value("${weavy.api.token}")
    private  String TOKEN ;

    private final OkHttpClient client = new OkHttpClient();
    private Request.Builder requestBuilder(String url) { return new Request.Builder() .url(url) .addHeader("Authorization", "Bearer " + TOKEN) .addHeader("Content-Type", "application/json"); }
    // Create User
    public String createUser(User user) throws Exception {
        String json = String.format("{\"uid\":\"%s\",\"name\":\"%s\",\"email\":\"%s\"}",
                user.getUid(), user.getName(), user.getEmail());

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    //  List Users
    public String listUsers() throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // Get User by UID
    public String getUser(String uid) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + uid)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // ️ Update User
    public String updateUser(String uid, User user) throws Exception {
        String json = String.format("{\"name\":\"%s\",\"email\":\"%s\"}",
                user.getName(), user.getEmail());

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + uid)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .patch(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // Delete User
    public String deleteUser(String uid) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + uid)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}

