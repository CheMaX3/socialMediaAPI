package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.Message;
import org.chemax.exception.FieldCantBeEmptyException;
import org.chemax.exception.FriendshipCheckFailureException;
import org.chemax.repository.MessageRepository;
import org.chemax.request.MessageCreateRequest;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessengerServiceImpl implements MessengerService {

    private static final Logger log = Logger.getLogger(PostServiceImpl.class.getName());

    private final FriendService friendService;

    private final MessageRepository messageRepository;

    public MessengerServiceImpl(FriendService friendService, MessageRepository messageRepository) {
        this.friendService = friendService;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendMessage(MessageCreateRequest messageCreateRequest) {
        try {
            if (friendService.friendshipCheck(messageCreateRequest.getSenderId(), messageCreateRequest.getReceiverId())) {
                Message message = buildMessageFromRequest(messageCreateRequest);
                messageRepository.save(message);
                return message;
            } else {
                throw new FriendshipCheckFailureException();
            }
        }
        catch (Exception ex) {
            log.error("Can't save object " + messageCreateRequest.toString());
            throw new DataSourceLookupFailureException("Can't save object " + messageCreateRequest);
        }
    }

    @Override
    public List<Message> getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        try {
            List<Message> fromSender = messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
            List<Message> fromReceiver = messageRepository.findBySenderIdAndReceiverId(receiverId, senderId);
            return Stream.concat(fromSender.stream(), fromReceiver.stream())
                    .sorted((message1, message2) -> Math.toIntExact(message2.getMessageId() - message1.getMessageId()))
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }

    private Message buildMessageFromRequest(MessageCreateRequest messageCreateRequest) {
        try {
            Message builtMessage = new Message();
            builtMessage.setText(Optional.ofNullable(messageCreateRequest.getText())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtMessage.setSenderId(Optional.ofNullable(messageCreateRequest.getSenderId())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtMessage.setReceiverId(Optional.ofNullable(messageCreateRequest.getReceiverId())
                    .orElseThrow(FieldCantBeEmptyException::new));

            return builtMessage;
        }
        catch (Exception ex) {
            log.error("Bad request" + messageCreateRequest.toString());
            throw new FieldCantBeEmptyException();
        }
    }
}
