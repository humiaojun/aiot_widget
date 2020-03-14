package edu.ecjtu.aiot_widget;

import android.widget.Button;
import android.widget.DatePicker;

import java.util.Comparator;
import java.util.Date;

public class ContactInfo implements Comparable<ContactInfo> {

    private String name;
    private String tele;
//    private int pos;
//
//    public void setPos(int pos) {
//        this.pos = pos;
//    }
//
//    public int getPos() {
//        return pos;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public int compareTo(ContactInfo contactInfo){
        return this.tele.compareTo(contactInfo.tele);
    }
}
