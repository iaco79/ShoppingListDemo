/**
 * List Fragment that contains the item list
 */
package com.iaco.android.slideswitchdemo;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.os.Bundle;

import com.iaco.android.slideswitchdemo.dao.ItemDao;
import com.iaco.android.slideswitchdemo.dto.Item;


public class ItemListFragment extends ListFragment {

	
	private ItemListAdapter m_adapter;
	private ItemDao m_dao = null;
	
	protected int getLayout() {
		return R.layout.listfragment;
	}
	
	private ListView mlv;
	
	
	public void setListAdapter() {
		
		//Initialize dao to retrieve Items from DB
		m_dao = new ItemDao(this.getActivity());
		
		this.m_adapter = new ItemListAdapter(
				this.getActivity(),
				R.layout.row,
				m_dao.getList());
		
		this.m_adapter.setOnItemViewChangeListener(
				new ItemListAdapter.OnItemViewChangeListener() {
					
					/**
					 * Update the item status in the DB table
					 */
					@Override
					public void onChange(Item item) {
						m_dao.update(item);
						
					}
				});
		
		setListAdapter(this.m_adapter);
		
	}


	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mlv = (ListView) inflater.inflate(getLayout(), container,
				false);

		return mlv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter();
	}	    
    
}
