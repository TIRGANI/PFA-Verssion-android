package emsi.pfa.smart_wattering_v0.ui.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.dao.IDao;

public class UserService implements IDao<User> {
    private List<User> users;
    private static UserService instance;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    @Override
    public boolean create(User o) {
        return users.add(o);
    }

    @Override
    public boolean update(User o) {
        for (User s : users) {
           /* if (s.getStar() == o.getId()) {
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
                s.setPrenom(o.getPrenom());
                s.setSexe(o.getSexe());
                s.setVille(o.getVille());
            }*/
        }
        return true;
    }

    @Override
    public boolean delete(User o) {
        return users.remove(o);
    }

    @Override
    public User findById(int id) {
        for (User s : users) {
         /*   if (s.getUser_id() == id)
                return s;
        */}
        return null;
    }
    public Boolean findByName(String login,String password) {
        for (User s : users) {
          //  Log.d("Username : "+s.getUsername(),"password"+s.getPassword());
            if (s.getUsername().equals(login) && s.getPassword().equals(password))
                return true;
        }
        return false;
    }
    public User findByNamee(String login,String password) {
        for (User s : users) {
              Log.d("id : "+s.getUser_id(),"password"+s.getPassword());
            if (s.getUsername().equals(login) && s.getPassword().equals(password))
                return s;
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

}