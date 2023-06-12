package org.chemax.exception;

public class FriendshipCheckFailureException extends RuntimeException {

    public FriendshipCheckFailureException() {
        super("Users must be friends to send messages");
    }
}
