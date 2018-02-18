package gdax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trade {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("trade_id")
    @Expose
    private Integer tradeId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("side")
    @Expose
    private String side;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "time='" + time + '\'' +
                ", tradeId=" + tradeId +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}