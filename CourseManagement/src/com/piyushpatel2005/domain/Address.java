package com.piyushpatel2005.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;
	private String city;
	
	@Column(name="zip_or_postcode")
	private String zipOrPostCode;
	
	public Address() {}

	public Address(String street, String city, String zipOrPostCode) {
		super();
		this.street = street;
		this.city = city;
		this.zipOrPostCode = zipOrPostCode;
	}

	public String toString() {
		return this.street + ", " + this.city + ", " + this.zipOrPostCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipOrPostCode == null) ? 0 : zipOrPostCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipOrPostCode == null) {
			if (other.zipOrPostCode != null)
				return false;
		} else if (!zipOrPostCode.equals(other.zipOrPostCode))
			return false;
		return true;
	}

}
