package me.faintcloudy.rationalnumber;

import java.util.*;

public class Ratio extends Number {
    private String value = "0.0";
    public Ratio(String value)
    {
        this.value = value;
    }
    public Ratio()
    {

    }



    public static Ratio addWithNonNegative(String v0, @NotNegative String value)
    {
        if (!v0.contains("."))
            v0 = v0 + ".0";
        if (!value.contains("."))
            value = value + ".0";
        String integerComplete = "";
        String decimalComplete = "";
        {
            String integerToAdd = getInteger(value);
            String integerCached = getInteger(v0);
            if (integerToAdd.length() != integerCached.length()) {
                if (integerToAdd.length() > integerCached.length()) {
                    int fs = integerToAdd.length() - integerCached.length();
                    for (int i = 0; i < fs; i++) {
                        integerCached = "0" + integerCached;
                    }
                } else {
                    int fs = integerCached.length() - integerToAdd.length();
                    for (int i = 0; i < fs; i++) {
                        integerToAdd = "0" + integerToAdd;
                    }
                }
            }
            char[] chars = new char[integerCached.length()];
            int carry = -1;
            for (int i = 0; i < integerCached.length(); i++) {
                String s = String.valueOf(Integer.parseInt("" + integerCached.toCharArray()[i]) + Integer.parseInt("" + integerToAdd.toCharArray()[i]));
                if (s.length() == 1) {
                    chars[i] = s.toCharArray()[0];
                } else {
                    chars[i] = s.toCharArray()[1];
                    if (chars.length - 1 >= i + 1)
                        chars[i + 1] = s.toCharArray()[0];
                    else
                        carry = s.toCharArray()[0];
                }
            }

            for (char c : chars) {
                integerComplete = integerComplete + c;
            }
            if (carry != -1)
                integerComplete = carry + integerComplete;
        }
        {
            String decimalToAdd = getDecimal(value);
            String decimalCached = getDecimal(v0);
            if (decimalToAdd.length() != decimalCached.length()) {
                if (decimalToAdd.length() > decimalCached.length()) {
                    int fs = decimalToAdd.length() - decimalCached.length();
                    for (int i = 0; i < fs; i++) {
                        decimalCached = decimalCached + "0";
                    }
                } else {
                    int fs = decimalCached.length() - decimalToAdd.length();
                    for (int i = 0; i < fs; i++) {
                        decimalToAdd = decimalToAdd + "0";
                    }
                }
            }
            char[] chars = new char[decimalCached.length()];
            int carry = -1;
            for (int i = 0; i < decimalCached.length(); i++) {
                String s = String.valueOf(Integer.parseInt("" + decimalCached.toCharArray()[i]) + Integer.parseInt("" + decimalToAdd.toCharArray()[i]));
                if (s.length() == 1) {
                    chars[i] = s.toCharArray()[0];
                } else {
                    chars[i] = s.toCharArray()[1];
                    if (chars.length - 1 >= i + 1)
                        chars[i + 1] = s.toCharArray()[0];
                    else
                        carry = s.toCharArray()[0];
                }
            }

            for (char c : chars) {
                decimalComplete = decimalComplete + c;
            }
            if (carry != -1)
                decimalComplete = carry + decimalComplete;
        }

        return new Ratio(integerComplete + "." + decimalComplete);
    }

    public static boolean isInFill(char[] chars, int pos)
    {
        if (chars[0] != '0')
            return false;
        int start = 0;
        int end = 0;
        for (char aChar : chars) {
            if (aChar == '0')
                end++;
            else
                break;
        }
        System.out.println(start);
        System.out.println(pos);
        System.out.println(end);
        return pos >= start && pos <= end;
    }

    public static String[] charSetToString(char[] chars)
    {
        List<String> strings = new ArrayList<>();
        for (int i = 0;i<chars.length;i++)
        {
            if (String.valueOf(chars[i]).equals("-"))
            {
                strings.add(chars[i] + String.valueOf(chars[i+1]) + "");
            } else {
                strings.add(chars[i] + "");
            }
        }
        return strings.toArray(new String[0]);
    }

