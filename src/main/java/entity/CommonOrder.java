package entity;


import java.util.Objects;

public class CommonOrder {

    private String pair;

    private String stockExchanger;

    private Integer id;

    private String side;

    private Double price;

    private Double size;

    private String execDate;

    public Integer getId() {
        return id;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getStockExchanger() {
        return stockExchanger;
    }

    public void setStockExchanger(String stockExchanger) {
        this.stockExchanger = stockExchanger;
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

    @Override
    public String toString() {
        return "CommonOrder{" +
                "pair='" + pair + '\'' +
                ", stockExchanger='" + stockExchanger + '\'' +
                ", id=" + id +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", execDate='" + execDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonOrder that = (CommonOrder) o;
        int idN = this.id;
        int thatId = that.id;
        return idN == thatId && that.stockExchanger.equals(this.stockExchanger);
    }

    @Override
    public int hashCode() {

        return this.id;
    }
}
