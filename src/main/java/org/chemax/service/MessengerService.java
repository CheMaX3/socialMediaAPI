package org.chemax.service;

import org.chemax.entity.Message;
import org.chemax.request.MessageCreateRequest;

import java.util.List;

public interface MessengerService {

    Message sendMessage(MessageCreateRequest messageCreateRequest);

    List<Message> getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
