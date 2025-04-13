package com.cafemate.Cafe.Managment.System.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemsDTO {
    private Long cartItemId;
    private Long userId;
}
