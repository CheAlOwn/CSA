package com.chealown.csa.Entities;

import com.chealown.csa.Controllers.MainController;
import com.chealown.csa.DataBase.Models.*;

import java.util.*;

public class StaticObjects {
    private static MainController controller;

    private static Children children;
    private static EducationGroup educationGroup;
    private static EducationLevel educationLevel;
    private static Employee employee;
    private static FamilySituation familySituation;
    private static HealthGroup healthGroup;
    private static HousingRights housingRights;
    private static Interaction interaction;
    private static Organization organization;
    private static Post post;
    private static SocialMonitoring socialMonitoring;
    private static SocialPassport socialPassport;
    private static WaitingListForHousing listForHousing;
    private static Document document;
    private static TemplateDocument template;
    private static TemplateField field;

    private static User currentUser;
    private static Map<String, List<Map<String, Object>>> currentTable;

    public static Children getChildren() {
        return children;
    }

    public static void setChildren(Children children) {
        StaticObjects.children = children;
    }

    public static EducationGroup getEducationGroup() {
        return educationGroup;
    }

    public static void setEducationGroup(EducationGroup educationGroup) {
        StaticObjects.educationGroup = educationGroup;
    }

    public static EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public static void setEducationLevel(EducationLevel educationLevel) {
        StaticObjects.educationLevel = educationLevel;
    }

    public static Employee getEmployee() {
        return employee;
    }

    public static void setEmployee(Employee employee) {
        StaticObjects.employee = employee;
    }

    public static FamilySituation getFamilySituation() {
        return familySituation;
    }

    public static void setFamilySituation(FamilySituation familySituation) {
        StaticObjects.familySituation = familySituation;
    }

    public static HealthGroup getHealthGroup() {
        return healthGroup;
    }

    public static void setHealthGroup(HealthGroup healthGroup) {
        StaticObjects.healthGroup = healthGroup;
    }

    public static HousingRights getHousingRights() {
        return housingRights;
    }

    public static void setHousingRights(HousingRights housingRights) {
        StaticObjects.housingRights = housingRights;
    }

    public static Interaction getInteraction() {
        return interaction;
    }

    public static void setInteraction(Interaction interaction) {
        StaticObjects.interaction = interaction;
    }

    public static Organization getOrganization() {
        return organization;
    }

    public static void setOrganization(Organization organization) {
        StaticObjects.organization = organization;
    }

    public static Post getPost() {
        return post;
    }

    public static void setPost(Post post) {
        StaticObjects.post = post;
    }

    public static SocialMonitoring getSocialMonitoring() {
        return socialMonitoring;
    }

    public static void setSocialMonitoring(SocialMonitoring socialMonitoring) {
        StaticObjects.socialMonitoring = socialMonitoring;
    }

    public static SocialPassport getSocialPassport() {
        return socialPassport;
    }

    public static void setSocialPassport(SocialPassport socialPassport) {
        StaticObjects.socialPassport = socialPassport;
    }

    public static WaitingListForHousing getListForHousing() {
        return listForHousing;
    }

    public static void setListForHousing(WaitingListForHousing listForHousing) {
        StaticObjects.listForHousing = listForHousing;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        StaticObjects.currentUser = currentUser;
    }

    public static String getCurrentTableName() {
        Set<String> set = currentTable.keySet();
        for (String key : set) {
            return key;
        }
        return null;
    }

    public static List<Map<String, Object>> getCurrentTableData() {
        String tableName = getCurrentTableName();
        if (tableName == null || currentTable == null) {
            return null;
        }
        return currentTable.get(tableName);
    }

    public static void setCurrentTable(String table, List<Map<String, Object>> dataArray) {
        currentTable = new HashMap<>();
        currentTable.put(table, dataArray);
    }

    public static MainController getController() {
        return controller;
    }

    public static void setController(MainController controller) {
        StaticObjects.controller = controller;
    }

    public static Document getDocument() {
        return document;
    }

    public static void setDocument(Document document) {
        StaticObjects.document = document;
    }

    public static TemplateDocument getTemplate() {
        return template;
    }

    public static void setTemplate(TemplateDocument template) {
        StaticObjects.template = template;
    }

    public static TemplateField getField() {
        return field;
    }

    public static void setField(TemplateField field) {
        StaticObjects.field = field;
    }
}
