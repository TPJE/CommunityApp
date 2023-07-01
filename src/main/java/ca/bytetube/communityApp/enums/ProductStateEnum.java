package ca.bytetube.communityApp.enums;

public enum ProductStateEnum {
    OFFLINE(-1, "Illegal Product"),
    DOWN(0, "Not Availabel"),
    EMPTY(-1002, "Product Empty"),
    SUCCESS(1, "Operation Success"),
    INNER_ERROR(-1001, "Operation Failed");

    private int state;

    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductStateEnum stateOf(int index) {
        for(ProductStateEnum state : values()) {
            if(state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}
