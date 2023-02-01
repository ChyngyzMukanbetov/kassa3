package com.example.kassa3.model.enums;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Color {

    YELLOW("yellow", "жёлтый"),
    GREEN("green", "зелёный"),
    BLUE("blue", "синий"),
    WHITEBLUE("whiteblue", "голубой"),
    BROWN("brown", "коричневый"),
    WHITE("white", "белый"),
    KHAKI("khaki", "хаки"),
    RED("red", "красный"),
    ORANGE("orange", "оранжевый"),
    PINK("pink", "розовый"),
    GRAY("gray", "серый"),
    BLACK("black", "чёрный"),
    PURPLE("purple", "фиолетовый"),
    SILVER("silver", "серебристый"),
    GOLDEN("golden", "золотистый"),
    BEIGE("beige", "бежевый"),
    EMERALD("emerald", "изумрудный"),
    CORAL("coral", "коралловый"),
    COOPER("copper", "медный"),
    OLIVE("olive", "оливковый"),
    LILAC("lilac", "сиреневый");

    private final String engName;
    private final String rusName;

    public String getEngName() {
        return engName;
    }

    public String getRusName() {
        return rusName;
    }

    // Reverse lookup methods
    public static Optional<Color> getColorByName(String name) {
        return Arrays.stream(Color.values())
                .filter(color -> color.engName.equals(name)
                        || color.rusName.equals(name))
                .findFirst();
    }
}