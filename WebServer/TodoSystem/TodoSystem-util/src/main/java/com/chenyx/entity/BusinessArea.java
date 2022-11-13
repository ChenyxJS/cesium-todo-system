package com.chenyx.entity;

import lombok.Data;

@Data
public class BusinessArea {
    private String id;
    private String title;
    private TXLocation location;
    private int _distance;
    private String _dir_desc;
}
