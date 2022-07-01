package ru.itis.stockmarket.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * Created by IntelliJ IDEA
 * Date: 26.05.2022
 * Time: 6:48 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

public class ToLowerCaseConverter extends StdConverter<String, String> {
    @Override
    public String convert(String s) {
        return s.toLowerCase();
    }
}
