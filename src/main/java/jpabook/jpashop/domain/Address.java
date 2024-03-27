package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable// jpa의 내장타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
        // jpa에서는 protected까지 허용해준다
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
