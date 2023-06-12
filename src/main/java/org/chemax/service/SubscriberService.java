package org.chemax.service;

import org.chemax.entity.Subscriber;

import java.util.List;

public interface SubscriberService {

    List<Subscriber> getSubscriberListByRequestedId(Long requestedId);

    List<Subscriber> getSubscriberListByRequesterId(Long requesterId);
}
