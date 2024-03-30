package fr.emse.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MoneyBag implements IMoney{
	private Vector<Money> fMonies = new Vector<Money>();
	MoneyBag(Money m1, Money m2) {
		appendMoney(m1); 
		appendMoney(m2);
	}
	MoneyBag(Money bag[]) {
		for (int i = 0; i < bag.length; i++)
		appendMoney(bag[i]);
	}
	public MoneyBag(List<Money> updatedMonies) {
		for (Money m : updatedMonies) {
			appendMoney(m);
	    }	
	}
	private void appendMoney(Money m) {
		if (fMonies.isEmpty()) {
			fMonies.add(m);
		} else {
			int i = 0;
			while ((i < fMonies.size())	&& (!(fMonies.get(i).currency().equals(m.currency()))))i++;
			if (i >= fMonies.size()) {
				fMonies.add(m);
			} else {
				fMonies.set(i, new Money(fMonies.get(i).amount() + m.amount(), m.currency()));
			}
		}
	}
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		MoneyBag monb = (MoneyBag) o;
        return fMonies.equals(monb.fMonies);		
	}
	@Override
	public IMoney add(IMoney m) {
		if (m instanceof Money) {
	        return addMoney((Money) m);
	    } else if (m instanceof MoneyBag) {
	        return addMoneyBag((MoneyBag) m);
	    } else {
	        throw new IllegalArgumentException("Unsupported type: " + m.getClass().getName());
	    }
	}
	@Override
    public IMoney simplify() {
        if (fMonies.size() == 1) {
            return fMonies.get(0);
        }
        return this;
    }
	@Override
	public IMoney addMoney(Money money) {
		List<Money> updatedMonies = new ArrayList<>(fMonies);
	    
	    for (Money m : fMonies) {
	        if (m.getfCurrency().equals(money.getfCurrency())) {
	            m.setfAmount(m.getfAmount() + money.getfAmount());
	            return new MoneyBag(updatedMonies);
	        }
	    }
	    
	    updatedMonies.add(money);
	    return new MoneyBag(updatedMonies);
	}
	@Override
	public IMoney addMoneyBag(MoneyBag moneyBag) {
		for (Money money : moneyBag.fMonies) {
            appendMoney(money);
        }
        return this;
	}
}
