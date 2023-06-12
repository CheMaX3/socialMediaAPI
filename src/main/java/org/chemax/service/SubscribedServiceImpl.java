package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.Subscribed;
import org.chemax.repository.SubscribedRepository;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribedServiceImpl implements SubscribedService {

    private static final Logger log = Logger.getLogger(SubscribedServiceImpl.class.getName());

    private final SubscribedRepository subscribedRepository;

    public SubscribedServiceImpl(SubscribedRepository subscribedRepository) {
        this.subscribedRepository = subscribedRepository;
    }

    @Override
    public List<Subscribed> getSubscribedListByRequesterId(Long requesterId) {
        try {
            return subscribedRepository.findByRequesterId(requesterId);
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }
}
