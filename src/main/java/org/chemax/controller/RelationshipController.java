package org.chemax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.chemax.entity.FriendshipInvite;
import org.chemax.request.DeleteFriendRequest;
import org.chemax.request.FriendshipInviteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер отношений",
     description = "Обслуживает запросы, касающиеся взаимоотношений пользователей. Уровень доступа USER")
@RequestMapping(value = "/relationship")
public interface RelationshipController {

    @Operation(summary = "Приглашение в друзья", description = "Создает приглашение в друзья. При отправке " +
            "приглашения, тот кто отправляет также подписывается на пользователя, которому он отправляет приглашение")
    @PostMapping(value = "/create_friendship_invite")
    ResponseEntity<FriendshipInvite> createFriendshipInviteAndSubscribe(@RequestBody
                                                                        @Parameter(description = "Принимает" +
            " json-объект, содержащий Id пользователя, который отправляет запрос в друзья и Id пользователя," +
            " которому отправляется запрос")
                                                                        FriendshipInviteRequest friendshipInviteRequest);

    @Operation(summary = "Принятие приглашения в друзья", description = "Как только пользователь принимает приглашение" +
            ", он подписывается на пользователя, который отправил приглашение. Оба пользователя добавляются в лист " +
            "друзей друг друга, а приглашение в друзья удаляется из базы")
    @GetMapping(value = "/approve")
    ResponseEntity<Boolean> approveFriendshipInviteWithInviteId(@RequestParam
                                                               @Parameter(description = "Id приглашения в друзья")
                                                               Long inviteId);

    @Operation(summary = "Отклонение приглашения в друзья", description = "Как только пользователь отклоняет " +
            "приглашение, оно удаляется из базы. Пользователь отправивший приглашение остается подписанным на" +
            "пользователя, которому было отправлено приглашение")
    @GetMapping(value = "/decline")
    ResponseEntity<Void> declineFriendshipInviteWithInviteId(@RequestParam
                                                             @Parameter(description = "Id приглашения в друзья")
                                                             Long inviteId);

    @Operation(summary = "Удаление из друзей", description = "Как только пользователь удаляет другого пользователя" +
            "из друзей, они удаляются друг у друга из листа друзей, инициатор удаления отписывается от удаляемого" +
            "пользователя. Пользователь который был удален остается подписан на удалившего его из друзей пользователя")
    @PostMapping(value = "/delete_friend")
    ResponseEntity<Void> deleteAndUnsubscribeFromUserWithId(@RequestBody
                                                            @Parameter(description = "Принимает json-объект, " +
            "содержащий Id пользователя, который инициировал удаление и Id пользователя, который удаляется из " +
            "списка друзей")
                                                            DeleteFriendRequest deleteFriendRequest);
}
