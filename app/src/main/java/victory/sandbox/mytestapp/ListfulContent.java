package victory.sandbox.mytestapp;

import android.net.Uri;

/**
 * listful content
 */
public class ListfulContent {
    private String name, info;
    private Uri imgUri;

    ListfulContent (String name, String info, Uri imgUri) {
        this.name = name;
        this.info = info;
        this.imgUri = imgUri;
    }

    public String getName () {
        return name;
    }

    public String getInfo () {
        return info;
    }

    public Uri getImgUri() { return imgUri; }
}
