package ca.bytetube.communityApp.enums;

public enum ProductCategoryStateEnum {
    SUCCESS(0, "Created Successfully"),
    INNER_ERROR(-1001, "Operation Failed"),
    EMPTY_LIST(-1002, "Product Category List is Empty");

    private int state;

    private String stateInfo;

    private ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductCategoryStateEnum stateOf(int index) {
        for(ProductCategoryStateEnum state : values()) {
            if(state.getState() == index) return state;
        }
        return null;
    }
}
