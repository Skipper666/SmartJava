package battleShip.enums;

public enum Notifications {

    //lose notification
    RIVAL_LEAVE("Противник покинул игру. Дальнейшая игра невозможна."),
    GAME__OVER_LOSE("Игра закончена. Вы проиграли."),
    SERVER_ERROR("Сервер недоступен."),
    GAME_ERROR("Непредвиденная ошибка. Дальнейшая игра невозможна."),

    //win notification
    GAME__OVER_WIN("Игра закончена. Поздравляем, вы победили!"),

    //temp notification
    INIT("Разместите корабли."),
    CONNECT_TO_SERVER("Подключаемся к серверу."),
    WAITING_FOR_RIVAL("Ожидаем противника."),
    GAME_STARTED_MOVE_ON("Игра началась, ваш ход."),
    GAME_STARTED_MOVE_OFF("Игра началась, противник ходит."),
    CWS("Если вам понравилась игра, пожалуйста, оставьте отзыв о ней в интернет-магазине Google Chrome (это займет " +
            "не более минуты)."),

    //steps notification
    MOVE_ON("Ваш ход."),
    MOVE_OFF("Противник ходит, ждите.");

    private String notification;

    Notifications(String notification) {
        this.notification = notification;
    }

    public String notification() {
        return notification;
    }
}
