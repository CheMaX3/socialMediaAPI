package org.chemax.controller;

import org.chemax.entity.Message;
import org.chemax.request.MessageCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "messenger")
public interface MessengerController {

    @PostMapping(value = "/send_message")
    ResponseEntity<Message> sendMessage(@RequestBody MessageCreateRequest messageCreateRequest);

    @GetMapping(value = "/get_conversation")
    ResponseEntity<List<Message>> getConversationBySenderIdAndReceiverId(@RequestParam Long senderId,
                                                                         @RequestParam Long receiverId);
}
