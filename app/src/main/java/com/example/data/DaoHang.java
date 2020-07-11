package com.example.data;



public class DaoHang {
    public int location(int px,int py){
        if(px>=0&&px<=6&&py>=0&&py<=6){
            return 6;
        }else if(px>=0&&px<=3&&py>6&&py<=12){
            return 3;
        }else if(px>3&&px<=7&&py>=6&&py<=11){
            return 4;
        }else if(px>6&&px<=10&&py>=0&&py<=3){
            return 7;
        }else if(px>=8&&px<=13&&py>=13&&py<=17){
            return 2;
        }else if(px>=8&&px<=13&&py>=7&&py<13){
            return 5;
        }else if(px>=8&&px<=13&&py>=0&&py<=5){
            return 8;
        }else if(px>=5&&px<8&&py>=13&&py<=17){
            return 1;
        }else return 0;
    }
    public  String dh(int i, int j){
        String s[][]=new String[9][9];
        s[0][1]="0 5 2 1";
        s[0][2]="0 5 2";
        s[0][3]="0 9 3";
        s[0][4]="0 4";
        s[0][5]="0 5";
        s[0][6]="0 9 5";
        s[0][7]="0 7";
        s[0][8]="0 8";

        s[1][2]="1 2";         s[2][1]="2 1";        s[3][1]="3 9 0 5 2 1";s[4][1]="4 0 5 2 1";
        s[1][3]="1 2 5 0 9 3"; s[2][3]="2 5 0 9 3";  s[3][2]="3 9 0 5 2";  s[4][2]="4 0 5 2";
        s[1][4]="1 2 5 0 4";   s[2][4]="2 5 0 4";    s[3][4]="3 9 0 4";    s[4][3]="4 0 9 3";
        s[1][5]="1 2 5";       s[2][5]="2 5";        s[3][5]="3 9 0 5";    s[4][5]="4 0 5";
        s[1][6]="1 2 5 0 9 6"; s[2][6]="2 5 0 9 6";  s[3][6]="3 9 6";      s[4][6]="4 0 9 6";
        s[1][7]="1 2 5 0 7";   s[2][7]="2 5 7";      s[3][7]="3 9 0 7";    s[4][7]="4 0 7";
        s[1][8]="1 2 5 8";     s[2][8]="2 5 8";      s[3][8]="3 9 0 8";    s[4][8]="4 0 8";

        s[5][2]="5 2";         s[6][1]="6 9 0 5 2 1";s[7][1]="7 0 5 2 1";  s[8][1]="8 5 2 1";
        s[5][3]="5 0 9 3";     s[6][3]="6 9 3";      s[7][2]="7 0 5 2";    s[8][2]="8 5 2";
        s[5][4]="5 0 4";       s[6][4]="6 9 0 4";    s[7][4]="7 0 4";      s[8][3]="8 0 9 3";
        s[5][1]="5 2 1";       s[6][5]="6 9 0 5";    s[7][5]="7 0 5";      s[8][5]="8 5";
        s[5][6]="5 0 9 6";     s[6][2]="6 9 0 5 2";  s[7][6]="7 0 9 6";    s[8][6]="8 0 9 6";
        s[5][7]="5 0 7";       s[6][7]="6 9 0 7";    s[7][3]="7 0 9 3";    s[8][7]="8 0 7";
        s[5][8]="5 8";         s[6][8]="6 9 0 8";    s[7][8]="7 0 8";      s[8][4]="8 0 4";

        String ss[]=s[i][j].split(" ");
        String result="从";
        switch (Integer.parseInt(ss[0])){
            case 1:
                result=result+"厨房";
                break;
            case 2:
                result=result+"饭厅";
                break;
            case 3:
                result=result+"左上卧室";
                break;
            case 4:
                result=result+"右上卧室";
                break;
            case 5:
                result=result+"客厅";
                break;
            case 6:
                result=result+"左下卧室";
                break;
            case 7:
                result=result+"右下卧室";
                break;
            case 8:
                result=result+"右下角阳台";
                break;
            case 0:
                result=result+"走廊";
                break;
        }

        for(int n=1;n<ss.length;n++){
            switch (Integer.parseInt(ss[n])){
                case 1:
                    result=result+"-->厨房";
                    break;
                case 2:
                    result=result+"-->饭厅";
                    break;
                case 3:
                    result=result+"-->左上卧室";
                    break;
                case 4:
                    result=result+"-->右上卧室";
                    break;
                case 5:
                    result=result+"-->客厅";
                    break;
                case 6:
                    result=result+"-->左下卧室";
                    break;
                case 7:
                    result=result+"-->右下卧室";
                    break;
                case 8:
                    result=result+"-->右下角阳台";
                    break;
                case 9:
                    result=result+"-->走廊西";
                    break;
                case 0:
                    result=result+"-->走廊东";
                    break;
            }
        }
        return result;
    }
}
