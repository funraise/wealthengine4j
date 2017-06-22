import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * Created by jason on 6/22/17.
 */
public class FakeInterceptor implements Interceptor {

    private String responseString = "";

    public FakeInterceptor(String responseResource) throws IOException {
        responseString = new String(readAllBytes(get(getClass().getResource(responseResource).getFile())));
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
            return new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
    }
}