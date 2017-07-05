package cn.edu.sjtu.se.walknshot.apiclient.tests;

import cn.edu.sjtu.se.walknshot.apiclient.CallbackAutoNetworkFailure;
import cn.edu.sjtu.se.walknshot.apiclient.Client;
import cn.edu.sjtu.se.walknshot.apiclient.ClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestClientImpl {
    public static void main(String[] args) {
        ClientImpl clientImpl = new ClientImpl();
        clientImpl.setBaseUrl("http://localhost:8080");
        Client client = clientImpl;

        client.login(new CallbackAutoNetworkFailure() {
                @Override
                public void onFailure(Object arg) {
                    System.out.println(arg);
                }

                @Override
                public void onSuccess(Object arg) {
                    System.out.println(arg);
                    try {
                        System.out.println(new ObjectMapper().writeValueAsString(clientImpl.getToken()));
                    } catch (JsonProcessingException e) {
                        System.out.println("JSON error");
                    }
                    System.out.println(client.isLoggedIn());
                }
            },
            "anonymous", "secret");

        System.out.println(client.isLoggedIn());
    }
}
