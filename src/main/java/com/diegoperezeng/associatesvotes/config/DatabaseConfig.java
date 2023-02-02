//package com.diegoperezeng.associatesvotes.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.github.cdimascio.dotenv.Dotenv;
//
//@Configuration
//public class DatabaseConfig {
//  
//  @Bean
//  public DataSource dataSource() {
//    Dotenv dotenv = Dotenv.load();
//    String host = dotenv.get("MYSQL_HOST");
//    String port = dotenv.get("MYSQL_PORT");
//    String database = dotenv.get("MYSQL_DATABASE");
//    String username = dotenv.get("MYSQL_USER");
//    String password = dotenv.get("MYSQL_PASSWORD");
//
//    @SuppressWarnings("rawtypes")
//	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//    
//    dataSourceBuilder.url("jdbc:mysql://" + host + ":" + port + "/" + database);
//    dataSourceBuilder.username(username);
//    dataSourceBuilder.password(password);
//    dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//    return dataSourceBuilder.build();
//  }
//}