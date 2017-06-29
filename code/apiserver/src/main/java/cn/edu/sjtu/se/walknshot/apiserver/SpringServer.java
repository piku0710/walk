package cn.edu.sjtu.se.walknshot.apiserver;

import cn.edu.sjtu.se.walknshot.csapi.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringServer {
    public static ServiceFactory serviceFactory = new cn.edu.sjtu.se.walknshot.services.ServiceFactoryImpl();

    public static void main(String[] args) {
        SpringApplication.run(SpringServer.class, args);
    }
}
