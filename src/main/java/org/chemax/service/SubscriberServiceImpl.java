package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.Subscriber;
import org.chemax.repository.SubscriberRepository;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private static final Logger log = Logger.getLogger(SubscriberServiceImpl.class.getName());

    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<Subscriber> getSubscriberListByRequestedId(Long requestedId) {
        try {
            return subscriberRepository.findByRequestedId(requestedId);
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }

    @Override
    public List<Subscriber> getSubscriberListByRequesterId(Long requesterId) {
        try {
            return subscriberRepository.findByRequesterId(requesterId);
        } catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }
}
