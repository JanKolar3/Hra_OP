package org.example;

public class LevelSettings {
    private int wave=2;
    private int level;
    private int max;
    private final String levelName="";
    private final String waveName="";
    private int damage=2;
    private boolean victory=false;

    public void waveSettings() {
        if (level<=1) {
            if (wave > 0) {
                wave--;
//                waveName=String.valueOf(wave);
                System.out.println("wave was " + wave);
            }
            if (wave <= 0) {
                level++;
                damage++;
                wave = 2;
                damage++;
//                levelName= String.valueOf(level);
                System.out.println("level was " + level);
            }

        }else if (level>=2) {
            victory=true;
            level=0;
            wave=5;

        }


    }

    public int enemyMax(){

        switch (wave) {
            case 4:
                max = 1;
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

    public boolean isVictory() {
        return victory;
    }

    public int getDamage() {
        return damage;
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
