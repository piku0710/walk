package cn.edu.sjtu.se.walknshot.csapi.clientapi;

public interface Authentication {
    void login(AsyncCallback onFinish, String user, String password);
}
