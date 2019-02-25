package com.example.user.attendancemanagement;

public class UserModel {

    boolean isSelected;
    String rollNum;

    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public UserModel(boolean isSelected, String rollNum) {
        this.isSelected = isSelected;
        this.rollNum = rollNum;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }


}