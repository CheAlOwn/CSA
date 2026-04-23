package com.chealown.csa.Controllers;

public interface FiltersController {
    String[] getFilterDataList();
    String[] getStartDateList();
    String[] getEndDateList();
    void clearFilters();
}
