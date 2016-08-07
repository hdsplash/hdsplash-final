package com.wallpaperteam.wallpaperhd.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wallpaperteam.wallpaperhd.CustomApplication;
import com.wallpaperteam.wallpaperhd.R;
import com.wallpaperteam.wallpaperhd.fragments.ImagesFragment;
import com.wallpaperteam.wallpaperhd.models.MyImage;
import com.wallpaperteam.wallpaperhd.network.MyApi;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements CountCategory{


    //private Toolbar toolbar;
    public enum Category {
        ALL(1000),
        FEATURED(1001),
        LOVED(1002),
        BUILDINGS(1),
        FOOD(2),
        NATURE(4),
        PEOPLE(8),
        TECHNOLOGY(16),
        OBJECTS(32);

        public final int id;

        private Category(int id) {
            this.id = id;
        }
    }

    public Drawer result;
    public static MainActivity instance;
    private OnFilterChangedListener onFilterChangedListener;

    public void setOnFilterChangedListener(OnFilterChangedListener onFilterChangedListener) {
        this.onFilterChangedListener = onFilterChangedListener;
    }

    public static MainActivity getInstance(){
        if(instance == null)
            instance = new MainActivity();
        return  instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ImagesFragment imagesFragment = (ImagesFragment) getFragmentManager().findFragmentById(R.id.ly_last_images_fragment);
        //imagesFragment.setToolbar(toolbar);

        setSupportActionBar(toolbar);

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.category_all).withIdentifier(Category.ALL.id).withIcon(R.drawable.all),
                        new PrimaryDrawerItem().withName(R.string.category_loved).withIdentifier(Category.LOVED.id).withIcon(R.drawable.love),
                        new SectionDrawerItem().withName(R.string.category_section_categories),
                        new PrimaryDrawerItem().withName(R.string.category_buildings).withIdentifier(Category.BUILDINGS.id).withIcon(R.drawable.building),
                        new PrimaryDrawerItem().withName(R.string.category_food).withIdentifier(Category.FOOD.id).withIcon(R.drawable.food),
                        new PrimaryDrawerItem().withName(R.string.category_nature).withIdentifier(Category.NATURE.id).withIcon(R.drawable.nature),
                        new PrimaryDrawerItem().withName(R.string.category_objects).withIdentifier(Category.OBJECTS.id).withIcon(R.drawable.object),
                        new PrimaryDrawerItem().withName(R.string.category_people).withIdentifier(Category.PEOPLE.id).withIcon(R.drawable.people),
                        new PrimaryDrawerItem().withName(R.string.category_technology).withIdentifier(Category.TECHNOLOGY.id).withIcon(R.drawable.tech)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem instanceof Nameable) {
                                toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                            }
                            if (onFilterChangedListener != null) {
                                onFilterChangedListener.onFilterChanged(drawerItem.getIdentifier());
                            }
                        }

                        return false;
                    }
                })
                .build();

      /*  new PrimaryDrawerItem().withName(R.string.category_buildings).withIdentifier(Category.BUILDINGS.id).withIcon(MaterialDesignIconic.Icon.gmi_select_all),
                new PrimaryDrawerItem().withName(R.string.category_food).withIdentifier(Category.FOOD.id).withIcon(MaterialDesignIconic.Icon.gmi_check_all),
                new PrimaryDrawerItem().withName(R.string.category_nature).withIdentifier(Category.NATURE.id).withIcon(MaterialDesignIconic.Icon.gmi_format_align_left),
                new PrimaryDrawerItem().withName(R.string.category_objects).withIdentifier(Category.OBJECTS.id).withIcon(MaterialDesignIconic.Icon.gmi_coffee),
                new PrimaryDrawerItem().withName(R.string.category_people).withIdentifier(Category.PEOPLE.id).withIcon(MaterialDesignIconic.Icon.gmi_font),
                new PrimaryDrawerItem().withName(R.string.category_technology).withIdentifier(Category.TECHNOLOGY.id).withIcon(MaterialDesignIconic.Icon.gmi_long_arrow_down)*/

        //disable scrollbar :D it's ugly
        result.getRecyclerView().setVerticalScrollBarEnabled(false);
    }

    /**
     * @param images
     */
    public void setCategoryCount(ArrayList<MyImage> images) {
        if (result.getDrawerItems() != null && result.getDrawerItems().size() == 9 && images != null ){//&& images.getData() != null) {
            result.updateBadge(Category.ALL.id, new StringHolder(images.size() + ""));
            //result.updateBadge(Category.FEATURED.id, new StringHolder(UnsplashApi.countFeatured(images.getData()) + ""));
            result.updateBadge(Category.LOVED.id, new StringHolder(MyApi.countLoved(images, 1 )+ ""));
            result.updateBadge(Category.BUILDINGS.id, new StringHolder(MyApi.countCategory(images, Category.BUILDINGS.id) + ""));
            result.updateBadge(Category.FOOD.id, new StringHolder(MyApi.countCategory(images, Category.FOOD.id) + ""));
            result.updateBadge(Category.NATURE.id, new StringHolder(MyApi.countCategory(images, Category.NATURE.id) + ""));
            result.updateBadge(Category.OBJECTS.id, new StringHolder(MyApi.countCategory(images, Category.OBJECTS.id) + ""));
            result.updateBadge(Category.PEOPLE.id, new StringHolder(MyApi.countCategory(images, Category.PEOPLE.id) + ""));
            result.updateBadge(Category.TECHNOLOGY.id, new StringHolder(MyApi.countCategory(images, Category.TECHNOLOGY.id) + ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // menu.findItem(R.id.action_open_source).setIcon(new IconicsDrawable(this, FontAwesome.Icon.faw_space_shuttle).color(R.color.icon_color).actionBar());
        //menu.findItem(R.id.action_shuffle).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_shuffle).paddingDp(1).color(Color.WHITE).actionBar());
        int layoutType= CustomApplication.sharedPreferences.getInt("LayoutType", 1);
        if (layoutType==0){
            menu.findItem(R.id.layout).setIcon(R.drawable.list_type);
        }
        else
        {
            menu.findItem(R.id.layout).setIcon(R.drawable.grid_type);
        }
        menu.findItem(R.id.action_shuffle).setIcon(R.drawable.shuffle);
        menu.findItem(R.id.action_help).setIcon(R.drawable.info);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_help){
            Intent showHelp = new Intent(this, WallpaperIntro.class);
            startActivity(showHelp);
        }
        return false;
    }


    public interface OnFilterChangedListener {
        public void onFilterChanged(int filter);
    }

    @Override
    public void updateCategory(ArrayList<MyImage> images) {
        setCategoryCount(images);
    }


}
