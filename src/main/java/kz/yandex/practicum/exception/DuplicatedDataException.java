package kz.yandex.practicum.exception;

public class DuplicatedDataException extends RuntimeException{
    public DuplicatedDataException(String message){
        super(message);
    }
}