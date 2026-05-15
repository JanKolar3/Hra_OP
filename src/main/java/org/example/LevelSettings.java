package org.example;

public class LevelSettings {
    private int wave=5;
    private int level;
    private int max;
    private String levelName="";
    private String waveName="";
    ProjectileSettings projectileS;
//    private int pocet;

//    private boolean konecWave;
//    private int max=1;




//    public LevelSettings() {
//        this.pocet=pocet;



//    }


//    public void nextLevelSettings() {
//        if (level==0){
//            if(konecWave){
//                if(pocet<=0) {
//                    level++;
//                    wave=5;
//                    konecWave=false;
//                }
//
//            }
//        }
//
//    }

    public void waveSettings() {


            if (wave>0) {
                wave--;
                waveName=String.valueOf(wave);
                System.out.println("wave was "+wave);
            }
            if (wave<=0) {
                level++;
                wave=5;
                levelName= String.valueOf(level);
                System.out.println("level was "+level);
            }
//        levelName= String.valueOf(level);
//        waveName= String.valueOf(wave);

    }

    public int enemyMax(){

        switch (wave) {
            case 4:
                max=1;

                break;
            case 3:
                max=2;
                break;
            case 2:
                max=3;
                break;
            case 1:
                max=4;
                break;
            case 0:
                max=5;
                break;
            }

    return max;
    }


    public int getMax() {
        return max;
    }

    public int getWave() {
        return wave;
    }

    public int getLevel() {
        return level;
    }
}
