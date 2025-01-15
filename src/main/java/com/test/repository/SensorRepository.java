package com.test.repository;

import com.test.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Sensor findByName(String name);

}
