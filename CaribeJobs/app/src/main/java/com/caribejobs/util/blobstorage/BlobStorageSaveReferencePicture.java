package com.caribejobs.util.blobstorage;

import android.app.Activity;
import android.graphics.Bitmap;

import com.caribejobs.R;
import com.caribejobs.model.Reference;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.UserProfession;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class BlobStorageSaveReferencePicture extends BlobStorageTask {
    private String username;
    private Bitmap bitmap;
    private UserProfession userProfession;
    private int position;
    private String urlres;

    public BlobStorageSaveReferencePicture(Activity context) {
        super(context);
    }
    public BlobStorageSaveReferencePicture(Activity context, String username, Bitmap bitmap, UserProfession userProfession, int position) {
        super(context);
        this.username = username;
        this.bitmap = bitmap;
        this.userProfession = userProfession;
        this.position = position;
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

            CloudBlockBlob blob = userscontainer.getBlockBlobReference(userProfession.getProfession().getProfessionid() + "_" + position + ".jpeg");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] bitmapdata = outputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(bitmapdata);
            blob.upload(is, bitmapdata.length);
            urlres = blob.getStorageUri().getPrimaryUri().toString();

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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ((BlobStorageTask.BlobStorageTaskListener) context).afterBlobExecution(urlres);
    }
}
