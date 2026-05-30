package org.example;

public class LevelSettings {
    private int wave=11;
    private int max=0;
    private int damage=1;
    private boolean victory=false;

    public void waveSettings() {

        if (wave > 0) {
            wave--;
        }
        if (wave <= 5) {
                damage = 2;
        }
        if  (wave<=0){
            victory=true;
            wave=5;
            max=1;
        }
    }

    public int enemyMax(){
        if (max <=6) {
            max += 1;
        }
        return max;
    }
    public void reset() {
        victory=false;
        wave=11;
        max=0;
        damage=1;
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

}
