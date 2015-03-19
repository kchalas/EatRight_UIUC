package com.example.kchal_000.eatright_uiuc;

import information.RestaurantInfo;

/**
 * Created by kchal_000 on 3/19/2015.
 */
public class MenuDataProvider {
    private RestaurantInfo rest;
    private String[] categs = {"Over 750 cal", "500 to 750 cal", "250 to 500 cal", "250 or less cal"};

    public MenuDataProvider(RestaurantInfo rI){
        this.rest = rI;
    }


}
