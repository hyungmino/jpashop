package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    // 객체는 다 컬렉션, 컬렉션이 있어서 다대다 관계가 가능한데 관계형 DB는 컬렉션관계를 양쪽에 다 가질수 없기 때문에
    // 일대다, 다대일로 풀어내는 중간 테이블이 필요
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    private Category parent;        // 내 부모가 내 타입이니까 넣어줌
}
