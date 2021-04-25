package com.task.devices.controller;

import com.task.devices.controller.data.AddDeviceRequest;
import com.task.devices.controller.data.ApiMapper;
import com.task.devices.controller.data.UpdateDeviceRequest;
import com.task.devices.domain.api.IUser;
import com.task.devices.services.DevicesService;
import com.task.devices.services.TokenService;
import com.task.devices.services.data.DeviceDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * to test
 */
@RestController
@AllArgsConstructor
public class DevicesController {
    private final DevicesService devicesService;
    private final TokenService tokenService;

    @GetMapping("devices")
    public Page<DeviceDto> getDevices(@RequestHeader(value="token") String token,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") @Min(0) Integer page,
                                      @RequestParam(name = "size", required = false, defaultValue = "20") @Min(1) Integer size){
        IUser user = tokenService.parseJwtToken(token);
        return devicesService.getDevices(user, PageRequest.of(page, size));
    }

    @GetMapping("devices/{deviceId}")
    public ResponseEntity<DeviceDto> getDevice(@RequestHeader(value="token") String token,
                                               @PathVariable String deviceId){
        IUser user = tokenService.parseJwtToken(token);
        DeviceDto result = devicesService.getDevice(user, deviceId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("devices")
    public ResponseEntity<DeviceDto> addDevice(@RequestHeader(value="token") String token,
                                               @RequestBody AddDeviceRequest newDevice){
        IUser user = tokenService.parseJwtToken(token);
        DeviceDto result = devicesService.addDevice(ApiMapper.requestToNewDevice(user, newDevice));
        return ResponseEntity.ok(result);
    }

    @PutMapping("devices/{deviceId}")
    public ResponseEntity<DeviceDto> updateDevice(@RequestHeader(value="token") String token,
                                                  @PathVariable String deviceId,
                                                  @RequestBody UpdateDeviceRequest updates){
        IUser user = tokenService.parseJwtToken(token);
        DeviceDto result = devicesService.updateDevice(user, deviceId, updates);
        return ResponseEntity.ok(result);
    }
}
