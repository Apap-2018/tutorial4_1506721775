package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licensenumber);

    void addPilot(PilotModel pilot);

    void deletePilot(String licenseNumber);
}