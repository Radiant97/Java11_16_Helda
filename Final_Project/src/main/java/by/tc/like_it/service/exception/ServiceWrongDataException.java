package by.tc.like_it.service.exception;

public class ServiceWrongDataException extends Exception{
    private static final long serialVersionUID = 1L;

    public ServiceWrongDataException(){
        super();
    }

    public ServiceWrongDataException(String message){
        super(message);
    }

    public ServiceWrongDataException(Exception e){
        super(e);
    }

    public ServiceWrongDataException(String message, Exception e){
        super(message, e);
    }
}
