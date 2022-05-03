package emsi.pfa.smart_wattering_v0.ui.service;

import java.util.ArrayList;
import java.util.List;

import emsi.pfa.smart_wattering_v0.ui.beans.Role;
import emsi.pfa.smart_wattering_v0.ui.dao.IDao;

public class RoleService implements IDao<Role> {
    private List<Role> roles;
    private static RoleService instance;

    public RoleService() {
        this.roles = new ArrayList<>();
    }

    public static RoleService getInstance() {
        if (instance == null)
            instance = new RoleService();
        return instance;
    }

    @Override
    public boolean create(Role o) {
        return roles.add(o);
    }

    @Override
    public boolean update(Role o) {
        for (Role s : roles) {
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
    public boolean delete(Role o) {
        return roles.remove(o);
    }

    @Override
    public Role findById(int id) {
        for (Role s : roles) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roles;
    }

}
