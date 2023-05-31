package org.chemax.request;

import org.chemax.entity.User;

public class UnsubscribeRequest {

    private User userWhoUnsubscribes;
    private Long targetForUnsubscribeUserId;

    public User getUserWhoUnsubscribes() {
        return userWhoUnsubscribes;
    }

    public void setUserWhoUnsubscribes(User userWhoUnsubscribes) {
        this.userWhoUnsubscribes = userWhoUnsubscribes;
    }

    public Long getTargetForUnsubscribeUserId() {
        return targetForUnsubscribeUserId;
    }

    public void setTargetForUnsubscribeUserId(Long targetForUnsubscribeUserId) {
        this.targetForUnsubscribeUserId = targetForUnsubscribeUserId;
    }
}
