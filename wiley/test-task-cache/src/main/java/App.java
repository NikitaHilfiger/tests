import caching.Cache;
import lombok.extern.slf4j.Slf4j;
import strategy.CacheHolder;
import strategy.CacheStrategies;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(
                (t, e) -> {
                    log.error(e.getMessage());

                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String stacktrace = sw.toString();
                    System.out.println(stacktrace);
                }
        );

        CacheHolder<String, String> cacheHolder = new CacheStrategies<String, String>().TWO_LEVELS_MEMORY_HARDDISK;

        Cache<String, String> cache = cacheHolder.getCache();

        cache.cache("key1", "value1");
        cache.cache("key2", "value2");
        cache.cache("key3", "value3");
        cache.cache("key4", "value4");

        cache.retrieve("key1");
        cache.retrieve("key8");

        // now values by key1 and key4 becomes the most used elements

        cacheHolder.recache();

        // now only the most used values are stored in memory,
        // the rest are stored on the hard disk

    }
}
