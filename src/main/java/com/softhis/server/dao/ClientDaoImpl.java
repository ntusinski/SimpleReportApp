package com.softhis.server.dao;

import com.softhis.server.model.Client;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao implements ClientDao {
    @Override
    public List<Client> getClients() {
        Criteria criteria = getSession().createCriteria(Client.class);
        return criteria.list();
    }
}
