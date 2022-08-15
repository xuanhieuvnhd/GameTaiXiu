package menu;

import main.Main;
import taikhoan.TaiKhoan;
import java.util.Scanner;

public class MenuAdmin {
    public static void MenuQuanLy() {
        Scanner scanner = new Scanner(System.in);
        TaiKhoan TK = new TaiKhoan();
        TK.docTaiLieu();
        int chon = -1;
        do {
            System.out.println(" >>>>>>>>  ADMIN  <<<<<<<");
            System.out.println("|       1. Hien thi tat ca tai khoan      ");
            System.out.println("|       2. Xoa tai khoan theo ten        ");
            System.out.println("|       3. Tim kiem tai khoan theo ten          ");
            System.out.println("|       4. Nap tien vao tai khoan         ");
            System.out.println("|       0. Dang xuat                              ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------> Vui long chon chuc nang: ");
            try {
                chon = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
            }

            switch (chon) {
                case 1 -> TK.hienTaiKhoan();
                case 2 -> TK.xoaTaiKhoan(scanner);
                case 3 -> TK.timTaiKhoan(scanner);
                case 4 -> TK.napTien(scanner);
                case 0 -> Main.MenuMain();
            }
        }
        while (chon != 0);
    }
}
