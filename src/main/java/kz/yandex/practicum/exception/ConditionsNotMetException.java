package kz.yandex.practicum.exception;

public class ConditionsNotMetException extends RuntimeException{
    public ConditionsNotMetException(String message){
        super(message);
    }
}
