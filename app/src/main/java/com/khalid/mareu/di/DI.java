package com.khalid.mareu.di;

import com.khalid.mareu.repository.MeetingRepository;
import com.khalid.mareu.service.FakeMeetingApiService;
import com.khalid.mareu.service.MeetingApiService;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class DI {

    private static MeetingRepository rep = new MeetingRepository(new FakeMeetingApiService());

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingRepository getMeetingRepository() {
        return rep;
    }

    /**
     * Get always a new instance on @{@link MeetingRepository}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingRepository getNewInstanceRepository() {
        return new MeetingRepository(new FakeMeetingApiService());
    }
}
