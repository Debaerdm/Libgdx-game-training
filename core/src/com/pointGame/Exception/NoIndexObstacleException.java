package com.pointGame.Exception;

/**
 * Created by mathieu on 08/10/16.
 */
public class NoIndexObstacleException extends Exception {
    private String message;

    public NoIndexObstacleException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
