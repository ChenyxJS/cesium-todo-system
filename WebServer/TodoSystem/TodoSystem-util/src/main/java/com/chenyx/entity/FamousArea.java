package com.chenyx.entity;

import lombok.Data;

@Data
public class FamousArea {

    private String id;
    private String title;
    private TXLocation location;
    private int _distance;
    private String _dir_desc;

}
