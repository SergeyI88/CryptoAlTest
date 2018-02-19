package api.coincheck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("starting_after")
    @Expose
    private Object startingAfter;
    @SerializedName("ending_before")
    @Expose
    private Object endingBefore;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Object getStartingAfter() {
        return startingAfter;
    }

    public void setStartingAfter(Object startingAfter) {
        this.startingAfter = startingAfter;
    }

    public Object getEndingBefore() {
        return endingBefore;
    }

    public void setEndingBefore(Object endingBefore) {
        this.endingBefore = endingBefore;
    }

}