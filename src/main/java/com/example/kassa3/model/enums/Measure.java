package com.example.kassa3.model.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Measure {

    MILLIMETER("Millimeter", "Миллиметр", "MMT", "ММ", "003"),
    CENTIMETER("Centimeter", "Сантиметр", "CMT", "СМ", "004"),
    DECIMETER("Decimeter", "Дециметр", "DMT", "ДМ", "005"),
    METER("Meter", "Метр", "MTR", "М", "006"),
    LINEAR_METER("Linear_meter", "Погонный_метр", "POG_M", "ПОГ_М", "018"),
    SQUARE_METER("Square_meter", "Квадратный_метр", "MTK", "М2", "055"),
    CUBIC_METER("Cubic_meter", "Кубический_метр", "MTQ", "М3", "113"),
    MILLIGRAM("Milligram", "Миллиграмм", "MGM", "МГ", "161"),
    METRIC_GRAM("Metric_Gram", "Метрический_карат", "CTM", "КАР", "162"),
    GRAM("Gram", "Грамм", "GRM", "Г", "163"),
    KILOGRAM("Kilogram", "Килограмм", "KGM", "КГ", "166"),
    TON("Ton", "Тонна", "TNE", "Т", "168"),
    LEAF("Leaf", "Лист", "LEF", "ЛИСТ", "625"),
    DOSE("Dose", "Доза", "DOS", "ДОЗ", "639"),
    THOUSAND_DOSE("Thousand_dose", "Тысяча_доз", "THD", "ТЫС_ДОЗ", "640"),
    UNIT("Unit", "Единица", "UNI", "ЕД", "642"),
    THOUSAND_UNIT("Thousand_unit", "Тысяча_Единиц", "THU", "ТЫС_ЕД", "643"),
    PRODUCT("Product", "Изделие", "NAR", "ИЗД", "657"),
    PAIR("Pair", "Пара", "NPR", "ПАР", "715"),
    TEN_PAIR("Ten_pair", "Десять_пар", "TNP", "ДЕС_ПАР", "732"),
    PACK("Pack", "Пачка", "PAC", "ПАЧ", "728"),
    THOUSAND_PACK("Thousand_pack", "Тысяча_Пачек", "THP", "ТЫС_ПАЧ", "729"),
    ROLL("Roll", "Рулон", "NPL", "РУЛ", "736"),
    PACKAGE("Package", "Упаковка", "NMP", "УПАК", "778"),
    HUNDRED_PACKS("Hundred_packs", "Сто_упаковок", "HNM", "СТО_УПАК", "781"),
    THOUSAND_PACKS("Thousand_packs", "Тысяча_упаковок", "THN", "ТЫС_УПАК", "782"),
    PIECE("Piece", "Штука", "PCE", "ШТ", "796"),
    HUNDRED_PIECES("Hundred_pieces", "Сто_Штука", "HPC", "СТО_ШТ", "797"),
    THOUSAND_PIECES("Thousand_pieces", "Штука", "TPC", "ТЫС_ШТ", "798"),
    DRAWER("Drawer", "Ящик", "DRW", "ЯЩ", "812"),
    SET("Set", "Комплект", "SET", "КОМПЛ", "839"),
    BOTTLE("Bottle", "Бутылка", "BTL", "БУТ", "868"),
    AMPOULE("Ampoule", "Ампула", "AMP", "АМПУЛ", "870"),
    FLACON("Flacon", "Флакон", "FLC", "ФЛАК", "872"),
    BOX("Box", "Коробка", "BOX", "КОР", "875"),
    DOCUMENT("document", "документ", "DOC", "ДОК", "9246");

    private final String engName;
    private final String rusName;
    private final String engShortName;
    private final String rusShortName;
    private final String code;

    public String getEngShortName() {
        return engShortName;
    }

    public String getRusShortName() {
        return rusShortName;
    }

    public String getCode() {
        return code;
    }

    public String getEngName() {
        return engName;
    }

    public String getRusName() {
        return rusName;
    }

    // Reverse lookup methods
    public static Optional<Measure> getMeasureByName(String name) {
        return Arrays.stream(Measure.values())
                .filter(measure -> measure.engName.equals(name)
                        || measure.rusName.equals(name)
                        || measure.rusShortName.equals(name)
                        || measure.engShortName.equals(name))
                .findFirst();
    }
}