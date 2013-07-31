package com.iaco.android.slideswitchdemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.iaco.android.slideswitchdemo.dto.BaseDto;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class BaseDao<T extends BaseDto> implements IBaseDao<T> {

	protected static SQLiteOpenHelper sqlHelper = null;
	protected static Context dbcontext;

	
	static void getDB() {
		if (dbcontext != null) {
			sqlHelper = new DBHelper(
					dbcontext,
					InitializeCatalog.class);

		}
	}
	
	protected void opendb()
	{
		getDB();
	}
	
	
	protected void closedb()
	{
		sqlHelper.close();
	}
	
	
	public BaseDao(Context context) {
		if (dbcontext == null) {
			dbcontext = context;
		}
	}

	
	public int update(T value) {
		
		if(value.isDirty())
		{
			
			try
			{
				opendb();
				SQLiteDatabase db = sqlHelper.getWritableDatabase();
				
				
				long i = db.update(
						getTableName(), 
						getUpdateValues(value),
						getIdQuery(),
						getIdValues(value));
				
				if(i>0)
				{
					Log.i(BaseDao.class.getName(), "Row updated successfully");
				}
			
				value.setDirty(false);
				
			}
			catch(Exception ex)
			{
				Log.w(BaseDao.class.getName(), "Error update: " + ex.getMessage());
			}
		
		}
		return 0;
	}

	public int delete(T value) {

		return 0;
	}

	public int get(T value) {
		return 0;
	}
	
	public List<T> getList() {
		
		opendb();
		List<T> list = new ArrayList<T>();
		  
		String tablename = getTableName();
		String[] columns = getColumns();
		
		try
		{
		
		SQLiteDatabase db = sqlHelper.getReadableDatabase();
		
		
		Cursor cursor = db.query(
			tablename,
		    columns, 
		    null, 
		    null, 
		    null, 
		    null, 
		    null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    			    	
		      T item = createFrom(cursor);
		      
		     
		      list.add(item);
		    
		      cursor.moveToNext();
		    }		   
		   cursor.close();
		}
		catch(Exception ex)
		{
			Log.w(BaseDao.class.getName(), "Error get all: " + ex.getMessage());
			
		}
		return list;
	}

}
