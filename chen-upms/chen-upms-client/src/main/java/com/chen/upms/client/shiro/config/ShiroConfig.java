package com.chen.upms.client.shiro.config;

import com.chen.upms.client.shiro.filter.UpmsAuthenticationFilter;
import com.chen.upms.client.shiro.filter.UpmsSessionForceLogoutFilter;
import com.chen.upms.client.shiro.listener.UpmsSessionListener;
import com.chen.upms.client.shiro.realm.UpmsRealm;
import com.chen.upms.client.shiro.session.UpmsSession;
import com.chen.upms.client.shiro.session.UpmsSessionDao;
import com.chen.upms.client.shiro.session.UpmsSessionFactory;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Tiltle: com.zheng.upms.client.shiro.config </p>
 * <p>Description: shiro配置中心 </p>
 *
 * @Author chen
 * @data: 2017-08-19
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    private static Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
    @Bean(name = "cacheShiroManager")
    public CacheManager getCacheManage() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return new EhCacheManager();
    }


    /**
     *  Shiro生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {

        SimpleCookie cookie = new SimpleCookie("sid");//Cookie名称
        cookie.setHttpOnly(true);//不会暴露给客户端
        cookie.setMaxAge(-1);//设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
        return cookie;
    }

    /**
     * cookie对象;
     * @return
     * */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);//不会暴露给客户端
        simpleCookie.setMaxAge(2592000);//记住我cookie生效时间
        return simpleCookie;
    }

    /**
     *  rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager getRememberManager(){
        CookieRememberMeManager meManager = new CookieRememberMeManager();
        meManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        meManager.setCookie(getRememberMeCookie());
        return meManager;
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getSessionManage() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(getSessionIdCookie());

        sessionManager.setSessionDAO(getCachingSessionDAO());
        sessionManager.setSessionFactory(getSessionFactory());
        sessionManager.setSessionListeners((Collection<SessionListener>) getSessionListener());

        EnterpriseCacheSessionDAO cacheSessionDAO = new EnterpriseCacheSessionDAO();
        cacheSessionDAO.setCacheManager(getCacheManage());
        sessionManager.setSessionDAO(cacheSessionDAO);
        // -----可以添加session 创建、删除的监听器

        return sessionManager;
    }

    @Bean(name = "UpmsRealm")
    public AuthorizingRealm getShiroRealm() {
        AuthorizingRealm realm = new UpmsRealm();
      //  realm.setName("shiro_auth_cache");
       // realm.setAuthenticationCache(getCacheManage().getCache(realm.getName()));
        realm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return realm;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(getCacheManage());
        securityManager.setSessionManager(getSessionManage());
        securityManager.setRememberMeManager(getRememberManager());
        securityManager.setRealm(getShiroRealm());

        return securityManager;
    }

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{getSecurityManager()});
        return factoryBean;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getSecurityManager());
        return advisor;
    }

    /**
     * Shiro的Web过滤器
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(getSecurityManager());
        factoryBean.setLoginUrl("/toLogin");
        factoryBean.setSuccessUrl("");
        factoryBean.setUnauthorizedUrl("unauthorizedUrl");
        filterMap.put("authc",getAuthenticationFilter());
        filterMap.put("upmsSessionForceLogout",getSessionForceLogoutFilter());
        factoryBean.setFilters(filterMap);
        filterChainDefinitionMap.put("/manage/index", "user");
        filterChainDefinitionMap.put("/druid/**", "user");
        filterChainDefinitionMap.put("/swagger-ui.html", "user");
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }



    @Bean(name = "UpmsSessionForceLogoutFilter")
    public AccessControlFilter getSessionForceLogoutFilter(){
        return new UpmsSessionForceLogoutFilter();
    }

    @Bean(name ="upmsAuthenticationFilter")
    public AuthenticationFilter getAuthenticationFilter(){
        return new UpmsAuthenticationFilter();
    }


    @Bean(name = "UpmsSessionListener")
    public SessionListener getSessionListener(){
        return new UpmsSessionListener();
    }
    @Bean(name = "UpmsSessionDao")
    public CachingSessionDAO getCachingSessionDAO(){
        return new UpmsSessionDao();
    }
    @Bean(name = "UpmsSessionFactory")
    public SessionFactory getSessionFactory(){
        return new UpmsSessionFactory();
    }
}
