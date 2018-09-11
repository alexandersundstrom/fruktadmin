package com.evry.server.servlet.common;

public enum ContentType {
    XML("application/xml"),
    PDF("application/pdf"),
    XSD("application/xsd");

    private String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
