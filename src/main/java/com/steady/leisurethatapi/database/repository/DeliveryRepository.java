package com.steady.leisurethatapi.database.repository;


import com.steady.leisurethatapi.database.entity.Delivery;
import com.steady.leisurethatapi.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    public Delivery findByDeliveryBasicYnAndMember(String date, Member member);
    public Delivery findById(int id);
}
