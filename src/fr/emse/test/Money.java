package fr.emse.test;

public class Money implements IMoney{
	private int fAmount;
	private String fCurrency;
	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
	}
	public int amount() {
		return fAmount;
	}
	public String currency() {
		return fCurrency;
	}
	
	public int getfAmount() {
		return fAmount;
	}
	public void setfAmount(int fAmount) {
		this.fAmount = fAmount;
	}
	public String getfCurrency() {
		return fCurrency;
	}
	public void setfCurrency(String fCurrency) {
		this.fCurrency = fCurrency;
	}
	public IMoney add(Money m) {
		if (m.currency().equals(currency())) {
	        return new Money(amount() + m.amount(), currency());
	    } else {
	        return new MoneyBag(this, m);
	    }
	}
	public boolean equals(Object o) {
	    if (o == null || getClass() != o.getClass()) return false;
	    Money money = (Money) o;
	    return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
	}
	@Override
	public IMoney add(IMoney m) {
		return m.addMoney(this);
	}
	@Override
	public IMoney addMoney(Money money) {
		return add(money);
	}
	@Override
	public IMoney addMoneyBag(MoneyBag moneyBag) {
		return null;
	}
	@Override
	public IMoney simplify() {
		// TODO Auto-generated method stub
		return null;
	}

}
