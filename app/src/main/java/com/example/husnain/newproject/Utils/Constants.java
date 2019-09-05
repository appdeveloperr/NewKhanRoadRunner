package com.example.husnain.newproject.Utils;

import android.content.Context;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.Sessions.AppSession;

public class Constants {

    public static Context APPLICATION_CONTEXT = null;

    public static class URLS{
        public static final String IP = AppSession.get(Constants.SHARED_PREF.IP);//"221.120.209.226/api";
        public static final String GET_CITIES = "http://"+ IP +"/api/APIMain.aspx?type=getSourceCities";
        public static final String GET_SERVICE_TYPES = "http://"+ IP + "/api/APIMain.aspx?type=getServiceType";
        public static final String GET_TERMINALS = "http://" + IP + "/api/APIMain.aspx?type=getTerminalsList";
        public static final String GET_ROUTE_ALL = "http://" + IP + "/api/APIMain.aspx?type=getRouteAll";
        public static final String GET_ROUTE_ALL_DETAIL = "http://" + IP + "/api/APIMain.aspx?type=getRouteAllDetail";
        public static final String GET_SCHEDULE_ALL = "http://" + IP + "/api/APIMain.aspx?type=getScheduleAll";
        public static final String GET_SCHEDULE_ALL_DETAIL = "http://" + IP + "/api/APIMain.aspx?type=getScheduleAllDetail";
        public static final String GET_ALL_USERS = "http://" + IP + "/api/APIMain.aspx?type=getAllUsers";
        public static final String GET_FARE_LIST = "http://" + IP + "/api/APIMain.aspx?type=getFareList";
        public static final String GET_VEHICLE_LIST = "http://" + IP + "/api/APIMain.aspx?type=getVehiclesList";
        public static final String POST_TICKETING_SCHEDULE = "http://" + IP + "/webp/api/TicketShed/Create";
        public static final String POST_TICKETING_SEAT = "http://" + IP + "/webp/api/TicketSeat/Create";
        public static final String POST_UPDATE_SEAT_STATUS = "http://" + IP + "/webp/api/MultipleSeat/Create";
        //public static final String POST_TICKETING_SCHEDULE = "http://34.237.245.32/webp/api/TicketShed/Create";
        //public static final String POST_TICKETING_SEAT = "http://34.237.245.32/webp/api/TicketSeat/Create";

        //public static final String POST_TICKETING_SCHEDULE = "http://221.120.209.226/webp/api/TicketShed/Create";
        //public static final String POST_TICKETING_SEAT = "http://221.120.209.226/webp/api/TicketSeat/Create";

        public static final String GET_SEAT_BY_SCHEDULE_ID = "http://" + IP + "/api/APIMain.aspx?type=SeatsByScheduleId&pDate=";
        public static final String UPDATE_SEAT_STATUS = "http://" + IP + "/api/APIMain.aspx?type=UpdateSeatStatusSoldAll&pOperator_Id=" + Global.User_ID +"&pBordingTerminal_Id=30151&pMaskDate=" + Global.VoucherDate + "&pMaskRoute=" + Global.VoucherServiceTypeId + "&pMaskTerminalId=30151&pMaskDepTime="+ Global.VoucherTime +"&pCNIC=00000-0000000-0&pPassenger_Name=OnTheWay&pContactNo=0333-7777777&Invoice_Id=0&";


        //String url = "http://" + starting_url + ":999
    }

    public static class TAGS{
        public static final String TAG_GET_CITIES = "getCities";
        public static final String TAG_GET_SERVICE_TYPES = "getServiceTypes";
        public static final String TAG_GET_TERMINALS = "getTerminals";
        public static final String TAG_GET_ROUTE_ALL = "getRouteAll";
        public static final String TAG_GET_ROUTE_ALL_DETAIL = "getRouteAllDetail";
        public static final String TAG_GET_SCHEDULE_ALL = "getScheduleAll";
        public static final String TAG_GET_SCHEDULE_ALL_DETAIL = "getScheduleAllDetail";
        public static final String TAG_GET_FARE_LIST = "getFareList";
        public static final String TAG_GET_ALL_USERS = "getAllUsers";
        public static final String TAG_GET_ALL_VEHICLES = "getAllVehicles";

        public static final String TAG_SCHEDULE = "ticketingSchedule";
        public static final String TAG_SEATS = "ticketingSeats";
        public static final String TAG_UPDATE_SEAT_STATUS = "updateseatstatus";

        public static final String TAG_SOURCE = "source";
        public static final String TAG_DESTINATION = "dest";
    }

    public static class TICKETING_SCHEDULE_COL {
        public static final String TICKETING_SCHEDULE_ID = "Ticketing_Schedule_ID";
        public static final String SR_NO = "Sr_No";
        public static final String TS_DATE = "TS_Date";
        public static final String SCHEDULE_ID = "Schedule_ID";
        public static final String VOUCHER_NO = "Voucher_No";
        public static final String VOUCHER_STATUS = "Voucher_Status";
        public static final String VOUCHER_OPENED_BY = "Voucher_Opened_By";
        public static final String VOUCHER_CLOSED_BY = "Voucher_Closed_By";
        public static final String VOUCHER_CLOSED_DATE = "Voucher_Closed_Date";
        public static final String DEPARTURE_TIME = "Departure_Time";
        public static final String VEHICLE_ID = "Vehicle_ID";
        public static final String DRIVER_NAME = "Driver_Name";
        public static final String HOSTESS_NAME = "Hostess_Name";
        public static final String USER_ID = "User_Id";
        public static final String ACCESS_DATETIME = "Access_DateTime";
        public static final String ACCESS_SYS_NAME = "Access_Sys_Name";
        public static final String ACCESS_TERMINAL_ID = "Access_Terminal_Id";
        public static final String ACTUAL_DEPARTURE_TIME = "Actual_Departure_Time";
        public static final String GUARD = "Guard";
        public static final String IS_CLOSED = "Is_Closed";
        public static final String BOOK_ID = "Book_Id";
        public static final String IS_PULLED = "Is_Pulled";
        public static final String SERVICETYPE_ID = "ServiceType_Id";
    }

