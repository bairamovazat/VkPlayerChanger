package models;

import services.Communication;

public interface SocketProcessor  extends Runnable{
    public void writeResponse(String s);
}
