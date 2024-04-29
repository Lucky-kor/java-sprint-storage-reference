package com.choongang;  // 패키지 선언, 해당 클래스를 com.choongang 패키지에 속하게 합니다.

import java.util.Scanner;  // Scanner 클래스를 사용하기 위해 java.util 패키지에서 가져옵니다.

public class MyStorage {
    static String EMPTY = "등록 가능";
    static String[] products = new String[]{EMPTY,EMPTY,EMPTY,EMPTY,EMPTY};
    static int[] counts = new int[]{0, 0, 0, 0, 0};

    static void showMenu(){
        System.out.println("1. 물건 정보(제품명) 등록하기");
        System.out.println("2. 물건 정보(제품명) 등록 취소하기");
        System.out.println("3. 물건 넣기 (제품 입고)");
        System.out.println("4. 물건 빼기 (제품 출고)");
        System.out.println("5. 재고 조회");
        System.out.println("6. 프로그램 종료");
        System.out.println("-".repeat(30));
    }

    static int selectMenu(Scanner s){
        System.out.print("[System] 원하는 기능의 번호를 입력하세요 : ");
        int select = Integer.parseInt(s.nextLine());
        return select;
    }

    static void prod_input(Scanner s){
        System.out.print("[System] 제품 등록을 원하는 제품명을 입력하세요 : ");

        String input = s.nextLine();
        int emptyIdx = -1;

        for (int i = 0; i < products.length; ++i){
            String product = products[i];
            if (EMPTY.equals(product)){
                emptyIdx = i;
                break;
            }
        }
        if (emptyIdx < 0){
            System.out.println("[Warning] 저장 공간이 가득 찼습니다. 더 이상 등록이 불가능 합니다.");
            return;
        }
        products[emptyIdx] = input;
        counts[emptyIdx] = 0;
        System.out.println("[System]등록이 완료됬습니다.\n[System] 현재 등록된 제품 목록 ▼");
        for (String product : products){
            System.out.println("> "+ product);
        }
        System.out.println("-".repeat(30));
    }

    static void prod_remove(Scanner s){
        System.out.print("[System] 제품 등록 취소를 원하는 제품명을 입력하세요 : ");

        String input = s.nextLine();
        int emptyIdx = -1;
        for (int i = 0; i < products.length; ++i) {
            String product = products[i];

            if (input.equals(product)) {
                products[i] = EMPTY;
                counts[i] = 0;
                System.out.println("[System] 제품 취소가 완료됬습니다.");
                break;
            } else {
                System.out.println("[Warning] 등록된 제품이 없습니다. 제품명을 확인해 주세요.");
                System.out.println("-".repeat(30));
                break;
            }
        }
    }

    public static void prod_amount_add(Scanner s) {
        System.out.println("[System] 물건의 수량을 추가합니다.(입고)");
        System.out.print("[System] 수량을 추가할 제품명을 입력하세요 : ");
        String input = s.nextLine();
        int foundIdx = -1;
        for (int i = 0; i < products.length; ++i) {
            if (input.equals(products[i])) {
                foundIdx = i;
                break;
            }
        }

        if (foundIdx < 0) {
            System.out.println("[Warning] 입력한 제품명이 없습니다. 다시 확인하여 주세요.");
            return;
//            s.next();
        }

        System.out.print("[System] 추가할 수량을 입력해주세요 : ");
        if (s.hasNextInt()) {
            int cnt = Integer.parseInt(s.nextLine());
            counts[foundIdx] += cnt;
            System.out.println("[Clear] 정상적으로 제품의 수량 추가가 완료되었습니다.");
        } else {
            System.out.println("[Error] 수량은 숫자로 입력해야 합니다.");
        }
    }

    public static void prod_amount_decrease(Scanner s) {
        System.out.println("[System] 제품의 출고를 진행합니다.");
        System.out.print("[System] 출고를 진행할 제품명을 입력하세요 : ");
        String input = s.nextLine();
        int foundIdx = -1;
        for (int i = 0; i < products.length; ++i) {
            if (input.equals(products[i])) {
                foundIdx = i;
                break;
            }
        }

        if (foundIdx < 0) {
            System.out.println("[Warning] 찾고자 하는 제품명이 존재하지 않습니다. 다시 확인하여 주세요.");
            return;
        }

        System.out.print("[System] 원하는 출고량을 입력하세요 : ");
        if (s.hasNextInt()) {
            int cnt = Integer.parseInt(s.nextLine());
            if(cnt > counts[foundIdx]) {
                System.out.println("[Error] 남은 수량보다 더 많이 출고할 수 없습니다.");
                return;
            }
            counts[foundIdx] -= cnt;
            System.out.println("[Clear] 출고가 완료되었습니다.");
        } else {
            System.out.println("[Error] 수량은 숫자로 입력해야 합니다.");
        }
    }

    static void prod_search(){
        System.out.println("[System] 현재 등록된 물건 목록 ▼");
        for (int i = 0; i < products.length; ++i){
            System.out.println("> " + products[i] + " : " + counts[i] + "개");
        }
    }

    public static void main(String[] args){
        System.out.println("[Item_Storage_V1]");
        System.out.println("-".repeat(30));
        System.out.printf("[System] 점장님 어서오세요.\n[System] 해당 프로그램의 기능입니다.\n");

        Scanner s = new Scanner(System.in);
        while (true){
            showMenu();
            int menu = selectMenu(s);
            if(menu == 6){
                System.out.println("[System] 프로그램을 종료합니다.");
                System.out.println("[System] 감사합니다.");
                break;
            }
            switch (menu){
                case 1:
                    prod_input(s);
                    break;
                case 2:
                    prod_remove(s);
                    break;
                case 3:
                    prod_amount_add(s);
                    break;
                case 4:
                    prod_amount_decrease(s);
                    break;
                case 5:
                    prod_search();
                    break;
                default:
                    System.out.println("[System] 시스템 번호를 다시 확인하여 주세요.");
            }
        }
        s.close();
    }
}