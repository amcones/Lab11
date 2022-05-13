interface Asset
{
    double getMarketValue();
    double getProfit();
}

class Cash implements Asset
{
    private double amount;

    public Cash(double amount){
        this.amount=amount;
    }

    @Override
    public double getMarketValue() {
        return amount;
    }

    @Override
    public double getProfit() {
        return 0;
    }
}

abstract class ShareAssert implements Asset
{
    private String symbol;
    private double totalCost=0;
    private double currentPrice=0;

    public String getSymbol() {
        return symbol;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    abstract void addCost(double cost);
}

class Stock extends ShareAssert {
    private int totalShares;

    public Stock(String symbol) {
        setSymbol(symbol);
    }

    // Returns the total shares purchased of this stock.
    public int getTotalShares() {  return totalShares; }
    // Records purchase of the given shares at the given price.

    @Override
    void addCost(double cost) {
        totalShares += cost/getCurrentPrice();
        setTotalCost(getTotalCost()+cost);
    }

    @Override
    public double getMarketValue() {
        return totalShares*getCurrentPrice();
    }

    @Override
    public double getProfit() {
        return getMarketValue()-getTotalCost();
    }
}
class DividendStock extends Stock{
    private double dividents;

    public DividendStock(String theSymbol) {
        super(theSymbol);
    }

    @Override
    public double getProfit()
    {
        return  getMarketValue()+dividents - getTotalCost();
    }
    public void payDividend(double amountPerShare)
    {
        dividents=getTotalShares()*amountPerShare;
    }
    @Override
    public String toString() {
        return "DividendStock [symbol=" + super.getSymbol() + ", totalShares=" + getTotalShares() + ", totalCost=" + getTotalCost() + ", dividends= "+dividents+"]";
    }
}

class MutualFund extends ShareAssert
{
    private double totalShares=0;

    public MutualFund(String symbol) {
        setSymbol(symbol);
    }

    //public double getTotalShares() {  return totalShares; }

    @Override
    void addCost(double cost) {
        totalShares += cost/getCurrentPrice();
        setTotalCost(getTotalCost()+totalShares);
    }

    @Override
    public double getMarketValue() {
        return getCurrentPrice()*totalShares;
    }

    @Override
    public double getProfit() {
        return getMarketValue() - getTotalCost();
    }
}

class Person
{
    Cash cash;
    Stock stock;
    DividendStock dividendStock;
    MutualFund mutualFund;
    double getAllMarketValue()
    {
        return cash.getMarketValue()+ stock.getMarketValue()+ dividendStock.getMarketValue()+ mutualFund.getMarketValue();
    }
    double getAllProfit()
    {
        return cash.getProfit()+ stock.getProfit()+ dividendStock.getProfit()+ mutualFund.getProfit();
    }
}
public class TestFinancial
{
    public static void main(String[] args) {
        Person person=new Person();
        person.cash=new Cash(10000);
        person.stock=new Stock("1");
        person.dividendStock=new DividendStock("2");
        person.mutualFund=new MutualFund("3");

        person.stock.setCurrentPrice(2.5);
        person.dividendStock.setCurrentPrice(4);
        person.dividendStock.payDividend(2);
        person.mutualFund.setCurrentPrice(5);

        person.stock.addCost(100);
        person.dividendStock.addCost(150);
        person.mutualFund.addCost(200);
        System.out.println("当前持有金融资产在当前市场的总的价值为"+person.getAllMarketValue());
        System.out.println("金融资产的所有的利润为"+person.getAllProfit());
    }
}