package com.example.husnain.newproject.Interfaces;


import com.example.husnain.newproject.entities.TicketingSchedule;

public interface TicketingScheduleResponse {

    void getSingleRowSucceded(TicketingSchedule output, long ticketing_schedule_id);
}
