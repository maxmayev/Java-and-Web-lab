package chat;

public enum ChatCommand {

    LOGIN("login:"),
    LOGOUT("logOut:"),
    MESSAGE("message:"),
    USER_LIST("userList:"),
    SHUTDOWN("shutdown"); //shutdown сейчас не используется

    ChatCommand(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return name;
    }

}
