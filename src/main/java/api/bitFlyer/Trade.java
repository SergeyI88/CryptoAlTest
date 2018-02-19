package api.bitFlyer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trade {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("exec_date")
    @Expose
    private String execDate;
    @SerializedName("buy_child_order_acceptance_id")
    @Expose
    private String buyChildOrderAcceptanceId;
    @SerializedName("sell_child_order_acceptance_id")
    @Expose
    private String sellChildOrderAcceptanceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getExecDate() {
        return execDate;
    }

    public void setExecDate(String execDate) {
        this.execDate = execDate;
    }

    public String getBuyChildOrderAcceptanceId() {
        return buyChildOrderAcceptanceId;
    }

    public void setBuyChildOrderAcceptanceId(String buyChildOrderAcceptanceId) {
        this.buyChildOrderAcceptanceId = buyChildOrderAcceptanceId;
    }

    public String getSellChildOrderAcceptanceId() {
        return sellChildOrderAcceptanceId;
    }

    public void setSellChildOrderAcceptanceId(String sellChildOrderAcceptanceId) {
        this.sellChildOrderAcceptanceId = sellChildOrderAcceptanceId;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", execDate='" + execDate + '\'' +
                ", buyChildOrderAcceptanceId='" + buyChildOrderAcceptanceId + '\'' +
                ", sellChildOrderAcceptanceId='" + sellChildOrderAcceptanceId + '\'' +
                '}';
    }
}