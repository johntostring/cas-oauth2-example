package com.millinch.casclient;

import com.millinch.spring.boot.autoconfigure.shiro.ShiroFilterCustomizer;
import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.profile.ShiroProfileManager;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.callback.QueryParameterCallbackUrlResolver;
import org.pac4j.oauth.client.CasOAuthWrapperClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This guy is busy, nothing left
 *
 * @author John Zhang
 */
@SpringBootApplication
public class CasClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasClientApplication.class, args);
    }

    private CasOAuthWrapperClient casOAuthWrapperClient() {
        CasOAuthWrapperClient client = new CasOAuthWrapperClient("this_is_the_key2", "this_is_the_secret2", "https://cas.example.org:8443/oauth2.0");
        client.setCallbackUrl("http://localhost:9000/callback?client_name=CasOAuthWrapperClient");
        client.setCallbackUrlResolver(new QueryParameterCallbackUrlResolver());
        client.setCasLogoutUrl("https://cas.example.org:8443/logout");
        return client;
    }

    private SecurityFilter securityFilter() {
        SecurityFilter filter = new SecurityFilter();
        Config config = oauthConfig();
        filter.setConfig(config);
        filter.setClients("casOAuthWrapperClient");
        return filter;
    }

    private Config oauthConfig() {
        Config config = new Config(casOAuthWrapperClient());
        config.setSessionStore(new ShiroSessionStore());
        config.setProfileManagerFactory(ShiroProfileManager::new);
        return config;
    }

    private CallbackFilter callbackFilter() {
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(oauthConfig());
        return callbackFilter;
    }

    @Bean
    public AllowAllCredentialsMatcher credentialsMatcher() {
        return new AllowAllCredentialsMatcher();
    }

    @Bean
    public ShiroFilterCustomizer shiroFilterCustomizer() {
        ShiroFilterCustomizer shiroFilterCustomizer = map -> {
            map.put("callbackFilter", callbackFilter());
            map.put("securityFilter", securityFilter());
            map.put("logoutFilter", logoutFilter());
            return map;
        };

        return shiroFilterCustomizer;
    }

    private LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(oauthConfig());
        logoutFilter.setDefaultUrl("/foo");
        logoutFilter.setLocalLogout(true);
        logoutFilter.setCentralLogout(true);
        return logoutFilter;
    }
}
