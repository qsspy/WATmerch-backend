package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.model.PaginatorModel;

import java.util.List;

public interface ICRMService {

    List<Role> getRoles();
    PaginatorModel getPageIndexes(int currentPage, int lastPage);
    List<Integer> getPageSizes();
}
