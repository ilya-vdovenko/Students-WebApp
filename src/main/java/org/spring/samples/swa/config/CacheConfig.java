package org.spring.samples.swa.config;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Configure class for cache.
 *
 * @author Ilya Vdovenko
 */

@Configuration
public class CacheConfig {

  /**
   * Configurate cache manager factory bean.
   *
   * @return EhCacheManagerFactoryBean.
   */
  @Bean("ehcache")
  public EhCacheManagerFactoryBean cacheManager() {
    EhCacheManagerFactoryBean ehCacheManager = new EhCacheManagerFactoryBean();
    ehCacheManager.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
    ehCacheManager.setShared(true);
    return ehCacheManager;
  }

  /**
   * Configurate ehcache manager bean.
   *
   * @return EhCacheCacheManager.
   */
  @Bean("cacheManager")
  @Autowired
  public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
    EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    ehCacheCacheManager.setCacheManager(cacheManager);
    return ehCacheCacheManager;
  }

}
