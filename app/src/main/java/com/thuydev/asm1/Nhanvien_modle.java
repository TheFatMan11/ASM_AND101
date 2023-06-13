package com.thuydev.asm1;

import java.io.Serializable;

public class Nhanvien_modle implements Serializable {
    private String ID;
    private String hoTen;
    private String Phongban;

    public Nhanvien_modle(String ID, String hoTen, String phongban) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.Phongban = phongban;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPhongban() {
        return Phongban;
    }

    public void setPhongban(String phongban) {
        Phongban = phongban;
    }
}
