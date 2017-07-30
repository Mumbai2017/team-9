package com.sumit.dell_pc.cfgfinal.fragment;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by DELL_PC on 7/20/2017.
 */

public class ListItem {

    private String id;
    private String description;
    private String phone_number;
    private String imageUrl;

    public ListItem(String id, String description, String phone_number, String imageUrl) {
        this.id = id;
        this.description = description;
        this.phone_number = phone_number;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone_number() {
        return phone_number;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
