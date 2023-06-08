package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.Message;
import org.chemax.repository.FriendRepository;
import org.chemax.repository.MessageRepository;
import org.chemax.request.MessageCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessengerServiceImpl implements MessengerService {

    private static final Logger log = Logger.getLogger(PostServiceImpl.class.getName());

    private final FriendRepository friendRepository;

    private final MessageRepository messageRepository;

    public MessengerServiceImpl(FriendRepository friendRepository, MessageRepository messageRepository) {
        this.friendRepository = friendRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendMessage(MessageCreateRequest messageCreateRequest) {
        Message message = new Message();
        try {
            if (friendshipCheck(messageCreateRequest.getSenderId(), messageCreateRequest.getReceiverId())) {
                message = buildMessageFromRequest(messageCreateRequest);
                messageRepository.save(message);
            } else {
                throw new RuntimeException("Can't send message not a friend");
            }
        }
        catch (RuntimeException re) {
                log.error(re);
            }
        catch (Exception ex) {
            log.error("Can't save object " + messageCreateRequest.toString());
        }
        return message;
    }

    @Override
    public List<Message> getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        List<Message> conversation = new ArrayList<>();
        try {
            List<Message> fromSender = messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
            List<Message> fromReceiver = messageRepository.findBySenderIdAndReceiverId(receiverId, senderId);
            conversation = Stream.concat(fromSender.stream(), fromReceiver.stream())
                    .sorted((message1, message2) -> Math.toIntExact(message2.getMessageId() - message1.getMessageId()))
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return conversation;
    }


    private Message buildMessageFromRequest(MessageCreateRequest messageCreateRequest) {
        Message builtMessage = new Message();
        builtMessage.setText(messageCreateRequest.getText());
        builtMessage.setSenderId(messageCreateRequest.getSenderId());
        builtMessage.setReceiverId(messageCreateRequest.getReceiverId());

        return builtMessage;
    }

    private boolean friendshipCheck(Long senderId, Long receiverId) {
        boolean isFriends = false;
        try {
            if (friendRepository.findByRequesterIdAndRequestedId(senderId, receiverId) != null) {
                isFriends = true;
            }
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB");
        }
        return isFriends;
    }
}