    public static class UPDATE_SEATS_INFO_COL {

        public static final String OPERATOR_ID = "OperatorID";
        public static final String BORDING_TERMINAL_ID = "BoardingTerminalID";
        public static final String MASK_DATE = "MaskDate";
        public static final String MASK_ROUTE = "MaskRoute";
        public static final String MASK_TERMINAL_ID = "MaskTerminalId";
        public static final String MASK_DEP_TIME = "MaskDepTime";
        public static final String PASSENGER_CNIC = "CNIC";
        public static final String PASSENGER_NAME = "PassengerName";
        public static final String CONTACT_NO = "ContactNo";
        public static final String INVOICE_ID = "InvoiceID";
        public static final String SEAT_ID = "seat_id";
        public static final String SEAT_NO = "Seat_No";
        public static final String GENDER = "Gender";
        public static final String SOURCE_ID = "SourceID";
        public static final String DESTINATION_ID = "DestinationID";
        public static final String PASSENGER_FARE = "fare";



    }

    public static class TICKETING_SEAT_COL {
        public static final String TICKETING_SEAT_ID = "Ticketing_Seat_ID";
        public static final String TICKETING_SCHEDULE_ID = "Ticketing_Schedule_ID";
        public static final String SEAT_NO = "Seat_No";
        public static final String STATUS = "Status";
        public static final String ISSUE_DATE = "Issue_Date";
        public static final String ISSUE_TERMINAL = "Issue_Terminal";
        public static final String ISSUED_BY = "Issued_By";
        public static final String SOURCE_ID = "Source_ID";
        public static final String DESTINATION_ID = "Destination_ID";
        public static final String PASSENGER_NAME = "Passenger_Name";
        public static final String CONTACT_NO = "Contact_No";
        public static final String FARE = "Fare";
        public static final String DISCOUNT = "Discount";
        public static final String TICKET_SR_NO = "Ticket_Sr_No";
        public static final String IS_BOOKEDSOLD = "Is_bookedSold";
        public static final String BOOKED_USER = "Booked_User";
        public static final String IS_SMS_SENT = "Is_SMS_Sent";
        public static final String CNIC = "CNIC";
        public static final String GENDER = "Gender";
        public static final String IS_TRANSIT = "Is_Transit";
        public static final String SHIFT_USER_ID = "Shift_User_Id";
        public static final String PNR_NO = "PNR_No";
        public static final String TELENOR = "Telenor";
        public static final String PAYMENTDATE = "PaymentDate";
        public static final String ISMISSED = "IsMissed";
        public static final String CHANGETICKET_NO = "ChangeTicket_No";
        public static final String COLLECTIONPOINT = "CollectionPoint";
        public static final String CHANGETYPE = "ChangeType";
        public static final String ROUTE_SR_NO = "Route_Sr_No";
        public static final String OPERATOR_ID = "Operator_Id";
        public static final String CUSTOMER_ID = "Customer_Id";
        public static final String POINTS = "Points";
        public static final String IS_ONLINEPRINTED = "Is_OnlinePrinted";
        public static final String ONLINEPRINTER_USERID = "OnlinePrinter_UserId";
        public static final String ONLINEPRINT_TERMINAL_ID = "OnlinePrint_Terminal_Id";
        public static final String ONLINEPRINT_DATE = "OnlinePrint_Date";
        public static final String INVOICE_ID = "Invoice_Id";
        public static final String IS_ONLINE = "Is_Online";
        public static final String LATITUDE = "Lat";
        public static final String LONGITUDE = "Long";
    }

    public static class ACTIVITY_CODES {
        public static final int SELECT_SEATS = 999;
    }

    public static class SHARED_PREF {
        public static final String IP = "ip";
        public static final String IS_INSERTED = "is_inserted";
        public static final String EXTRA_SEATS = "extra_seats";
        public static final String SCHEDULE_ID = "schedule_id";
        public static final String VOUCHER_NO = "voucher_no";
        public static final String VEHICLE_ID = "vehicle_id";
        public static final String VOUCHER_TIME = "v_time";
        public static final String VOUCHER_DATE = "v_date";
        public static final String SERVICE_TYPE_ID = "srv_type_id";
        public static final String IS_VOUCHER_SCANNED = "voucher_scanned";
        public static final String SEATS_FETCHED = "is_seats_fetched";
        public static final String USER_NAME = "username";
        public static final String PASW = "pasw";
        public static final String SEAT_NO_CLICKED = "seat_clicked";
        public static final String SEAT_GENDER = "gender";
        public static final String CLICK_COUNTER = "clickcounter";
    }

    public static class SEAT_INS_TYPES {
        public static final String API = "API";
        public static final String LOCAL = "LOCAL";
    }

}
