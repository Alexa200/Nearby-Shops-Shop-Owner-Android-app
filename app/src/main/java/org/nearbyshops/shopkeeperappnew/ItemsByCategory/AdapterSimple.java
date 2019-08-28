package org.nearbyshops.shopkeeperappnew.ItemsByCategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.nearbyshops.shopkeeperappnew.ItemsByCategory.Model.ItemCategoriesList;
import org.nearbyshops.shopkeeperappnew.ItemsByCategory.ViewHolders.ViewHolderItemCategory;
import org.nearbyshops.shopkeeperappnew.ItemsByCategory.ViewHolders.ViewHolderItemSimple;
import org.nearbyshops.shopkeeperappnew.Model.Item;
import org.nearbyshops.shopkeeperappnew.Model.ItemCategory;
import org.nearbyshops.shopkeeperappnew.Model.ShopItem;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.LoadingViewHolder;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.Models.EmptyScreenData;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.Models.HeaderTitle;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.ViewHolderEmptyScreen;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.ViewHolderHeader;
import org.nearbyshops.shopkeeperappnew.ViewHolderCommon.ViewHolderHorizontalList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sumeet on 19/12/15.
 */


public class AdapterSimple extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Map<Integer, ShopItem> shopItemMap = new HashMap<>();
    Map<Integer, Item> selectedItems = new HashMap<>();

    private List<Object> dataset;
    private Context context;
//    private NotificationsFromAdapter notificationReceiver;

    public static final int VIEW_TYPE_ITEM_CATEGORY = 1;
    public static final int VIEW_TYPE_ITEM_CATEGORY_LIST = 2;
    public static final int VIEW_TYPE_ITEM = 3;


    public static final int VIEW_TYPE_EMPTY_SCREEN = 4;
    public static final int VIEW_TYPE_HEADER = 5;
    private final static int VIEW_TYPE_PROGRESS_BAR = 6;


    private boolean loadMore;






    private Fragment fragment;



    public AdapterSimple(List<Object> dataset, Context context, Fragment fragment) {


        this.dataset = dataset;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;


        if(viewType == VIEW_TYPE_ITEM_CATEGORY)
        {
            return ViewHolderItemCategory.create(parent,context,fragment,this,shopItemMap,selectedItems);
        }
        else if(viewType == VIEW_TYPE_ITEM_CATEGORY_LIST)
        {
            return ViewHolderHorizontalList.create(parent,context,fragment);
        }
        else if(viewType == VIEW_TYPE_ITEM)
        {
            return ViewHolderItemSimple.create(parent,context,fragment,this,shopItemMap,selectedItems);
        }
        else if (viewType == VIEW_TYPE_HEADER) {

            return ViewHolderHeader.create(parent,context);
        }
        else if (viewType == VIEW_TYPE_PROGRESS_BAR) {

            return LoadingViewHolder.create(parent,context);
        }
        else if(viewType==VIEW_TYPE_EMPTY_SCREEN)
        {
            return ViewHolderEmptyScreen.create(parent,context);
        }


        return null;
    }






    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof ViewHolderItemCategory)
        {
            ((ViewHolderItemCategory) holder).bindItemCategory((ItemCategory) dataset.get(position));
        }
        else if(holder instanceof ViewHolderItemSimple)
        {

            ((ViewHolderItemSimple) holder).bindItem((Item) dataset.get(position));

        }
        else if(holder instanceof ViewHolderHorizontalList)
        {

            List<ItemCategory> list = ((ItemCategoriesList)dataset.get(position)).getItemCategories();

            ((ViewHolderHorizontalList) holder).setItem(new AdapterItemCatHorizontalList(list,context,fragment));

        }
        else if (holder instanceof ViewHolderHeader) {

            if (dataset.get(position) instanceof HeaderTitle) {

                ((ViewHolderHeader) holder).setItem((HeaderTitle) dataset.get(position));
            }

        }
        else if (holder instanceof LoadingViewHolder) {

            ((LoadingViewHolder) holder).setLoading(loadMore);

        }
        else if(holder instanceof ViewHolderEmptyScreen)
        {
            ((ViewHolderEmptyScreen) holder).setItem((EmptyScreenData) dataset.get(position));
        }
    }







    @Override
    public int getItemViewType(int position) {

        super.getItemViewType(position);


        if(position == dataset.size())
        {
            return VIEW_TYPE_PROGRESS_BAR;
        }
        else if(dataset.get(position) instanceof ItemCategory)
        {
            return VIEW_TYPE_ITEM_CATEGORY;
        }
        else if(dataset.get(position) instanceof ItemCategoriesList)
        {
            return VIEW_TYPE_ITEM_CATEGORY_LIST;
        }
        else if (dataset.get(position) instanceof Item)
        {
            return VIEW_TYPE_ITEM;
        }
        else if(dataset.get(position) instanceof HeaderTitle)
        {
            return VIEW_TYPE_HEADER;
        }
        else if(dataset.get(position) instanceof EmptyScreenData)
        {
            return VIEW_TYPE_EMPTY_SCREEN;
        }


        return -1;
    }

    @Override
    public int getItemCount() {

        return (dataset.size()+1);
    }



    public void setLoadMore(boolean loadMore)
    {
        this.loadMore = loadMore;
    }




    public Map<Integer, Item> getSelectedItems() {
        return selectedItems;
    }

}