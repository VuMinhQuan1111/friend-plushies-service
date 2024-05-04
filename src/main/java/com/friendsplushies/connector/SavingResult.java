package com.friendsplushies.connector;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SavingResult {

    public enum SavingType {
        LOCAL("local"),
        AWS("aws");

        String value;

        SavingType(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    private SavingType type;
    private String fileLocation;
    private String filePath;
}
