package com.madaochi.kafkaconsumer.repository;

import com.madaochi.kafkaconsumer.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity,Long> {
}
