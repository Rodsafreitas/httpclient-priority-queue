import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class HttpClientCall {

    private static final int TIME_OUT = 20;

    public static String getResponse(String url) throws ExecutionException, InterruptedException {

        HttpClient client = httpClientBuilder();
        HttpRequest request = createRequestGET(url);

        return client.sendAsync(request,
               HttpResponse.BodyHandlers.ofString())
               .thenApply(HttpResponse::body).get();
    }

    private static HttpClient httpClientBuilder() {

        return HttpClient.newBuilder()
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .build();

    }

    private static HttpRequest createRequestGET(String url) {

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(TIME_OUT))
                .build();
    }

}
