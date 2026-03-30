package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class SpriteLoader {

//    public static Image load(String path) {
//        try {
//            return ImageIO.read(Objects.requireNonNull(SpriteLoader.class.getResource(path)));
//        } catch (IOException e) {
//            throw new RuntimeException("Nelze načíst obrázek: " + path, e);
//        }
//    }

    /**
     * Vyřízne a vrátí konkrétní snímek ze sprite sheetu (řady obrázků vedle sebe).
     *
     * @param path        Cesta ke sprite sheetu v resources
     * @param frameWidth  Šířka jednoho snímku (px)
     * @param frameHeight Výška jednoho snímku (px)
     * @param frameCount  Počet snímků v jednom řádku
     * @param index       Index snímku (0 = první)
     * @return Obrázek (Image) obsahující vyříznutý snímek
     */
    public static Image getFrame(String path, int frameWidth, int frameHeight, int frameCount, int index) {
        try {
            BufferedImage sheet = ImageIO.read(
                    Objects.requireNonNull(SpriteLoader.class.getResource(path))
            );

            if (index < 0 || index >= frameCount)
                throw new IllegalArgumentException("Index snímku mimo rozsah: " + index);

            int x = index * frameWidth;
            int y = 0;

            return sheet.getSubimage(x, y, frameWidth, frameHeight);

        } catch (IOException e) {
            throw new RuntimeException("Nelze načíst spritesheet: " + path, e);
        }
    }

    /**
     * Načte celý sprite sheet a vrátí pole všech snímků v jednom řádku.
     *
     * @param path        Cesta ke sprite sheetu
     * @param frameWidth  Šířka jednoho snímku
     * @param frameHeight Výška jednoho snímku
     * @param frameCount  Počet snímků v jednom řádku
     * @return Pole Image[] obsahující jednotlivé snímky
     */
    public static Image[] getFrames(String path, int frameWidth, int frameHeight, int frameCount) {
        try {
            // Načteme celý sprite sheet jen jednou
            BufferedImage sheet = ImageIO.read(
                    Objects.requireNonNull(SpriteLoader.class.getResource(path))
            );

            Image[] frames = new Image[frameCount];

            // Vyřízneme postupně všechny snímky
            for (int i = 0; i < frameCount; i++) {
                int x = i * frameWidth;
                frames[i] = sheet.getSubimage(x, 0, frameWidth, frameHeight);
            }

            return frames;

        } catch (IOException e) {
            throw new RuntimeException("Nelze načíst spritesheet: " + path, e);
        }
    }
}
