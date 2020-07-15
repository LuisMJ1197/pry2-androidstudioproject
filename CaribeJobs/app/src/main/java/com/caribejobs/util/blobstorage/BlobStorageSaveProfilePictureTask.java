package com.caribejobs.util.blobstorage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.caribejobs.R;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
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

public class BlobStorageSaveProfilePictureTask extends BlobStorageTask {
    private String username;
    private Bitmap bitmap;

    public BlobStorageSaveProfilePictureTask(Activity context) {
        super(context);
    }

    public BlobStorageSaveProfilePictureTask(Activity context, String username, Bitmap bitmap) {
        super(context);
        this.username = username;
        this.bitmap = bitmap;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            account = CloudStorageAccount.parse(context.getString(R.string.connectionString));
            blobClient = account.createCloudBlobClient();
            System.out.println(username);
            userscontainer = blobClient.getContainerReference(username.toLowerCase());
            userscontainer.createIfNotExists();
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            userscontainer.uploadPermissions(containerPermissions);

            CloudBlockBlob blob = userscontainer.getBlockBlobReference("profilepicture.jpeg");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] bitmapdata = outputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(bitmapdata);
            blob.upload(is, bitmapdata.length);
            String imageString = blob.getStorageUri().getPrimaryUri().toString();
            ((BlobStorageTask.BlobStorageTaskListener) context).afterBlobExecution(imageString);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
