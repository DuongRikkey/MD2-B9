package ra.entity;

import java.util.Scanner;

public class Book {
    private String id;
    private String name;
    private Double price;
    private String author;
    private int categoryId;
    private Boolean status;

    public Book() {
        this.status = true;
    }

    public Book(String id, String name, Double price, String author, int categoryId, Boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.categoryId = categoryId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void inputDataBook(Scanner scanner,Book[] books,int currentBook, Categories [] categories,int currentCate){
        this.id=inputID(scanner,books,currentBook);
        this.name=inputName(scanner,books,currentBook);
        this.price=inputPrice(scanner);
        this.author=inputAuthor(scanner);
        this.categoryId=inputCategory(scanner,categories,currentCate);
        this.status=true;

    }
    public void inputUpdateBook(Scanner scanner,Book[] books,int currentBook, Categories [] categories,int currentCate){
        this.name=inputName(scanner,books,currentBook);
        this.price=inputPrice(scanner);
        this.author=inputAuthor(scanner);
        this.categoryId=inputCategory(scanner,categories,currentCate);
        this.status=true;

    }

//    public Boolean inputStatus(Scanner scanner) {
//        System.out.println("Mời bạn nhập trạng thái status");
//        do {
//            String status = scanner.nextLine();
//            if(status.trim().isEmpty()){
//                System.err.println("ko để trống trạng thái");
//            }
//            else {
//                if(status.equalsIgnoreCase("true")||status.equalsIgnoreCase("false")){
//                    return Boolean.parseBoolean(status);
//                }
//                else {
//                    System.err.println("Vui long nhap true hoac false");
//                }
//
//            }
//        }while (true);
//    }

    public int inputCategory(Scanner scanner, Categories[] categories, int currentCate) {
        //Show cate ra
        for(int i=0;i<currentCate;i++){
            System.out.printf("[ID: %d | Name: %s ] \n", categories[i].getId(),categories[i].getName());
        }
        do {
            System.out.println("Mời bạn nhập Íd ");
            int idchoice = Integer.parseInt(scanner.nextLine());
            boolean isExist = false;
            for(int i=0;i<currentCate;i++){
                if(categories[i].getId()==idchoice){
                    isExist=true;
                    break;
                }
            }
            if(isExist){
                return idchoice;
            }
            else {
                System.err.println("ko tồn tại danh mục đó");
            }
        }while (true);
    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Mời bạn nhập tên tác giả");
        do {
            String author = scanner.nextLine();
            if(author.trim().isEmpty()){
                System.out.println("Không để trùng lặp");
            }
            else {
                return author;
            }
        }while (true);
    }

    public Double inputPrice(Scanner scanner) {
        System.out.println("Mời bạn nhập giá sản phẩm");
        do {
            String price = scanner.nextLine();
            if(price.trim().isEmpty()){
                System.err.println("Sản phẩm không được để trống");
            }else {
                double priceInt = Double.parseDouble(price);
                if (priceInt>0){
                    return priceInt;

                }
                else {
                    System.err.println("Gía sản phẩm phải lớn hơn không");
                }
            }

        }while (true);

    }

    public String inputName(Scanner scanner, Book[] books, int currentBook) {
        System.out.println("Mời bạn nhập tên");
        do {
            String name=scanner.nextLine();
            if(name.trim().equals("")){
                System.err.println("Không được để trống tên");
            }else {
                boolean check=false;
                for(int i=0;i<currentBook;i++){
                    if(books[i].getName().equals(name)){
                        check=true;
                        break;
                    }
                }if(check){
                    System.err.println("Tên sách đã bị trùng");
                }else {
                    return name;
                }
            }
        }while (true);
    }

    public String inputID(Scanner scanner, Book[] books, int currentBook) {
        System.out.println("Mời bạn nhập ID ví dụ B123" );
        do {
            String id = scanner.nextLine();
            if(id.matches("^B\\w{3}$"))
            {
                boolean check = false;
                for (int i=0;i<currentBook;i++){
                    if(books[i].getId().equals(id)){
                    check=true;
                    break;}
                }
                if(check){
                    System.err.println("Tên đã bị trùng");
                }else {
                    return id;
                }
            }else {
                System.err.println("Tên phải bắt đầu B và có 4 ký tự");
            }

        }while (true);
    }
    public void displayDataBook(Categories[] categories,int currentCate){
        System.out.printf("[ID:%s|Name:%s|Price:%f|Authors:%s|Category:%s|Status:%s]\n ]",this.id,this.name,this.price,getCatelogNamebyID(categories,currentCate),this.author,this.status);
    }
    public String getCatelogNamebyID(Categories[] categories,int currentCate){
        for(int i=0;i<currentCate;i++){
            if(categories[i].getId()==this.categoryId){
                return categories[i].getName();
            }
        }return null;
    }
}
