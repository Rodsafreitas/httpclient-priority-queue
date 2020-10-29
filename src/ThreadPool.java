import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    final static int MAX_THREADS = 2;
    private static ExecutorService executorService = null;

    public static ExecutorService getInstance() {

        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(MAX_THREADS);
        }

        return executorService;
    }

}
