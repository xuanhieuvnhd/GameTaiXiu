package controller;

import views.Main;
import menu.MenuAdmin;
import menu.MenuGame;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Account implements Comparable<Account>,Serializable {
    private String name, password;
    private int money;

    public Account() {
    }
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account(String name, String password, int money) {
        this.name = name;
        this.password = password;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    Locale locale = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getInstance(locale);

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "Ten: '" + name + '\'' +
                ", Mat khau: '" + password + '\'' +
                ", So tien dang co: " + numberFormat.format(money)+ " VND" +
                '}';
    }

    public ArrayList<Account> getAccount() {
        return accounts;
    }

    public void setAccount(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    private ArrayList<Account> accounts = new ArrayList<>();
    File file = new File("account.txt");

    public void writeData(ArrayList<Account> accounts) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(accounts);
            o.close();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public ArrayList<Account> readData() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file));
            accounts = (ArrayList<Account>) o.readObject();
            o.close();
        } catch (Exception e) {
            System.out.println();
        }
        return accounts;
    }
    public boolean checkRegisteredName(String name) {
        String adm = "adm";
        String none1 = "";
        String none2 = " ";
        if (name.equals(adm)) {
            System.err.print("Tai khoan da ton tai ! vui long nhap ten khac :");
            return true;
        }
        if (name.equals(none1) || name.startsWith(none2)) {
            System.err.print("Khong de trong ten tai khoan ! vui long nhap lai: ");
            return true;
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                System.err.print("Tai khoan da ton tai, vui long nhap ten khac: ");
                return true;
            }
        }
        return false;
    }
    public boolean checkRegistrationPassword(String password) {
        String none1 = "";
        String none2 = " ";
        if (password.equals(none1) || password.startsWith(none2)) {
            System.err.print("Khong de trong mat khau ! vui long nhap lai: ");
            return true;
        }
        return false;
    }
    public boolean checkAdmin(Account account) {
        return account.getName().equals("adm") && account.getPassword().equals("adm");
    }
    public void checkLogin(Account myAccount) {
        boolean check = false;
        for (Account account : accounts) {
            if (account.getName().equals(myAccount.getName()) && account.getPassword().equals(myAccount.getPassword())) {
                check = true;
                System.out.println("Dang nhap thanh cong !");
                MenuGame.MenuTalent(account);
            }
        }
        if (!check) {
            System.err.println("Sai ten dang nhap hoac mat khau , vui long nhap lai");
        }
    }
    public Account createAccount(Scanner scanner) {
        System.out.print("Ten tai khoan: ");
        Pattern p = Pattern.compile("[a-zA-Z0-9]{3,18}$");
        String name = scanner.nextLine();
        while (!p.matcher(name).find()) {
            if (!p.matcher(name).find()) {
                System.err.println("Sai quy tac tao tai khoan ! vui long nhap lai: ");
                name = scanner.nextLine();
            }
        }
        while (checkRegisteredName(name)) {
            name = scanner.nextLine();
        }
        System.out.print("Nhap mat khau: ");
        String password = scanner.nextLine();
        while (checkRegistrationPassword(password)) {
            password = scanner.nextLine();
        }
        return new Account(name, password);
    }
    public void addAucount(Scanner scanner) {
        Account account = createAccount(scanner);
        accounts.add(account);
        System.out.println("Dang ky thanh cong , vui long chon dang nhap");
        writeData(accounts);
        Main.MenuMain();
    }
    public void login(Scanner scanner){
        System.out.print("Ten dang nhap: ");
        String name = scanner.nextLine();
        System.out.print("Mat khau dang nhap: ");
        String password = scanner.nextLine();
        Account account = new Account(name, password);
        if (checkAdmin(account)) {
            System.out.println("Dang nhap thanh cong , xin chao Admin!");
            MenuAdmin.MenuAdmin();
        } else {
            checkLogin(account);
        }
    }
    public void displayAccount() {
        boolean check = false;
        for (Account account : accounts) {
            System.out.println(account);
            check = true;
        }
        if (!check) {
            System.err.println("Chua co tai khoan nao trong he thong, vui long nhan 0 de tro ve Trang Chu va chon dang ky");
        }
    }
    public void deleteAccount(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ten tai khoan muon xoa: ");
        String name = scanner.nextLine();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                accounts.remove(i);
                check = true;
                System.out.println("Xoa tai khoan thanh cong");
                writeData(accounts);
            }
        }
        if (!check) {
            System.err.println("Tai khoan khong ton tai !");
        }
    }
    public void changePassword(Scanner scanner) {
        System.out.print("Nhap mat khau hien tai: ");
        String password = scanner.nextLine();
        for (int i = 0 ; i < accounts.size() ; i++){
            if (accounts.get(i).getPassword().equals(password)){
                System.out.print("Nhap mat khau moi: ");
                String password1 = scanner.nextLine();
                accounts.get(i).setPassword(password1);
                System.out.println("Thay doi mat khau thanh cong !");
                writeData(accounts);
            }
        }
    }
    public void changeLoginName(Scanner scanner) {
        System.out.print("Nhap ten hien tai: ");
        String name = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                System.out.print("Nhap ten moi: ");
                String name1 = scanner.nextLine();
                account.setName(name1);
                System.out.println("Doi ten tai khoan thanh cong !");
                writeData(accounts);
            }
        }
    }
    public int findByAccount(Account account){
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(account.getName())) {
               return i;
            }
        }
       return -1;
    }
    public void findAccountByName(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ten tai khoan can tim: ");
        String name = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().contains(name)) {
                System.out.println(account);
                check = true;
            }
        }
        if (!check) {
            System.out.println("Khong tim thay tai khoan co ten la: " + name);
        }
    }

    public void recharge(Scanner scanner) {
        System.out.print("Nhap ten tai khoan muon nap: ");
        String ten = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().equals(ten)) {
                System.out.print("Nhap so tien muon nap: ");
                int nap = scanner.nextInt();
                if (nap > 0) {
                    account.money += nap;
                    System.out.println("Nap tien thanh cong! So tien hien tai cua ban la: " + numberFormat.format(account.money) + " VND");
                    writeData(accounts);
                } else {
                    System.err.println("Nhap so tien khong hop le");
                }
            }
        }
    }
    public void sortAccountByMoneyAscending(){
        Comparator<Account> comparator = new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return (int) (o1.getMoney() - o2.getMoney());
            }
        };
        accounts.sort(comparator);
        System.out.println("Danh sach tai khoan da duoc xep theo so tien dang co tang dan: ");
        displayAccount();
        System.out.println("---------------------------------------------------------------------------------------");
    }
    public void sortAccountByMoneyDescending(){
        Comparator<Account> comparator = new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return (int) (o2.getMoney() - o1.getMoney());
            }
        };
        accounts.sort(comparator);
        System.out.println("Danh sach tai khoan da duoc xep theo so tien dang co giam dan: ");
        displayAccount();
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void findAccountHighestMoney() {
        double max = 0;
        int account = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (max < accounts.get(i).getMoney()) {
                max = accounts.get(i).getMoney();
                account = i;
            }
        }
        System.out.println("Tai khoan co so tien nhat trong he thong la: ");
        System.out.println(accounts.get(account));
        System.out.println("---------------------------------------------------------------------------------------");
    }
    @Override
    public int compareTo(Account account) {
        return this.getName().compareTo(account.getName());
    }
    public void sortAccountByName() {
        readData();
        Collections.sort(accounts);
        System.out.println("Danh sach tai khoan da sap xep theo ten:");
        displayAccount();
        System.out.println("---------------------------------------------------------------------------------------");
    }
    public void sortProductByMoney(Scanner scanner) {
        int choose = -1;
        do {
            System.out.println("1. Sap xep tai khoan theo so tien dang co (tang dan)");
            System.out.println("2. Sap xep tai khoan theo so tien dang co (giam dan)");
            System.out.println("3. Sap xep tai khoan theo ten");
            System.out.println("4. In ra tai khoan dang co so tien lon nhat");
            System.out.println("0. Quay ve MenuAdmin");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
            }
            switch (choose) {
                case 1 -> sortAccountByMoneyAscending();
                case 2 -> sortAccountByMoneyDescending();
                case 3 -> sortAccountByName();
                case 4 -> findAccountHighestMoney();
                case 0 -> MenuAdmin.MenuAdmin();
            }
        }
        while (choose != 0);
    }
}
