package com.task.devices.templates;

import com.task.devices.repository.DeviceEntity;
import com.task.devices.repository.DevicesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DevicesTestRepository implements DevicesRepository {
    private final List<DeviceEntity> dbEmulator = new ArrayList<>();

    @Override
    public Optional<DeviceEntity> findByDeviceId(String deviceId) {
        return dbEmulator.stream()
                .filter(deviceEntity -> deviceEntity.getDeviceId().equals(deviceId))
                .findFirst();
    }

    @Override
    public Optional<DeviceEntity> findByDeviceIdAndUserId(String deviceId, String userId) {
        return findAllByUserId(userId, null).stream()
                .filter(deviceEntity -> deviceEntity.getDeviceId().equals(deviceId))
                .findFirst();

    }

    @Override
    public Page<DeviceEntity> findAllByUserId(String userId, Pageable pageable) {
        return new PageImpl<>(dbEmulator.stream()
                .filter(deviceEntity -> deviceEntity.getUserId().equals(userId))
                .collect(Collectors.toList()));
    }

    @Override
    public <S extends DeviceEntity> S save(S s) {
        int id = dbEmulator.indexOf(s);
        if (id < 0) {
            s.setId(dbEmulator.size());
        } else {
            s.setId(id);
        }
        dbEmulator.add(s);
        return (S) dbEmulator.get(dbEmulator.indexOf(s));
    }

    @Override
    public <S extends DeviceEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<DeviceEntity> findById(Integer aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer aLong) {
        return false;
    }

    @Override
    public Iterable<DeviceEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<DeviceEntity> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer aLong) {

    }

    @Override
    public void delete(DeviceEntity deviceEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends DeviceEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<DeviceEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DeviceEntity> findAll(Pageable pageable) {
        return null;
    }
}

