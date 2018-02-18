package coincheck;

public class Data {
    private String amount;

    private String id;

    private String rate;

    private String pair;

    private String created_at;

    private String order_type;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    @Override
    public String toString() {
        return "ClassPojo [amount = " + amount + ", id = " + id + ", rate = " + rate + ", pair = " + pair + ", created_at = " + created_at + ", order_type = " + order_type + "]";
    }
}
			
			