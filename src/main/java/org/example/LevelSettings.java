package org.example;

public class LevelSettings {
    private int wave=6;
    private int level=1;
    private int max;
    private int damage=1;
    private boolean victory=false;

    public void waveSettings() {
        if (level<=1) {
            if (wave > 0) {
                wave--;
            }
            if (wave <= 0) {
                level++;
                wave = 5;
                if (level==1) {
                    damage = 2;
                }
            }

        }
        if (level>=3) {
            victory=true;
            level=0;
            wave=5;
            max=1;

        }
    }

    public int enemyMax(){

        switch (wave) {
            case 5:

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
    public void reset() {
        victory=false;
        wave=5;
        max=1;
        level=0;
        damage=1;
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