    public static Ratio subtractWithNonNegative(String v0, String value)
    {
        if (!v0.contains("."))
            v0 = v0 + ".0";
        if (!value.contains("."))
            value = value + ".0";
        String integerComplete = "";
        String decimalComplete = "";
        {

            String integerToAdd = getInteger(value); // 假设为 "1"
            String integerCached = getInteger(v0); // 假设为 "2"
            if (integerToAdd.length() != integerCached.length()) {
                if (integerToAdd.length() > integerCached.length()) {
                    int fs = integerToAdd.length() - integerCached.length();
                    for (int i = 0; i < fs; i++) {
                        integerCached = "0" + integerCached;
                    }
                } else {
                    int fs = integerCached.length() - integerToAdd.length();
                    for (int i = 0; i < fs; i++) {
                        integerToAdd = "0" + integerToAdd;
                    }
                }
            }
            String[] chars = charSetToString(integerCached.toCharArray());
            System.out.println(Arrays.toString(chars));
            for (int i = 0;i<chars.length;i++) {

                String result = String.valueOf(Integer.parseInt(chars[i])
                        - Integer.parseInt(charSetToString(integerToAdd.toCharArray())[i]));
                chars[i] = result;
                System.out.println(result + " (result");
            }

            StringBuilder s = new StringBuilder();
            boolean flag = false;
            for (String aChar : chars) {
                if (!Objects.equals(aChar, "0"))
                    flag = true;
                if (flag)
                    s.append(aChar);
            }
            System.out.println(s + " SB");
            String[] handle = charSetToString(s.toString().toCharArray());
            System.out.println(Arrays.toString(handle));
            int backspace = 0;
            for (int i = handle.length;i > 0;i--)
            {
                int n = Integer.parseInt(handle[i-1]);
                if (Integer.toString(n).startsWith("-"))
                {
                    handle[i-1] = String.valueOf(10 + n);
                    if (i-2 != -1)
                    {
                        handle[i-2] = String.valueOf(Integer.parseInt(handle[i-2]) - 1);
                    }
                    else
                    {
                        backspace++;
                    }
                }
            }
            if (backspace == 1)
            {
                String[] handledHandle = new String[handle.length-1];
                System.arraycopy(handle, 1, handledHandle, 0, handle.length - 1);
                handle = handledHandle;
            }
            for (String s1 : handle) {
                integerComplete = integerCached + s1;
            }

        }
        {
            String decimalToAdd = getDecimal(value);
            String decimalCached = getDecimal(v0);
            if (decimalToAdd.length() != decimalCached.length()) {
                if (decimalToAdd.length() > decimalCached.length()) {
                    int fs = decimalToAdd.length() - decimalCached.length();
                    for (int i = 0; i < fs; i++) {
                        decimalCached = decimalCached + "0";
                    }
                } else {
                    int fs = decimalCached.length() - decimalToAdd.length();
                    for (int i = 0; i < fs; i++) {
                        decimalToAdd = decimalToAdd + "0";
                    }
                }
            }

            String[] chars = charSetToString(decimalCached.toCharArray());
            System.out.println(Arrays.toString(chars));
            for (int i = 0;i<chars.length;i++) {
                System.out.println(chars[i]);
                String result = String.valueOf(Integer.parseInt(chars[i]) - Integer.parseInt(charSetToString(decimalToAdd.toCharArray())[i]));
                chars[i] = result;
            }

            for (String c : chars) {
                decimalComplete = decimalComplete + c;
            }

        }
        String result = "";
        if (integerComplete.startsWith("-") && decimalComplete.startsWith("-")) { //整数部分和小数部分都是负数
            result = "-" + integerComplete + "." + decimalComplete;
        } else if (!integerComplete.startsWith("-") && decimalComplete.startsWith("-")) {
            result = subtractWithNonNegative(integerComplete, "1").getValue() + "." + (1 - Double.parseDouble("0." + decimalComplete));
        } else if (integerComplete.startsWith("-") && !decimalComplete.startsWith("-")) {
            result = "-" + subtractWithNonNegative(integerComplete.replace("-", ""), "1").getValue() +
                    "." + (1 - Double.parseDouble("0." + decimalComplete));
        } else if (!integerComplete.startsWith("-") && !decimalComplete.startsWith("-")) {
            result = integerComplete + "." + decimalComplete;
        }

        return new Ratio(result);
    }


    private static String getDecimal(String value)
    {
        return value.split("\\.")[1];
    }

    private static String getInteger(String value)
    {
        return value.split("\\.")[0];
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return Integer.parseInt(value);
    }

    @Override
    public long longValue() {
        return Long.parseLong(value);
    }

    @Override
    public float floatValue() {
        return Float.parseFloat(value);
    }

    @Override
    public double doubleValue() {
        return Double.parseDouble(value);
    }
}
