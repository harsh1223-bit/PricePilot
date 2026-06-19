package com.groceryai.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grocery_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "groceryList",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonManagedReference           
    @Builder.Default
    private List<GroceryItem> items = new ArrayList<>();

   
}