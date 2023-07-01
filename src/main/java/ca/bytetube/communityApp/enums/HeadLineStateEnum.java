package ca.bytetube.communityApp.enums;

public enum HeadLineStateEnum {
    SUCCESS(0, "Successfully Created"),
    INNER_ERROR(-1001, "Failed Operation"),
    EMPTY(-1002, "HeadLine Empty");

    private int state;

    private String stateInfo;

    private HeadLineStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static HeadLineStateEnum stateOf(int index) {
        for(HeadLineStateEnum state : values()) {
            if(state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}
