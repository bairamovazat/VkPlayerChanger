package ru.azat.telegramBot.models;

public interface SocketProcessor  extends Runnable{
    public void writeResponse(String s);
}
