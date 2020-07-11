package com.example.data;

public class MyData {
    private static int id = 0;
    private static int biaohao = -1;
    private static int dengluzhuangtai =0;


    public static int getId() {
        return id;
    }
    public static void setId(int id) {
        MyData.id = id;
    }
    public static int getBiaohao() {
        return biaohao;
    }
    public static void setBiaohao(int biaohao) {
        MyData.biaohao = biaohao;
    }

    public static int getDengluzhuangtai() {
        return dengluzhuangtai;
    }

    public static void setDengluzhuangtai(int dengluzhuangtai) {
        MyData.dengluzhuangtai = dengluzhuangtai;
    }
}
