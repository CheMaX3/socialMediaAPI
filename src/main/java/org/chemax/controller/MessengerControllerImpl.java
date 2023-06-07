package org.chemax.controller;

import org.chemax.entity.Message;
import org.chemax.request.MessageCreateRequest;
import org.chemax.service.MessengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessengerControllerImpl implements MessengerController {

    private MessengerService messengerService;

    public MessengerControllerImpl(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @Override
    public ResponseEntity<Message> sendMessage(MessageCreateRequest messageCreateRequest) {
        final Message message = messengerService.sendMessage(messageCreateRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Message>> getConversationBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        final List<Message> conversation = messengerService.getConversationBySenderIdAndReceiverId(senderId, receiverId);
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }
}
