package com.chealown.csa.Entities;

import com.chealown.csa.UI.Controllers.MainController;
import com.chealown.csa.DataBase.Models.*;
import javafx.scene.control.Hyperlink;

import java.util.*;

public class StaticObjects {
    private static MainController controller;
    private static Hyperlink lastPage;
    private static List<Object> listTableData;
    private static List<Map<String, Object>> mapTableData;
    private static Object selectedObject;
    private static boolean firstRun = true;

    private static User currentUser;

    public static Object getSelectedObject() {
        return selectedObject;
    }

    public static void setSelectedObject(Object selectedObject) {
        StaticObjects.selectedObject = selectedObject;
    }

    public static Hyperlink getLastPage() {
        return lastPage;
    }

    public static void setLastPage(Hyperlink lastPage) {
        StaticObjects.lastPage = lastPage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        StaticObjects.currentUser = currentUser;
    }

    public static MainController getController() {
        return controller;
    }

    public static void setController(MainController controller) {
        StaticObjects.controller = controller;
    }

    public static List<Object> getListTableData() {
        return listTableData;
    }

    public static void setListTableData(List<Object> listTableData) {
        StaticObjects.listTableData = listTableData;
    }

    public static List<Map<String, Object>> getMapTableData() {
        return mapTableData;
    }

    public static void setMapTableData(List<Map<String, Object>> mapTableData) {
        StaticObjects.mapTableData = mapTableData;
    }

    public static boolean isFirstRun() {
        return firstRun;
    }

    public static void setFirstRun(boolean firstRun) {
        StaticObjects.firstRun = firstRun;
    }
}
