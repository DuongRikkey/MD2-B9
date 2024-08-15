package ra.entity;

import java.util.Scanner;

public class Categories {
    private int id;
    private String name;
    private Boolean status;

    public Categories() {}
    public Categories(int id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void inputData(Scanner scanner,Categories [] categories,int currentCate) {
        this.id=idAutoIncrement(scanner,categories,currentCate);
        this.name=inputCateName(scanner,categories,currentCate);
        this.status=true;
    }
    public void inputUpdate(Scanner scanner,Categories [] categories,int currentCate) {
        this.name=inputCateName(scanner,categories,currentCate);
        this.status=true;
    }

//    public Boolean inputStatus(Scanner scanner) {
//        System.out.println("Mời bạn nhập trạng thái status");
//        do {
//            String status = scanner.nextLine();
//            if(status.trim().isEmpty()){
//                System.err.println("Không được nhập trống tên");
//            }
//            else {
//                if(status.equalsIgnoreCase("true")||status.equalsIgnoreCase("false")){
//                    return Boolean.parseBoolean(status);
//                }
//                else {
//                    System.err.println("Bạn vui lòng nhập lại true hoặc false");
//                }
//            }
//
//        }while (true);
//    }

    public String inputCateName(Scanner scanner, Categories[] categories, int currentCate) {
        System.out.println("Mời bạn nhập tên Cate");
        do {
            String cateName = scanner.nextLine();
            if (cateName.trim().isEmpty()){
                System.err.println("Không được để trống tên sản phâmmr");


            }else {
                boolean check = false;
                for (int i = 0; i < currentCate; i++) {
                    if (categories[i].getName().equals(cateName)) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    System.err.println("Không được để trùng tên");
                }
                else {
                    return cateName;
                }


            }
        }while (true);
    }

    public int idAutoIncrement(Scanner scanner, Categories[] categories, int currentCate) {
        int maxID=0;
        for (int i=0;i<currentCate;i++) {
            if(categories[i].getId()>maxID) {
                maxID=categories[i].getId();
            }
        }
        return maxID+1;
    }
    public void displayCategories() {
        System.out.printf("[ID:%d || NAME:%s || STATUS:%s ]\n",this.id,this.name,this.status);
    }


}
