package chat;

public enum ChatCommand {

    LOGIN("login:"),
    LOGOUT("logOut:"),
    MESSAGE("message:"),
    USER_LIST("userList:"),
    SHUTDOWN("shutdown"); 

    ChatCommand(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return name;
    }

}
