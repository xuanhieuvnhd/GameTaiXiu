package taikhoan;

import menu.MenuAdmin;
import menu.MenuGame;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaiKhoan implements Serializable {
    private String ten, matKhau;
    private int tien;

    public int tien(){
        return tien;
    }
    public TaiKhoan() {
    }
    public TaiKhoan(String ten, String matKhau) {
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String ten, String matKhau, int tien) {
        this.ten = ten;
        this.matKhau = matKhau;
        this.tien = tien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
    Locale locale = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getInstance(locale);
    @Override
    public String toString() {
        return "TaiKhoan{" +
                "Ten: '" + ten + '\'' +
                ", Mat khau: '" + matKhau + '\'' +
                ", So tien dang co: " + numberFormat.format(tien) + " vnd" +
                '}';
    }
    static ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
    static File file = new File("D:\\PHANMEM\\JAVA\\Module2\\TaiXiu\\src\\taikhoan\\TaiKhoan.txt");

    public void ghiTaiLieu(ArrayList<TaiKhoan> taiKhoans) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(taiKhoans);
            o.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void docTaiLieu() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file));
            taiKhoans = (ArrayList<TaiKhoan>) o.readObject();
            o.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public TaiKhoan taoTaiKhoan(Scanner scanner) {
        System.out.print("Ten tai khoan: ");
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{3,18}$");
        String ten = scanner.nextLine();
        while (p.matcher(ten).find() == false) {
            if (!p.matcher(ten).find()) {
                System.err.println("Sai quy tac tao tai khoan ! vui long nhap lai: ");
                ten = scanner.nextLine();
            }
        }
        while (checkTenDangKy(ten)) {
            ten = scanner.nextLine();
        }
        System.out.print("Nhap mat khau: ");
        String matkhau = scanner.nextLine();
        while (checkMatKhauDangKy(matkhau)) {
            matkhau = scanner.nextLine();
        }
        return new TaiKhoan(ten, matkhau,tien);
    }

    public void themTaiKhoan(Scanner scanner) {
        TaiKhoan taiKhoan = taoTaiKhoan(scanner);
        taiKhoans.add(taiKhoan);
        System.out.println("Dang ky thanh cong , vui long chon dang nhap");
        ghiTaiLieu(taiKhoans);
    }

    public boolean checkAdmin(TaiKhoan taiKhoan) {
        return taiKhoan.getTen().equals("adm") && taiKhoan.getMatKhau().equals("adm");
    }

    public boolean checkTenDangKy(String ten) {
        String adm = "adm";
        String none1 = "";
        String none2 = " ";
        if (ten.equals(adm)) {
            System.err.print("Tai khoan da ton tai ! vui long nhap ten khac :");
            return true;
        }
        if (ten.equals(none1) || ten.startsWith(none2)) {
            System.err.print("Khong de trong ten tai khoan ! vui long nhap lai: ");
            return true;
        }

        for (int i = 0; i < taiKhoans.size(); i++) {
            if (taiKhoans.get(i).getTen().equals(ten)) {
                System.err.print("Tai khoan da ton tai, vui long nhap ten khac: ");
                return true;
            }
        }
        return false;
    }

    public boolean checkMatKhauDangKy(String matkhau) {
        String none1 = "";
        String none2 = " ";
        if (matkhau.equals(none1) || matkhau.startsWith(none2)) {
            System.err.print("Khong de trong mat khau ! vui long nhap lai: ");
            return true;
        }
        return false;
    }

    public void checkDangNhap(TaiKhoan taiKhoan) {
        boolean check = false;
        for (TaiKhoan a : taiKhoans) {
            if (taiKhoan.getTen().equals(a.getTen()) && taiKhoan.getMatKhau().equals(a.getMatKhau())) {
                check = true;
                System.out.println("Dang nhap thanh cong , vui long chon chuc nang");
                MenuGame.MenuTaiXiu();
            }
        }
        if (!check) {
            System.err.println("Sai ten dang nhap hoac mat khau , vui long nhap lai");
        }
    }

    public void dangNhap(Scanner scanner) {
        System.out.print("Ten dang nhap: ");
        String ten = scanner.nextLine();
        System.out.print("Mat khau dang nhap: ");
        String matkhau = scanner.nextLine();
        TaiKhoan taiKhoan = new TaiKhoan(ten, matkhau);
        if (checkAdmin(taiKhoan)) {
            System.out.println("Dang nhap thanh cong , xin chao Admin!");
            MenuAdmin.MenuQuanLy();
        } else {
            checkDangNhap(taiKhoan);
        }
    }

    public void hienTaiKhoan() {
        boolean check = false;
        for (TaiKhoan a : taiKhoans) {
            System.out.println(a);
            check = true;
        }
        if (!check) {
            System.err.println("Chua co tai khoan nao trong he thong, vui long nhan 0 de tro ve Trang Chu va chon dang ky");
        }
    }

    public void xoaTaiKhoan(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ten tai khoan muon xoa: ");
        String ten = scanner.nextLine();
        for (int i = 0; i < taiKhoans.size(); i++) {
            if (taiKhoans.get(i).getTen().equals(ten)) {
                taiKhoans.remove(i);
                check = true;
                System.out.println("Xoa tai khoan thanh cong");
                ghiTaiLieu(taiKhoans);
            }
        }
        if (!check) {
            System.err.println("Tai khoan khong ton tai !");
        }
    }

    public void doiMatKhau(Scanner scanner) {
        System.out.print("Nhap mat khau hien tai: ");
        String matkhau = scanner.nextLine();
        for (int i = 0; i < taiKhoans.size(); i++) {
            if (taiKhoans.get(i).getMatKhau().equals(matkhau)) {
                System.out.print("Nhap mat khau moi: ");
                String matkhau1 = scanner.nextLine();
                taiKhoans.get(i).setMatKhau(matkhau1);
                System.out.println("Thay doi mat khau thanh cong !");
                ghiTaiLieu(taiKhoans);
            }
        }
    }

    public void doiTenDangNhap(Scanner scanner) {
        System.out.print("Nhap ten hien tai: ");
        String ten = scanner.nextLine();
        for (int i = 0; i < taiKhoans.size(); i++) {
            if (taiKhoans.get(i).getTen().equals(ten)) {
                System.out.print("Nhap ten moi: ");
                String ten1 = scanner.nextLine();
                taiKhoans.get(i).setTen(ten1);
                System.out.println("Doi ten tai khoan thanh cong !");
                ghiTaiLieu(taiKhoans);
            }
        }
    }    public void timTaiKhoan(Scanner scanner){
        boolean check = false;
        System.out.print("Nhap ten tai khoan can tim: ");
        String ten = scanner.nextLine();
        for (TaiKhoan a: taiKhoans){
            if (a.getTen().equalsIgnoreCase(ten)){
                System.out.println(a);
                check = true;
            }
        }
        if (!check){
            System.out.println("Khong tim thay tai khoan co ten la: "+ten);
        }
    }

    public void napTien(Scanner scanner){
        System.out.print("Nhap ten tai khoan muon nap: ");
        String ten = scanner.nextLine();
        for (int i = 0; i < taiKhoans.size(); i++) {
            if (taiKhoans.get(i).getTen().equals(ten)){
                System.out.print("Nhap so tien muon nap: ");
                int nap = scanner.nextInt();
                if (nap>0){
                    tien+=nap;
                    System.out.println("Nap tien thanh cong! So tien hien tai cua ban la: "+ numberFormat.format(tien)+ "VND");
                    ghiTaiLieu(taiKhoans);
                }else {
                    System.err.println("Nhap so tien khong hop le");
                }
            }
        }
    }
}
