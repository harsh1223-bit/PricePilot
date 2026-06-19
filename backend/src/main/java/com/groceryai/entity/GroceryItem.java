package com.groceryai.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grocery_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double quantity;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "grocery_list_id")
    @JsonBackReference
    private GroceryList groceryList;
}