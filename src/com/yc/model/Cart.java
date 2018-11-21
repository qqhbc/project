package com.yc.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<CartItem> list = new LinkedList();

	public List<CartItem> getList() {
		return this.list;
	}

	public void setList(List<CartItem> list) {
		this.list = list;
	}

	public void add(CartItem cartItem) {
		for (CartItem item : this.list) {
			if (item.getProductId() == cartItem.getProductId()) {
				item.setCount(item.getCount() + 1);

				return;
			}
		}
		this.list.add(cartItem);
	}

	public boolean remove(CartItem cartItem) {
		return this.list.remove(cartItem);
	}

	public double getTotalPrice() {
		double total = 0.0D;
		for (CartItem item : this.list) {
			total += item.getTotalPrice();
		}
		return total;
	}
}