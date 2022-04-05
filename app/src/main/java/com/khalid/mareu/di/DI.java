package com.khalid.mareu.di;

import com.khalid.mareu.service.FakeMeetingApiService;
import com.khalid.mareu.service.MeetingApiService;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class DI {

    private static MeetingApiService service = new FakeMeetingApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new FakeMeetingApiService();
    }

}
