package com.evry.server.servlet.download.model;

public class FileInformationHolder {

    private byte[] fileAsBytes;
    private String filename;
    private String contentType;

    public FileInformationHolder(byte[] fileAsBytes, String filename, String contentType) {
        this.fileAsBytes = fileAsBytes;
        this.filename = filename;
        this.contentType = contentType;
    }

    public byte[] getFileAsBytes() {
        return fileAsBytes;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }
}
