package com.task.devices.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevicesRepository extends PagingAndSortingRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByDeviceId(String deviceId);
    Optional<DeviceEntity> findByDeviceIdAndUserId(String deviceId, String userId);
    Page<DeviceEntity> findAllByUserId(String userId, Pageable pageable);
}
