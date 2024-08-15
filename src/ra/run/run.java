package ra.run;

import ra.entity.Book;
import ra.entity.Categories;

import java.util.Scanner;

public class run {
    public static Categories[] categories = new Categories[100];
    public static int currentCate = 0;
    public static Book[] books = new Book[100];
    public static int currentBook = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // show ra menu
        do {
            run main = new run();
            System.out.println("********************* SHOP *********************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("************************************************");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    main.menuCategory(scanner);
                    break;
                }
                case 2: {
                    main.menuProduct(scanner);
                    break;
                }
                case 3: {
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3");
            }
        }
        while (true);
    }

    public void menuCategory(Scanner scanner) {
        boolean isLoop = true;
        do {
            System.out.println("---------------------------CATEGORIES MENU---------------------------\n" +
                    "\n" +
                    "1. Hiển thị tất cả category\n" +
                    "2. Thêm mới category\n" +
                    "3. Sửa thông tin category\n" +
                    "4. Xóa danh mục\n" +
                    "5. Tìm kiếm danh mục theo tên\n" +
                    "6. Thoát");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    showAllCategory();
                    break;
                }
                case 2: {
                    addNewCategory(scanner);
                    break;
                }
                case 3: {
                    updateCategory(scanner);
                    break;
                }
                case 4: {
                    deleteCategory(scanner);
                    break;
                }
                case 5: {
                    searchCatebyName(scanner);
                    break;
                }
                case 6: {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6");
            }
        }
        while (isLoop);
    }

    private void deleteCategory(Scanner scanner) {
//      xóa category neu nhu trong book khong co quyen sách nào thuoc ve category dang xóa
        System.out.println("Mời bạn nhập ID category cần xóa");
        int categoryId = Integer.parseInt(scanner.nextLine());
        boolean categoryFound = false;
        int indexUpdate = -1;

        for (int i = 0; i < currentBook; i++) {
            if (books[i].getCategoryId() == categoryId) {
                categoryFound = true;
                indexUpdate = i;
                break;
            }
        }

        if (categoryFound) {
//             thông báo và thay đổi trạng thái
            System.out.println("Danh mục sách có liên quan đã xóa trạng thái đã được thay đổi");
            categories[indexUpdate].setStatus(!categories[indexUpdate].getStatus());
        } else {
//             xóa di danh muc vi khong ton tai quyen sách nào thuoc danh muc duoc xoa
            int indexCate = findIndexByCate(categoryId);
            if (indexCate != -1) {
                for (int i = indexCate; i < currentCate; i++) {
                    {
                        categories[i] = categories[i + 1];


                    }
                }
                currentCate--;
            } else {
                System.out.println("Ko tìm thấy index" + categoryId);
            }

        }

    }




    private void searchCatebyName(Scanner scanner) {
        System.out.println("Nhập tên cần tìm kiếm");
        String keyword = scanner.nextLine();
        for (int i = 0; i < currentCate; i++) {
            if (categories[i].getName().contains(keyword)) {
                categories[i].displayCategories();
            }
        }
    }

    private void updateCategory(Scanner scanner) {
        System.out.println("Mời bạn lựa chọn id Update");
        int idUpdate = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexByCate(idUpdate);
        if (indexUpdate != -1) {
            categories[indexUpdate].inputUpdate(scanner, categories, indexUpdate);

        } else {
            System.err.println("Không tồn tại id student có mã" + idUpdate);
        }
    }

    private int findIndexByCate(int id2) {
        for (int i = 0; i < currentCate; i++) {
            if (categories[i].getId() == id2) {
                return i;
            }
        }
        return -1;
    }

    private void addNewCategory(Scanner scanner) {
        System.out.println("Mời bạn nhập sô n");
        do {
            int n = Integer.parseInt(scanner.nextLine());
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    System.out.println("Mời bạn nhập số category thứ" + (i + 1) + ":\n");
                    categories[currentCate] = new Categories();
                    categories[currentCate].inputData(scanner, categories, currentCate);
                    currentCate++;

                }
                break;
            } else {
                System.err.println("n ko được nhỏ hơn không");
            }
        } while (true);
    }

    private void showAllCategory() {
        if (currentCate == 0) {
            System.err.println("Danh sách trống");
            return;
        }
        for (int i = 0; i < currentCate; i++) {
            categories[i].displayCategories();
        }
    }

    public void menuProduct(Scanner scanner) {
        boolean isLoop = true;
        do {
            System.out.println("---------------------------PRODUCT MANAGEMENT---------------------------\n" +
                    "\n" +
                    "1. Hiển thị tất cả book\n" +
                    "2. Thêm mới book\n" +
                    "3. Sửa thông tin book\n" +
                    "4. Xóa thông tin sách \n" +
                    "5. Tìm kiếm sách theo tên sách hoặc tên tác giả\n" +
                    "7. Tìm kiếm sách trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    showAllBook();
                    break;
                }
                case 2: {
                    addNewBook(scanner);
                    break;
                }
                case 3: {
                    updateBook(scanner);
                    break;
                }
                case 4: {
                    deleteBook(scanner);
                    break;
                }
                case 5: {
                    searchNameBookbyAuthor(scanner);
                    break;
                }
                case 6: {
                    searchPriceBook(scanner);
                    // sử dụng for -> lấy name ra so sánh sử dụng contains( tìm kiếm ) xong gọi displayData() để hiển thị
                    break;
                }
                case 8: {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 6");
            }
        }
        while (isLoop);
    }

    private void searchPriceBook(Scanner scanner) {
        System.out.println("Mời bạn nhập khoảng giá ban đầu");
        double startPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Mời bạn nhập khoảng giá kết thúc");
        double endPrice = Double.parseDouble(scanner.nextLine());

        if (startPrice >= endPrice) {
            System.err.println("Gía ban đầu phải nhỏ hơn khoảng giá tìm");
        } else {
            boolean check = false;
            for (int i = 0; i < currentCate; i++) {
                if (books[i].getPrice() >= startPrice && books[i].getPrice() <= endPrice) {
                    check = true;
                    books[i].displayDataBook(categories, currentCate);
                }
            }
            if (!check) {
                System.err.println("KO tìm thấy sp");
            }
        }

    }

    private void searchNameBookbyAuthor(Scanner scanner) {
        if (currentBook == 0) {
            System.out.println("List sách đang trông");
            return;
        }
        System.out.println("Mời bạn nhập tên sách hoặc tên tác giả");
        String keyword = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < currentBook; i++) {
            if (books[i].getName().contains(keyword) || books[i].getAuthor().contains(keyword)) {
                books[i].displayDataBook(categories, currentBook);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Ko tìm thấy bài hát" + keyword);
        }
    }

    private void deleteBook(Scanner scanner) {
        while (true) {
            System.out.println("Mời bạn nhập ID muốn xóa");
            String idDelete = scanner.nextLine();
            int indexDelete = -1;
            for (int i = 0; i < currentBook; i++) {
                if (books[i].getId().equals(idDelete)) {
                    indexDelete = i;
                    break;
                }

            }
            if (indexDelete != -1) {
                System.err.println("Không timf thấy id muốn xóa");
            } else {
                for (int i = 0; i < currentBook; i++) {
                    books[i] = books[i + 1];
                }
            }
            System.out.println("Xoóa thành công");
            currentBook--;
            break;
        }
    }

    private void updateBook(Scanner scanner) {
        System.out.println("Mời bạn nhập id muốn xóa");
        String idUpdate = scanner.nextLine();
        if (finđinexByID(idUpdate) != null) {
//            for (int i=0;i< currentBook;i++){
            finđinexByID(idUpdate).inputUpdateBook(scanner, books, currentBook, categories, currentCate);

//            }
        } else {
            System.out.println("không tìm thấy id có mã" + idUpdate);
        }
    }

    private void addNewBook(Scanner scanner) {
        System.out.println("Số lượng sách muốn thêm");
        do {
            int n = Integer.parseInt(scanner.nextLine());
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    books[currentBook] = new Book();
                    books[currentBook].inputDataBook(scanner, books, currentBook, categories, currentCate);
                    currentBook++;
                }
                break;
            } else {
                System.err.println("số lượng thêm phải lớn hơn 0");
            }

        } while (true);
    }

    private void showAllBook() {
        if (currentBook == 0) {
            System.err.println("Danh sách book trống");
            return;
        }
        for (int i = 0; i < currentBook; i++) {
            books[i].displayDataBook(categories, currentCate);
        }
    }

    public static Book finđinexByID(String id2) {
        for (int i = 0; i < currentBook; i++) {
            if (books[i].getId().equals(id2)) {
                return books[i];
            }
        }
        return null;
    }


}

