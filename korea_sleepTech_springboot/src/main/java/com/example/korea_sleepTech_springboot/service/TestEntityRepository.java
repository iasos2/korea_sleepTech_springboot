package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.entity.TestEntity;
import org.springframework.data.repository.Repository;

interface TestEntityRepository extends Repository<TestEntity, Long> {
}
