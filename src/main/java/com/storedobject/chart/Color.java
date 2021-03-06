/*
 *  Copyright 2019-2020 Syam Pillai
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.storedobject.chart;

/**
 * Representation of color.
 *
 * @author Syam
 */
public class Color extends AbstractColor {

    public static final Color TRANSPARENT = new SpecialColor("transparent");

    private int red, green, blue, alpha = Integer.MAX_VALUE;

    public Color(int red, int green, int blue) {
        this(red, green, blue, Integer.MAX_VALUE);
    }

    public Color(int red, int green, int blue, int alpha) {
        set(red, green, blue, alpha);
    }

    public void set(int red, int green, int blue) {
        set(red, green, blue, Integer.MAX_VALUE);
    }

    public void set(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        validate();
    }

    public Color(String hexValue) {
        set(hexValue);
    }

    public void set(String hexValue) {
        if(hexValue == null) {
            return;
        }
        if(hexValue.startsWith("#")) {
            hexValue = hexValue.substring(1);
        }
        hexValue = hexValue.toUpperCase();
        if(hexValue.length() > 8) {
            hexValue = hexValue.substring(0, 8);
        }
        if(!isHex(hexValue)) {
            return;
        }
        if(hexValue.length() > 6) {
            alpha = value(hexValue.substring(6));
            hexValue = hexValue.substring(0, 6);
        }
        if(hexValue.length() <= 2) {
            red = value(hexValue);
            green = blue = 0;
            return;
        }
        red = value(hexValue.substring(0, 2));
        hexValue = hexValue.substring(2);
        if(hexValue.length() <= 2) {
            green = value(hexValue);
            blue = 0;
            return;
        }
        green = value(hexValue.substring(0, 2));
        blue = value(hexValue.substring(2));
    }

    private static int value(String hex) {
        return Integer.parseInt(hex, 16);
    }

    private static boolean isHex(String s) {
        return s.chars().allMatch(Color::isHex);
    }

    private static boolean isHex(int c) {
        return ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F'));
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        validate();
    }

    public void setRed(int red) {
        this.red = red;
        validate();
    }

    public void setGreen(int green) {
        this.green = green;
        validate();
    }

    public void setBlue(int blue) {
        this.blue = blue;
        validate();
    }

    private void validate() {
        red = Math.max(0, Math.min(red, 0xFF));
        green = Math.max(0, Math.min(green, 0xFF));
        blue = Math.max(0, Math.min(blue, 0xFF));
        if(alpha != Integer.MAX_VALUE) {
            alpha = Math.max(0, Math.min(alpha, 100));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"rgb");
        if(alpha != Integer.MAX_VALUE) {
            sb.append('a');
        }
        sb.append('(').append(red).append(',').append(green).append(',').append(blue);
        if(alpha != Integer.MAX_VALUE) {
            sb.append(',').append(alpha / 100.0);
        }
        sb.append(")\"");
        return sb.toString();
    }

    private static class SpecialColor extends Color {

        private final String color;

        private SpecialColor(String color) {
            super(0, 0,0);
            this.color = color;
        }

        @Override
        public String toString() {
            return "\"" + color + "\"";
        }
    }
}
