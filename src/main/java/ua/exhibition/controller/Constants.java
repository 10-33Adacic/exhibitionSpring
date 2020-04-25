package ua.exhibition.controller;

public interface Constants {

    String EXHIBITION = "exhibition";

    String IS_CURRENT_USER = "isCurrentUser";

    String USER_CHANNEL = "userChannel";

    String SUBSCRIPTIONS_COUNT = "subscriptionsCount";

    String SUBSCRIBERS_COUNT = "subscribersCount";

    String IS_SUBSCRIBER = "isSubscriber";

    String URL = "url";

    String PAGE = "page";

    String DELETE_FACTOR = "deleteFactor";

    String PAGE_USER_EXHIBITIONS = "userExhibitions";

    String URL_USER_EXHIBITIONS = "/user-exhibitions/";

    String URL_MAIN = "/main";

    String PARAM_ID = "id";

    String PARAM_EXHB_ID = "exhibitionId";

    String REDIRECT = "redirect:";

    String SHOWROOM = "showroom";

    String PAGE_HOME = "home";

    String PAGE_MAIN_PAGE = "mainPage";

    String USERNAME = "username";

    String PAGE_PROFILE = "profile";

    String URL_USER_PROFILE = "/user/profile";

    String PAGE_REGISTRATION = "registration";

    String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";

    String PASSWORD_CONFIRM_ERROR = "passwordConfirmError";

    String PASSWORD_ERROR = "passwordError";

    String CAPTCHA_ERROR = "captchaError";

    String USERNAME_ERROR = "usernameError";

    String URL_LOGIN = "/login";

    String PASSWORD_ERROR_MESSAGE = "Passwords are different!";

    String PASSWORD_CONFIRM_ERROR_MESSAGE = "Password confirmation cannot be empty";

    String CAPTCHA_ERROR_MESSAGE = "Fill captcha";

    String USERNAME_ERROR_MESSAGE = "User is exists!";

    String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    String BUY_ERROR = "buyError";

    String BUY_ERROR_MESSAGE = "You have already bought this ticket";

    String PAGE_SALES = "sales";

    String PAGE_SALES_USER = "salesUser";

    String BALANCE = "balance";

    String TICKETS = "tickets";

    String USER = "user";

    String URL_SALES = "/sales/";

    String PARAM_MONEY = "money";

    String TYPE = "type";

    String SUBSCRIPTIONS = "subscriptions";

    String PAGE_SUBSCRIPTIONS = "subscriptions";

    String USERS = "users";

    String ROLES = "roles";

    String URL_USER = "/user";

    String PAGE_USER_LIST = "userList";

    String PAGE_USER_EDIT = "userEdit";

    String PARAM_USER_ID = "userId";

    String USER_EXHIBITIONS_MAPPING = "/user-exhibitions/{user}";

    String EDIT_EXHIBITION_MAPPING = "/user-exhibitions/{user}/{exhibition}";

    String UPDATE_EXHIBITION_MAPPING = "/user-exhibitions/{user}";

    String DELETE_EXHIBITION_MAPPING = "/user-exhibitions/delete";

    String MAIN_MAPPING = "/";

    String MAIN_FORM_MAPPING = "/main";

    String USER_MAPPING = "/user";

    String PROFILE_MAPPING = "/profile";

    String REGISTRATION_MAPPING = "registration";

    String RECAPTCHA_SECRET_VALUE = "${recaptcha.secret}" ;

    String BUY_TICKET_MAPPING = "/sales/{user}";

    String BOUGHT_TICKETS_MAPPING = "bought-tickets/{user}" ;

    String SUBSCRIBE_MAPPING = "subscribe/{user}" ;

    String UNSUBSCRIBE_MAPPING = "unsubscribe/{user}" ;

    String USER_LIST_MAPPING =  "{type}/{user}/list";

    String USER_EDIT_MAPPING = "{user}";

    String BUY_ERROR_MONEY = "Not enough money!";
}
