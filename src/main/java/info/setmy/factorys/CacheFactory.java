package info.setmy.factorys;

import static info.setmy.constants.CacheConstants.CACHE_NAME;
import io.micronaut.context.annotation.Factory;
import javax.inject.Named;

import javax.inject.Singleton;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Factory
public class CacheFactory {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Singleton
    @Named("cacheManager")
    public EmbeddedCacheManager getEmbeddedCacheManager() {
        log.info("Creating cache manager");
        final DefaultCacheManager cacheManager = new DefaultCacheManager();
        cacheManager.defineConfiguration(CACHE_NAME, new ConfigurationBuilder().build());
        return cacheManager;
    }

    @Singleton
    @Named("cache")
    public Cache getCache(final EmbeddedCacheManager cacheManager) {
        log.info("Getting cache");
        return cacheManager.getCache(CACHE_NAME);
    }
}
