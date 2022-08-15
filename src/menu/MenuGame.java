package menu;

import main.Main;
import taikhoan.TaiKhoan;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

//Một người chơi sẽ có tài khoản,người chơi có quyền đặt cược
//sô tiên ít hơn hoặc bằng số tiền họ đang có
//Luật chơi như sau: có 3 viên xúc xắc,mỗi viên xúc xắc có 6 mặt có giá trị từ 1->6
//Mỗi lần lắc sẽ ra 1 kết qua.Kết quả bằng = giá trị xx1+giá trị xx2+giá trị xx3
//1.nếu tổng bằng = 3 hoặc 18: cái ăn hết,người chơi thua
//2.nếu tổng bằng = (4->10:xỉu)ng đặt xỉu thắng,ngược lại thua
//3.nếu tổng bằng = (11->17:tài)ng đặt tài thắng,ngược lại thua
public class MenuGame {
    public static void MenuTaiXiu() {
        int taiKhoan = 999999999;
        Scanner scanner = new Scanner(System.in);
        TaiKhoan TK = new TaiKhoan();
        //thêm dấu ngăn cách vào tiền tệ
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        int chon = 1;
        do {
            System.out.println("---------- > TAI XIU < -------------");
            System.out.println("            1. Vao tro choi");
            System.out.println("            2. Doi mat khau");
            System.out.println("            3. Doi ten tai khoan");
            System.out.println("            0. Ve man hinh dang nhap");
            System.out.println("--------------------------------------");
            System.out.print("---------> Hay chon chuc nang: ");
            chon = scanner.nextInt();
            if (chon == 1) {
                System.out.println("DAT CUOC: ");
                //Đặt cược
                System.out.println("Tai khoan cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                double datCuoc = 0;
                do {
                    System.out.print("(Luu y:Dat cuoc so tien lon hon 0 va nho hon hoac bang " + numberFormat.format(taiKhoan) + " vnd): ");
                    datCuoc = scanner.nextDouble();
                } while (datCuoc <= 0 || datCuoc > taiKhoan);
                //Chọn tài hoặc xỉu
                int luaChonTaiXiu = 0;
                do {
                    System.out.println("Chon 1 = TAI  || Chon 2 = XIU");
                    luaChonTaiXiu = scanner.nextInt();
                } while (datCuoc <= 0 || datCuoc > taiKhoan);
                //Tung xúc xắc
                Random xucXac1 = new Random();
                Random xucXac2 = new Random();
                Random xucXac3 = new Random();
                int giaTri1 = xucXac1.nextInt(5) + 1;
                int giaTri2 = xucXac2.nextInt(5) + 1;
                int giaTri3 = xucXac3.nextInt(5) + 1;
                int ketQua = giaTri1 + giaTri2 + giaTri3;
                //Tính toán kết quả:
                System.out.println("Ket qua: " + giaTri1 + "-" + giaTri2 + "-" + giaTri3);
                if (ketQua == 3 || ketQua == 18) {
                    taiKhoan -= datCuoc;
                    System.out.println("Ket qua: " + ketQua + " Nha cai an het");
                    System.out.println("So tien con lai cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                } else if (ketQua >= 4 && ketQua <= 10) {
                    System.out.println("Ket qua: " + ketQua + " => XIU");
                    if (luaChonTaiXiu == 2) {
                        System.out.println("Ban da Thang");
                        taiKhoan += datCuoc;
                        System.out.println("So tien hien tai cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                    } else {
                        System.out.println("Ban da thua");
                        taiKhoan -= datCuoc;
                        System.out.println("So tien con lai cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                    }
                } else {
                    System.out.println("Ket qua: " + ketQua + " => TAI");
                    if (luaChonTaiXiu == 1) {
                        System.out.println("Ban thang cuoc");
                        taiKhoan += datCuoc;
                        System.out.println("So tien hien tai cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                    } else {
                        System.out.println("Ban da thua");
                        taiKhoan -= datCuoc;
                        System.out.println("So tien con lai cua ban: " + numberFormat.format(taiKhoan) + " vnd");
                    }
                }
            } else if (chon == 2) {
                TK.doiMatKhau(scanner);
            } else if (chon == 3) {
                TK.doiTenDangNhap(scanner);
            } else if (chon == 0) {
                Main.MenuMain();
            }
        } while (true);
    }
}

