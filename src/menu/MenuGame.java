package menu;

import views.Main;
import controller.Account;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class MenuGame extends Account {
    public static void MenuTalent(Account account) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts =  account.readData();
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        int choose = 1;
        do {
            System.out.println("---------- > TAI XIU  <Tai khoan: " + account.getName() + " - So tien dang co: " + numberFormat.format(account.getMoney()) + " VND>");
            System.out.println("            1. Vao tro choi");
            System.out.println("            2. Doi mat khau");
            System.out.println("            3. Doi ten tai khoan");
            System.out.println("            0. Ve man hinh dang nhap");
            System.out.println("--------------------------------------");
            choose = scanner.nextInt();
            scanner.nextLine();
            if (choose == 1) {
                System.out.println("DAT CUOC: ");
                System.out.println("Tai khoan cua ban: " + numberFormat.format(account.getMoney()) + " VND");
                if (account.getMoney() > 0) {
                    double placeBet = 0;
                    do {
                        System.out.print("(Luu y:Dat cuoc so tien lon hon 0 va nho hon hoac bang " + numberFormat.format(account.getMoney()) + " VND): ");
                        placeBet = scanner.nextDouble();
                    } while (placeBet <= 0 || placeBet > account.getMoney());
                    int chooseFortune = 0;
                    do {
                        System.out.println("Chon 1 = TAI  || Chon 2 = XIU");
                        chooseFortune = scanner.nextInt();
                    } while (placeBet <= 0 || placeBet > account.getMoney());
                    //Tung xúc xắc
                    Random dice1 = new Random();
                    Random dice2 = new Random();
                    Random dice3 = new Random();
                    int value1 = dice1.nextInt(5) + 1;
                    int value2 = dice2.nextInt(5) + 1;
                    int value3 = dice3.nextInt(5) + 1;
                    int result = value1 + value2 + value3;
                    //Tính toán kết quả:
                    System.out.println("Ket qua: " + value1 + "+" + value2 + "+" + value3 + " = " + result);
                    if (result == 3 || result == 18) {
                        account.setMoney((int) (account.getMoney() - placeBet));
                        System.out.println("Ket qua: " + result + " Nha cai an het");
                        System.out.println("So tien con lai cua ban: " + numberFormat.format(account.getMoney()) + " VND");

                    } else if (result >= 4 && result <= 10) {
                        System.out.println("Ket qua: " + result + " => XIU");
                        if (chooseFortune == 2) {
                            System.out.println("Ban thang!");
                            account.setMoney((int) (account.getMoney() + placeBet));
                            System.out.println("So tien hien tai cua ban: " + numberFormat.format(account.getMoney()) + " VND");
                        } else {
                            System.out.println("Ban thua!");
                            account.setMoney((int) (account.getMoney() - placeBet));
                            System.out.println("So tien con lai cua ban: " + numberFormat.format(account.getMoney()) + " VND");
                        }
                    } else {
                        System.out.println("Ket qua: " + result + " => TAI");
                        if (chooseFortune == 1) {
                            System.out.println("Ban thang !");
                            account.setMoney((int) (account.getMoney() + placeBet));
                            System.out.println("So tien hien tai cua ban: " + numberFormat.format(account.getMoney()) + " VND");
                        } else {
                            System.out.println("Ban thua!");
                            account.setMoney((int) (account.getMoney() - placeBet));
                            System.out.println("So tien con lai cua ban: " + numberFormat.format(account.getMoney()) + " VND");
                        }
                    }
                }
                int index = account.findByAccount(account);
                accounts.set(index, account);
                account.writeData(accounts);
            } else if (choose == 2) {
                account.changePassword(scanner);
            } else if (choose == 3) {
                account.changeLoginName(scanner);
            } else if (choose == 0) {
                Main.MenuMain();
            }
        } while (true);
    }
}

