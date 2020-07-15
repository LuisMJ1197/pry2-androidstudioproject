package com.caribejobs.model;

import java.io.Serializable;

public class ReferencePicture implements Serializable {
    private int imageID;
    private String imageURL;

    public ReferencePicture(int imageID, String imageURL) {
        this.imageID = imageID;
        this.imageURL = imageURL;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
