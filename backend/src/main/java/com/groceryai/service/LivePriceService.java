package com.groceryai.service;

import com.groceryai.dto.LivePriceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivePriceService {

    public List<LivePriceResponse> getLivePrices(
            String productName
    ) {

        return List.of(

                LivePriceResponse.builder()
                        .storeName("Blinkit")
                        .price(312.0)
                        .build(),

                LivePriceResponse.builder()
                        .storeName("Zepto")
                        .price(305.0)
                        .build(),

                LivePriceResponse.builder()
                        .storeName("Instamart")
                        .price(309.0)
                        .build(),

                LivePriceResponse.builder()
                        .storeName("BigBasket")
                        .price(315.0)
                        .build()
        );
    }
}