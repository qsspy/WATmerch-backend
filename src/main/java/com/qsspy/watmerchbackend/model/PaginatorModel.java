package com.qsspy.watmerchbackend.model;

import lombok.Data;

import java.util.List;

@Data
public class PaginatorModel {

    private List<Integer> pages;
    private boolean showFirstPage = true;
    private boolean showLastPage = true;
    private int lastPage;
}
