package emsi.pfa.smart_wattering_v0.ui.service;

import java.util.ArrayList;
import java.util.List;

import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.dao.IDao;

public class FermeService implements IDao<Ferme> {
    private List<Ferme> ferms;
    private static FermeService instance;

    public FermeService() {
        this.ferms = new ArrayList<>();
    }

    public static FermeService getInstance() {
        if (instance == null)
            instance = new FermeService();
        return instance;
    }

    @Override
    public boolean create(Ferme o) {
        return ferms.add(o);
    }

    @Override
    public boolean update(Ferme o) {
        for (Ferme s : ferms) {
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
    public boolean delete(Ferme o) {
        return ferms.remove(o);
    }

    @Override
    public Ferme findById(int id) {
        for (Ferme s : ferms) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    @Override
    public List<Ferme> findAll() {
        return ferms;
    }

}
