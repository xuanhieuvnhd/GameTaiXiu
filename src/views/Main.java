package views;

import controller.Account;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    public static void main(String[] args) {
        MenuMain();
    }

    public static void MenuMain() {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        account.readData();
        int choose = -1;
        System.out.println("*****************************");
        System.out.println("------------->Trang Chu<-------------");
        System.out.println("**          1. Dang nhap              **");
        System.out.println("**          2. Dang ky                  **");
        System.out.println("------------------------------------------");
        System.out.println("**          0. Thoat he thong        **");
        System.out.println("*****************************");
        do {

            System.out.print("  --> Vui long chon chuc nang:");
            try {
                choose = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
            }
            switch (choose) {
                case 1 -> account.login(scanner);
                case 2 -> account.addAucount(scanner);
                case 0 -> System.exit(0);
            }
        }
        while (true);
    }
}
