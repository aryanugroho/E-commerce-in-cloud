package cz.muni.fi.bapr.service.impl;

import cz.muni.fi.bapr.dao.VatDAO;
import cz.muni.fi.bapr.entity.Vat;
import cz.muni.fi.bapr.service.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
@Service
@Transactional
public class VatServiceImpl extends AbstractServiceImpl<Vat, VatDAO> implements VatService {

    @Autowired
    private VatDAO vatDAO;


    @Override
    public VatDAO getDao() {
        return vatDAO;
    }

    @Override
    public void create(Vat entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Vat is null");
        }
        if (entity.getId() != null) {
            throw new IllegalArgumentException("Vat ID is not null");
        }
        if ((vatDAO.findByVat(entity.getVat())) != null) {
            throw new IllegalArgumentException("Vat with value '" + entity.getVat() + "' already exists");
        }

        vatDAO.create(entity);
    }

    @Override
    public void edit(Vat entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Vat is null");
        }
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Vat ID is null");
        }

        Vat vat = vatDAO.findByVat(entity.getVat());

        if ((vat != null) && !vat.getId().equals(entity.getId())) {
            throw new IllegalArgumentException("Vat with value '" + entity.getVat() + "' already exists");
        }

        vatDAO.edit(entity);
    }

    @Override
    public Vat findByVat(Integer vat) {
        if (vat == null) {
            throw new IllegalArgumentException("Vat value is null");
        }

        return vatDAO.findByVat(vat);
    }
}
