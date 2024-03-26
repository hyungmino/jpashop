package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)     // Joined = 정교화된 스타일, SingleTable = 한 테이블에 여러개
@DiscriminatorColumn(name = "dtype")    // 구분자로 쓰기위해 부모클래스에 부분컬럼 지정 => dtype이라는 컬럼이 생성
@Getter @Setter
public abstract class Item {
    // 구현체를 가지고 갈 것이기 때문에 추상클래스로 만듬

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
