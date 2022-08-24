package menu;

import views.Main;
import controller.Account;
import java.util.Scanner;

public class MenuAdmin {
    public static void MenuAdmin() {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        account.readData();
        int choose = -1;
        do {
            System.out.println(" >>>>>>>>  ADMIN  <<<<<<<");
            System.out.println("|       1. Hien thi tat ca tai khoan      ");
            System.out.println("|       2. Nap tien vao tai khoan        ");
            System.out.println("|       3. Tim kiem tai khoan theo ten          ");
            System.out.println("|       4. Xoa tai khoan         ");
            System.out.println("|       5. Sap xep tai khoan theo so tien");
            System.out.println("|       0. Dang xuat                              ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------> Vui long chon chuc nang: ");
            try {
                choose = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
            }

            switch (choose) {
                case 1 -> account.displayAccount();
                case 2 -> account.recharge(scanner);
                case 3 -> account.findAccountByName(scanner);
                case 4 -> account.deleteAccount(scanner);
                case 5 -> account.sortProductByMoney(scanner);
                case 0 -> Main.MenuMain();
            }
        }
        while (choose != 0);
    }
}
