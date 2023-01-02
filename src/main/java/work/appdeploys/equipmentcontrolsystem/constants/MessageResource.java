package work.appdeploys.equipmentcontrolsystem.constants;

public interface MessageResource {

   String SCHOOL_EXIST_NOT_SAVE = "The School not exist, cant be save order";
   String SCHOOL_SAVED = "The School saved";
   String SCHOOL_UPDATE = "The School update";
   String SCHOOL_DELETED = "The School as deleted";
   String SCHOOLS_LISTED = "The all Schools as listed";
   String SCHOOL_SELECTED = "The School as listed";
   String SCHOOLS_NOT_EXIST_RECORDS = "Not exist records of schools";
   String SCHOOL_NOT_EXIST_NOT_DELETE = "The School no exist, cant be delete";
   String SCHOOL_NOT_EXIST_NOT_UPDATE = "The School no exist, cant be update";
   String SCHOOL_EXIST_NAME_NOT_UPDATE = "The School name already exist, cant be update";
   String USER_SAVED = "The user as saved";
   String USER_UPDATED = "The user as updated";
   String USER_DELETED = "The user as deleted";
   String USERS_LISTED = "The all users as listed";
   String USER_LISTED = "The user as listed";
   String USER_BAT_EMAIL = "You entered an incorrect email address, cant be save";
   String USER_BAT_PASSWORD = "You entered an incorrect password" +
            "the password must contain: \n" +
            "1. start of the string \n" +
            "2. a digit must occur at least once \n" +
            "3. a lower case letter must occur at least once \n" +
            "4. an upper case letter must occur at least once \n" +
            "5. 4-8 character password, both inclusive \n" +
            "cant be save";

   String USER_BAT_ROL = "Bat rol, cant be save";
   String USERS_EXIST_NOT_SAVE = "The user already exist, cant be save";
   String USERS_EXIST_NOT_DELETE = "The user no exist, cant be delete";
   String USERS_NOT_EXIST_NOT_UPDATE = "The user no exist, cant be update";
   String USERS_EMAIL_ALREADY_EXIST_NOT_UPDATE = "The email alredy exist in the other user, cant be update";
   String USERS_EMAIL_ALREADY_EXIST_NOT_SAVE = "The email alredy exist in the other user, cant be save";
   String UPDATE_FAIL = ", cant by update";
   String ORDER_SAVED = "The order as saved";
   String ORDER_NOT_EXIST_RECORD = "Not exist records of orders";
   String ORDER_NUNBER_NOT_EXIST_RECORD = "Not exist records of order number";
   String ORDER_TMP_DIRECTORY = "Error to make a directory temporal to Excel";
   String ORDER_NUNBER_NOT_EXIST_RECORD_DATE = "Not exist records of order number in this date";
   String ORDER_UPDATED = "The order as updated";
   String ORDER_NUMBER_LISTED = "The records for order number as listed";
   String ORDER_LISTED = "The order as listed";

   String ORDER_EXCEL_GENERATE = "The order excel as gemerated";
   String ORDERS_DELETED = "The order as deleted";

   String ORDERS_LISTED = "The all orders as listed";

   String USERS_NOT_EXIST_RECORDS = "Not exist records of users";

   String USERS_NOT_EXIST_RECORDS_EMAIL = "Not exist records of users by this email";

   String USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE = "The user create no exist, cant be save";

   String USER_MOD_ORDER_NOT_EXIST_NOT_SAVE = "The user modification no exist, cant be save";

   String DATA_USER_CREATE_NOT_VALID_NOT_SAVE = "The user create no exist, cant be save";

   String DATA_USER_CREATE_NOT_VALID_NOT_UPDATE = "The user create no exist, cant be update";

   String ORDER_ALREADY_EXIST_NOT_SAVE = "The order alredy exist, cant be save";

   String ORDER_NOT_EXIST_NOT_DELETE = "The order no exist, cant be delete";

   String ORDER_NOT_EXIST_NOT_UPDATE = "The order no exist, cant be update";

   String ORDER_DATE_INVALID_NOT_SAVE = "The date time is invalid, cant be update";

   String DIARY_TIME_START_INVALID_NOT_SAVE = "The start hour is invalid, cant be update";

   String DIARY_TIME_ENDING_INVALID_NOT_SAVE = "The ending hour is invalid, cant be update";

   String DIARY_WEEKDAY_INVALID_NOT_SAVE = "The weekday is invalid, cant be update";

   String DIARY_REPLACEMENT_INVALID_NOT_SAVE = "The replacement is invalid, cant be update";

   String DIARY_NOT_EXIST_NOT_DELETE = "The diary no exist, cant be delete";
   String DIARY_NOT_EXIST_NOT_UPDATE = "The diary no exist, cant be update";
   String DIARY_NOT_EXIST_RECORD = "Not exist records of diary";
   String DIARY_EXIST_NOT_SAVE = "The diary already exist, cant be save";
   String DIARY_SAVED = "The diary as saved";
   String DIARY_UPDATE = "The diary update";
   String DIARY_SELECTED = "The diary as listed";
   String DIARY_NOT_EXIST_RECORDS = "Not exist records of diarys";
   String DIARY_DELETED = "The diary as deleted";
   String DIARY_LISTED = "The all diary as listed";
   String USER_NOT_EXIST_RECORD = "Not exist records by user id";
   String DIARY_SELECTED_USER = "The diary as listed by user id";
   String SCHOOL_STATUS_NOT_EXIST_RECORD = "Not exist records by school/status ";
   String TOKEN_NOT_EXIST = "The token does not exist";
   String TOKEN_EMAIL_IS_NULL = "Error generating access token, username is null";

}
