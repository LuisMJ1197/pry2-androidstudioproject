package com.caribejobs.view.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;

import com.caribejobs.R;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.UserProfession;
import com.caribejobs.view.FullImageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridViewImageAdapter extends BaseAdapter {
    private Activity _activity;
    private ArrayList<ReferencePicture> referencePictures = new ArrayList<>();
    private MutableLiveData<ArrayList<ReferencePicture>> arrayListMutableLiveData = new MutableLiveData<>();
    private int imageWidth;
    private UserProfession _userProfession;

    public GridViewImageAdapter(Activity activity, ArrayList<ReferencePicture> filePaths, UserProfession userProfession,
                                int imageWidth) {
        this._activity = activity;
        this.referencePictures = filePaths;
        this.imageWidth = imageWidth;
        this._userProfession = userProfession;
        this.arrayListMutableLiveData.setValue(referencePictures);

    }

    public ArrayList<ReferencePicture> getReferencePictures() {
        return referencePictures;
    }

    public void setReferencePictures(ArrayList<ReferencePicture> referencePictures) {
        this.referencePictures = referencePictures;
        this.arrayListMutableLiveData.setValue(referencePictures);
    }

    @Override
    public int getCount() {
        return this.referencePictures.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static void putImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }

    public MutableLiveData<ArrayList<ReferencePicture>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(_activity);
        } else {
            imageView = (ImageView) convertView;
        }
        putImage(imageView, referencePictures.get(position).getImageURL());

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,
                imageWidth));

        // image view click listener
        imageView.setOnClickListener(new OnImageClickListener(position, _userProfession));

        return imageView;
    }

    class OnImageClickListener implements View.OnClickListener {
        int _postion;
        UserProfession userProfession;
        // constructor
        public OnImageClickListener(int position) {
            this._postion = position;
        }

        public OnImageClickListener(int position, UserProfession userProfession) {
            this._postion = position;
            this.userProfession = userProfession;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(_activity, FullImageActivity.class);
            i.putExtra("userprofession", userProfession);
            i.putExtra("position", _postion);
            _activity.startActivity(i);
        }
    }
}
