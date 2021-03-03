package info.setmy.examples.lessons.level5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.infinispan.Cache;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.jupiter.api.Test;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class Lesson1InfinispanIT {

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX, OS.MAC})
    public void defaultInfinispan() {
        final String cacheName = "exampleCache";
        final String key = "firstName";
        final String value = "This is value in cache";
        final GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
        final DefaultCacheManager cacheManager = new DefaultCacheManager(global.build());
        final ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.clustering().cacheMode(CacheMode.LOCAL);
        cacheManager.administration().withFlags(CacheContainerAdmin.AdminFlag.VOLATILE).getOrCreateCache(cacheName, builder.build());
        final Cache<Object, Object> cache = cacheManager.getCache(cacheName);

        cache.put(key, value);

        assertThat(cache.get(key), is(equalTo("This is value in cache")));
    }

    @Test
    @Disabled("TODO : how to do it correctly")
    public void jcache() {
        CachingProvider jcacheProvider = Caching.getCachingProvider();
        CacheManager cacheManager = jcacheProvider.getCacheManager();
        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(String.class, String.class);
        javax.cache.Cache<String, String> cache = cacheManager.createCache("myCache", configuration);
        cache.put("key", "value");
        assertThat(cache.get("key"), is(equalTo("value")));
        cacheManager.close();
    }
}
