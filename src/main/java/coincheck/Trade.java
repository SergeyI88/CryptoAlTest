package coincheck;

public class Trade
{
    private Data[] data;

    private Pagination pagination;

    private String success;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public Pagination getPagination ()
    {
        return pagination;
    }

    public void setPagination (Pagination pagination)
    {
        this.pagination = pagination;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", pagination = "+pagination+", success = "+success+"]";
    }
}
			
			