package com.example.imagepaper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImagePaperAdapter extends PagerAdapter {

    Context context;
    int[] images;
    LayoutInflater inflater;

    public ImagePaperAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);

        ImageView image;

        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
        // we use the LayoutInflater instance to declare and initialize a new View instance from our
        // paper_item.xml file
        View itemView = inflater.inflate(R.layout.paper_item, container, false);

        //get reference to imageView in pager_item layout
        image = itemView.findViewById(R.id.imageView);

        //set an image to ImageView
        image.setImageResource(images[position]);

        //add paper_item layout as the current page to the ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;

    }

    // destroyItem method cam be called when it needs to remove an appropriate item based on
    // the value of the position of param
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        // remove pager_item layout from ViewPager
        container.removeView((RelativeLayout) object);
    }
}
