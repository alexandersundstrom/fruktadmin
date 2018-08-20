package com.evry.client;

import java.io.Serializable;

public class ClientReport implements Serializable, Comparable<ClientReport> {

    private static final long serialVersionUID = 12357889687575343L;
    private long id;
    private String location;
    private String created;
    private boolean read;


    public ClientReport() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


    @Override
    public int compareTo(ClientReport o) {
        return this.location.compareTo(o.getLocation());
    }
}
