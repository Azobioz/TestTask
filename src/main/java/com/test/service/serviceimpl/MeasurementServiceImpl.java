package com.test.service.serviceimpl;

import com.test.dto.MeasurementDto;
import com.test.mapper.MeasurementMapper;
import com.test.model.Measurement;
import com.test.model.Sensor;
import com.test.repository.MeasurementRepository;
import com.test.repository.SensorRepository;
import com.test.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.test.mapper.MeasurementMapper.mapToMeasurementDto;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }


    @Override
    public String createMeasurement(double value, boolean isRaining, String sensorName) {
        Measurement measurement = new Measurement();
        try {
            measurement.setValue(value);
            measurement.setRaining(isRaining);
        }
        catch (Exception e) {
            return "Ошибка в вводных данных измерения";
        }
        Sensor sensor = sensorRepository.findByName(sensorName);
        if (sensor == null) {
            return "Сенсора с таким названием нет";
        }
        else {
            measurement.setSensor(sensor);
            measurementRepository.save(measurement);
            sensor.getMeasurements().add(measurement);
            return "Измерения от сенсора добавлены";
        }
    }

    @Override
    public List<MeasurementDto> getAllMeasurements() {
        return measurementRepository.findAll().stream().map(measurement -> mapToMeasurementDto(measurement)).toList();
    }

    @Override
    public Map<String, Long> getHowManyRainyDays() {
        List<MeasurementDto> allMeasurements = getAllMeasurements();
        long rainyDaysCount = 0;
        for (MeasurementDto measurement : allMeasurements) {
            if (measurement.isRaining()) {
                rainyDaysCount++;
            }
        }

        return Collections.singletonMap("rainyDaysCount", rainyDaysCount);
    }


}
