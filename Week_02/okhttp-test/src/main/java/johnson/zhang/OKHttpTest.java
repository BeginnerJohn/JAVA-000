package johnson.zhang;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OKHttpTest {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://localhost:8801/").build();
        Response response = okHttpClient.newCall(request).execute();
        if (response!=null && response.body()!=null) {
            System.out.println(response.body().string());
        }

    }
}
