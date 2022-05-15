package emsi.pfa.smart_wattering_v0.ui.service;

import java.util.ArrayList;
import java.util.List;

import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;
import emsi.pfa.smart_wattering_v0.ui.dao.IDao;

public class ParcelleService implements IDao<Parcelle> {

    private List<Parcelle> parcelles;
    private static ParcelleService instance;

    public ParcelleService() {
        this.parcelles = new ArrayList<>();
    }

    public static ParcelleService getInstance() {
        if (instance == null)
            instance = new ParcelleService();
        return instance;
    }

    @Override
    public boolean create(Parcelle o) {
        return parcelles.add(o);
    }

    @Override
    public boolean update(Parcelle o) {
        for (Parcelle s : parcelles) {
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
    public boolean delete(Parcelle o) {
        return parcelles.remove(o);
    }

    @Override
    public Parcelle findById(int id) {
        for (Parcelle s : parcelles) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    @Override
    public List<Parcelle> findAll() {
        return parcelles;
    }

}

