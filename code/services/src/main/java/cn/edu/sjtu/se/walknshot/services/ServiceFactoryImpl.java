package cn.edu.sjtu.se.walknshot.services;

import cn.edu.sjtu.se.walknshot.csapi.ServiceFactory;
import cn.edu.sjtu.se.walknshot.csapi.serverapi.*;
import cn.edu.sjtu.se.walknshot.services.impl.*;

public class ServiceFactoryImpl implements ServiceFactory {
    public Authentication createAuthentication() {
        return new AuthenticationImpl();
    }
}
