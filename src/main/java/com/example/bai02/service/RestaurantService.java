package com.example.bai02.service;

import com.example.bai02.model.MenuItem;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {

    /**
     * Lấy danh sách thực đơn theo restaurantId.
     * Sử dụng chiến lược Lazy Loading với @Cacheable:
     * - Lần đầu (Cache Miss): Thực thi SQL phức tạp (giả lập Thread.sleep(3000)) và lưu kết quả vào Redis.
     * - Các lần sau (Cache Hit): Lấy trực tiếp từ Redis trong < 20ms mà không gọi DB.
     */
    @Cacheable(value = "restaurantMenu", key = "#id")
    public List<MenuItem> getMenuByRestaurantId(Long id) {
        System.out.println("--> [DB Query] Executing heavy SQL query for restaurantId: " + id + " (Simulating 3s delay)...");
        try {
            Thread.sleep(3000); // Giả lập độ trễ truy vấn database JOIN phức tạp
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return Arrays.asList(
                new MenuItem(1L, id, "Phở Bò", 55000.0),
                new MenuItem(2L, id, "Bún Chả", 60000.0)
        );
    }
}
