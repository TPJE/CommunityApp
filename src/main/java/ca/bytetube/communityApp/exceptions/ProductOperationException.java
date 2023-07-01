package ca.bytetube.communityApp.exceptions;

public class ProductOperationException extends RuntimeException{
    public static final long serialVersionUID = 5076172298827469013L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
