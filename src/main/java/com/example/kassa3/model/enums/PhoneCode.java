package com.example.kassa3.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
public enum PhoneCode {
    MEGA999("MegaCom", "999"),
    MEGA998("MegaCom", "998"),
    MEGA997("MegaCom", "997"),
    MEGA995("MegaCom", "995"),
    MEGA990("MegaCom", "990"),
    MEGA755("MegaCom", "755"),
    MEGA559("MegaCom", "559"),
    MEGA558("MegaCom", "558"),
    MEGA557("MegaCom", "557"),
    MEGA556("MegaCom", "556"),
    MEGA555("MegaCom", "555"),
    MEGA554("MegaCom", "554"),
    MEGA553("MegaCom", "553"),
    MEGA552("MegaCom", "552"),
    MEGA551("MegaCom", "551"),
    MEGA550("MegaCom", "550"),

    BEELINE779("Beeline", "779"),
    BEELINE778("Beeline", "778"),
    BEELINE777("Beeline", "777"),
    BEELINE776("Beeline", "776"),
    BEELINE775("Beeline", "775"),
    BEELINE774("Beeline", "774"),
    BEELINE773("Beeline", "773"),
    BEELINE772("Beeline", "772"),
    BEELINE771("Beeline", "771"),
    BEELINE770("Beeline", "770"),
    BEELINE227("Beeline", "227"),
    BEELINE225("Beeline", "225"),
    BEELINE224("Beeline", "224"),
    BEELINE223("Beeline", "223"),
    BEELINE222("Beeline", "222"),
    BEELINE221("Beeline", "221"),
    BEELINE220("Beeline", "220"),

    NUR709("O!", "709"),
    NUR708("O!", "708"),
    NUR707("O!", "707"),
    NUR706("O!", "706"),
    NUR705("O!", "705"),
    NUR704("O!", "704"),
    NUR703("O!", "703"),
    NUR702("O!", "702"),
    NUR701("O!", "701"),
    NUR700("O!", "700"),
    NUR509("O!", "509"),
    NUR508("O!", "508"),
    NUR507("O!", "507"),
    NUR506("O!", "506"),
    NUR505("O!", "505"),
    NUR504("O!", "504"),
    NUR503("O!", "503"),
    NUR502("O!", "502"),
    NUR501("O!", "501"),
    NUR500("O!", "500");

    @ToString.Exclude
    private final String operatorName;
    private final String code;

    public static Optional<PhoneCode> getPhoneCodeByCode(String code) {
        return Arrays.stream(PhoneCode.values())
                .filter(phoneCode -> phoneCode.code.equals(code))
                .findFirst();
    }
}