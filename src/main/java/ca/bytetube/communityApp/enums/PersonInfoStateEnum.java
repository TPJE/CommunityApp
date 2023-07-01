package ca.bytetube.communityApp.enums;

public enum PersonInfoStateEnum {
    SUCCESS(0, "Successfully Created"),
    INNER_ERROR(-1001, "Operation Failed"),
    EMPTY(-1002, "PersonInfo Empty");

    private int state;

    private String stateInfo;

    private PersonInfoStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static PersonInfoStateEnum stateOf(int index){
        for(PersonInfoStateEnum state : values()) {
            if(state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}
