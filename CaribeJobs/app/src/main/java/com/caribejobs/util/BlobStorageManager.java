package com.caribejobs.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

import com.caribejobs.R;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class BlobStorageManager extends AsyncTask<String, Void, Void> {
    private Activity context;
    private String connection;
    private CloudStorageAccount account;
    private CloudBlobClient blobClient;
    private static final String profilePicturesContainer = "profilepicture";
    private static BlobStorageManager instance;
    private boolean succesfulSetted = false;
    private CloudBlobContainer profilePictureContainer;

    private BlobStorageManager(Activity context) throws InvalidKeyException, StorageException, URISyntaxException {
        this.context = context;
        init();
    }

    public static BlobStorageManager getInstance(Activity context) throws InvalidKeyException, StorageException, URISyntaxException {
        if (instance == null) {
            instance = new BlobStorageManager(context);
        }
        return instance;
    }

    public void init() throws URISyntaxException, InvalidKeyException, StorageException {
        account = CloudStorageAccount.parse(context.getString(R.string.connectionString));
        blobClient = account.createCloudBlobClient();
        profilePictureContainer = blobClient.getContainerReference("images");
        profilePictureContainer.createIfNotExists();
        BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
        containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
        profilePictureContainer.uploadPermissions(containerPermissions);
        succesfulSetted = true;
    }

    public String saveProfilePicture(String username, Uri image) throws URISyntaxException, StorageException, IOException {
        String urlImage = username + "username.jpeg";
        CloudBlockBlob blob = profilePictureContainer.getBlockBlobReference(urlImage);
        File source = new File(image.toString());
        blob.upload(new FileInputStream(source), source.length());
        urlImage = blob.getStorageUri().getPrimaryUri().toString();
        return urlImage;
    }

    public String saveProfilePicture(String username, Bitmap image) throws URISyntaxException, StorageException, IOException {
        String urlImage = username + "username.jpeg";
        CloudBlockBlob blob = profilePictureContainer.getBlockBlobReference(urlImage);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] bitmapdata = outputStream.toByteArray();
        InputStream is = new ByteArrayInputStream(bitmapdata);
        blob.upload(is, bitmapdata.length);
        urlImage = blob.getStorageUri().getPrimaryUri().toString();
        return urlImage;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return null;
    }
}
