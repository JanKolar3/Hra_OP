package org.example;

public class LevelSettings {
    private int wave=5;
    private int level=0;
    private int max;
    private String levelName;
    private String waveName;
    private int damage=1;
    private boolean victory=false;

    public void waveSettings() {
        if (level<=1) {
            if (wave > 0) {
                wave--;
                waveName=String.valueOf("wave: "+wave);
                System.out.println("wave was " + wave);
            }
            if (wave <= 0) {
                level++;
                wave = 4;
                if (level==1) {
                    damage = 2;
                }
                levelName= String.valueOf(level);
                System.out.println("level was " + level);
            }

        }
        if (level>=2) {
            victory=true;
            level=0;
            wave=5;
            max=1;

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

    public void setVictory(boolean victory) {
        this.victory = victory;
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
