import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        String numberString = "5";
        BigInteger number = new BigInteger(numberString);

        String textRepresentation = convertToText(number);
        System.out.println(textRepresentation);
    }

    // Hàm chuyển số BigInteger thành văn bản
    public static String convertToText(BigInteger number) {
        String[] unit = {"", "nghìn", "triệu", "tỉ", "nghìn tỉ", "triệu tỉ", "tỉ tỉ"}; // Các đơn vị của số

        // Chuyển số BigInteger thành chuỗi
        String numberString = number.toString();
        int length = numberString.length();

        // Nếu độ dài của chuỗi là 0 thì trả về chuỗi rỗng
        if (length == 0) {
            return "";
        }

        // Đảo chuỗi để bắt đầu từ hàng đơn vị
        numberString = new StringBuilder(numberString).reverse().toString();

        StringBuilder text = new StringBuilder();
        int chunkCount = (length + 2) / 3; // Số lượng nhóm 3 chữ số

        for (int i = 0; i < chunkCount; i++) {
            String chunk = numberString.substring(3 * i, Math.min(3 * (i + 1), length)); // Lấy nhóm 3 chữ số
            int chunkValue = Integer.parseInt(new StringBuilder(chunk).reverse().toString()); // Đảo chuỗi và chuyển thành số nguyên

            if (chunkValue == 0) {
                continue; // Nếu giá trị của nhóm là 0 thì bỏ qua
            }

            String chunkText = convertChunkToText(chunkValue); // Chuyển nhóm thành văn bản
            text.insert(0, chunkText + " " + unit[i] + " "); // Chèn vào văn bản kết quả
        }

        return text.toString().trim(); // Trả về chuỗi kết quả
    }

    // Hàm chuyển nhóm 3 chữ số thành văn bản
    public static String convertChunkToText(int chunk) {
        String[] digitText = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"}; // Các chữ số từ 0 đến 9

        int hundreds = chunk / 100; // Số hàng trăm
        int tens = (chunk % 100) / 10; // Số hàng chục
        int ones = chunk % 10; // Số hàng đơn vị

        StringBuilder text = new StringBuilder();

        if (hundreds > 0) {
            text.append(digitText[hundreds]).append(" trăm "); // Chèn hàng trăm
        }

        if (tens > 1) {
            text.append(digitText[tens]).append(" mươi "); // Chèn hàng chục từ 2 đến 9
        } else if (tens == 1) {
            text.append("mười "); // Trường hợp hàng chục là 1
        }

        if (tens != 1 && ones > 0) {
            text.append(digitText[ones]); // Chèn hàng đơn vị
        }

        return text.toString().trim(); // Trả về chuỗi kết quả
    }
}
