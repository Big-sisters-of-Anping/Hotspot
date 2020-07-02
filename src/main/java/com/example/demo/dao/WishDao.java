package com.example.demo.dao;

import com.example.demo.entity.SpotWishTime;
import com.example.demo.entity.Wish;

import java.sql.Date;
import java.util.List;

public interface WishDao {
    List<Wish> listAllWishes();
    List<Wish> listUserWishes(int userId);
    List<SpotWishTime> listSpotWishTime(int spotId);
    int insertWish(Wish wish);
    int cancelWish(int wishId);
}
