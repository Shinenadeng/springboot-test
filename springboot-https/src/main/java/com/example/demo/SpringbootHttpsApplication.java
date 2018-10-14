package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootHttpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHttpsApplication.class, args);
    }

    /**
     * ����һ��TomcatEmbeddedServletContainerFactory bean
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * �����ǵ�Ӧ��֧��HTTP�Ǹ����뷨��������Ҫ�ض���HTTPS��
     * ���ǲ���ͬʱ��application.properties��ͬʱ��������connector��
     * ����Ҫ�Ա�̵ķ�ʽ����HTTP connector��Ȼ���ض���HTTPS connector
     *
     * @return Connector
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080); // http�˿�
        connector.setSecure(false);
        connector.setRedirectPort(7443); // application.properties�����õ�https�˿�
        return connector;
    }
}
