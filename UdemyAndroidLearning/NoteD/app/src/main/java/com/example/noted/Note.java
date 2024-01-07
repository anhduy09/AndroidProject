package com.example.noted;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private static final String JSON_TITLE = "title";
    private static final String JSON_CATEGORY = "category";
    private static final String JSON_DESCRIPTION = "description";

    private String mTitle;
    private String mCategory;
    private String mDescription;



    public Note() {

    }

    //constructor used when new is called with a JSONObject
    public Note(JSONObject jo) throws JSONException {
        mTitle = jo.getString(JSON_TITLE);
        mCategory = jo.getString(JSON_CATEGORY);
        mDescription = jo.getString(JSON_DESCRIPTION);
    }

    public Note(String mTitle, String mCategory, String mDescription) {
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.mDescription = mDescription;

    }

    public JSONObject convert2JSON() throws JSONException {

        JSONObject  jo = new JSONObject();
        jo.put(JSON_TITLE, mTitle);
        jo.put(JSON_CATEGORY, mCategory);
        jo.put(JSON_DESCRIPTION, mDescription);

        return jo;
    }


    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmCategory() {
        return mCategory;
    }
}
