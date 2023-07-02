package ca.bytetube.communityApp.dto;

/**
 * Pack Json object, all return results use this class
 */
public class Result<T> {
    // If success tag
    private boolean success;

    // Result data if success
    private T data;

    // Error message
    private String errorMsg;

    private int errorCode;

    public Result(){};

    // Constructor for Success
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // Constructor for Error
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
