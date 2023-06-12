package org.chemax.service;

import org.chemax.entity.Subscribed;

import java.util.List;

public interface SubscribedService {

    List<Subscribed> getSubscribedListByRequesterId(Long requesterId);
}
