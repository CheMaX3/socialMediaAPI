package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.entity.Message;
import org.chemax.request.MessageCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер мессенджера", description = "Предоставляет доступ к функционалу мессенджера." +
        " Уровень доступа USER")
@RequestMapping(value = "messenger")
public interface MessengerController {

    @Operation(summary = "Отправка личного сообщения",
               description = "Создает сообщение, сохраняет его в базе, работает только для пользователей, находящихся " +
                    "в friendList")
    @PostMapping(value = "/send_message")
    ResponseEntity<Message> sendMessage(@RequestBody @Parameter(description = "Принимает json-объект, содержащий " +
                                        "текст сообщения, Id отправителя и Id получателя сообщения", required = true)
                                        MessageCreateRequest messageCreateRequest);

    @Operation(summary = "Личная переписка",
               description = "Возращает лист сообщений двух пользователей, отсортированный от последнего к первому")
    @GetMapping(value = "/get_conversation")
    ResponseEntity<List<Message>> getConversationBySenderIdAndReceiverId(@RequestParam
                                                                         @Parameter(description = "Id отправителя",
                                                                                 required = true)
                                                                         Long senderId,
                                                                         @RequestParam
                                                                         @Parameter(description = "Id получателя",
                                                                                 required = true)
                                                                         Long receiverId);
}
