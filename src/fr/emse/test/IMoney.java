package fr.emse.test;

public interface IMoney {
	public IMoney add(IMoney aMoney);

	public IMoney addMoney(Money money);

	public IMoney addMoneyBag(MoneyBag moneyBag);

	IMoney simplify();
}
