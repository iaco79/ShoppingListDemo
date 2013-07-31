/**
 * Item array adapter
 */
package com.iaco.android.slideswitchdemo;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.iaco.android.slideswitchdemo.dto.Item;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private List<Item> items;
    private OnItemViewChangeListener itemViewChangeListener;
    
    public interface OnItemViewChangeListener
    {
  	  void onChange(Item item);
    }
    
    public void setOnItemViewChangeListener(OnItemViewChangeListener itemChangeListener)
    {
    	itemViewChangeListener = itemChangeListener;
    }
    public ItemListAdapter(
    		Context context, 
    		int textViewResourceId, 
    		List<Item> items) {
            super(context, textViewResourceId, 
            items);
            this.items = items;
            
    }
    
    public Drawable  getImageFromAssets(String imagename) 
    {
        try {

            InputStream ims = getContext().getAssets().open("images/" + imagename);
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        }
        catch(IOException ex) {
            return null;
        }
    }
        
    @Override
    public View getView(int position, 
    					View convertView, 
    					ViewGroup parent
    		) {
    	
            View v = convertView;
            if (v == null) {            	
                LayoutInflater vi = 
                		(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                
                v = vi.inflate(R.layout.row, null);
            }
            
            Item o = items.get(position);
            
            if (o != null) {
            		Drawable d= getImageFromAssets(o.getPictureName());
            	    SlideToggle cr = (SlideToggle) v.findViewById(R.id.rowid);
                    cr.mTextTop.setText(o.getName());
                    cr.mTextBottom.setText(o.getDescription());
                    cr.mImageLeft.setImageDrawable(d);
                    cr.mImageRight.setImageDrawable(d);
                    cr.resetToState(o.getStatus());
                    cr.setStateListener( position, new SlideToggle.StateChangeListener() 
           				{	
							@Override
							public void OnChange(int lposition, int state) 
							{	
								Item pd = items.get(lposition);
								pd.setStatus(state);
								
								if(itemViewChangeListener!=null)
									itemViewChangeListener.onChange(pd);
								
							};
           
           				}
                    );
            }
            return v;
    }
}