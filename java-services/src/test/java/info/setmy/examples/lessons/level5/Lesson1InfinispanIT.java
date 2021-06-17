package info.setmy.examples.lessons.level5;

import java.io.IOException;
import org.infinispan.Cache;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.infinispan.configuration.cache.CacheMode.LOCAL;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.condition.EnabledOnOs;
import static org.junit.jupiter.api.condition.OS.WINDOWS;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;

public class Lesson1InfinispanIT {

    private final String cacheName = "exampleCache";
    private final String key = "firstName";
    private final String value = "This is value in cache";

    @EnabledOnOs({WINDOWS, LINUX, MAC}) // TODO : investigate, why it fails on FreeBSD
    @Test
    public void jcache() throws IOException {
        final GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
        try ( DefaultCacheManager cacheManager = new DefaultCacheManager(global.build())) {
            final ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.clustering().cacheMode(LOCAL);//LOCAL, DIST_SYNC
            final Cache<Object, Object> cache = cacheManager.administration().withFlags(CacheContainerAdmin.AdminFlag.VOLATILE).getOrCreateCache(cacheName, builder.build());
            final Cache<Object, Object> sameCache = cacheManager.getCache(cacheName);

            cache.put(key, value);

            assertThat(cache.get(key)).isEqualTo(value);
            assertSame(cache, sameCache);
        }
    }
}
