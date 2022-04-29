package com.cinco;

import java.util.Map;
//The Mapholder class is a container to transfer data to the InvoiceReport class
public class MapHolder {
	
	public MapHolder(Map<String, Person> personMap, Map<String, Customer> customerMap, Map<String, Product> productMap) {
		PersonMap = personMap;
		CustomerMap = customerMap;
		ProductMap = productMap;
	}
    private Map<String, Person> PersonMap;
    private Map<String, Customer> CustomerMap;
    private Map<String, Product> ProductMap;
    
	public Map<String, Person> getPersonMap() {
		return PersonMap;
	}
	public Map<String, Customer> getCustomerMap() {
		return CustomerMap;
	}
	public Map<String, Product> getProductMap() {
		return ProductMap;
	}
	public Person getPersonFromMap(String str) {
		return PersonMap.get(str);
	}
	public Customer getCustomerFromMap(String str) {
		return CustomerMap.get(str);
	}
	public Product getProductFromMap(String str) {
		return ProductMap.get(str);
	}
}