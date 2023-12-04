package com.awadinhoo.code.drones.constants;

public class Constants {

    public static class StatusMessages {
        public static final String INVALID_REQUEST_MESSAGE = "Invalid Request, URI doesn't match body";
        public static final String ZONE_NOT_FOUND_MESSAGE = "Zone is not found for this id :: ";
        public static final String ZONE_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE = "Another zone is already found with the same name :: ";
        public static final String ZONES_NOT_FOUND_MESSAGE = "There is no zones in the system ";
        public static final String ZONE_DELETED_SUCCESSFULLY_MESSAGE = "Zone successfully deleted! Zone Id :: ";
        public static final String MEDICATION_ALREADY_EXIST_BY_NAME_CODE_MESSAGE = "Another Medication already exist by this name Or Code";
        public static final String MEDICATION_ALREADY_EXIST_BY_CODE_MESSAGE = "Another Medication already exist by this code ::";
        public static final String MEDICATION_NOT_FOUND_MESSAGE = "Medication is not found for this id :: ";
        public static final String MEDICATION_STOCK_NOT_FOUND_MESSAGE = "There is no stock for this Medication id :: ";
        public static final String MEDICATION_DELETED_SUCCESSFULLY_MESSAGE = "Medication successfully deleted!";
        public static final String MEDICATIONS_NOT_FOUND_MESSAGE = "There is no medications in the system ";
        public static final String FLEET_SUPPORTED_ZONES_INVALID_STATUS_MESSAGE = "The assigned zones one or more of them are not found or served by another fleet";
        public static final String FLEET_IS_FOUND_WITH_THE_SAME_NAME_MESSAGE = "Another fleet is already found with the same name :: ";
        public static final String FLEET_MUST_CONTAIN_AT_LEAST_ONE_DRONE_MESSAGE = "Fleet must contain at least one drone ";
        public static final String NUMBER_OF_DRONES_EXCEEDS_FLEET_LIMIT_MESSAGE = "Number of drones exceeds the fleet limit ";
        public static final String FLEET_NOT_FOUND_MESSAGE = "Fleet is not found for this id :: ";
        public static final String FLEETS_NOT_FOUND_MESSAGE = "There is no fleets in the system ";
        public static final String FLEET_HAS_NO_DRONES_MESSAGE = " Fleet doesn't contain any drones :: ";
        public static final String FLEET_HAS_NO_DRONES_TO_DELIVER_MESSAGE = " Fleet doesn't contain any available drones to deliver at the moment :: ";
        public static final String FLEET_DELETED_SUCCESSFULLY_MESSAGE = "Fleet successfully deleted!";
        public static final String DRONE_IS_NOT_ON_INITIAL_STATE_MESSAGE = "Drone is not on its initial state ";
        public static final String FLEET_MUST_BE_IN_ACTIVE_STATE_MESSAGE = "Fleet must be in active state :: ";
        public static final String DRONE_IS_FOUND_WITH_THE_SAME_SERIAL_NUMBER_MESSAGE = "Drone is already found with the same serial number :: ";
        public static final String DRONE_IS_NOT_FOUND_MESSAGE = "Drone is not found for this id :: ";
        public static final String DRONE_MUST_BE_IN_ACTIVE_STATUS_MESSAGE = "The assigned Drone must be in active status ";
        public static final String DRONE_IS_ALREADY_EXIST_WITH_SERIALNUMBER_MESSAGE = "Another Drone already exist by this serial number :: ";
        public static final String DRONE_MUST_BE_NOT_DELIVERING_TO_BE_ASSIGNED_TO_ANOTHER_FLEET_MESSAGE = "Drone Must be not delivering to be assigned to another Fleet";
        public static final String DRONE_DELETED_SUCCESSFULLY_MESSAGE = "Drone successfully deleted! ";
        public static final String ZONE_IS_NOT_SERVED_BY_ANY_FLEETS_MESSAGE = "Zone is not served by any fleets ::  ";
        public static final String Zone_HAS_NO_DRONES_TO_DELIVER_MESSAGE = "Zone doesn't contain any available drones to deliver at the moment :: ";
        public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error!";
        public static final String INVALID_NEW_FLEET_CAPACITY_MESSAGE = "Fleet Capacity is more than the new capacity, current capacity :: ";
        public static final String URGENT_ORDER_MISSING_DRONE_ID_MESSAGE = "Urgent order must contain the drone id ";
        public static final String ZONE_MUST_BE_IN_ACTIVE_STATUS_MESSAGE = "Zone Must be in active status ";
        public static final String ZONE_IS_NOT_SUPPORTED_BY_THE_DRONE_MESSAGE = "Zone is not supported by the assigned drone";
        public static final String MEDICATIONS_ARE_NOT_FOUND_MESSAGE = "The requested medications are not found";
        public static final String ONE_OR_MORE_THAN_ONE_OF_MEDICATIONS_ARE_NOT_FOUND_MESSAGE = "One or more than one medication is not found ";
        public static final String ORDER_NOT_FOUND_MESSAGE = "Order is not found for this id :: ";
        public static final String ORDER_CAN_NOT_CANCELLED_MESSAGE = "Order can't be cancelled";
        public static final String ORDER_IS_ALREADY_CANCELLED_MESSAGE = "Order is already cancelled";
        public static final String ORDER_SUCCESSFULLY_CANCELLED_MESSAGE = "Order Successfully cancelled!";
        public static final String DRONE_IS_NOT_LOADED_YET_MESSAGE = "Drone Needs to be Loaded with medications first ";
        public static final String DRONE_CAN_NOT_BE_LOADED_WHILE_CHARGING_MESSAGE = "Drone can't be loaded while charging ";
        public static final String DRONE_CAN_NOT_DELIVER_WHILE_CHARGING_MESSAGE = "Drone can't deliver while charging ";
        public static final String DRONE_IS_ALREADY_CHARGING_MESSAGE = "Drone can't deliver while charging ";
        public static final String DRONE_IS_ALREADY_LOADED_MESSAGE = "Drone is already loaded ";
        public static final String DRONE_CAN_NOT_CHARGE_AFTER_LOADED = "Drone can't charge After loading ";

    }

}
