import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Main {

    static final String URL_CEP = "https://api.postmon.com.br/v1/cep/";

    public static void main(String[] args) {

        Queue<String> urlPriorityQueue = new PriorityQueue<>(
                List.of("PUT_SOME_ZIP_CODE_HERE")
        );

        urlPriorityQueue.stream().peek(Main::getPool)
                .collect(Collectors.toList());

    }

    private static void getPool(String url) {

        ExecutorService executorService = ThreadPool.getInstance();

        executorService.submit(
                () -> callHttpClient(url)
        );

    }

    private static void callHttpClient(String url) {

        url = URL_CEP.concat(url);

        try {
            System.out.println(HttpClientCall.getResponse(url));
        } catch (Exception ex) {
            System.out.println("Error on get data on " + url);
        }
    }
}
