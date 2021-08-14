package me.faintcloudy.rationalnumber;

public class Main {

    public static void main(String[] args) {
        String num1 = "1111111111111111111111111111111111111111111111111111111111111111111111.123";
        String num2 = "1145141919810.123456754567865456789876567898767897678987678976789876567876567812322544434567545676545676545676545676543456765456765413456789098765434567898765423544434561234543345654312314123123123124152345";
        System.out.println(num1);
        System.out.println("+");
        System.out.println(num2);
        System.out.println("=");
        System.out.println(Ratio.addWithNonNegative(num1, num2).getValue());
    }

}
