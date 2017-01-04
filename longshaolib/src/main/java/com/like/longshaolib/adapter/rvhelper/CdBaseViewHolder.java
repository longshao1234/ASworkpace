package com.like.longshaolib.adapter.rvhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.LinkedHashSet;

/**
 * Created by longshao on 2016/10/24.
 */

public class CdBaseViewHolder extends RecyclerView.ViewHolder{

    private Context _context;
    private View _view;
    private final SparseArray<View> _views;
    private final LinkedHashSet<Integer> _childClickViewIds;
    private final LinkedHashSet<Integer> _childLongClickViewIds;

    public CdBaseViewHolder(Context context, View itemView) {
        super(itemView);
        _context=context;
        _view=itemView;
        _views=new SparseArray<View>();
        _childClickViewIds=new LinkedHashSet<Integer>();
        _childLongClickViewIds=new LinkedHashSet<Integer>();
    }

    public  <T extends View> T cdFindViewById(int viewId){
        View view=_views.get(viewId);
        if (view==null){
            view=_view.findViewById(viewId);
            _views.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 设置文本框内容
     * @param viewId
     * @param value
     * @return
     */
    public CdBaseViewHolder setText(int viewId,CharSequence value){
        TextView view=cdFindViewById(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 设置图片 待完善
     * @param viewId
     * @param imageUrl
     * @return
     */
    public CdBaseViewHolder setImageUrl(int viewId,String imageUrl){
        ImageView view=cdFindViewById(viewId);
        Glide.with(_context)
                .load(imageUrl)
                .into(view);
        return this;
    }

    /**
     * 设置图片 待完善
     * @param viewId
     * @param imageUrl
     * @param width 图片的宽度 像素
     * @param height 图片的高度 像素
     * @return
     */
    public CdBaseViewHolder setImageUrl(int viewId,String imageUrl,int width,int height){
        ImageView view=cdFindViewById(viewId);
        Glide.with(_context)
                .load(imageUrl)
                .override(width,height)
                .into(view);
        return this;
    }

    /**
     * Sets the childView click listener of the view
     *
     * @param viewId   The view id.
     * @return The BaseViewHolder for chaining.
     */
    public CdBaseViewHolder addItemClickListener(int viewId) {
        _childClickViewIds.add(viewId);
        return this;
    }

    /**
     * add long click view id
     * @param viewId
     * @return
     */
    public CdBaseViewHolder addItemLongClickListener(int viewId){
        _childLongClickViewIds.add(viewId);
        return this;
    }

    /**
     * 设置是否隐藏
     * @param viewId  The view id.
     * @param visible boolean:isshow
     * @return
     */
    public CdBaseViewHolder setVisible(int viewId, boolean visible) {
        View view = cdFindViewById(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     *设置 选中与否（CompoundButton与CheckedTextView）
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseViewHolder for chaining.
     */
    public CdBaseViewHolder setChecked(int viewId, boolean checked) {
        View view = cdFindViewById(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    /**
     * 设置 tag
     * @param viewId view Id
     * @param key the key of tag
     * @param tag the tag value
     * @return
     */
    public CdBaseViewHolder setTag(int viewId,int key,Object tag){
        View view=cdFindViewById(viewId);
        view.setTag(key,tag);
        return this;
    }

    /**
     * 设置 tag
     * @param viewId view Id
     * @param tag the tag value
     * @return
     */
    public CdBaseViewHolder setTag(int viewId,Object tag){
        View view=cdFindViewById(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置view是否为超链接
     * @param viewId
     * @return
     */
    public CdBaseViewHolder linkify(int viewId) {
        TextView view = cdFindViewById(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public View get_view() {
        return _view;
    }

    public LinkedHashSet<Integer> getChildClickViewIds() {
        return _childClickViewIds;
    }

    public LinkedHashSet<Integer> getChildLongClickViewIds() {
        return _childLongClickViewIds;
    }
}
