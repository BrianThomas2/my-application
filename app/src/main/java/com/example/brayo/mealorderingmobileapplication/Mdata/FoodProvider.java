package com.example.brayo.mealorderingmobileapplication.Mdata;



/**
 * Created by Brayo on 27/04/2018.
 */

public class FoodProvider {
    private String desc;
    private String quantity;
    private String totalprice;




    public FoodProvider(String desc, String quantity, String totalprice) {
        this.desc = desc;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
