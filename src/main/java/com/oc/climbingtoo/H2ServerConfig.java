package com.oc.climbingtoo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;

import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

	@Bean(initMethod = "start", destroyMethod = "stop")
	@ConditionalOnProperty(name="h2.servePublicly")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

}