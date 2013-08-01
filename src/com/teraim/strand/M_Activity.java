package com.teraim.strand;

import com.teraim.nils.CommonVars;
import com.teraim.nils.DataTypes.Provyta;

import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class M_Activity extends Activity {

	
	Provyta py = Strand.getCurrentProvyta(this);
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		return MenuChoice(item);
	}

	MenuItem mnu1 = null,mnu2 = null,mnu3=null,mnu4=null;
	private void CreateMenu(Menu menu)
	{
		mnu1 = menu.add(0, 0, 0, "");
		mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		mnu2 = menu.add(0, 1, 1, "");
		mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		mnu3 = menu.add(0, 2, 2, "");
		mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		mnu4 = menu.add(0, 3, 3,"");
		mnu4.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		MenuItem mnu5 = menu.add(0, 4, 4, "Item 5");
		mnu5.setIcon(android.R.drawable.ic_menu_preferences);
		mnu5.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		refreshStatusRow();
	}

	protected void refreshStatusRow() {
		Log.d("NILS","Refreshing status row");
		if (mnu1!=null) {
			String p="?";
			Provyta py = CommonVars.cv().getProvyta();
			if (py!=null)
				p=py.getId();
			mnu1.setTitle("Ruta/Provyta: "+CommonVars.cv().getRuta().getId()+"/"+p);
		}
		if (mnu2!=null)
			mnu2.setTitle("Synkning: "+CommonVars.cv().getSyncStatusS());
		if (mnu3!=null)
			mnu3.setTitle("Användare: "+CommonVars.cv().getUserName());
		if (mnu4!=null)
			mnu4.setTitle("Färg: "+CommonVars.cv().getDeviceColor());
		
	}
}
